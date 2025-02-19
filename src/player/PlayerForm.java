package player;

import db.Util;
import main.MainFrame;
import match.ResultForm;
import schedule.ScheduleForm;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.awt.Color.*;
import static java.awt.SystemColor.text;

public class PlayerForm extends JFrame implements ActionListener, MouseListener, KeyListener {
    private JLabel pn;
    private JLabel name;
    private JLabel age;
    private JLabel height;
    private JLabel weight;
    private JLabel position;
    private JLabel injury;
    private JLabel roster;
    private JLabel playerImage;

    private JLabel playerDetailsPanel;

    //상위 매뉴 버튼 시작---------------------
    public JButton btn1;
    public JButton btn2;
    public JButton btn3;
    public JButton btn4;
    public JButton btn5;
    public JButton updateBtn;
    public JButton deleteBtn;

    private JTable table;
    JTextField playerSearch;
    public JPanel mainSpace;
    //상위 매뉴 버튼 시작---------------------

    public PlayerForm(String title) {
        //----메인화면 기본틀 시작-----
        setTitle(title);
        setSize(1000,600);
        setLocation(460,240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); //크기 변경 x
        setLayout(null); //절대값으로 위치 설정

        Container ct = getContentPane();  //패널의 배경색을 설정하기 위해 사용
        ct.setBackground(new Color(250, 240, 230));

        mainSpace = new JPanel();
        mainSpace.setBackground(new Color(255, 255, 240));
        mainSpace.setBounds(0, 60, 1000, 540);

        btn1 = new JButton("대한민국 축구단");
        btn1.setBounds(10, 10, 185, 50);
        btn1.setBackground(new Color(255, 228, 196));
        btn1.setBorderPainted(false);
        btn1.addActionListener(this);

        btn2 = new JButton("대표팀 선수 목록");
        btn2.setBounds(205, 10, 185, 50);
        btn2.setBackground(new Color(255, 228, 196));
        btn2.setBorderPainted(false);
        btn2.addActionListener(this);

        btn3 = new JButton("대표팀 선수 입력");
        btn3.setBounds(400, 10, 185, 50);
        btn3.setBackground(new Color(255, 228, 196));
        btn3.setBorderPainted(false);
        btn3.addActionListener(this);

        btn4 = new JButton("대표팀 경기 결과");
        btn4.setBounds(595, 10, 185, 50);
        btn4.setBackground(new Color(255, 228, 196));
        btn4.setBorderPainted(false);
        btn4.addActionListener(this);

        btn5 = new JButton("대표팀 일정");
        btn5.setBounds(790, 10, 185, 50);
        btn5.setBackground(new Color(255, 228, 196));
        btn5.setBorderPainted(false);
        btn5.addActionListener(this);

        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);

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
        infoPlayerPanel.setBorder(new TitledBorder(new LineBorder(LIGHT_GRAY,1)));

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

        playerSearch = new JTextField();
        playerSearch.setBounds(160, 120, 170, 20);
        playerSearch.addKeyListener(this);
        add(playerSearch);

        //선수 리스트 검색창 끝

        //메인화면 맨위 버튼 시작-------

        /*btn1 = new JButton("대한민국 축구단");
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
*/
        //메인화면 맨위 버튼 끝-------

        //사이드 버튼 시작-----------
        updateBtn = new JButton("업데이트");
        updateBtn.setBounds(880, 200, 90, 50);
        updateBtn.setBackground(new Color(0x476EC5));
        updateBtn.setBorderPainted(false);
        updateBtn.addActionListener(this); // ★ 이 코드 추가

        /*btn6 = new JButton("업데이트");
        btn6.setBounds(880, 200, 90, 50);
        btn6.setBackground(new Color(0x476EC5));
        btn6.setBorderPainted(false);

        add(btn6);*/

        deleteBtn = new JButton("방출");
        deleteBtn.setBounds(880, 400, 90, 50);
        deleteBtn.setBackground(new Color(0x476EC5));
        deleteBtn.setBorderPainted(false);
        deleteBtn.addActionListener(this); // ★ 이 코드 추가
        add(updateBtn);
        add(deleteBtn);

        //사이드 버튼 끝-----------

