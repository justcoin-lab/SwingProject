package player;


import db.Util;
import main.MainFrame;
import match.ResultForm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerAdd extends JFrame implements ActionListener {
    // 상단 메뉴 버튼
    private JButton btn1, btn2, btn3, btn4, btn5;
    
    // 입력 필드
    private JTextField tfPn, tfName, tfAge, tfHeight, tfWeight;
    private JComboBox<String> cbPosition, cbInjury, cbRoster;
    
    // 이미지 선택
    private JButton btnSelectImage;
    private JLabel imagePreview;
    private File selectedImageFile;
    
    // 저장, 취소 버튼
    private JButton btnSave, btnCancel;
    
    public PlayerAdd(String title) {
        setTitle(title);
        setSize(1000, 600);
        setLocation(460, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        // 컨테이너 가져오기
        Container ct = getContentPane();
        ct.setBackground(new Color(250, 240, 230));

        // 상단 메뉴 초기화
        initTopButtons();
        
        // 메인 패널
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 240));
        mainPanel.setBounds(0, 60, 1000, 600);
        mainPanel.setLayout(null);
        add(mainPanel);

        // 입력 패널 초기화
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(255, 255, 240));
        inputPanel.setBounds(250, 20, 500, 400);
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("선수 정보 입력"));
        mainPanel.add(inputPanel);
        
        // 입력 필드 초기화
        initInputFields(inputPanel);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(255, 255, 240));
        buttonPanel.setBounds(250, 430, 500, 50);

        btnSave = new JButton("저장");
        btnSave.addActionListener(this);
        btnCancel = new JButton("취소");
        btnCancel.addActionListener(this);

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        mainPanel.add(buttonPanel);

        setVisible(true);
    }
    
    private void initTopButtons() {
        btn1 = new JButton("대한민국 축구단");
        btn1.setBounds(10, 10, 185, 50);
        btn1.setBackground(new Color(255, 228, 196));
        btn1.setBorderPainted(false);
        btn1.addActionListener(this);

        btn2 = new JButton("구단 선수 목록");
        btn2.setBounds(205, 10, 185, 50);
        btn2.setBackground(new Color(255, 228, 196));
        btn2.setBorderPainted(false);
        btn2.addActionListener(this);

        btn3 = new JButton("선수 입력");
        btn3.setBounds(400, 10, 185, 50);
        btn3.setBackground(new Color(255, 228, 196));
        btn3.setBorderPainted(false);
        btn3.addActionListener(this);

        btn4 = new JButton("구단 최근 경기");
        btn4.setBounds(595, 10, 185, 50);
        btn4.setBackground(new Color(255, 228, 196));
        btn4.setBorderPainted(false);
        btn4.addActionListener(this);

        btn5 = new JButton("구단 일정");
        btn5.setBounds(790, 10, 185, 50);
        btn5.setBackground(new Color(255, 228, 196));
        btn5.setBorderPainted(false);
        btn5.addActionListener(this);

        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
    }

    private void initInputFields(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        tfPn = new JTextField(20);
        tfName = new JTextField(20);
        tfAge = new JTextField(20);
        tfHeight = new JTextField(20);
        tfWeight = new JTextField(20);

        cbPosition = new JComboBox<>(new String[]{"FW", "MF", "DF", "GK"});
        cbInjury = new JComboBox<>(new String[]{"없음", "부상중"});
        cbRoster = new JComboBox<>(new String[]{"선발", "후보"});

        imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(150, 180));
        imagePreview.setBorder(BorderFactory.createEtchedBorder());
        imagePreview.setHorizontalAlignment(JLabel.CENTER);
        imagePreview.setText("이미지 미리보기");

        btnSelectImage = new JButton("사진 선택");
        btnSelectImage.addActionListener(this);

        addFormField(panel, gbc, "백넘버", tfPn, 0);
        addFormField(panel, gbc, "이름", tfName, 1);
        addFormField(panel, gbc, "나이", tfAge, 2);
        addFormField(panel, gbc, "키(cm)", tfHeight, 3);
        addFormField(panel, gbc, "몸무게(kg)", tfWeight, 4);
        addFormField(panel, gbc, "포지션", cbPosition, 5);
        addFormField(panel, gbc, "부상여부", cbInjury, 6);
        addFormField(panel, gbc, "선발여부", cbRoster, 7);

        JPanel imagePanel = new JPanel(new BorderLayout(5, 5));
        imagePanel.add(imagePreview, BorderLayout.CENTER);
        imagePanel.add(btnSelectImage, BorderLayout.SOUTH);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(imagePanel, gbc);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent component, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(labelText + ": "), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(component, gbc);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
