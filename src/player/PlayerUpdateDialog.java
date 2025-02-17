package player;

import db.Util;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class PlayerUpdateDialog extends JDialog {
    //입력 필드 선언
    private JTextField tfPn;
    private JTextField tfName;
    private JTextField tfAge;
    private JTextField tfHeight;
    private JTextField tfWeight;

    private JComboBox<String> cbPosition;
    private JComboBox<String> cbRoster;
    private JComboBox<String> cbInjury;
    private JButton btnUpdate;
    private JButton btnCancel;
    private PlayerDto currentPlayer;
    private PlayerForm parentForm;

    private int pn;
    private String name;
    private int age;
    private double height;
    private double weight;
    private String position;
    private String injury;
    private String roster;



    public PlayerUpdateDialog(PlayerDto player, PlayerForm parent) {
        // 부모 프레임이 null일 경우 기본 JFrame 사용
        super(parent != null ? parent : new JFrame(), "선수정보수정", true);

        if (player == null) {
            throw new IllegalArgumentException("Player 정보가 올바르지 않습니다.");
        }

        this.currentPlayer = player;
        this.parentForm = parent;

        // 다이얼로그 기본 설정
        setSize(400, 500);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // UI 초기화 및 배치
        initializeComponents();
        setupLayout(gbc);
        populateFields();
        setupEventListeners();
    }

    private void initializeComponents() {
        //텍스트 필드 초기화
        tfPn = new JTextField(20);
        tfName = new JTextField(20);
        tfAge = new JTextField(20);
        tfHeight = new JTextField(20);
        tfWeight = new JTextField(20);

        //콤보박스 초기화
        cbPosition = new JComboBox<>(new String[]{"FW", "MF", "DF", "GK"});
        cbInjury = new JComboBox<>(new String[]{"없음", "부상중"});
        cbRoster = new JComboBox<>(new String[]{"선발", "후보"});

        //버튼 초기화
        btnUpdate = new JButton("선수정보 Update 확인");
        btnCancel = new JButton("취소");

    }

    private void setupLayout(GridBagConstraints gbc) {
        addFormField(gbc, "백넘버", tfPn, 0);
        addFormField(gbc, "이름", tfName, 1);
        addFormField(gbc, "나이", tfAge, 2);
        addFormField(gbc, "키", tfHeight, 3);
        addFormField(gbc, "몸무게", tfWeight, 4);
        addFormField(gbc, "포지션", cbPosition, 5);
        addFormField(gbc, "부상여부", cbInjury, 6);
        addFormField(gbc, "선발여부", cbRoster, 7);

        //버튼 패널 설정
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnCancel);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    private void addFormField(GridBagConstraints gbc, String labelText, JComponent component, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(component, gbc);

    }

    private void populateFields() {
        tfPn.setText(String.valueOf(currentPlayer.getPn()));
        tfName.setText(currentPlayer.getName());
        tfAge.setText(String.valueOf(currentPlayer.getAge()));
        tfHeight.setText(String.valueOf(currentPlayer.getHeight()));
        tfWeight.setText(String.valueOf(currentPlayer.getWeight()));
        cbPosition.setSelectedItem(currentPlayer.getPosition());

        cbInjury.setSelectedItem("1".equals(currentPlayer.getInjury()) ? "부상중": "없음");
        cbRoster.setSelectedItem("m".equals(currentPlayer.getRoster()) ? "선발":"후보");

        //백넘버는 수정 불가능하게 설정
        //tfPn.setEditable(false);

    }

    private void setupEventListeners() {
        // 수정 버튼 이벤트
        btnUpdate.addActionListener( e -> {
            if (validateInput()) {
                updatePlayerInfo();
            }
        });

        // 취소 버튼 이벤트
        btnCancel.addActionListener(e -> dispose());
    }
    //유효성 검사 메서드
    private boolean validateInput() {
        // 각 필드가 비어있는지 확인
        if (tfPn.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "선수 번호를 입력해주세요.");
            tfPn.requestFocus(); // 해당 필드에 포커스 이동
            return false;
        }

        if (tfName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "선수 이름을 입력해주세요.");
            tfName.requestFocus();
            return false;
        }

        if (tfAge.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "나이를 입력해주세요.");
            tfAge.requestFocus();
            return false;
        }

        if (tfHeight.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "키를 입력해주세요.");
            tfHeight.requestFocus();
            return false;
        }

        if (tfWeight.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "몸무게를 입력해주세요.");
            tfWeight.requestFocus();
            return false;
        }

        // 콤보박스의 선택 여부 확인
        if (cbPosition.getSelectedItem() == null ||
                cbPosition.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "포지션을 선택해주세요.");
            cbPosition.requestFocus();
            return false;
        }

        if (cbInjury.getSelectedItem() == null ||
                cbInjury.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "부상 여부를 선택해주세요.");
            cbInjury.requestFocus();
            return false;
        }

        if (cbRoster.getSelectedItem() == null ||
                cbRoster.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "선발 여부를 선택해주세요.");
            cbRoster.requestFocus();
            return false;
        }

        // 숫자 필드에 대한 숫자 형식 검사
        try {
            Integer.parseInt(tfAge.getText());
            Integer.parseInt(tfHeight.getText());
            Integer.parseInt(tfWeight.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "나이, 키, 몸무게는 숫자만 입력 가능합니다.");
            return false;
        }

        return true; // 모든 검사를 통과하면 true 반환
    }

    private void updatePlayerInfo() {
        try {
            // DB 연결 초기화
            Util.init();

            // PreparedStatement 생성
            PreparedStatement pstmt = Util.conn.prepareStatement(
                    "UPDATE player SET pn=?, age=?, height=?, weight=?, " +
                            "position=?, injury=?, roster=? WHERE name=?"
            );

            // 파라미터 설정
            pstmt.setString(1, tfPn.getText());
            pstmt.setInt(2, Integer.parseInt(tfAge.getText()));
            pstmt.setInt(3, Integer.parseInt(tfHeight.getText()));
            pstmt.setInt(4, Integer.parseInt(tfWeight.getText()));
            pstmt.setString(5, (String) cbPosition.getSelectedItem());
            pstmt.setString(6, cbInjury.getSelectedItem().equals("부상중") ? "1" : "0");
            pstmt.setString(7, cbRoster.getSelectedItem().equals("선발") ? "m" : "s");
            pstmt.setString(8, tfName.getText());



            // 쿼리 실행
            int result = pstmt.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "선수 정보가 성공적으로 수정되었습니다.");
                // 부모 폼의 테이블 데이터 갱신
                ((PlayerList)parentForm.getTable().getModel()).setData();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "선수 정보 수정에 실패했습니다.");
            }

            // 리소스 정리
            pstmt.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "데이터베이스 오류: " + ex.getMessage());
            ex.printStackTrace();
        }
    }








}
