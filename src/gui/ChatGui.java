package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatGui extends JFrame {
    JButton sendBt;//���Ͱ�ť
    JTextArea chatContent, sendArea;//չʾ����,//��������
    Font font, sendFont;//չʾ���壬��������
    JMenuBar menuBar;//�˵���
    JMenu menu;//�˵���
    JMenuItem item;//�˵�ѡ����
    SimpleDateFormat format;//����ʱ��
    //����ʱ��
    JScrollPane showPanel, areaScrool;//չʾ������壬���͹������

    public ChatGui() {
        font = new Font("����", Font.PLAIN, 15);
        sendFont = new Font("����", Font.PLAIN, 13);
        chatContent = new JTextArea(8, 12);
        sendArea = new JTextArea(5, 24);
        showPanel = new JScrollPane(chatContent);
        areaScrool = new JScrollPane(sendArea);
        menuBar = new JMenuBar();
        sendBt = new JButton("����");
        menu = new JMenu("�ļ�");
        item = new JMenuItem("�ر�");
    }

    public static void main(String[] args) {
        ChatGui chatGui = new ChatGui();
        chatGui.init();
    }

    public void init() {

        this.setLayout(null);//����Ϊ�ղ���
        this.setJMenuBar(menuBar);//��Ӳ˵���
        this.setTitle("����AI�ӿڵ��˻�����");//���ñ���
        this.setBounds(400, 120, 596, 530);//���ô��ڴ�С
        showPanel.setBounds(1, 1, getWidth() - 16, (int) (getHeight() * 0.65) - 25);//����չʾ�����С��λ��
        areaScrool.setBounds(1, showPanel.getHeight() + 2, showPanel.getWidth(), (int) (getHeight() * 0.25) - 25);//���÷��������С��λ��
        sendBt.setBounds(getWidth() - 100, showPanel.getHeight() + areaScrool.getHeight() + 3, 85, 30);//���÷��Ͱ�ť��С��λ��
        DefaultCaret caret = (DefaultCaret) chatContent.getCaret();//����������
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);//����������
        chatContent.setEditable(false);//չʾ�����ֹ�༭
        chatContent.setLineWrap(true);//�Զ�����
        chatContent.setBackground(Color.getHSBColor(0.67F, 0.01F, 0.96F));//չʾ�������ñ���
        chatContent.setFont(font);//��������
        format = new SimpleDateFormat("HH:mm:ss");
        chatContent.append(format.format(new Date(System.currentTimeMillis())) + " ");
        chatContent.append("ϵͳ:�����ɹ�������������" + "\n");
        areaScrool.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//����������Ҫʱ�ɼ�
        sendArea.setLineWrap(true);//�����Զ�����
        sendArea.setFont(sendFont);//��������
        Border border = BorderFactory.createLineBorder(Color.lightGray);//����߿�
        sendArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));//���ñ߿�


        sendBt.addActionListener(new Dispose(sendArea, chatContent));//���÷��Ͱ�ť����ʱ�ļ���
        sendBt.registerKeyboardAction(new Dispose(sendArea, chatContent), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);//���÷��Ͱ�ť�󶨿�ݼ�Ctrl+Enter
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });//���ò˵���ĵ����¼�
        this.addComponentListener(new ComponentAdapter() {//�Զ����ڱ仯
            @Override
            public void componentResized(ComponentEvent e) {
                showPanel.setBounds(1, 1, getWidth() - 16, (int) (getHeight() * 0.65) - 25);
                areaScrool.setBounds(1, showPanel.getHeight() + 2, showPanel.getWidth(), (int) (getHeight() * 0.25) - 25);
                sendBt.setBounds(getWidth() - 100, showPanel.getHeight() + areaScrool.getHeight() + 6, 85, 30);
            }
        });//���ô����Զ��仯
        menuBar.add(menu);
        menu.add(item);
        this.add(showPanel);
        this.add(areaScrool);
        this.add(sendBt);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}