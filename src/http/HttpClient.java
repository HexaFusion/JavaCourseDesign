package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {
    public HttpClient() {    }

    // HTTP POST����
    public String sendPost(String url, String param) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL Url = new URL(url);
            // �򿪺�URL֮�������
            URLConnection conn = Url.openConnection();
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // �����������
            out.write(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //System.out.println(result);
        } catch (Exception e) {

            System.out.println("���� POST ��������쳣��");
            return  "{\"emotion\":{\"robotEmotion\":{\"a\":0,\"d\":0,\"emotionId\":0,\"p\":0},\"userEmotion\":{\"a\":0,\"d\":0,\"emotionId\":0,\"p\":0}},\"intent\":{\"actionName\":\"\",\"code\":10004,\"intentName\":\"\"},\"results\":[{\"groupType\":1,\"resultType\":\"text\",\"values\":{\"text\":\"���������������������ԣ���\"}}]}";
            //e.printStackTrace();

        }
        //ʹ��finally�����ر��������������
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                //return "�������,�������������ԣ�";
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // System.out.println("post���ͽ����"+result);
        return result;
    }
}
