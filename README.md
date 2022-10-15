# 项目名称

《谷粒商城gulimall》

# 项目介绍

[本地-人人代码生成器](http://127.0.0.1:8082/#generator.html)

[本地-人人后台管理系统](http://localhost:8001/#/login)

[本地-Nacos注册配置中心](http://127.0.0.1:8848/nacos/#/login)

[本地-Elasticsearch7.4.2](http://127.0.0.1:9200/)

[本地-Kibana7.4.2](http://localhost:5601/app/kibana#/home?_g=())

[本地-谷粒商城-首页-域名](http://gulimall.com/)

[本地-谷粒商城-首页-ip:port](http://127.0.0.1:10001/#)

[本地-谷粒商城-搜索页-域名](http://search.gulimall.com/)

[本地-?谷粒商城-搜索页-ip:port](http://127.0.0.1:12001/list.html)

[本地-RabbitMQ](http://127.0.0.1:15672/)


登录页 http://auth.gulimall.com/login.html

注册页 http://auth.gulimall.com/reg.html 


# 依赖版本选择

| 组件                           | 版本                       |
|------------------------------|--------------------------|
| Spring Cloud Alibaba Version | 2.2.8.RELEASE            |
| Spring Cloud Version         | Spring Cloud Hoxton.SR12 |
| Spring Boot Version          | 2.3.12.RELEASE           |

# 服务规划

| 服务                   | 端口    |
|----------------------|-------|
| gulimall-common      | /     |
| gulimall-gateway     | 88    |
| gulimall-coupon      | 7001  |
| gulimall-member      | 8002  |
| gulimall-order       | 9001  |
| gulimall-product     | 10001 |
| gulimall-ware        | 11001 |
| gulimall-search      | 12001 |
| gulimall-third-party | 30001 |
| ...                  | ...   |
| renren-fast          | 8080  |
| renren-generator     | 8082  |
| 后台管理系统-vue           | 8001  |
