package com.aiz.gulimall.search.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    /**
     * 线程池
     */
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void thread(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 1、继承Thread
         * 2、实现Runnable接口
         * 3、实现Callable接口 + FutureTask （可以拿到返回结果，可以处理异常）
         * 4、使用线程池
         *
         * 区别：
         *      1、2 不能得到返回值。 3可以获取返回值
         *      1、2、3都不能控制资源
         *      4可以控制资源，性能稳定
         */
        System.out.println("继承Thread main......start.....");
        Thread thread = new Thread01();
        thread.start();
        System.out.println("继承Thread main......end.....");


        System.out.println("实现Runnable接口 main......start.....");
        Runable01 runable01 = new Runable01();
        new Thread(runable01).start();
        System.out.println("实现Runnable接口 main......end.....");


        System.out.println("实现Callable接口 + FutureTask main......start.....");
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
        System.out.println("实现Callable接口 + FutureTask main......end.....");

        //我们以后在业务代码里，以上三种启动线程的方式都不用。【将所有的多线程异步任务都交给线程池执行】

        //当前系统中池只有一两个，每个异步任务，提交给线程池，让他自己去执行就行
        System.out.println("线程池 main......start.....");
        executor.execute(new Runable01());
        Future<Integer> submit = executor.submit(new Callable01());
        submit.get();
        System.out.println("线程池 main......start.....");
    }

    public static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }
    }

    public static class Runable01 implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }
    }

    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }
    }

    private static void threadPool() {

        ExecutorService threadPool = new ThreadPoolExecutor(
                200,
                10,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(10000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        //定时任务的线程池
        ExecutorService service = Executors.newScheduledThreadPool(2);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main......start.....future_0");
        CompletableFuture<Void> future_0 = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }, executor);
        System.out.println("main......end....." + future_0.get());


        /**
         * 方法完成后的处理
         */
        System.out.println("main......start.....future_1");
        CompletableFuture<Integer> future_1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).whenComplete((res, exception) -> {
            //虽然能得到异常信息，但是没法修改返回数据
            System.out.println("异步任务成功完成了...结果是：" + res + "异常是：" + exception);
        }).exceptionally(throwable -> {
            //可以感知异常，同时返回默认值
            return 10;
        });
        System.out.println("main......end....." + future_1.get());


        /**
         * 方法执行完后端处理
         */
        System.out.println("main......start.....future_2");
        CompletableFuture<Integer> future_2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).handle((result, thr) -> {
            if (result != null) {
                return result * 2;
            }
            if (thr != null) {
                System.out.println("异步任务成功完成了...结果是：" + result + "异常是：" + thr);
                return 0;
            }
            return 0;
        });
        System.out.println("main......end....." + future_2.get());


        /**
         * 线程串行化
         * 1、thenRunL：不能获取上一步的执行结果
         * 2、thenAcceptAsync：能接受上一步结果，但是无返回值
         * 3、thenApplyAsync：能接受上一步结果，有返回值
         */
        System.out.println("main......start.....future_3");
        CompletableFuture<String> future_3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了..." + res);
            return "Hello" + res;
        }, executor);
        System.out.println("main......end....." + future_3.get());


        /**
         * 两个都完成
         */
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("任务1结束：");
            return i;
        }, executor);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程：" + Thread.currentThread().getId());
            System.out.println("任务2结束：");
            return "Hello";
        }, executor);

//        future01.runAfterBothAsync(future02, () -> {
//            System.out.println("任务3开始");
//        }, executor);
//        future01.thenAcceptBothAsync(future02, (f1, f2) -> {
//            System.out.println("任务3开始。。。之前的结果：" + f1 + "==>" + f2);
//        }, executor);
//        System.out.println("main....end.....");

        CompletableFuture<String> future = future01.thenCombineAsync(future02, (f1, f2) -> {
            return f1 + "：" + f2 + " -> Haha";
        }, executor);
        System.out.println("main....end....." + future.get());

    }

}
