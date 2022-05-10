package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatGui extends JFrame {
    JButton sendBt;//发送按钮
    JTextArea chatContent, sendArea;//展示区域,//发送区域
    Font font, sendFont;//展示字体，发送字体
    JMenuBar menuBar;//菜单栏
    JMenu menu;//菜单项
    JMenuItem item;//菜单选择项
    SimpleDateFormat format;//日期时间
    //日期时间
    JScrollPane showPanel, areaScrool;//展示滚动面板，发送滚动面板

    public ChatGui() {
        font = new Font("宋体", Font.PLAIN, 15);
        sendFont = new Font("宋体", Font.PLAIN, 13);
        chatContent = new JTextArea(8, 12);
        sendArea = new JTextArea(5, 24);
        showPanel = new JScrollPane(chatContent);
        areaScrool = new JScrollPane(sendArea);
        menuBar = new JMenuBar();
        sendBt = new JButton("发送");
        menu = new JMenu("文件");
        item = new JMenuItem("关闭");
    }

    public static void main(String[] args) {
        ChatGui chatGui = new ChatGui();
        chatGui.init();
    }

    public void init() {

        this.setLayout(null);//设置为空布局
        this.setJMenuBar(menuBar);//添加菜单栏
        this.setTitle("基于AI接口的人机交互");//设置标题
        this.setBounds(400, 120, 596, 530);//设置窗口大小
        showPanel.setBounds(1, 1, getWidth() - 16, (int) (getHeight() * 0.65) - 25);//设置展示区域大小和位置
        areaScrool.setBounds(1, showPanel.getHeight() + 2, showPanel.getWidth(), (int) (getHeight() * 0.25) - 25);//设置发送区域大小和位置
        sendBt.setBounds(getWidth() - 100, showPanel.getHeight() + areaScrool.getHeight() + 3, 85, 30);//设置发送按钮大小和位置
        DefaultCaret caret = (DefaultCaret) chatContent.getCaret();//滚动条更新
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);//滚动条更新
        chatContent.setEditable(false);//展示区域禁止编辑
        chatContent.setLineWrap(true);//自动换行
        chatContent.setBackground(Color.getHSBColor(0.67F, 0.01F, 0.96F));//展示区域设置背景
        chatContent.setFont(font);//设置字体
        format = new SimpleDateFormat("HH:mm:ss");
        chatContent.append(format.format(new Date(System.currentTimeMillis())) + " ");
        chatContent.append("系统:启动成功，请输入内容" + "\n");
        areaScrool.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//滚动条在需要时可见
        sendArea.setLineWrap(true);//设置自动换行
        sendArea.setFont(sendFont);//设置字体
        Border border = BorderFactory.createLineBorder(Color.lightGray);//定义边框
        sendArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));//设置边框


        sendBt.addActionListener(new Dispose(sendArea, chatContent));//设置发送按钮单击时的监听
        sendBt.registerKeyboardAction(new Dispose(sendArea, chatContent), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);//设置发送按钮绑定快捷键Ctrl+Enter
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });//设置菜单项的单击事件
        this.addComponentListener(new ComponentAdapter() {//自动窗口变化
            @Override
            public void componentResized(ComponentEvent e) {
                showPanel.setBounds(1, 1, getWidth() - 16, (int) (getHeight() * 0.65) - 25);
                areaScrool.setBounds(1, showPanel.getHeight() + 2, showPanel.getWidth(), (int) (getHeight() * 0.25) - 25);
                sendBt.setBounds(getWidth() - 100, showPanel.getHeight() + areaScrool.getHeight() + 6, 85, 30);
            }
        });//设置窗口自动变化
        menuBar.add(menu);
        menu.add(item);
        this.add(showPanel);
        this.add(areaScrool);
        this.add(sendBt);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}