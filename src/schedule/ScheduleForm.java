package schedule;

import main.MainFrame;
import match.ResultForm;
import player.PlayerForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleForm extends JFrame implements ActionListener {
    public JPanel mainSpace;
    public JButton btn1;
    public JButton btn2;
    public JButton btn3;
    public JButton btn4;
    public JButton btn5;

    public ScheduleForm(String title) {
        setTitle(title);
        setSize(1000, 600);
        setLocation(460, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        Container ct = getContentPane();
        ct.setBackground(new Color(250, 240, 230));

        mainSpace = new JPanel();
        mainSpace.setBackground(new Color(255, 255, 240));
        mainSpace.setBounds(0, 60, 1000, 540);

        btn1 = new JButton("대한민국 축구단");
        btn1.setBounds(10, 10, 185, 50);
        btn1.setBackground(new Color(255, 228, 196));
        btn1.setBorderPainted(false);


        btn2 = new JButton("구단 선수 목록");
        btn2.setBounds(205, 10, 185, 50);
        btn2.setBackground(new Color(255, 228, 196));
        btn2.addActionListener(this);

        btn3 = new JButton("선수 입력");
        btn3.setBounds(400, 10, 185, 50);
        btn3.setBackground(new Color(255, 228, 196));
        btn3.setBorderPainted(false);


        btn4 = new JButton("구단 최근 경기");
        btn4.setBounds(595, 10, 185, 50);
        btn4.setBackground(new Color(255, 228, 196));
        btn4.setBorderPainted(false);
        btn4.addActionListener(this);

        btn5 = new JButton("구단 일정");
        btn5.setBounds(790, 10, 185, 50);
        btn5.setBackground(new Color(255, 228, 196));
        btn5.setBorderPainted(false);


        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);

        add(mainSpace);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == btn1) {
            new MainFrame("대한민국 축구 국가대표팀");
            dispose();
        } else if (obj == btn2) {
            new PlayerForm("대한민국 축구 국가대표팀");
            dispose();
        } else if (obj == btn3) {
            // new InputForm();
        } else if (obj == btn4) {
            new ResultForm("대한민국 축구 국가대표팀");
            dispose();
        } else if (obj == btn5) {
            new ScheduleForm("대한민국 축구 국가대표팀");
            dispose();
        }
    }
}
