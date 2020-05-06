package TypingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class TG_Menu extends JFrame implements ActionListener {
	TG_Main main;

	private JLabel selectMenuLabel; // 상단 문구 라벨
	private JButton startGameButton; // "게임시작" 버튼
	private JButton showRankingButton; // "순위보기" 버튼
	private JButton endGameButton; // "게임종료" 버튼

	public TG_Menu() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TypingGame");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 242, 230));

		selectMenuLabel = new JLabel("메뉴를 선택해주세요.");
		selectMenuLabel.setBounds(130, 130, 400, 40);
		selectMenuLabel.setFont(new Font("굴림", Font.BOLD, 35));
		getContentPane().add(selectMenuLabel);

		startGameButton = new JButton("게임시작");
		startGameButton.setBounds(60, 280, 160, 60);
		startGameButton.setBackground(new Color(250, 244, 192));
		startGameButton.setForeground(Color.BLACK);
		startGameButton.setFont(new Font("굴림", Font.BOLD, 20));
		getContentPane().add(startGameButton);

		showRankingButton = new JButton("순위보기");
		showRankingButton.setBounds(210, 280, 160, 60);
		showRankingButton.setBackground(new Color(250, 244, 192));
		showRankingButton.setForeground(Color.BLACK);
		showRankingButton.setFont(new Font("굴림", Font.BOLD, 20));
		getContentPane().add(showRankingButton);

		endGameButton = new JButton("게임종료");
		endGameButton.setBounds(360, 280, 160, 60);
		endGameButton.setBackground(new Color(250, 244, 192));
		endGameButton.setForeground(Color.BLACK);
		endGameButton.setFont(new Font("굴림", Font.BOLD, 20));
		getContentPane().add(endGameButton);

		setVisible(true);

		startGameButton.addActionListener(this);
		showRankingButton.addActionListener(this);
		endGameButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// "게임시작" 버튼 눌렀을 때 이벤트 처리
		if (e.getSource().equals(startGameButton)) {
			main.startInitView(this); // 현재 객체를 인수로 main의 startInitView 메소드 실행
		}
		// "순위보기" 버튼 눌렀을 때 이벤트 처리
		else if (e.getSource().equals(showRankingButton)) {
			main.showRankingView(this); // 현재 객체를 인수로 main의 showRankingView 메소드 실행
		}
		// "게임종료" 버튼 눌렀을 때 이벤트 처리
		else if (e.getSource().equals(endGameButton)) {
			main.endGameView(); // main의 endGameView 메소드 실행
		}
	}

	public void setTGMain(TG_Main main) {
		this.main = main; // 현재 객체의 main에 매개변수로 받은 되돌아갈 main을 set
	}
}