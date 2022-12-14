package com.aiz.gulimall.product.web;

import com.aiz.gulimall.product.entity.CategoryEntity;
import com.aiz.gulimall.product.service.CategoryService;
import com.aiz.gulimall.product.vo.Catalog2Vo;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName IndexController
 * @Description 跳转首页
 * @Author Zhang Yao
 * @Date Create in 21:50 2020/8/13 0013
 * @Version 1.0
 */

@Controller
public class IndexController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedissonClient redisson;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /* 首页面跳转 */
    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {

        System.out.println("当前线程号：" + Thread.currentThread().getId());

        // TODO 1.查出所有1级分类
        List<CategoryEntity> categoryEntityList = categoryService.getLeve1Categorys();

        model.addAttribute("categorys", categoryEntityList);

        /*
         *  默认前缀spring:thymeleaf:prefix = classpath:/templates/
         *  默认后缀spring:thymeleaf:prefix = .html
         *  视图解析器进行拼串：classpath:/templates/ + 返回值 + .html
         */
        return "index";
    }


    //index/catelog.json
    @ResponseBody //由于是ajax请求返回json数据
    @GetMapping("index/catalog.json")
    public Map<String, List<Catalog2Vo>> getCatelogJson() {
        Map<String, List<Catalog2Vo>> catelogJson = categoryService.getCatalogJson();
        return catelogJson;
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        //1.获取一把锁，只要锁的名字一样，就是同一把锁。
        RLock lock = redisson.getLock("my-lock");
        //2.加锁
        //lock.lock();//阻塞式等待，默认加的锁时间都是30s。
        //(1).锁的自动续期，如果业务超长，运行期间自动给锁续上新的30s。不用担心业务时间长，锁自动过期被删除。【看门狗】
        //(2).加锁的业务只有运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s以后自动续期。

        lock.lock(10, TimeUnit.SECONDS);//10s自动解锁,自动解锁时间一定要大于业务的执行时间。
        //问题：lock.lock(10, TimeUnit.SECONDS);在锁时间到了之后不会自动续期。
        //(1).如果传递了锁的超时时间，就发送给redis执行脚本，进行占锁，默认超时时间就是我们传递的时间
        //(2).如果我们未指定锁的超时时间，就使用30*1000。【lockWatchdogTimeout看门狗默认时间】
        //  只要占锁成功，就会启动一个定时任务【重新给锁设置过期时间，新的过期时间就是看门狗的默认时间】,每隔10s都会自动再次续期满30s
        //  internalLockleaseTime【看门狗时间】/3，10s

        // 最佳实战
        //(1).lock.lock(30, TimeUnit.SECONDS);//省掉了整个续期操作，手动解锁
        try {
            System.out.println("加锁成功，执行业务......" + Thread.currentThread().getId());
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
        } finally {
            //3.解锁。假设解锁代码没有运行，redisson会不会出现死锁？不会！
            System.out.println("释放锁......" + Thread.currentThread().getId());
            lock.unlock();
        }
        return "Hello";
    }


    @ResponseBody
    @GetMapping("/write")
    public String writeValue() {
        RReadWriteLock lock = redisson.getReadWriteLock("rw-lock");
        RLock rLock = lock.writeLock();
        String s = "";
        try {
            //改数据加写锁，读数据加读锁。
            rLock.lock();
            System.out.println("写锁加锁成功..." + Thread.currentThread().getId());
            s = UUID.randomUUID().toString();
            Thread.sleep(30 * 1000);
            redisTemplate.opsForValue().set("writeValue", s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("写锁释放..." + Thread.currentThread().getId());
        }
        return s;
    }

    //保证一定能读到最新数据，修改期间，写锁是一个排他锁（互斥锁，独享），读锁使一个共享锁。
    //写锁没释放读就必须等待
    //读+读 相当于无锁，并发读只会在redis中记录好所有当前的读锁，他们都会同时加锁成功
    //写+读 等待写锁释放
    //写+写 阻塞方式
    //读+写 有读锁，写也需要等待
    //只要有写的存在，都必须等待。
    @ResponseBody
    @GetMapping("/read")
    public String readValue() {
        RReadWriteLock lock = redisson.getReadWriteLock("rw-lock");
        System.out.println("读锁加锁成功..." + Thread.currentThread().getId());
        String s = "";
        //加读锁
        RLock rLock = lock.readLock();
        rLock.lock();
        try {
            s = redisTemplate.opsForValue().get("writeValue");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("读锁释放..." + Thread.currentThread().getId());
        }
        return s;
    }


    /**
     * 车位停车
     * 3车位
     * 信号量也可以用作分布式限流
     */
    @ResponseBody
    @GetMapping("/park")
    public String park() throws InterruptedException {
        RSemaphore park = redisson.getSemaphore("park");
        //park.acquire();//获取一个信号，获取一个值，占一个车位
        boolean b = park.tryAcquire();
        if (b) {
            //执行业务
        } else {
            return "error";
        }
        return "ok=>" + b;
    }

    @ResponseBody
    @GetMapping("/go")
    public String go() throws InterruptedException {
        RSemaphore park = redisson.getSemaphore("park");
        park.release();//释放一个车位
        return "ok";
    }


    /**
     * 放假,锁门
     * 1班没人了，2班没人了...
     * 5个班级全部走完，我们可以锁大门
     */
    @ResponseBody
    @GetMapping("/lockDoor")
    public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.await();//等待闭锁都完成

        return "放假了...";
    }

    @ResponseBody
    @GetMapping("/gogogo/{id}")
    public String gogogog(@PathVariable("id") Long id) {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.countDown();//计数减--

        return id + "班的人都走了...";
    }


}

