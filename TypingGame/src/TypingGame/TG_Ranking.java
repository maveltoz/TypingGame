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

	private ArrayList<String> rankList; // ���� ��ŷ�� �����ϴ� ArrayList

	private JLabel rankingLabel; // ��� ���� ��
	private JLabel rankLabel; // Rank ���� ��
	private JLabel usernameLabel; // username ���� ��
	private JLabel scoreLabel; // score ���� ��
	private JButton goMenuButton; // �ʱ� �޴��� �̵��ϴ� ��ư

	public TG_Ranking() {
		makeRanking nowRankingList = new makeRanking(); // makeRanking ��ü ����
		rankList = nowRankingList.getRankList(); // rankList�� ���� ��ŷ�� ����

		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TypingGame");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 242, 230));

		rankingLabel = new JLabel("Ranking");
		rankingLabel.setFont(new Font("����", Font.BOLD, 30));
		rankingLabel.setBounds(240, 30, 600, 50);
		getContentPane().add(rankingLabel);

		rankLabel = new JLabel("Rank");
		rankLabel.setFont(new Font("����", Font.BOLD, 25));
		rankLabel.setBounds(80, 100, 600, 50);
		getContentPane().add(rankLabel);

		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("����", Font.BOLD, 25));
		usernameLabel.setBounds(220, 100, 600, 50);
		getContentPane().add(usernameLabel);

		scoreLabel = new JLabel("Score");
		scoreLabel.setFont(new Font("����", Font.BOLD, 25));
		scoreLabel.setBounds(420, 100, 600, 50);
		getContentPane().add(scoreLabel);

		// Rank, Username, Score�� ���� ��ŷ ������ ������
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < rankList.size(); j++) {
				// Rank
				if (i == 0) {
					JLabel tmpLabel = new JLabel(Integer.toString(j + 1)); // ���� ǥ��
					rankingLabel.setFont(new Font("����", Font.BOLD, 23));
					tmpLabel.setBounds(110 + 150 * i, 150 + 30 * j, 300, 100);
					getContentPane().add(tmpLabel);
				}
				// Username
				else if (i == 1) {
					JLabel tmpLabel = new JLabel(rankList.get(j).substring(0, rankList.get(j).length() - 4)); // substring�� �̿��Ͽ� username ���� �� ǥ��
					rankingLabel.setFont(new Font("����", Font.BOLD, 23));
					tmpLabel.setBounds(125 + 150 * i, 150 + 30 * j, 300, 100);
					getContentPane().add(tmpLabel);
				}
				// Score
				else if (i == 2) {
					String scoreString = rankList.get(j).substring(rankList.get(j).length() - 4,
							rankList.get(j).length() - 1); // substring�� �̿��Ͽ� Score ����
					if (scoreString.charAt(0) == '0') {
						scoreString = scoreString.replaceFirst("0", " ");
						if (scoreString.charAt(1) == '0')
							scoreString = scoreString.replaceFirst("0", " ");
					}

					JLabel tmpLabel = new JLabel(
							scoreString + " (" + (rankList.get(j).charAt(rankList.get(j).length() - 1)) + ")"); // score�� ���̵� ǥ��
					rankingLabel.setFont(new Font("����", Font.BOLD, 23));
					tmpLabel.setBounds(140 + 150 * i, 150 + 30 * j, 300, 100);
					getContentPane().add(tmpLabel);
				}
			}
		}

		goMenuButton = new JButton("Menu");
		goMenuButton.setFont(new Font("����", Font.BOLD, 20));
		goMenuButton.setBounds(230, 450, 120, 60);
		goMenuButton.setBackground(new Color(237, 160, 227));
		getContentPane().add(goMenuButton);

		setVisible(true);

		goMenuButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Menu ��ư�� ������ ��
		if (e.getSource().equals(goMenuButton)) {
			this.dispose(); // ���� �������� �ִ� ��ŷ ȭ���� ����
			TG_Main main2 = new TG_Main(); // main ��ü ����
			main2.goMenuView(main2); // main�� goMenuView �޼ҵ� ����
		}
	}

	public void setTGMain(TG_Main main) {
		this.main = main; // ���� ��ü�� main�� �Ű������� ���� �ǵ��ư� main�� set
	}

}