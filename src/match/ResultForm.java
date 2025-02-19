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
	private JButton btn1, btn2, btn3, btn4, btn5;
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

		// 패널 생성 및 배치 구간
		// 메인 컨텐츠 공간
		mainSpace = createPanel(0, 60, 1000, 540, new Color(255, 255, 240));
		// 경기 리스트 테이블이 들어갈 공간
		listInfo = createPanel(20, 80, 540, 460, new Color(255, 255, 240), true);
		// 경기 클릭 시 해당 경기 mvp 정보 공간
		mvpInfo = createPanel(580, 80, 280, 460, new Color(255, 255, 240), true);
		// 해당 경기 mvp 정보 패널
		mvpDataPanel = createPanel(600, 100, 240, 420, new Color(255, 255, 240));
		// --- 여기까지

		// 버튼 생성 및 배치 구간
		// default button(상단 탭 버튼)
		btn1 = createTabButton("대한민국 축구단", 10, 10);
		btn2 = createTabButton("대표팀 선수 목록", 205, 10);
		btn3 = createTabButton("대표팀 선수 입력", 400, 10);
		btn4 = createTabButton("대표팀 경기 결과", 595, 10);
		btn5 = createTabButton("대표팀 일정", 790, 10);

		// resultForm button(우측 기능 버튼)
		resultInsertBtn = createRightButton("기록 입력", 870, 80);
		resultModifyBtn = createRightButton("기록 수정", 870, 150);
		resultDeleteBtn = createRightButton("기록 삭제", 870, 220);

		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);
		add(resultInsertBtn);
		add(resultModifyBtn);
		add(resultDeleteBtn);
		// --- 여기까지

		// 경기 결과 테이블 설정
		JLabel resultTitlelbl = createLabel("경기 기록", 250, 70, 60, 15);
		add(resultTitlelbl);
		refreshTable();

		// 테이블 클릭 시 mvp 정보 업데이트
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

		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(30, 90, 520, 440);
		add(jsp);

		JLabel mvpTitlelbl = createLabel("경기 MVP", 690, 70, 60, 15);
		add(mvpTitlelbl);

		add(mvpDataPanel);
		add(listInfo);
		add(mvpInfo);
		add(mainSpace);

		setVisible(true);
	}

	// 패널
	private JPanel createPanel(int x, int y, int width, int height, Color color) {
		return createPanel(x, y, width, height, color, false);
	}

	// border switch 패널 생성
	private JPanel createPanel(int x, int y, int width, int height, Color color, boolean border) {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		panel.setBounds(x, y, width, height);
		if (border) {
			panel.setBorder(new TitledBorder(new LineBorder(new Color(105, 105, 105))));
		}
		return panel;
	}

	// 라벨 생성
	private JLabel createLabel(String text, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("바탕", Font.PLAIN, 13));
		label.setBounds(x, y, width, height);
		label.setOpaque(true);
		label.setBackground(new Color(255, 255, 240));
		return label;
	}

	// 상단 탭 기능 버튼
	private JButton createTabButton(String text, int x, int y) {
		return createButton(text, x, y, 185, 50);
	}

	// 우측 버튼
	private JButton createRightButton(String text, int x, int y) {
		return createButton(text, x, y, 100, 50);
	}

	// 통합 버튼 생성
	private JButton createButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setBackground(new Color(255, 228, 196));
		button.setBorderPainted(false);
		button.addActionListener(this);
		return button;
	}

	// 경기 결과 테이블 설정
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

		table = new JTable(data, columnNames);
		table.setDefaultEditor(Object.class, null);  // 테이블 셀 편집 방지
		setTableRendererAndWidth();
	}

	// 테이블 셀 정렬 및 너비 구간 설정
	private void setTableRendererAndWidth() {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);  // 가운데 정렬
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			TableColumn column = table.getColumnModel().getColumn(i);
			int width = 0;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, i);
				Component comp = table.prepareRenderer(renderer, row, i);
				width = Math.max(comp.getPreferredSize().width, width);
			}
			column.setPreferredWidth(width + 10);  // 여백을 주기 위해 +10
		}
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

		mvpDataPanel.revalidate();  // UI 업데이트
		mvpDataPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn1) {
			navigateTo(new MainFrame("대한민국 축구 국가대표팀"));
		} else if (obj == btn2) {
			navigateTo(new PlayerForm("대한민국 축구 국가대표팀"));
		} else if (obj == btn3) {
			navigateTo(new PlayerInputForm("대한민국 축구 국가대표팀"));
		} else if (obj == btn4) {
			navigateTo(new ResultForm("대한민국 축구 국가대표팀"));
		} else if (obj == btn5) {
			navigateTo(new ScheduleForm("대한민국 축구 국가대표팀"));
		} else if (obj == resultInsertBtn) {
			new ResultPopup(this, false, null, null).setVisible(true);
			refreshTable();
		} else if (obj == resultModifyBtn) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "수정할 경기를 제대로 클릭하고 눌러주세요", "확인", JOptionPane.WARNING_MESSAGE);
				MvpInfo(null);
			} else {
				String oppoForEdit = (String) table.getValueAt(row, 2);
				String mvpForEdit = (String) table.getValueAt(row, 7);
				new ResultPopup(this, true, oppoForEdit, mvpForEdit).setVisible(true);
			}
			refreshTable();
		} else if (obj == resultDeleteBtn) {
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

	private void navigateTo(JFrame frame) {
		frame.setVisible(true);
		dispose();
	}
}
