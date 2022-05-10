package control;

import aichat.ChatBot;
import aichat.TuLBot;
import bean.Bean;
import bean.ResultBean;
import bean.TencentBean;
import com.google.gson.Gson;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Control {
    String url = "http://openapi.turingapi.com/openapi/api/v2";//ͼ������˽ӿ�
    List<String> code = new ArrayList<>();
    Bean bean;
    Bean.UserInfo userInfo;

    public String formation(String text) {
        bean = new Bean();
        Bean.Perception.InputText inputText = new Bean.Perception.InputText();//Jsonģ���ڲ���Ĵ���
        bean.setReqType("0");//���������Json����
        inputText.setText(text);//���÷��͵���Ϣ
        Bean.Perception perception = new Bean.Perception();////Jsonģ���ڲ���Ĵ���
        perception.setInputText(inputText);//��Ϣ�ķ�װ
        bean.setPerception(perception);//�ڲ���ķ�װ
        bean.setUserInfo(userInfo);//�ڲ���ķ�װ
        Gson gson = new Gson();//ʵ����Gson��ʽ������
        TuLBot lBot = new TuLBot();
        return lBot.Respon(url, gson.toJson(bean));//���û����˵ķ�������Json���ݣ��õ��ӿڵ���Ӧ

    }

    public List<String> parse(String text) {
        Random r = new Random();//�����û����������ʹ��һ��
        //www.tuling123.com����
        code.add("6ce************************");
        code.add("75d************************");
        code.add("1e1************************");
        userInfo = new Bean.UserInfo();
        userInfo.setApiKey(code.get(r.nextInt(3)));//��������Json���ݣ�����������û���������
        userInfo.setUserId("628***");
        String str = formation(text);//��ʽ�����ݲ��õ����
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(str, ResultBean.class);//�õ�Json���ݺ�ʹ��Gson�����ݰ���ResultBean��ģ��ת��
        List<ResultBean.Results> list = resultBean.getResults();//����ֵΪ����ӳ�䵽List��
        List<String> result = new ArrayList<>();//
        if (resultBean.getIntent().getCode() != 10004) {//��Ӧ�ɹ��Ĵ���Ϊ10004��ͨ��ģ���з�װ�ķ�����ȥ����
            ChatBot chatBot = new ChatBot();//��ͼ��ӿ�ʧЧ��ʹ����Ѷ�ƽӿ�
            try {
                result.add(TencentParse(chatBot.chatbot(text)));//����chatbot����������ֵΪ��Ѷ�ƽӿڵ�Json��ʽ����
            } catch (TencentCloudSDKException e) {
                result.add("����ϵͳʧЧ������ϵ����Ա�������");
                return result;
            }
            return result;
        } else {
            for (ResultBean.Results rs : list) {
                if (rs.getResultType().equals("text"))//Json�з�װ�˷��ص��������ͣ���ϵͳֻ����text��url����
                    result.add(rs.getValues().getText());
                else if (rs.getResultType().equals("url"))
                    result.add(rs.getValues().getUrl());
                else
                    result.add("��������Ϣ�޷���ʾ");
            }
        }
        return result;
    }

    public String TencentParse(String json) {
        Gson gson = new Gson();
        TencentBean tencentBean = gson.fromJson(json, TencentBean.class);//ʹ��Gson����TencentBeanģ�ͽ���Json
        return tencentBean.getReply();
    }
}
