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

	private JPanel logoPanel, imagePanel, textPanel;
	private JButton btn1, btn2, btn3, btn4, btn5;
	private Image mainImage, logoImage;

	public MainFrame(String title) {
		setTitle(title);
		setSize(1000, 600);
		setLocation(460, 240);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		Container ct = getContentPane();
		ct.setBackground(new Color(250, 240, 230));

		// 이미지 경로
		String imagePath = "images/main3.jpg";
		String logoPath = "images/logo.png";

		// 메인 이미지 불러오기
		File imageFile = new File(imagePath);
		if (imageFile.exists()) {
			mainImage = new ImageIcon(imageFile.getAbsolutePath()).getImage();
		} else {
			System.out.println("메인 이미지 파일을 찾을 수 없습니다.");
		}

		// 로고 이미지 불러오기
		File logoFile = new File(logoPath);
		if (logoFile.exists()) {
			logoImage = new ImageIcon(logoFile.getAbsolutePath()).getImage();
		} else {
			System.out.println("로고 이미지 파일을 찾을 수 없습니다.");
		}

		// 로고 이미지가 배치될 패널 생성 좌측 배치
		logoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (logoImage != null) {
					g.drawImage(logoImage, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};
		logoPanel.setBounds(10, 70, 190, 480);

		// 메인 이미지가 배치될 패널 생성 우측 상단 배치
		imagePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (mainImage != null) {
					g.drawImage(mainImage, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};
		imagePanel.setBounds(205, 70, 770, 385);

		// 텍스트가 배치될 패널 생성 우측 하단 배치
		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());

		// 텍스트 패널 라벨 생성 후 적용
		JLabel label = new JLabel("Korea football-team management", JLabel.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 18)); // 라벨 폰트
		label.setBackground(new Color(250, 240, 230));
		label.setOpaque(true);  // 배경색 적용

		JPanel textPanel = new JPanel(new BorderLayout()); // 가운데 정렬을 위한 BorderLayout 설정
		textPanel.add(label, BorderLayout.CENTER); // 라벨 가운데정렬
		textPanel.setBounds(205, 460, 770, 90); // 텍스트 패널의 위치 및 크기 설정

		// 순서대로 배치
		add(logoPanel);
		add(imagePanel);
		add(textPanel);

		// 버튼 생성
		btn1 = createTabButton("대한민국 축구단", 10, 10);
		btn2 = createTabButton("대표팀 선수 목록", 205, 10);
		btn3 = createTabButton("대표팀 선수 입력", 400, 10);
		btn4 = createTabButton("대표팀 경기 결과", 595, 10);
		btn5 = createTabButton("대표팀 일정", 790, 10);

		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);

		setVisible(true);
	}

	public JButton createTabButton(String text, int x, int y) {
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
