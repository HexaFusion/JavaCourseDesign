package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {
    public HttpClient() {    }

    // HTTP POST请求
    public String sendPost(String url, String param) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL Url = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = Url.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //System.out.println(result);
        } catch (Exception e) {

            System.out.println("发送 POST 请求出现异常！");
            return  "{\"emotion\":{\"robotEmotion\":{\"a\":0,\"d\":0,\"emotionId\":0,\"p\":0},\"userEmotion\":{\"a\":0,\"d\":0,\"emotionId\":0,\"p\":0}},\"intent\":{\"actionName\":\"\",\"code\":10004,\"intentName\":\"\"},\"results\":[{\"groupType\":1,\"resultType\":\"text\",\"values\":{\"text\":\"网络错误，请连接网络后重试！！\"}}]}";
            //e.printStackTrace();

        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                //return "网络错误,请连接网络重试！";
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // System.out.println("post推送结果："+result);
        return result;
    }
}
