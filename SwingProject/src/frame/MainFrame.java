package frame;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MainFrame extends JFrame{
	
	private JPanel mainSpace;
	private JPanel listInfo;
	private JPanel mvpInfo;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	
	public MainFrame(String title) {
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
		
		listInfo = new JPanel();
		listInfo.setBackground(new Color(255, 255, 240));
		listInfo.setBounds(20, 80, 540, 460);
		listInfo.setBorder(new TitledBorder(new LineBorder(new Color(105, 105, 105))));
		
		mvpInfo = new JPanel();
		mvpInfo.setBackground(new Color(255, 255, 240));
		mvpInfo.setBounds(580, 80, 280, 460);
		mvpInfo.setBorder(new TitledBorder(new LineBorder(new Color(105, 105, 105))));
		
		btn1 = new JButton("대한민국 축구단");
		btn1.setBounds(10, 10, 185, 50);
		btn1.setBackground(new Color(255, 228, 196));
		btn1.setBorderPainted(false);
		
		btn2 = new JButton("구단 선수 목록");
		btn2.setBounds(205, 10, 185, 50);
		btn2.setBackground(new Color(255, 228, 196));
		btn2.setBorderPainted(false);
		
		btn3 = new JButton("선수 입력");
		btn3.setBounds(400, 10, 185, 50);
		btn3.setBackground(new Color(255, 228, 196));
		btn3.setBorderPainted(false);
		
		btn4 = new JButton("구단 최근 경기");
		btn4.setBounds(595, 10, 185, 50);
		btn4.setBackground(new Color(255, 228, 196));
		btn4.setBorderPainted(false);
		
		btn5 = new JButton("구단 일정");
		btn5.setBounds(790, 10, 185, 50);
		btn5.setBackground(new Color(255, 228, 196));
		btn5.setBorderPainted(false);
		
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);
		
		add(mvpInfo);
		add(listInfo);
		add(mainSpace);
		
		setVisible(true);
	}
}
