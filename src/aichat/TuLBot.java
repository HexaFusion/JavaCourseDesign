package aichat;


import http.HttpClient;

public class TuLBot {
    public String Respon(String url, String json) {
        HttpClient httpClient;//����HttpClient��
        httpClient = new HttpClient();
        String result = httpClient.sendPost(url, json);//���������������������
        return result;
    }
}
