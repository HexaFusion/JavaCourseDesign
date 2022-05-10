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
        format = new SimpleDateFormat("HH:mm:ss");//日期时间的格式化
        control = new Control();//实例化一个控制对象
            result = control.parse(content);//调用控制类的格式化方法
            for (String res : result) {
                date = new Date(System.currentTimeMillis());
                chatContent.append(format.format(date) + " ");
                chatContent.append("AI:" + res + "\n");//在展示区域展示消息
                DefaultCaret caret = (DefaultCaret) chatContent.getCaret();//滚动条更新
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);//滚动条更新
        }
    }
}
