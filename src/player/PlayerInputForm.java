package player;

import db.Util;
import main.MainFrame;
import match.ResultForm;
import schedule.ScheduleForm;

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
import java.sql.SQLException;
import java.util.Objects;

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
        initTopButtons();

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
        initInputFields(inputPanel);

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

    private void initTopButtons() {
        btn1 = new JButton("대한민국 축구단");
        btn1.setBounds(10,10,185,50);
        btn1.setBackground(new Color(255,228,196));
        btn1.setBorderPainted(false);
        btn1.addActionListener(this);

        btn2 = new JButton("구단 선수 목록");
        btn2.setBounds(205,10,185,50);
        btn2.setBackground(new Color(255,228,196));
        btn2.setBorderPainted(false);
        btn2.addActionListener(this);

        btn3 = new JButton("선수 입력");
        btn3.setBounds(400,10,185,50);
        btn3.setBackground(new Color(255,228,196));
        btn3.setBorderPainted(false);
        btn3.addActionListener(this);

        btn4 = new JButton("구단 최근 경기");
        btn4.setBounds(595,10,185,50);
        btn4.setBackground(new Color(255,228,196));
        btn4.setBorderPainted(false);
        btn4.addActionListener(this);

        btn5 = new JButton("구단 일정");
        btn5.setBounds(790,10,185,50);
        btn5.setBackground(new Color(255,228,196));
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
        gbc.insets = new Insets(5,5,5,5);

        //입력 필드 초기화
        tfPn = new JTextField(20);
        tfName = new JTextField(20);
        tfAge = new JTextField(20);
        tfHeight = new JTextField(20);
        tfWeight = new JTextField(20);

        cbPosition = new JComboBox<>(new String[]{"FW", "MF", "DF", "GK"});
        cbInjury = new JComboBox<>(new String[] {"없음", "부상중"}); //0, 1로 저장됨
        cbRoster = new JComboBox<>(new String[]{"선발", "후보"}); //m, s로 저장됨

        //이미지 미리보기
        imagePreview = new JLabel();
        imagePreview.setPreferredSize(new Dimension(150,180));
        imagePreview.setBorder(BorderFactory.createEtchedBorder());
        imagePreview.setHorizontalAlignment(JLabel.CENTER);
        imagePreview.setText("이미지 미리보기");

        btnSelectImage = new JButton("사진 선택");
        btnSelectImage.addActionListener(this);

        //필드 배치
        addFormField(panel, gbc, "백넘버", tfPn, 0);
        addFormField(panel, gbc, "이름", tfName, 1);
        addFormField(panel, gbc, "나이", tfAge, 2);
        addFormField(panel, gbc, "키(cm)", tfHeight, 3);
        addFormField(panel, gbc, "몸무게(kg)", tfWeight, 4);
        addFormField(panel, gbc, "포지션", cbPosition, 5);
        addFormField(panel, gbc, "부상여부", cbInjury, 6);
        addFormField(panel, gbc, "선발여부", cbRoster, 7);

        //이미지 미리보기와 버튼 배치
        JPanel imagePanel  = new JPanel(new BorderLayout(5,5));
        imagePanel.add(imagePreview, BorderLayout.CENTER);
        imagePanel.add(btnSelectImage, BorderLayout.SOUTH);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(imagePanel, gbc);
    }

    private void  addFormField(JPanel panel, GridBagConstraints gbc, String labelText,
                               JComponent component, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(labelText + ": "), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(component, gbc);

    }



