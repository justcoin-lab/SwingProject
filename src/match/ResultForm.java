package match;

import db.Util;
import main.MainFrame;
import player.PlayerDto;
import player.PlayerForm;
import player.PlayerInputForm;
import schedule.ScheduleForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ResultForm extends JFrame implements ActionListener {
	private JPanel mainSpace;
	private JPanel listInfo;
	private JPanel mvpInfo;
	private JPanel mvpDataPanel;
	private JTable table;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton resultInsertBtn;
	private JButton resultModifyBtn;
	private JButton resultDeleteBtn;


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

		resultInsertBtn = new JButton("기록 입력");
		resultInsertBtn.setBounds(870, 80, 100, 50);
		resultInsertBtn.setBackground(new Color(255, 228, 196));
		resultInsertBtn.setBorderPainted(false);
		resultInsertBtn.addActionListener(this);

		resultModifyBtn = new JButton("기록 수정");
		resultModifyBtn.setBounds(870, 150, 100, 50);
		resultModifyBtn.setBackground(new Color(255, 228, 196));
		resultModifyBtn.setBorderPainted(false);
		resultModifyBtn.addActionListener(this);

		resultDeleteBtn = new JButton("기록 삭제");
		resultDeleteBtn.setBounds(870, 220, 100, 50);
		resultDeleteBtn.setBackground(new Color(255, 228, 196));
		resultDeleteBtn.setBorderPainted(false);
		resultDeleteBtn.addActionListener(this);

		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);
		add(resultInsertBtn);
		add(resultModifyBtn);
		add(resultDeleteBtn);

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

		table = new JTable(data, columnNames); // 필드 `table`에 할당

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

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow(); // 클릭 행 가져오기
				if(row != -1) { // 선택 확인
					String mvpName = (String) table.getValueAt(row, 7);
					MvpInfo(mvpName);
                }
			}
		});

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

		mvpDataPanel = new JPanel();
		mvpDataPanel.setBounds(600, 100, 240, 420);
		mvpDataPanel.setBackground(new Color(255, 255, 240));
		add(mvpDataPanel);

		add(listInfo);
		add(mvpInfo);
		add(mainSpace);

		setVisible(true);
	}

	// mvpInfo 업데이트
	public void MvpInfo(String mvpName) {
		mvpDataPanel.removeAll(); // 업데이트를 위한 초기화
		mvpDataPanel.setLayout(new GridBagLayout());
		mvpDataPanel.setBackground(new Color(255, 255, 240));
		GridBagConstraints gbc = new GridBagConstraints();

		PlayerDto mvpPlayer = ResultList.getMvpPlayer(mvpName);

		if (mvpPlayer != null) {
			// 선수 사진
			JLabel piclbl = new JLabel();
			ImageIcon playerImage = new ImageIcon("images/" + mvpPlayer.getName() + ".jpg");
			Image img = playerImage.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
			piclbl.setIcon(new ImageIcon(img));

			// 선수 사진과 선수 정보와의 간격 조절
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(10, 0, 20, 0); // 사진 bottom 여백 20px
			gbc.anchor = GridBagConstraints.CENTER;
			mvpDataPanel.add(piclbl, gbc);
			// -- 여기까지

			// 선수 정보
			String[] labels = {
					"이름 : " + mvpPlayer.getName(),
					"나이 : " + mvpPlayer.getAge(),
					"키 : " + mvpPlayer.getHeight() + "cm",
					"몸무게 : " + mvpPlayer.getWeight() + "kg",
					"포지션 : " + mvpPlayer.getPosition()
			};

			for (int i = 0; i < labels.length; i++) {
				JLabel label = new JLabel(labels[i]);
				label.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
				label.setHorizontalAlignment(SwingConstants.CENTER);

				gbc.gridy = i + 1; // 사진 아래로 순서대로 추가
				gbc.insets = new Insets(5, 0, 5, 0); // top, bottom 간격 5px
				mvpDataPanel.add(label, gbc);
			}
		} else {
			// MVP 정보가 없을 때 정보없음 메시지
			JLabel notFoundlbl = new JLabel("해당 경기 MVP 정보가 없습니다.");
			notFoundlbl.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			notFoundlbl.setForeground(Color.RED);
			notFoundlbl.setHorizontalAlignment(SwingConstants.CENTER);

			gbc.gridy = 0;
			gbc.insets = new Insets(10, 0, 10, 0);
			mvpDataPanel.add(notFoundlbl, gbc);
		}

		mvpDataPanel.revalidate();
		mvpDataPanel.repaint();
	}

	// 테이블 새로고침 메서드
	public void refreshTable() {
		List<MatchDto> matchResult = ResultList.getMatchResultList();

		String[] columnNames = {"매치", "날짜", "상대팀", "득점", "실점", "경고", "퇴장", "MVP"};
		Object[][] data = new Object[matchResult.size()][8];

		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < matchResult.size(); i++) {
			MatchDto match = matchResult.get(i);
			data[i][0] = match.getMatchName();
			data[i][1] = dataFormat.format(match.getMatchDate());
			data[i][2] = match.getOpposing();
			data[i][3] = match.getOurScore();
			data[i][4] = match.getOppScore();
			data[i][5] = match.getYellowCard();
			data[i][6] = match.getRedCard();
			data[i][7] = match.getMvp();
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		table.setModel(model); // NullPointerException 방지 (table 초기화된 상태)
		table.revalidate();
		table.repaint();

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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btn1) {
			new MainFrame("대한민국 축구 국가대표팀");
			dispose();
		} else if (obj == btn2) {
			new PlayerForm("대한민국 축구 국가대표팀");
			dispose();
		} else if (obj == btn3) {
			new PlayerInputForm("대한민국 축구 국가대표팀");
			dispose();
		} else if (obj == btn4) {
			new ResultForm("대한민국 축구 국가대표팀");
			dispose();
		} else if (obj == btn5) {
			new ScheduleForm("대한민국 축구 국가대표팀");
			dispose();
		}

		// 기록 입력 버튼 액션 리스너
		if (obj == resultInsertBtn) {
			new ResultPopup(this, false, null, null).setVisible(true);
			refreshTable();
		}
		if (obj == resultModifyBtn) {
			int row = table.getSelectedRow();
			String oppoForEdit = (String) table.getValueAt(row, 2);
			String mvpForEdit = (String) table.getValueAt(row, 7);
			new ResultPopup(this, true, oppoForEdit, mvpForEdit).setVisible(true);
			refreshTable();
		}
		// 기록 삭제 버튼 액션 리스너
		if (obj == resultDeleteBtn) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "삭제할 경기를 제대로 클릭하고 눌러주세요", "확인", JOptionPane.WARNING_MESSAGE);
				return;
			}

			int confirm = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				String name = (String) table.getValueAt(row, 7);
				String opposing = (String) table.getValueAt(row, 2);
				Util.executeSql("delete from match where mvp = '" + name + "' and opposing = '" + opposing + "'");
			}
			refreshTable();
		}
	}
}
