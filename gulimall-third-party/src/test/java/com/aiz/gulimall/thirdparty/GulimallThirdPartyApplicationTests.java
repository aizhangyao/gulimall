package com.aiz.gulimall.thirdparty;

import com.aiz.gulimall.thirdparty.component.SmsComponent;
import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.oss.OSSClient;
import com.aliyun.tea.TeaException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Slf4j
@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Autowired
    private OSSClient ossClient;

    @Value("${spring.cloud.alicloud.access-key}")
    private String accessKeyId;

    @Value("${spring.cloud.alicloud.secret-key}")
    private String accessKeySecret;

    @Resource
    private SmsComponent smsComponent;

    @Test
    public void testSendSmsCode() throws Exception {
        smsComponent.sendSmsCode("17805053916", "888666");
    }

    @Test
    public void sendSmsCode() throws Exception {
        com.aliyun.dysmsapi20170525.Client client = GulimallThirdPartyApplicationTests.createClient(accessKeyId, accessKeySecret);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers("17551320715")
                .setTemplateParam("{\"code\":\"4444\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            log.info("{}", JSON.toJSONString(sendSmsResponse));
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }

    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    @Test
    public void testUpdateUseSpringCloud() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("/Users/zhangyao/Picture/square/zhangyao-logo.png");
        ossClient.putObject("gulimall-aiz", "zhangyao-logo.png",inputStream);
        ossClient.shutdown();
        System.out.println("上传成功....");
    }

    @Test
    void contextLoads() {

    }
}
