package schedule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import db.Util;
import main.MainFrame;
import match.ResultForm;
import player.PlayerForm;

public class ScheduleForm extends JFrame implements ActionListener {
    public JPanel mainSpace;
    public JButton btn1, btn2, btn3, btn4, btn5;
    private JPanel schedulePanel, calendarPanel;
    private JLabel monthLabel;
    private JButton[] dateButtons;
    private int currentYear, currentMonth;
    private JTable table;
    private javax.swing.table.DefaultTableModel tableModel;

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
        mainSpace.setLayout(null);

        btn1 = new JButton("대한민국 축구단");
        btn1.setBounds(10, 10, 185, 50);
        btn1.setBackground(new Color(255, 228, 196));

        btn2 = new JButton("구단 선수 목록");
        btn2.setBounds(205, 10, 185, 50);
        btn2.setBackground(new Color(255, 228, 196));
        btn2.addActionListener(this);

        btn3 = new JButton("선수 입력");
        btn3.setBounds(400, 10, 185, 50);
        btn3.setBackground(new Color(255, 228, 196));

        btn4 = new JButton("구단 최근 경기");
        btn4.setBounds(595, 10, 185, 50);
        btn4.setBackground(new Color(255, 228, 196));
        btn4.addActionListener(this);

        btn5 = new JButton("구단 일정");
        btn5.setBounds(790, 10, 185, 50);
        btn5.setBackground(new Color(255, 228, 196));

        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(mainSpace);

        // 경기 일정 패널
        schedulePanel = new JPanel();
        schedulePanel.setLayout(new BorderLayout());
        schedulePanel.setBounds(35, 20, 490, 460);
        mainSpace.add(schedulePanel);

