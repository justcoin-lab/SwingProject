package match;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ResultForm extends JFrame implements ActionListener{
	public JPanel mainSpace;
	public JPanel listInfo;
	public JPanel mvpInfo;
	public JButton btn1;
	public JButton btn2;
	public JButton btn3;
	public JButton btn4;
	public JButton btn5;

	public ResultForm(String title) {
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

		// 경기 결과 리스트(Panel)
		listInfo = new JPanel();
		listInfo.setBackground(new Color(255, 255, 240));
		listInfo.setBounds(20, 80, 540, 460);
		listInfo.setBorder(new TitledBorder(new LineBorder(new Color(105, 105, 105))));

		// 리스트 클릭 시 해당 경기 MVP 프로필 정보(Panel)
		mvpInfo = new JPanel();
		mvpInfo.setBackground(new Color(255, 255, 240));
		mvpInfo.setBounds(580, 80, 280, 460);
		mvpInfo.setBorder(new TitledBorder(new LineBorder(new Color(105, 105, 105))));

		// default button(상단 탭 버튼)
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
		// 여기까지

		// 경기 결과 리스트 title
		JLabel mainlbl = new JLabel("최근 경기");
		mainlbl.setFont(new Font("바탕", Font.PLAIN, 13));
		mainlbl.setBounds(250, 70, 60, 15);
		mainlbl.setOpaque(true);
		mainlbl.setBackground(new Color(255, 255, 240));
		add(mainlbl);

		List<MatchDto> matchResult = ResultList.getMatchResultList();

		// 컬럼 이름 지정
		String[] columnNames = {"매치", "날짜", "상대팀", "득점", "실점", "경고", "퇴장", "MVP"};

		Object[][] data = new Object[matchResult.size()][8];

		// 날짜 데이터 포맷 설정
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

		// row 마다 값 입력 data[i : row][columnNames] <- List<MatchDto> matchResult.get(i)
		for (int i = 0; i < matchResult.size(); i++) {
			MatchDto match = matchResult.get(i);
			data[i][0] = match.getMatchName();
			data[i][1] = dataFormat.format(match.getMatchDate()); // 날짜 데이터 yyyy-MM-dd 로 포맷
			data[i][2] = match.getOpposing();
			data[i][3] = match.getOurScore();
			data[i][4] = match.getOppScore();
			data[i][5] = match.getYellowCard();
			data[i][6] = match.getRedCard();
			data[i][7] = match.getMvp();
		}

		JTable table = new JTable(data, columnNames);

		// 테이블 셀 편집 방지
		table.setDefaultEditor(Object.class, null);

		// 각 열에 대해 가운데 정렬 설정
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬

		// 모든 열 가운데 정렬, 데이터에 맞춰 각 열의 크기 조정
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			TableColumn column = table.getColumnModel().getColumn(i);
			int width = 0;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, i);
				Component comp = table.prepareRenderer(renderer, row, i);
				width = Math.max(comp.getPreferredSize().width, width);
			}
			column.setPreferredWidth(width + 10); // 여백을 주기 위해 +10
		}

		JScrollPane jsp1 = new JScrollPane(table);
		jsp1.setBounds(30, 90, 520, 440);
		add(jsp1);

		// 경기 결과 리스트 title
		JLabel mvplbl = new JLabel("경기 MVP");
		mvplbl.setFont(new Font("바탕", Font.PLAIN, 14));
		mvplbl.setBounds(690, 70, 70, 15);
		mvplbl.setOpaque(true);
		mvplbl.setBackground(new Color(255, 255, 240));
		add(mvplbl);



		add(listInfo);
		add(mvpInfo);
		add(mainSpace);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
