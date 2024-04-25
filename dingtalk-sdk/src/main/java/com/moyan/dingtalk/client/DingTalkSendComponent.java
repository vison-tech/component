package com.moyan.dingtalk.client;

import com.moyan.dingtalk.config.DingTalkComponent;
import com.moyan.dingtalk.config.DingTalkConfig;
import com.moyan.dingtalk.model.DingTalkMessageInfo;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

/**
 * @author moyan
 * @date 2023/10/31 15:48
 */
@Configuration
@Import({DingTalkConfig.class, DingTalkComponent.class})
public class DingTalkSendComponent {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DingTalkClient dingTalkClient;


    public void sendDingDingMessage(DingTalkMessageInfo dingTalkMessageInfo) {
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent(dingTalkMessageInfo.getMessage());
            request.setText(text);
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            if (dingTalkMessageInfo.atAll()) {
                at.setIsAtAll(true);
            } else {
                at.setAtMobiles(dingTalkMessageInfo.getAtPhones());
            }
            request.setAt(at);
            OapiRobotSendResponse response = dingTalkClient.execute(request);
            logger.info("success:{}, code:{}, errorCode:{}, errorMsg:{}", response.isSuccess(), response.getCode(), response.getErrcode(), response.getErrmsg());
        } catch (Exception e) {
            logger.error("发送钉钉消息异常：{}", e.getMessage());
        }
    }


}