//대한민국 축구단, 구단 선수 목록, 선수 입력, 구단 최근 경기, 구단 일정
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        //상단 네비게이션 버튼 처리
        if(source == btn1) {
            new MainFrame("대한민국 국가대표");
            dispose();
        }else if (source == btn2) {
            new PlayerForm("구단 선수 목록");
            dispose();
        }else if (source == btn3) {
            new PlayerInputForm("선수 입력");
            dispose();
        }else if (source == btn4) {
            new ResultForm("구단 최근 경기");
            dispose();
        }else if (source == btn5) {
             new ScheduleForm("경기 일정");
             dispose();
        } else if (source == btnSelectImage) { //이미지 선택 버튼
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "이미지파일", "jpg", "jpeg", "png", "gif"));
            int result = fileChooser.showOpenDialog(this);

            if(result == JFileChooser.APPROVE_OPTION) {
                selectedImageFile = fileChooser.getSelectedFile();
                try {
                    BufferedImage originalImage = ImageIO.read(selectedImageFile);
                    Image scaledImage = originalImage.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaledImage);

                    imagePreview.setIcon(icon);
                    imagePreview.setText("");


                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "이미지 로드중 오류 발생했습니다: " + ex.getMessage());
                    imagePreview.setIcon(null);
                    imagePreview.setText("이미지 로드 실패");
                }
            }

        } else if(source == btnSave) { // 저장 버튼
            if(validateInput()) {
                savePlayerInfo();
            }
        } else if(source == btnCancel) {  //취소 버튼
            int response = JOptionPane.showConfirmDialog(this,
                    "선수등록을 취소하시겠습니까? 입력된 선수정보는 저장되지 않습니다",
                    "입력취소",  JOptionPane.YES_NO_OPTION);

            if(response == JOptionPane.YES_OPTION) {
                new PlayerForm("선수 목록");
                dispose();
            }
        }
    }

    private boolean validateInput() {
        //각 필드가 비어있는지 확인
        if(tfPn.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "선수 번호를 입력해 주세요");
            tfPn.requestFocus();
            return false;
        }

        if(tfName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "선수 이름을 입력해 주세요");
            tfName.requestFocus();
            return false;
        }

        if(tfAge.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "선수 나이를 입력해 주세요");
            tfAge.requestFocus();
            return false;
        }

        if(tfHeight.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "선수 키를 입력해 주세요");
            tfHeight.requestFocus();
            return false;
        }

        if(tfWeight.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "선수 몸무게를 입력해 주세요");
            tfWeight.requestFocus();
            return false;
        }
        //숫자필드에 숫자형식 검사
        try {
            Integer.parseInt(tfPn.getText());
            Integer.parseInt(tfAge.getText());
            Integer.parseInt(tfHeight.getText());
            Integer.parseInt(tfWeight.getText());
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "백넘버, 나이, 키, 몸무게는 숫자입력만 가능합니다");
            return false;
        }

        //중복 백넘버 확인
        try {
            Util.init();
            String sql = "SELECT COUNT(*) FROM player Where pn = " + tfPn.getText();
            ResultSet rs = Util.getResult(sql);
            rs.next();
            int count = rs.getInt(1);
            if(count > 0) {
                JOptionPane.showMessageDialog(this,
                        "이미 존재하는 백넘버 입니다. 다른 번호를 사용해 주세요");
                tfPn.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "백넘버 중복 확인중 오류발생"
            + ex.getMessage());
            return false;
        }

        //중복 이름 확인(Primery Key)
        try {
            Util.init();
            String sql = "SELECT COUNT(*) FROM player Where name = '" + tfName.getText() + "'";
            ResultSet rs = Util.getResult(sql);
            rs.next();
            int count = rs.getInt(1);
            if(count > 0) {
                JOptionPane.showMessageDialog(this,
                        "이미 존재하는 선수 이름 입니다. 다른 이름을 사용해 주세요");
                tfName.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "이름 중복 확인중 오류발생"
                    + ex.getMessage());
            return false;
        }

        return true;

    }

    private void savePlayerInfo() {
        try {
            Util.init();
            //name PRIMARY KEY 이기 때문에 중복확인
            String checkSql = "SELECT COUNT(*) FROM player WHERE name = ?";
            PreparedStatement checkSmt = Util.conn.prepareStatement(checkSql);
            checkSmt.setString(1, tfName.getText());
            ResultSet rs = checkSmt.executeQuery();
            rs.next();
            if(rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "이미 존재하는 선수 이름입니다.");
                return;
            }

            PreparedStatement pstmt = Util.conn.prepareStatement(
                    "INSERT INTO player (pn, name, age, height, weight, position, injury, roster) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            //파라미터 설정
            pstmt.setInt(1, Integer.parseInt(tfPn.getText()));
            pstmt.setString(2, tfName.getText());
            pstmt.setInt(3, Integer.parseInt(tfAge.getText()));
            pstmt.setInt(4, Integer.parseInt(tfHeight.getText()));
            pstmt.setInt(5, Integer.parseInt(tfWeight.getText()));
            pstmt.setString(6, (String) cbPosition.getSelectedItem());
            pstmt.setString(7, cbInjury.getSelectedItem().equals("부상중") ? "1" : "0");
            pstmt.setString(8, cbRoster.getSelectedItem().equals("선발")? "m" : "s");

            //쿼리 실행
            int result = pstmt.executeUpdate();


            if (result > 0) {
                // 이미지 파일이 선택되었다면 저장
                if (selectedImageFile != null) {
                    try {
                        // 이미지를 프로젝트의 images 폴더에 선수 이름으로 저장
                        String projectPath = System.getProperty("user.dir");
                        File imagesDir = new File(projectPath + File.separator + "images");
                        if (!imagesDir.exists()) {
                            imagesDir.mkdir();
                        }

                        String targetPath = imagesDir.getPath() + File.separator + tfName.getText() + ".jpg";

                        // 원본 이미지 읽기
                        BufferedImage originalImage = ImageIO.read(selectedImageFile);

                        // 이미지 저장(jpg 형식으로)
                        File targetFile = new File(targetPath);
                        ImageIO.write(originalImage, "jpg", targetFile);
                        System.out.println("이미지가 성공적으로 저장되었습니다: " + targetPath);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this,
                                "이미지 저장 중 오류가 발생했습니다: " + e.getMessage());
                    }
                }
                JOptionPane.showMessageDialog(this, "선수 정보가 성공적으로 저장되었습니다.");
                // 선수 목록 화면으로 이동
                new PlayerForm("national-team management");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "선수 정보 저장에 실패했습니다.");
            }
// 리소스 정리
            pstmt.close();
        }  catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "데이터베이스 오류: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


// 메인 메서드 (테스트용)

}


