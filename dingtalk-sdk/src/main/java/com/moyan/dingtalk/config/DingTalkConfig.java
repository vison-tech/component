package com.moyan.dingtalk.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author moyan
 * @date 2023/10/31 15:46
 */
@Configuration
@ConfigurationProperties(prefix = "dingtalk")
public class DingTalkConfig {

    private String secret;

    private String url;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
