package gui;

import control.NetThread;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dispose implements ActionListener {
    JTextArea sendArea, chatContent;
    NetThread netThread;
    SimpleDateFormat format;
    Date date;

    public Dispose(JTextArea sendArea, JTextArea chatContent) {
        this.sendArea = sendArea;
        this.chatContent = chatContent;
        format = new SimpleDateFormat("HH:mm:ss");
    }

    public void action() {
        String content = sendArea.getText();//获取发送区域的文本
        content = content.trim().replaceAll("\n", "").replaceAll("\r", "").trim();//删除回车和空格。
        if (content != null && !content.equals("")) {//判断消息是否为空白
            date = new Date(System.currentTimeMillis());
            chatContent.append(format.format(date) + " ");
            chatContent.append("我:" + content + "\n");
            netThread = new NetThread(chatContent, sendArea, content);//使用线程处理AI接口消息
            netThread.start();
        } else {
            date = new Date(System.currentTimeMillis());
            chatContent.append(format.format(date) + " ");
            chatContent.append("系统:聊天内容不能为空" + "\n");//如果为空输出系统提示
        }
        sendArea.setText("");//设置文本区域为空
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.action();
        DefaultCaret caret = (DefaultCaret) chatContent.getCaret();//滚动条更新
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);//滚动条更新
    }
}