        String[] headings = {"경기이름", "경기날짜", "경기장소", "상대팀"};
        tableModel = new javax.swing.table.DefaultTableModel(new Object[][]{}, headings);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(450, 460));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        schedulePanel.add(scrollPane, BorderLayout.CENTER);

        // 캘린더 패널 생성 및 추가
        calendarPanel = new JPanel();
        calendarPanel.setBounds(550, 95, 400, 300);
        mainSpace.add(calendarPanel);

        //  ScheduleForm 클래스 내부
        JButton addMatchButton;


        addMatchButton = new JButton("경기 추가");
        addMatchButton.setBounds(550, 410, 400, 40); // 위치 조정
        addMatchButton.setBackground(Color.LIGHT_GRAY); // 연한 초록색
        addMatchButton.addActionListener(e -> openAddMatchDialog()); // 버튼 클릭 시 팝업 열기
        mainSpace.add(addMatchButton);



        // 현재 연도와 월 저장
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);

        setupUI();  // UI 설정
        displayMonth(currentYear, currentMonth);  // 현재 월 표시

        loadScheduleData(); // 📌 경기 일정 데이터 불러오기

        setVisible(true);
    }

    public void setupUI() {
        monthLabel = new JLabel();
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthLabel.setFont(new Font("맑은고딕", Font.BOLD, 20));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.setBounds(550, 40, 400, 40); // 캘린더 위쪽 배치

        JButton prevButton = new JButton("<");
        prevButton.setBackground(Color.white);
        prevButton.addActionListener(e -> {
            currentMonth--;
            if (currentMonth < 0) {
                currentMonth = 11;
                currentYear--;
            }
            displayMonth(currentYear, currentMonth);
        });

        JButton nextButton = new JButton(">");
        nextButton.setBackground(Color.white);
        nextButton.addActionListener(e -> {
            currentMonth++;
            if (currentMonth > 11) {
                currentMonth = 0;
                currentYear++;
            }
            displayMonth(currentYear, currentMonth);
        });

        headerPanel.add(prevButton);
        headerPanel.add(monthLabel);
        headerPanel.add(nextButton);

        mainSpace.add(headerPanel); // 헤더 패널 추가

        // 캘린더 패널 설정
        calendarPanel.setLayout(new GridLayout(7, 7, 5, 5));
        String[] daysOfWeek = {"일", "월", "화", "수", "목", "금", "토"};
        for (String day : daysOfWeek) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(new Font("맑은고딕", Font.BOLD, 14));
            calendarPanel.add(label);
        }

        dateButtons = new JButton[42];
        for (int i = 0; i < 42; i++) {
            dateButtons[i] = new JButton();
            dateButtons[i].setEnabled(false);
            dateButtons[i].setBackground(Color.white);
            calendarPanel.add(dateButtons[i]);
        }
    }

    private void displayMonth(int year, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
        monthLabel.setText(sdf.format(new GregorianCalendar(year, month, 1).getTime()));

        Calendar cal = new GregorianCalendar(year, month, 1);
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // 📌 오늘 날짜 가져오기
        Calendar today = Calendar.getInstance();
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int todayDay = today.get(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < 42; i++) {
            if (i < firstDayOfWeek - 1 || i >= firstDayOfWeek - 1 + lastDate) {
                dateButtons[i].setText("");
                dateButtons[i].setEnabled(false);
                dateButtons[i].setBackground(Color.WHITE); // 기본 색상
            } else {
                int day = i - (firstDayOfWeek - 2);
                dateButtons[i].setText(String.valueOf(day));
                dateButtons[i].setEnabled(true);

                // 📌 오늘 날짜인 경우 연한 빨강색으로 표시
                if (year == todayYear && month == todayMonth && day == todayDay) {
                    dateButtons[i].setBackground(new Color(255, 182, 193)); // 연한 빨강
                } else {
                    dateButtons[i].setBackground(Color.WHITE); // 기본 색상
                }
            }
        }
    }
    private void openAddMatchDialog() {
        JDialog dialog = new JDialog(this, "경기 추가", true);
        setLocationRelativeTo(null);
        setResizable(false);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(5, 2, 5, 5));

        // 입력 필드
        JLabel lblMatchName = new JLabel("경기 이름:");
        JTextField txtMatchName = new JTextField();

        JLabel lblMatchDate = new JLabel("경기 날짜(YYYY-MM-DD):");
        Integer[] years = {2025, 2026, 2027};  // 연도 목록
        Integer[] months = new Integer[12]; // 1~12월
        Integer[] days = new Integer[31];   // 1~31일

        for (int i = 0; i < 12; i++) months[i] = i + 1;
        for (int i = 0; i < 31; i++) days[i] = i + 1;

        JComboBox<Integer> comboYear = new JComboBox<>(years);
        JComboBox<Integer> comboMonth = new JComboBox<>(months);
        JComboBox<Integer> comboDay = new JComboBox<>(days);

        JPanel datePanel = new JPanel();
        datePanel.add(comboYear);
        datePanel.add(new JLabel("년"));
        datePanel.add(comboMonth);
        datePanel.add(new JLabel("월"));
        datePanel.add(comboDay);
        datePanel.add(new JLabel("일"));


        JLabel lblOpposing = new JLabel("상대팀:");
        JTextField txtOpposing = new JTextField(20);

        JLabel lblStadium = new JLabel("경기장:");
        JTextField txtStadium = new JTextField(20);

        JButton btnSubmit = new JButton("추가");

        // 버튼 클릭 시 DB 저장
        btnSubmit.addActionListener(e -> {
            String matchName = txtMatchName.getText();
            int year = (Integer) comboYear.getSelectedItem();
            int month = (Integer) comboMonth.getSelectedItem();
            int day = (Integer) comboDay.getSelectedItem();
            String matchDate = String.format("%04d-%02d-%02d", year, month, day);
            String opposing = txtOpposing.getText();
            String stadium = txtStadium.getText();

            // DB에 저장
            addMatchToDatabase(matchName, matchDate, opposing, stadium);
            dialog.dispose(); // 팝업 닫기
            loadScheduleData(); // 테이블 새로고침
        });

        dialog.add(lblMatchName); dialog.add(txtMatchName);
        dialog.add(lblMatchDate); dialog.add(datePanel);
        dialog.add(lblOpposing); dialog.add(txtOpposing);
        dialog.add(lblStadium); dialog.add(txtStadium);
        dialog.add(new JLabel()); // 빈 공간
        dialog.add(btnSubmit);

        dialog.setVisible(true);
    }
    private void addMatchToDatabase(String matchName, String matchDate, String opposing, String stadium) {
        // ✅ DB 연결
        Util.init();

        // ✅ SQL 실행 로그 출력
        System.out.println("경기 추가: " + matchName + ", " + matchDate + ", " + opposing + ", " + stadium);

        // ✅ SQL 작성
        String query = "INSERT INTO schedule (matchName, matchDate, opposing, stadium) VALUES ('"+ matchName + "','" + matchDate + "','" + opposing + "','" + stadium + "')";
        Util.executeSql(query);

        JOptionPane.showMessageDialog(this, "경기가 추가되었습니다.");
        loadScheduleData();
    }




    public void loadScheduleData() {
        try {
            // ✅ DB 연결 확인
            Util.init();

            // ✅ SQL 실행 전에 로그 출력 (디버깅)
            String query = "SELECT matchName, matchDate, opposing, stadium FROM schedule order by matchDate asc";
            System.out.println("Executing Query: " + query);

            ResultSet rs = Util.getResult(query);

            // ✅ Null 체크 추가
            if (rs == null) {
                System.out.println("ResultSet is NULL. Check DB connection and query.");
                JOptionPane.showMessageDialog(this, "DB 오류: ResultSet이 null입니다.");
                return;
            }

            // ✅ 데이터를 저장할 리스트
            List<Object[]> rowData = new ArrayList<>();
            while (rs.next()) {
                String matchName = rs.getString("matchName");
                String matchDate = rs.getString("matchDate");
                String opposing = rs.getString("opposing");
                String stadium = rs.getString("stadium");

                rowData.add(new Object[]{matchName, matchDate, opposing, stadium});
            }

            // ✅ 기존 데이터 삭제 후 추가
            tableModel.setRowCount(0);
            for (Object[] row : rowData) {
                tableModel.addRow(row);
            }

            // ✅ 테이블 갱신
            tableModel.fireTableDataChanged();
            System.out.println("경기 일정 로드 완료");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "SQL 오류: " + e.getMessage());
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == btn2) {
            new PlayerForm("대한민국 축구 국가대표팀");
            dispose();
        } else if (obj == btn4) {
            new ResultForm("대한민국 축구 국가대표팀");
            dispose();
        } else if (obj == btn5) {
            new ScheduleForm("대한민국 축구 국가대표팀");
            dispose();
        }
    }
}
