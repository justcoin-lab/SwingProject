package player;

import javax.swing.*;
import java.awt.event.*;

public class PlayerForm extends JFrame implements ActionListener, MouseListener, KeyListener {
    private JLabel pn;
    private JLabel name;
    private JLabel age;
    private JLabel height;
    private JLabel weight;
    private JLabel position;
    private JLabel injury;
    private JLabel roster;
    private JLabel comment;


    public PlayerForm(String title) {
        //----메인화면 기본틀 시작-----
        setTitle(title);
        setSize(1000,600);
        setLocation(570,240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        //----메인화면 기본틀 끝-----

        //--선수리스트 라벨 시작-----
        JLabel playerList = new JLabel("선수 리스트");
        playerList.setBounds(100,70,80,20);
        add(playerList);
        //--선수리스트 라벨 끝-----

        //메인화면 맨위 버튼 시작-------

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
