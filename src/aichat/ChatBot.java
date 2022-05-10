package aichat;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.nlp.v20190408.NlpClient;
import com.tencentcloudapi.nlp.v20190408.models.ChatBotRequest;
import com.tencentcloudapi.nlp.v20190408.models.ChatBotResponse;

public class ChatBot {
    public String chatbot(String text) throws TencentCloudSDKException {
        // ʵ����һ����֤���������Ҫ������Ѷ���˻�secretId��secretKey,�˴�����ע����Կ�Եı���
        // ��Կ��ǰ��https://console.cloud.tencent.com/cam/capi��վ���л�ȡ
        Credential cred = new Credential("AKI************************", "uZy9************************");
        NlpClient client = new NlpClient(cred, "ap-guangzhou");
        // ʵ����һ���������,ÿ���ӿڶ����Ӧһ��request����
        ChatBotRequest req = new ChatBotRequest();
        req.setQuery(text);
        // ���ص�resp��һ��ChatBotResponse��ʵ��������������Ӧ
        ChatBotResponse resp = null;
        resp = client.ChatBot(req);
        return ChatBotResponse.toJsonString(resp);
    }
}