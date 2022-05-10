package control;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NetThread extends Thread {
    JTextArea sendArea, chatContent;
    String content = "";
    List<String> result;
    Control control;
    Date date;
    SimpleDateFormat format;

    public NetThread(JTextArea chatContent, JTextArea sendArea, String content) {
        this.sendArea = sendArea;
        this.chatContent = chatContent;
        this.content = content;

    }
    @Override
    public void run() {
        super.run();
        format = new SimpleDateFormat("HH:mm:ss");//����ʱ��ĸ�ʽ��
        control = new Control();//ʵ����һ�����ƶ���
            result = control.parse(content);//���ÿ�����ĸ�ʽ������
            for (String res : result) {
                date = new Date(System.currentTimeMillis());
                chatContent.append(format.format(date) + " ");
                chatContent.append("AI:" + res + "\n");//��չʾ����չʾ��Ϣ
                DefaultCaret caret = (DefaultCaret) chatContent.getCaret();//����������
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);//����������
        }
    }
}
