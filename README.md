# 项目名称

《谷粒商城gulimall》

# 项目介绍

| 组件地址                                                           |
|----------------------------------------------------------------|
| [本地-人人代码生成器](http://127.0.0.1:8082/#generator.html)            |
| [本地-人人后台管理系统](http://localhost:8001/#/login)                   |
| [本地-Nacos注册配置中心](http://127.0.0.1:8848/nacos/#/login)          |
| [本地-Elasticsearch7.4.2](http://127.0.0.1:9200/)                |
| [本地-Kibana7.4.2](http://localhost:5601/app/kibana#/home?_g=()) |
| [本地-谷粒商城-首页](http://gulimall.com/)                             |
| [本地-谷粒商城-搜索页](http://search.gulimall.com/)                     |
| [本地-RabbitMQ](http://127.0.0.1:15672/)                         |

# 用户端页面地址

|      | 静态资源          | 页面url                                                      |
|------|---------------|------------------------------------------------------------|
| 登录页面 | login         | http://auth.gulimall.com/login.html                        |
| 等待付款 | order/detail  | http://order.gulimall.com/detail.html                      |
| 订单页  | member/list   | http://member.gulimall.com/                                |
| 分类   |               |                                                            |
| 购物车  | cart          | http://cart.gulimall.com/cart.html                         |
|      |               | http://cart.gulimall.com/addToCartSuccessPage.html?skuId=3 |
| 结算页  | order/confirm | http://order.gulimall.com/toTrade                          |
| 收银页  | order/pay     | http://order.gulimall.com/pay.html                         |
| 首页资源 | index         | http://gulimall.com/                                       |
| 搜索页  | search        | http://search.gulimall.com/list.html?keyword=              |
| 详情页  | item          | http://item.gulimall.com/1.html                            |
| 注册页面 | reg           | http://auth.gulimall.com/reg.html                          |

# 依赖版本选择

| 组件                           | 版本                       |
|------------------------------|--------------------------|
| Spring Cloud Alibaba Version | 2.2.8.RELEASE            |
| Spring Cloud Version         | Spring Cloud Hoxton.SR12 |
| Spring Boot Version          | 2.3.12.RELEASE           |

# 服务规划

| 服务                       | 端口    |
|--------------------------|-------|
| gulimall-common          | /     |
| gulimall-gateway         | 88    |
| gulimall-coupon          | 7001  |
| gulimall-member          | 8002  |
| gulimall-order           | 9001  |
| gulimall-product         | 10001 |
| gulimall-ware            | 11001 |
| gulimall-search          | 12001 |
| gulimall-auth-server     | 20000 |
| gulimall-seckill         | 25001 |
| gulimall-third-party     | 30001 |
| gulimall-cart            | 31000 |
| ...                      | ...   |
| gulimall-test-sso-client | 8081  |
| gulimall-test-sso-server | 8080  |
| renren-fast              | 8080  |
| renren-generator         | 8082  |
| 后台管理系统-vue               | 8001  |



host配置

```bash
127.0.0.1 gulimall.com
127.0.0.1 search.gulimall.com
127.0.0.1 item.gulimall.com
127.0.0.1 auth.gulimall.com
127.0.0.1 cart.gulimall.com
127.0.0.1 order.gulimall.com
127.0.0.1 member.gulimall.com
127.0.0.1 seckill.gulimall.com

127.0.0.1 ssoserver.com
127.0.0.1 client1.com
127.0.0.1 client2.com
```