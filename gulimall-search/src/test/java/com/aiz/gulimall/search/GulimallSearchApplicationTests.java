package com.aiz.gulimall.search;

import com.aiz.gulimall.search.config.GuliElasticSearchConfig;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GulimallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;


    /**
     * (1).方便检索
     * {
     *     skuId:1
     *     spkId:11
     *     skuTitle:
     *     saleCount:
     *     attrs:[
     *          {尺寸：6寸},
     *          {CPU：高通945},
     *          {分辨率：全高清}
     *     ]
     * }
     * 冗余：
     *  100万*20=100000*2KB=2000MB=2G
     *
     * (2).
     * sku索引{
     *      skuId:1
     *      spkId:11
     *      xxxxx
     * }
     *
     * attr索引{
     *     spuId:11,
     *     attrs:[
     *          {尺寸：6寸},
     *          {CPU：高通945},
     *          {分辨率：全高清}
     *     ]
     * }
     *
     * 搜索 小米：粮食、手机、电器
     * 10000个，4000个spu
     * 分步骤，4000个spu对应的所有可能属性；
     * esClient：spuId:[4000个spuId] 4000*8=32000byte=32KB 一个请求
     * 一万人同时访问 32KB*10000人=320MB 如果百万人 32GB
     *
     *
     */
    /**
     * 测试复杂检索
     */
    @Test
    void searchDate() throws IOException {
        //1.创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        //指定索引
        searchRequest.indices("bank");
        //指定DSL，检索条件
        // SearchSourceBuilder searchSourceBuilder 封装的条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.source(searchSourceBuilder);

//        searchSourceBuilder.query();
//        searchSourceBuilder.from();
//        searchSourceBuilder.size();
//        searchSourceBuilder.aggregation();
        searchSourceBuilder.query(QueryBuilders.matchQuery("address","mill"));
        //1.2 按照年龄的值分布进行聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        searchSourceBuilder.aggregation(ageAgg);
        //1.3 计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        searchSourceBuilder.aggregation(balanceAvg);

        System.out.println(searchSourceBuilder);

        //2.执行检索
        SearchResponse searchResponse = client.search(searchRequest, GuliElasticSearchConfig.COMMON_OPTIONS);

        //3.分析结果 SearchResponse
        System.out.println(searchResponse.toString());
        //JSON.parseObject(searchResponse.toString(), Map.class);
        //3.1 获取所有查到的数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // do something with the SearchHit
            /**
             * "_index" : "bank",
             *  "_type" : "account",
             *  "_id" : "1",
             *  "_score" : 1.0,
             */
            //hit.getIndex();hit.getType();hit.getId();
            String string = hit.getSourceAsString();
            Account account = JSON.parseObject(string, Account.class);
            System.out.println("accunt"+ account);
        }
        //3.2 获取这次检索到的分析信息
        Aggregations aggregations = searchResponse.getAggregations();
//        for (Aggregation aggregation : aggregations.asList()) {
//            System.out.println("当前聚合"+aggregation.getName());
//        }
        Terms ageAgg1 = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("年龄"+keyAsString+"===>"+bucket.getDocCount());
        }

        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("平均薪资"+balanceAvg1.getValue());

    }

    @ToString
    @Data
    static class Account {
        private int account_number;
        private int balance;
        private String firstname;
        private String lastname;
        private int age;
        private String gender;
        private String address;
        private String employer;
        private String email;
        private String city;
        private String state;
    }


    /**
     * 测试存储数据到es
     * 更新也可以
     */
    @Test
    void indexDate() throws IOException {
        IndexRequest request = new IndexRequest("users");
        request.id("1");//数据的id
        //request.source("userName","zhangsan","age",18,"gender","男");
        User user = new User();
        user.setUserName("zhangsan");
        user.setAge(18);
        user.setGender("男");
        String jsonString = JSON.toJSONString(user);
        request.source(jsonString, XContentType.JSON);//要保存的内容
        //执行操作
        IndexResponse indexResponse = client.index(request, GuliElasticSearchConfig.COMMON_OPTIONS);
        //提取有用的响应
        System.out.println(indexResponse);
    }

    @Data
    class User{
        private String userName;
        private Integer age;
        private String gender;
    }

    @Test
    void contextLoads() {
        System.out.println(client);
    }


}
