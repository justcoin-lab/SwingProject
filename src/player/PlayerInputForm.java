package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PlayerInputForm extends JFrame implements ActionListener {
    //상위 메뉴 버튼
    public JButton btn1;
    public JButton btn2;
    public JButton btn3;
    public JButton btn4;
    public JButton btn5;

    //입력 필드
    private JTextField tfPn;
    private JTextField tfName;
    private JTextField tfAge;
    private JTextField tfHeight;
    private JTextField tfWeight;
    private JComboBox<String> cbPosition;
    private JComboBox<String> cbInjury;
    private JComboBox<String> cbRoster;

    //이미지 선택
    private JButton btnSelectImage;
    private JLabel imagePreview;
    private File selectedImageFile;

    //저장 취소 버튼
    private JButton btnSave;
    private JButton btnCancel;

    public PlayerInputForm(String title) {
        //메인 프레임 설정
        setTitle(title);
        setSize(1000,600);
        setLocation(460,240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);


        //컨테이너 가져오기
        Container ct = getContentPane();
        ct.setBackground(new Color(250,240,230));

        //상단 메뉴 버튼 초기화
        //initTopButtons();

        //메인 패널 초기화
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255,255,240));
        mainPanel.setBounds(0, 60, 1000, 600); // bounds 추가
        mainPanel.setLayout(null);
        add(mainPanel);

        //입력 폼 패널
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(255,255,240));
        inputPanel.setBounds(250,20,500,400);
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("선수 정보 입력"));
        mainPanel.add(inputPanel);

        //입력 필드 초기화
        //initInputFields(inputPanel);

        //버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(255,255,240));
        buttonPanel.setBounds(250,430,500,50);

        btnSave = new JButton("저장");
        btnSave.addActionListener(this);
        btnCancel = new JButton("취소");
        btnCancel.addActionListener(this);

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        mainPanel.add(buttonPanel); // buttonPanel을 mainPanel에 추가
        setVisible(true);

    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
