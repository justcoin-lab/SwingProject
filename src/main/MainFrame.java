package main;

import match.ResultForm;
import player.PlayerForm;
import player.PlayerInputForm;
import schedule.ScheduleForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

	private JPanel mainSpace;
	private JButton btn1, btn2, btn3, btn4, btn5;
	private Image mainImage;

	public MainFrame(String title) {
		setTitle(title);
		setSize(1000, 600);
		setLocation(460, 240);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		Container ct = getContentPane();
		ct.setBackground(new Color(250, 240, 230));

		// 이미지 로드
		String imagePath = "images/main3.jpg";
		File imageFile = new File(imagePath);
		if (imageFile.exists()) {
			mainImage = new ImageIcon(imageFile.getAbsolutePath()).getImage();
		} else {
			System.out.println("이미지 파일을 찾을 수 없습니다.");
		}

		// 배경 패널 생성
		mainSpace = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (mainImage != null) {
					g.drawImage(mainImage, 10, 10, 964, 480, this);
				}
			}
		};
		mainSpace.setBounds(0, 60, 1000, 540);
		mainSpace.setLayout(null);
		add(mainSpace);

		btn1 = createButton("대한민국 축구단", 10, 10);
		btn2 = createButton("구단 선수 목록", 205, 10);
		btn3 = createButton("선수 입력", 400, 10);
		btn4 = createButton("구단 최근 경기", 595, 10);
		btn5 = createButton("구단 일정", 790, 10);

		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);

		setVisible(true);
	}

	public JButton createButton(String text, int x, int y) {
		JButton button = new JButton(text);
		button.setBounds(x, y, 185, 50);
		button.setBackground(new Color(255, 228, 196));
		button.setBorderPainted(false);
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			new MainFrame("대한민국 축구 국가대표팀");
		} else if (e.getSource() == btn2) {
			new PlayerForm("대한민국 축구 국가대표팀");
		} else if (e.getSource() == btn3) {
			new PlayerInputForm("대한민국 축구 국가대표팀");
		} else if (e.getSource() == btn4) {
			new ResultForm("대한민국 축구 국가대표팀");
		} else if (e.getSource() == btn5) {
			new ScheduleForm("대한민국 축구 국가대표팀");
		}
		dispose();
	}
}
