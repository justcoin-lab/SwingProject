package match;

import db.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ResultInputDialog extends JDialog implements ActionListener{
    private JComboBox<Integer> yComboBox, mComboBox, dComboBox;
    private JTextField matchNameField, opposingField, ourScoreField, oppScoreField, yellowCardField, redCardField, mvpField;
    private JButton confirm, cancel;

    public ResultInputDialog(JFrame jf) {
        super(jf, "매치데이터 ", true);
        setSize(400, 350);
        setLocationRelativeTo(jf);
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

        gbc.gridx = 1;
        add(yComboBox, gbc);
        gbc.gridx = 2;
        add(mComboBox, gbc);
        gbc.gridx = 3;
        add(dComboBox, gbc);

        String[] lbl = {"경기 이름 : ", "상대팀 : ", "득점 : ", "실점 : ", "경고 : ", "퇴장 : ", "MVP 선수 이름 : "};
        JTextField[] jField = {matchNameField, opposingField, ourScoreField, oppScoreField, yellowCardField, redCardField, mvpField};

        for (int i = 0; i < lbl.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            add(new JLabel(lbl[i]), gbc);

            jField[i] = new JTextField(15);
            gbc.gridx = 1;
            gbc.gridwidth = 3;
            add(jField[i], gbc);
            gbc.gridwidth = 1;
        }

        matchNameField = jField[0];
        opposingField = jField[1];
        ourScoreField = jField[2];
        oppScoreField = jField[3];
        yellowCardField = jField[4];
        redCardField = jField[5];
        mvpField = jField[6];

        confirm = new JButton("확인");
        confirm.addActionListener(this);
        cancel = new JButton("취소");
        cancel.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy = lbl.length + 1;
        gbc.gridwidth = 2;
        add(confirm, gbc);

        gbc.gridx = 3;
        add(cancel, gbc);
    }

    // 확인 취소 버튼 액션 리스너 설정
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == confirm) {
            saveMatchResult();
            dispose();
        } else if(obj == cancel) {
            dispose();
        }
    }

    // 입력받은 데이터를 변환작업하여 MatchDto에 저장
    private void saveMatchResult() {
        try {
            Util.init();

            int year = (int) yComboBox.getSelectedItem();
            int month = (int) mComboBox.getSelectedItem();
            int day = (int) dComboBox.getSelectedItem();
            String matchDateStr = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day); // yyyy-MM-dd 형식

            String matchName = matchNameField.getText();
            String opposing = opposingField.getText();
            int ourScore = Integer.parseInt(ourScoreField.getText());
            int oppScore = Integer.parseInt(oppScoreField.getText());
            int yellowCard = Integer.parseInt(yellowCardField.getText());
            int redCard = Integer.parseInt(redCardField.getText());
            String mvp = mvpField.getText();

            String sql = "INSERT INTO match (matchName, matchDate, opposing, ourScore, oppScore, yellowCard, redCard, mvp) " +
                    "VALUES ('" + matchName + "', '" + matchDateStr + "', '" + opposing + "', " +
                    ourScore + ", " + oppScore + ", " + yellowCard + ", " + redCard + ", '" + mvp + "')";
            Util.executeSql(sql);

            JOptionPane.showMessageDialog(this, "경기 기록이 저장되었습니다.");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "입력값을 확인하시요", "실패", JOptionPane.ERROR_MESSAGE);
        }
    }
}


