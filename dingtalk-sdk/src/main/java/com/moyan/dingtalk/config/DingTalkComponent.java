package com.moyan.dingtalk.config;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author moyan
 * @date 2023/10/31 16:22
 */
@Configuration
public class DingTalkComponent {

    @Resource
    DingTalkConfig dingTalkConfig;

    @Bean
    public DingTalkClient setDingTalkClient() throws NoSuchAlgorithmException, InvalidKeyException {
        return new DefaultDingTalkClient(getURL(dingTalkConfig.getSecret(), dingTalkConfig.getUrl()));
    }


    public static String getURL(String secret, String url) throws NoSuchAlgorithmException, InvalidKeyException {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        String sign = URLEncoder.encode(Base64.getEncoder().encodeToString(signData));
        String signResult = "&timestamp=" + timestamp + "&sign=" + sign;
        // 得到拼接后的 URL
        return url + signResult;
    }

}
