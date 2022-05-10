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
        String content = sendArea.getText();//��ȡ����������ı�
        content = content.trim().replaceAll("\n", "").replaceAll("\r", "").trim();//ɾ���س��Ϳո�
        if (content != null && !content.equals("")) {//�ж���Ϣ�Ƿ�Ϊ�հ�
            date = new Date(System.currentTimeMillis());
            chatContent.append(format.format(date) + " ");
            chatContent.append("��:" + content + "\n");
            netThread = new NetThread(chatContent, sendArea, content);//ʹ���̴߳���AI�ӿ���Ϣ
            netThread.start();
        } else {
            date = new Date(System.currentTimeMillis());
            chatContent.append(format.format(date) + " ");
            chatContent.append("ϵͳ:�������ݲ���Ϊ��" + "\n");//���Ϊ�����ϵͳ��ʾ
        }
        sendArea.setText("");//�����ı�����Ϊ��
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.action();
        DefaultCaret caret = (DefaultCaret) chatContent.getCaret();//����������
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);//����������
    }
}
