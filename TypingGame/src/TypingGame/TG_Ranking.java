package TypingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class TG_Ranking extends JFrame implements ActionListener {
	TG_Main main;

	private ArrayList<String> rankList; // 현재 랭킹을 저장하는 ArrayList

	private JLabel rankingLabel; // 상단 문구 라벨
	private JLabel rankLabel; // Rank 문구 라벨
	private JLabel usernameLabel; // username 문구 라벨
	private JLabel scoreLabel; // score 문구 라벨
	private JButton goMenuButton; // 초기 메뉴로 이동하는 버튼

	public TG_Ranking() {
		makeRanking nowRankingList = new makeRanking(); // makeRanking 객체 생성
		rankList = nowRankingList.getRankList(); // rankList에 현재 랭킹을 저장

		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TypingGame");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 242, 230));

		rankingLabel = new JLabel("Ranking");
		rankingLabel.setFont(new Font("굴림", Font.BOLD, 30));
		rankingLabel.setBounds(240, 30, 600, 50);
		getContentPane().add(rankingLabel);

		rankLabel = new JLabel("Rank");
		rankLabel.setFont(new Font("굴림", Font.BOLD, 25));
		rankLabel.setBounds(80, 100, 600, 50);
		getContentPane().add(rankLabel);

		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("굴림", Font.BOLD, 25));
		usernameLabel.setBounds(220, 100, 600, 50);
		getContentPane().add(usernameLabel);

		scoreLabel = new JLabel("Score");
		scoreLabel.setFont(new Font("굴림", Font.BOLD, 25));
		scoreLabel.setBounds(420, 100, 600, 50);
		getContentPane().add(scoreLabel);

		// Rank, Username, Score를 현재 랭킹 순으로 보여줌
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < rankList.size(); j++) {
				// Rank
				if (i == 0) {
					JLabel tmpLabel = new JLabel(Integer.toString(j + 1)); // 순위 표시
					rankingLabel.setFont(new Font("굴림", Font.BOLD, 23));
					tmpLabel.setBounds(110 + 150 * i, 150 + 30 * j, 300, 100);
					getContentPane().add(tmpLabel);
				}
				// Username
				else if (i == 1) {
					JLabel tmpLabel = new JLabel(rankList.get(j).substring(0, rankList.get(j).length() - 4)); // substring을 이용하여 username 추출 및 표시
					rankingLabel.setFont(new Font("굴림", Font.BOLD, 23));
					tmpLabel.setBounds(125 + 150 * i, 150 + 30 * j, 300, 100);
					getContentPane().add(tmpLabel);
				}
				// Score
				else if (i == 2) {
					String scoreString = rankList.get(j).substring(rankList.get(j).length() - 4,
							rankList.get(j).length() - 1); // substring을 이용하여 Score 추출
					if (scoreString.charAt(0) == '0') {
						scoreString = scoreString.replaceFirst("0", " ");
						if (scoreString.charAt(1) == '0')
							scoreString = scoreString.replaceFirst("0", " ");
					}

					JLabel tmpLabel = new JLabel(
							scoreString + " (" + (rankList.get(j).charAt(rankList.get(j).length() - 1)) + ")"); // score와 난이도 표시
					rankingLabel.setFont(new Font("굴림", Font.BOLD, 23));
					tmpLabel.setBounds(140 + 150 * i, 150 + 30 * j, 300, 100);
					getContentPane().add(tmpLabel);
				}
			}
		}

		goMenuButton = new JButton("Menu");
		goMenuButton.setFont(new Font("굴림", Font.BOLD, 20));
		goMenuButton.setBounds(230, 450, 120, 60);
		goMenuButton.setBackground(new Color(237, 160, 227));
		getContentPane().add(goMenuButton);

		setVisible(true);

		goMenuButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Menu 버튼을 눌렀을 시
		if (e.getSource().equals(goMenuButton)) {
			this.dispose(); // 현재 보여지고 있는 랭킹 화면을 종료
			TG_Main main2 = new TG_Main(); // main 객체 생성
			main2.goMenuView(main2); // main의 goMenuView 메소드 실행
		}
	}

	public void setTGMain(TG_Main main) {
		this.main = main; // 현재 객체의 main에 매개변수로 받은 되돌아갈 main을 set
	}

}