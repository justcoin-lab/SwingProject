package player;

import main.MainFrame;
import match.ResultForm;
import schedule.ScheduleForm;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class PlayerForm extends JFrame implements ActionListener, MouseListener, KeyListener {
    /*private JLabel pn;
    private JLabel name;
    private JLabel age;
    private JLabel height;
    private JLabel weight;
    private JLabel position;
    private JLabel injury;
    private JLabel roster;
    */

    //상위 매뉴 버튼 시작---------------------
    public JButton btn1;
    public JButton btn2;
    public JButton btn3;
    public JButton btn4;
    public JButton btn5;
    public JButton btn6;
    public JButton btn7;
    //상위 매뉴 버튼 시작---------------------


    public PlayerForm(String title) {
        //----메인화면 기본틀 시작-----
        setTitle(title);
        setSize(1000,600);
        setLocation(570,240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        //----메인화면 기본틀 끝-----

        //화면별 구분선 시작

        JPanel panCenter = new JPanel();
        panCenter.setBackground(new Color(0xEDEDED));
        panCenter.setOpaque(false);
        panCenter.setBounds(0, 40, 1000, 600);

        /*JPanel infoListPlayer = new JPanel(); //선수리스트정보 패널
        infoListPlayer.setBackground(new Color(0xEDEDED));
        infoListPlayer.setBounds(10, 150, 400, 400);
        infoListPlayer.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,1)));*/

        JPanel infoPlayerPanel = new JPanel(); //선수정보 패널
        infoPlayerPanel.setBackground(new Color(0xEDEDED));
        infoPlayerPanel.setBounds(470, 150, 400, 400);
        infoPlayerPanel.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,1)));

        add(panCenter);
        // add(infoListPlayer);
        add(infoPlayerPanel);
        //화면별 구분선 끝---------------------------


        //--선수리스트 라벨 시작---------------------
        JLabel playerList = new JLabel("선수 리스트 검색");
        playerList.setBounds(60, 120, 100, 20);
        playerList.setOpaque(true);
        playerList.setBackground(new Color(0xEDEDED));
        add(playerList);
        //--선수리스트 라벨 끝-----

        //--선수정보 라벨 시작---------------------
        JLabel playerInfo = new JLabel("선수 정보");
        playerInfo.setBounds(630, 120, 80, 20);
        playerList.setOpaque(true);
        playerList.setBackground(new Color(0xEDEDED));
        add(playerInfo);
        //--선수정보 라벨 끝---------------------

        //선수 리스트 검색창

        JTextField playerSearch = new JTextField();
        playerSearch.setBounds(160, 120, 170, 20);
        playerSearch.addKeyListener(this);
        add(playerSearch);

        //선수 리스트 검색창 끝



        //메인화면 맨위 버튼 시작-------

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
        btn4.addActionListener(this);

        btn5 = new JButton("구단 일정");
        btn5.setBounds(790, 10, 185, 50);
        btn5.setBackground(new Color(255, 228, 196));
        btn5.setBorderPainted(false);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);

        //메인화면 맨위 버튼 끝-------

        //사이드 버튼 시작-----------
        btn6 = new JButton("업데이트");
        btn6.setBounds(880, 200, 90, 50);
        btn6.setBackground(new Color(0x476EC5));
        btn6.setBorderPainted(false);

        btn7 = new JButton("방출");
        btn7.setBounds(880, 400, 90, 50);
        btn7.setBackground(new Color(0x476EC5));
        btn7.setBorderPainted(false);

        add(btn6);
        add(btn7);

        //사이드 버튼 끝-----------


        //JTable 시작--------------------
        PlayerList model = new PlayerList();
        model.setData();

        JTable table = new JTable(model);
        table.addMouseListener(this);
        table.setRowHeight(30);
        table.setFont(new Font("명조", Font.BOLD, 10));
        table.setAlignmentX(0);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 자동 크기 조정 비활성화 (가로 스크롤 활성화)
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // 선수 내용 가운데 정렬
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //컬럼 헤드 설정
        table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 10));

        // 헤더 가운데 정렬
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        //컬럼 너비 고정
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
        table.getColumnModel().getColumn(6).setPreferredWidth(30);
        table.getColumnModel().getColumn(7).setPreferredWidth(30);

        JScrollPane jscrollPane = new JScrollPane(table);
        jscrollPane.setBounds(60, 150, 400, 400);
        jscrollPane.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,1)));

// 스크롤 정책 설정
        jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

// 테이블 헤더 설정
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        add(jscrollPane);

// 컴포넌트 갱신
      /*  SwingUtilities.invokeLater(() -> {
            model.fireTableDataChanged();
            table.repaint();
            jscrollPane.revalidate();
            jscrollPane.repaint();
        });*/

        setVisible(true);
        //JTable 끝--------------------

        // 여기까지 선수리스트 폼 디자인 끝-----------------------------------
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj == btn1) {
            new MainFrame("national-team management");
            dispose();
        } else if (obj == btn2) {
            new PlayerForm("national-team management");
            dispose();
        } else if (obj == btn3) {
            // new InputForm();
        } else if (obj == btn4) {
            new ResultForm("national-team management");
            dispose();
        } else if (obj == btn5) {
            new ScheduleForm("national-team management");
            dispose();
        }
    }
}
