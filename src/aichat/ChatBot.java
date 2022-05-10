package aichat;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.nlp.v20190408.NlpClient;
import com.tencentcloudapi.nlp.v20190408.models.ChatBotRequest;
import com.tencentcloudapi.nlp.v20190408.models.ChatBotResponse;

public class ChatBot {
    public String chatbot(String text) throws TencentCloudSDKException {
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
        // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
        Credential cred = new Credential("AKI************************", "uZy9************************");
        NlpClient client = new NlpClient(cred, "ap-guangzhou");
        // 实例化一个请求对象,每个接口都会对应一个request对象
        ChatBotRequest req = new ChatBotRequest();
        req.setQuery(text);
        // 返回的resp是一个ChatBotResponse的实例，与请求对象对应
        ChatBotResponse resp = null;
        resp = client.ChatBot(req);
        return ChatBotResponse.toJsonString(resp);
    }
}