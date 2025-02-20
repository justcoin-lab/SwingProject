package match;

import db.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class ResultPopup extends JDialog implements ActionListener {

    private JComboBox<Integer> yComboBox, mComboBox, dComboBox;
    private JComboBox<String> mvpComboBox;
    private JTextField matchNameField, opposingField, ourScoreField, oppScoreField, yellowCardField, redCardField;
    private JButton confirm, cancel;
    private String oppoForEdit, mvpForEdit;
    private boolean isEditMode;

    // oppForEdit, mvpForEdit 은 modify 키 값
    public ResultPopup(JFrame jFrame, boolean isEditMode, String oppoForEdit, String mvpForEdit) {
        super(jFrame, isEditMode? "기록 수정" : "경기 입력", true);
        this.isEditMode = isEditMode;
        this.oppoForEdit = oppoForEdit;
        this.mvpForEdit = mvpForEdit;
        setSize(400, 350);
        setLocationRelativeTo(jFrame);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("경기 날짜 : "), gbc);

        // year JComboBox
        yComboBox = new JComboBox<>();
        for (int i = 2000; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
            yComboBox.addItem(i);
        }

        // month JComboBox
        mComboBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            mComboBox.addItem(i);
        }

        // day JComboBox
        dComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dComboBox.addItem(i);
        }
        // 년도 입력 JComboBox 위치
        gbc.gridx = 1;
        add(yComboBox, gbc);
        gbc.gridx = 2;
        add(mComboBox, gbc);
        gbc.gridx = 3;
        add(dComboBox, gbc);
        
        // input title, field 생성 및 배치
        String[] lbl = {"경기 이름 : ", "상대팀 : ", "득점 : ", "실점 : ", "경고 : ", "퇴장 : ", "MVP 선수 이름 : "};
        JTextField[] jField = {matchNameField, opposingField, ourScoreField, oppScoreField, yellowCardField, redCardField};

        for (int i = 0; i < lbl.length - 1; i++) { // mvpComboBox 제외
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            add(new JLabel(lbl[i]), gbc);

            jField[i] = new JTextField(15);
            gbc.gridx = 1;
            gbc.gridwidth = 3;
            add(jField[i], gbc);
            gbc.gridwidth = 1;
        }

        // mvp JComboBox
        gbc.gridx = 0;
        gbc.gridy = lbl.length;
        add(new JLabel(lbl[lbl.length - 1]), gbc);

        mvpComboBox = new JComboBox<>();
        addMvpList();
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        add(mvpComboBox, gbc);
        gbc.gridwidth = 1;

        matchNameField = jField[0];
        opposingField = jField[1];
        ourScoreField = jField[2];
        oppScoreField = jField[3];
        yellowCardField = jField[4];
        redCardField = jField[5];

        // 입력, 수정 스위칭 확인
        if (isEditMode) {
            loadMatchData();
        }

        confirm = new JButton("확인");
        confirm.addActionListener(this);
        cancel = new JButton("취소");
        cancel.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy = lbl.length + 1;
        add(confirm, gbc);

        gbc.gridx = 3;
        add(cancel, gbc);
    }

    // 선수 이름 mvpComboBox 추가
    private void addMvpList() {
        try {
            Util.init();
            ResultSet rs = Util.getResult("SELECT name FROM player");

            while (rs.next()) {
                String name = rs.getString("name");
                mvpComboBox.addItem(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "선수 목록을 불러오는 데 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 수정 모드일 때 기존 데이터 불러오기
    private void loadMatchData() {
        try {
            Util.init();
            ResultSet rs = Util.getResult("select * from match where opposing = '" + oppoForEdit + "' AND mvp = '" + mvpForEdit + "'");

            if (rs.next()) {
                String matchDate = rs.getString("matchDate");
                String[] dateParts = matchDate.split("-"); // '-' 기준으로 matchDate year, month, day 구분 저장
                yComboBox.setSelectedItem(Integer.parseInt(dateParts[0]));
                mComboBox.setSelectedItem(Integer.parseInt(dateParts[1]));
                // dataParts[2] -> day hh:mm:ss 이므로 공백을 기준으로 첫번째 데이터(day) set
                dComboBox.setSelectedItem(Integer.parseInt(new String(dateParts[2]).split(" ")[0]));

                matchNameField.setText(rs.getString("matchName"));
                opposingField.setText(rs.getString("opposing"));
                ourScoreField.setText(String.valueOf(rs.getInt("ourScore")));
                oppScoreField.setText(String.valueOf(rs.getInt("oppScore")));
                yellowCardField.setText(String.valueOf(rs.getInt("yellowCard")));
                redCardField.setText(String.valueOf(rs.getInt("redCard")));
                String mvp = rs.getString("mvp");
                mvpComboBox.setSelectedItem(mvp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "경기 데이터를 불러오는 데 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 입력받은 데이터를 변환작업하여 MatchDto 저장 (입력, 수정 스위칭기능 : isEditMode)
    private void saveMatchResult() {
        try {
            Util.init();

            int year = (int) yComboBox.getSelectedItem();
            int month = (int) mComboBox.getSelectedItem();
            int day = (int) dComboBox.getSelectedItem();
            String matchDateStr = String.format("%d-%02d-%02d", year, month, day); // yyyy-MM-dd 형식
            java.sql.Date strToDate = java.sql.Date.valueOf(matchDateStr); // cast string -> sql.date

            String matchName = matchNameField.getText();
            String opposing = opposingField.getText();
            int ourScore = Integer.parseInt(ourScoreField.getText());
            int oppScore = Integer.parseInt(oppScoreField.getText());
            int yellowCard = Integer.parseInt(yellowCardField.getText());
            int redCard = Integer.parseInt(redCardField.getText());
            String mvp = (String) mvpComboBox.getSelectedItem();

            String sql = isEditMode ?  // 수정 모드 update
                    "update match set matchName = '" + matchName + "', matchDate = '" + strToDate + "', " +
                            "opposing = '" + opposing + "', ourScore = " + ourScore + ", oppScore = " + oppScore + ", " +
                            "yellowCard = " + yellowCard + ", redCard = " + redCard + ", mvp = '" + mvp + "' " +
                            "where opposing = '" + oppoForEdit + "' and mvp = '" + mvpForEdit + "'"
                    :  // 입력 모드 insert
                    "insert into match (matchName, matchDate, opposing, ourScore, oppScore, yellowCard, redCard, mvp) " +
                            "values ('" + matchName + "', '" + strToDate + "', '" + opposing + "', " +
                            ourScore + ", " + oppScore + ", " + yellowCard + ", " + redCard + ", '" + mvp + "')";

            Util.executeSql(sql);

            JOptionPane.showMessageDialog(this, "경기 기록이 " + (isEditMode ? "수정" : "저장") + "되었습니다.");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "입력값을 확인해주세요", "실패", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 확인 취소 버튼 액션 리스너 설정
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirm) {
            saveMatchResult();
            dispose();
        } else if(e.getSource() == cancel) {
            dispose();
        }
    }
}
