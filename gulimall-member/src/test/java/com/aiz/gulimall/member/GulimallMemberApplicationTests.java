package com.aiz.gulimall.member;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class GulimallMemberApplicationTests {

    /**
     * 测试 MD5 加密
     */
    @Test
    void contextLoads() {
        // 抗修改性：彩虹表 e10adc3949ba59abbe56e057f20f883e
        String s = DigestUtils.md5Hex("123456");
        System.out.println(s);

        // MD5不能直接进行密码的加密存储
        System.out.println(Md5Crypt.md5Crypt("123456".getBytes()));
        System.out.println(Md5Crypt.md5Crypt("123456".getBytes(), "$1$qqqqqqqq"));


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        // $2a$10$.vpG5yOpom9LvA/A4CtfUuG9v4I7dv.HQdZFKvoqyABGq44I4FFjK
        System.out.println(encode);
        encode = bCryptPasswordEncoder.encode("123456");
        // $2a$10$WwqwRurL1VYugT0/rjVKNu4l8NxUMm6qOFHlw.WGD64o6.ohmaXIa
        System.out.println(encode);
        boolean matches = bCryptPasswordEncoder.matches("123456",
                "$2a$10$WwqwRurL1VYugT0/rjVKNu4l8NxUMm6qOFHlw.WGD64o6.ohmaXIa");
        System.out.println(encode + "==>" + matches);

    }

}