        //선수 상세 정보 패널 시작
        setupPlayerDetailsPanel(infoPlayerPanel);



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
        jscrollPane.setBorder(new TitledBorder(new LineBorder(LIGHT_GRAY,1)));

// 스크롤 정책 설정
        jscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

// 테이블 헤더 설정
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        add(jscrollPane);

        setVisible(true);
        this.table = table;

        //JTable 끝--------------------

        // 여기까지 선수리스트 폼 디자인 끝-----------------------------------

    }



    private void setupPlayerDetailsPanel(JPanel panel) {
        panel.setLayout(null); //절대 위치 사용을 위한 null 레이아웃 설정

        //이미지 라벨 초기화
        playerImage = new JLabel();
        playerImage.setBounds(20,30,150,180);
        //playerImage.setBorder(BorderFactory.createBevelBorder(new Color(GRAY));
        playerImage.setHorizontalAlignment(JLabel.CENTER);
        playerImage.setText("선수 이미지");
        panel.add(playerImage);

        //선수 정보 라벨 초기화
        int labelX = 250;
        int startY = 30;
        int labelHeight = 25;
        int gap = 10;

        //각 정보 라벨 생성 및 위치 설정
        pn = createInfoLabel("번호: ", labelX, startY);
        name = createInfoLabel("이름: ", labelX, startY + (labelHeight + gap));
        age = createInfoLabel("나이: ", labelX, startY + 2 * (labelHeight + gap));
        height = createInfoLabel("키: ", labelX, startY + 3 * (labelHeight + gap));
        weight = createInfoLabel("몸무게: ", labelX, startY + 4 * (labelHeight + gap));
        position = createInfoLabel("포지션: ", labelX, startY + 5 * (labelHeight + gap));
        injury = createInfoLabel("부상여부: ", labelX, startY + 6 * (labelHeight + gap));
        roster = createInfoLabel("선발여부: ", labelX, startY + 7 * (labelHeight + gap));

        panel.add(pn);
        panel.add(name);
        panel.add(age);
        panel.add(height);
        panel.add(weight);
        panel.add(position);
        panel.add(injury);
        panel.add(roster);

    }

    private JLabel createInfoLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 180, 25);
        label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        return label;
    }

    private void updatePlayerDetails(int row) {
        //선수 이미지 업데이트
        //실제 이미지 파일이 있을 경우:
        String playerName = (String) table.getValueAt(row, 1); // 선수 이름 가져오기
        try {
            // 절대 경로 사용
            String projectPath = System.getProperty("user.dir");
            String imagePath = projectPath + File.separator + "images" + File.separator + playerName + ".jpg";
            File imageFile = new File(imagePath);

            if (imageFile.exists()) {
                System.out.println("이미지 파일 찾음: " + imagePath); // 디버깅용

                BufferedImage originalImage = ImageIO.read(imageFile);
                if (originalImage != null) {
                    // 이미지 크기 조절
                    Image scaledImage = originalImage.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaledImage);

                    playerImage.setIcon(icon);
                    playerImage.setText(""); // 텍스트 제거
                    System.out.println("이미지 로드 성공!");
                } else {
                    System.out.println("이미지 파일을 읽을 수 없습니다.");
                    playerImage.setIcon(null);
                    playerImage.setText("이미지 로드 실패");
                }
            } else {
                System.out.println("이미지 파일이 존재하지 않습니다: " + imagePath);
                playerImage.setIcon(null);
                playerImage.setText("이미지 없음");
            }
        } catch (Exception e) {
            e.printStackTrace(); // 상세한 에러 정보 출력
            System.out.println("이미지 로드 중 에러 발생: " + e.getMessage());
            playerImage.setIcon(null);
            playerImage.setText("이미지 로드 에러");
        }

        // 선수 정보 라벨 업데이트
        pn.setText("번호: " + table.getValueAt(row, 0));
        name.setText("이름: " + table.getValueAt(row, 1));
        age.setText("나이: " + table.getValueAt(row, 2) + "세");
        height.setText("키: " + table.getValueAt(row, 3) + "cm");
        weight.setText("몸무게: " + table.getValueAt(row, 4) + "kg");
        position.setText("포지션: " + table.getValueAt(row, 5));
        injury.setText("부상여부: " + table.getValueAt(row, 6));
        roster.setText("선발여부: " + table.getValueAt(row, 7));
    }

    public JTable getTable() {
        return table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateBtn) {  // e.getSource()는 이벤트 발생 객체를 가져옴
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "수정할 선수를 선택해주세요.");
                return;
            }

            // 선택된 선수 정보 가져오기
            int pn = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            String name = table.getValueAt(selectedRow, 1).toString();
            int age = Integer.parseInt(table.getValueAt(selectedRow, 2).toString());
            int height = Integer.parseInt(table.getValueAt(selectedRow, 3).toString());
            int weight = Integer.parseInt(table.getValueAt(selectedRow, 4).toString());
            String position = table.getValueAt(selectedRow, 5).toString();
            String injury = table.getValueAt(selectedRow, 6).toString();
            String roster = table.getValueAt(selectedRow, 7).toString();

            // PlayerDto 객체 생성
            PlayerDto player = new PlayerDto(pn, name, age, height, weight, position, injury, roster);

            // PlayerUpdateDialog 창 열기 (PlayerForm을 부모 창으로 전달)
            PlayerUpdateDialog updateForm = new PlayerUpdateDialog(player, this);
            updateForm.setVisible(true);
        } else if(e.getSource() == deleteBtn) { //방출버튼
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "방출할 선수를 선택해주세요");
                return;
            }
            //선수 정보 가져오기

            String name = table.getValueAt(selectedRow, 1).toString();

            // confirmation dialog
            int result = JOptionPane.showConfirmDialog(this,
                    name + "선수를 방출하시겠습니까?", "선수방출",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if(result == JOptionPane.YES_OPTION) {
                //선수 데이타 삭제
                deletePlayer(name);

                //테이블 새로고침
                ((PlayerList)table.getModel()).setData();

                //  선수세부사항 클리어
                clearPlayerDetails();

                JOptionPane.showMessageDialog(this,
                        name + "선수가 방출되었습니다",
                        "방출완료",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(e.getSource() == btn1) {
            new MainFrame("대한민국 축구 국가대표팀");
            dispose();
        } else if (e.getSource() == btn2) {
            new PlayerForm("대한민국 축구 국가대표팀");
            dispose();
        } else if (e.getSource() == btn3) {
            new PlayerInputForm("대한민국 축구 국가대표팀");
            dispose();
        } else if (e.getSource() == btn4) {
            new ResultForm("대한민국 축구 국가대표팀");
            dispose();
        } else if (e.getSource() == btn5) {
            new ScheduleForm("대한민국 축구 국가대표팀");
            dispose();
        }
    }

    private boolean deletePlayer(String name) {
        try {
            //디비 연결 초기화
            Util.init();

            //선수 이름 기준 삭제 쿼리 생성 및 실행
            String sql = "DELETE FROM player WHERE name = '" + name + "'";
            Util.executeSql(sql);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "선수 방출중 오류가 발생했습니다.\n" +
                    e.getMessage(),
                    "오류",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void clearPlayerDetails() {

        //세부선수 정보 클리어
        pn.setText("번호");
        name.setText("선수이름");
        age.setText("나이");
        height.setText("키");
        weight.setText("몸무게");
        position.setText("포지션");
        injury.setText("부상여부");
        roster.setText("선발여부");

        //선수 이미지 클리어
        playerImage.setText(null);
        playerImage.setText("선수 이미지");
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getSource() == playerSearch) {
            String searchText = playerSearch.getText().toLowerCase();
            filterTable(searchText);
        }
    }

    private void filterTable(String searchText) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        if(searchText.length() == 0) {
            sorter.setRowFilter(null);
        }else {
            try {
                //이름열을 기준으로 필터링
                RowFilter<TableModel, Object> rf = RowFilter.regexFilter("(?i)" + searchText, 1);
                sorter.setRowFilter(rf);
            }catch (java.util.regex.PatternSyntaxException e) {
                return;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table) {
            int row = table.getSelectedRow();
            if (row != -1) {
                updatePlayerDetails(row);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
