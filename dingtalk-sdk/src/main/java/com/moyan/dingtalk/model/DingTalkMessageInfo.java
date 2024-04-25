package com.moyan.dingtalk.model;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author moyan
 * @date 2023/10/31 16:47
 */
public class DingTalkMessageInfo {

    private String message;

    private List<String> atPhones;

    public Boolean atAll() {
        return CollectionUtils.isEmpty(atPhones);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getAtPhones() {
        return atPhones;
    }

    public void setAtPhones(List<String> atPhones) {
        this.atPhones = atPhones;
    }
}
