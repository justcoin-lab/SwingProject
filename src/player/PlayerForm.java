package player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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
    private JLabel comment;*/

    //상위 매뉴 버튼 시작---------------------
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
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

        JPanel infoListPlayer = new JPanel(); //선수리스트정보 패널
        infoListPlayer.setBackground(new Color(0xEDEDED));
        infoListPlayer.setBounds(10, 80, 300, 450);
        infoListPlayer.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,1)));

        JPanel infoPlayerPanel = new JPanel(); //선수정보 패널
        infoPlayerPanel.setBackground(new Color(0xEDEDED));
        infoPlayerPanel.setBounds(340, 80, 500, 450);
        infoPlayerPanel.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,1)));

        add(panCenter);
        add(infoListPlayer);
        add(infoPlayerPanel);
        //화면별 구분선 끝---------------------------


        //--선수리스트 라벨 시작---------------------
        JLabel playerList = new JLabel("선수 리스트");
        playerList.setBounds(30, 55, 80, 20);
        playerList.setOpaque(true);
        playerList.setBackground(new Color(0xEDEDED));
        add(playerList);
        //--선수리스트 라벨 끝-----

        //--선수정보 라벨 시작---------------------
        JLabel playerInfo = new JLabel("선수 정보");
        playerInfo.setBounds(530, 55, 80, 20);
        playerList.setOpaque(true);
        playerList.setBackground(new Color(0xEDEDED));
        add(playerInfo);
        //--선수정보 라벨 끝---------------------


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

        //사이드 버튼 끝-----------

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
