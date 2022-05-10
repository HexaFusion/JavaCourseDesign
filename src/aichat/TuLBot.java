package aichat;


import http.HttpClient;

public class TuLBot {
    public String Respon(String url, String json) {
        HttpClient httpClient;//创建HttpClient类
        httpClient = new HttpClient();
        String result = httpClient.sendPost(url, json);//与服务器交互，发送请求。
        return result;
    }
}
