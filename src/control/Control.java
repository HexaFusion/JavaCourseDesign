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
    String url = "http://openapi.turingapi.com/openapi/api/v2";//图灵机器人接口
    List<String> code = new ArrayList<>();
    Bean bean;
    Bean.UserInfo userInfo;

    public String formation(String text) {
        bean = new Bean();
        Bean.Perception.InputText inputText = new Bean.Perception.InputText();//Json模型内部类的创建
        bean.setReqType("0");//设置请求的Json数据
        inputText.setText(text);//设置发送的信息
        Bean.Perception perception = new Bean.Perception();////Json模型内部类的创建
        perception.setInputText(inputText);//信息的封装
        bean.setPerception(perception);//内部类的封装
        bean.setUserInfo(userInfo);//内部类的封装
        Gson gson = new Gson();//实例化Gson格式化数据
        TuLBot lBot = new TuLBot();
        return lBot.Respon(url, gson.toJson(bean));//调用机器人的方法发送Json数据，得到接口的响应

    }

    public List<String> parse(String text) {
        Random r = new Random();//三个用户名密码随机使用一个
        //www.tuling123.com申请
        code.add("6ce************************");
        code.add("75d************************");
        code.add("1e1************************");
        userInfo = new Bean.UserInfo();
        userInfo.setApiKey(code.get(r.nextInt(3)));//构造请求Json数据，设置请求的用户名和密码
        userInfo.setUserId("628***");
        String str = formation(text);//格式化数据并得到结果
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(str, ResultBean.class);//得到Json数据后，使用Gson将数据按照ResultBean的模型转换
        List<ResultBean.Results> list = resultBean.getResults();//返回值为数组映射到List中
        List<String> result = new ArrayList<>();//
        if (resultBean.getIntent().getCode() != 10004) {//响应成功的代码为10004，通过模型中封装的方法过去数据
            ChatBot chatBot = new ChatBot();//当图灵接口失效后使用腾讯云接口
            try {
                result.add(TencentParse(chatBot.chatbot(text)));//调用chatbot方法，返回值为腾讯云接口的Json格式数据
            } catch (TencentCloudSDKException e) {
                result.add("所有系统失效，请联系管理员解决错误！");
                return result;
            }
            return result;
        } else {
            for (ResultBean.Results rs : list) {
                if (rs.getResultType().equals("text"))//Json中封装了返回的数据类型，本系统只处理text和url类型
                    result.add(rs.getValues().getText());
                else if (rs.getResultType().equals("url"))
                    result.add(rs.getValues().getUrl());
                else
                    result.add("此类型信息无法显示");
            }
        }
        return result;
    }

    public String TencentParse(String json) {
        Gson gson = new Gson();
        TencentBean tencentBean = gson.fromJson(json, TencentBean.class);//使用Gson按照TencentBean模型解析Json
        return tencentBean.getReply();
    }
}
