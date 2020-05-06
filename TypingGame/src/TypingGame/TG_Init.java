package TypingGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class TG_Init extends JFrame implements ActionListener {
	TG_Main main;

	public String username; // 입력받을 플레이어명을 저장하는 변수
	public String difficulty; // 입력받을 난이도를 저장하는 변수

	private JLabel initLabel; // 상단 문구 라벨
	private JLabel usernameLabel; // username 문구 라벨
	private JTextField usernameTextField; // 플레이어명을 입력할 TextField
	private JLabel difficultyLabel; // 난이도 문구 라벨
	private JButton difficultyButton1; // 난이도 '하' 버튼
	private JButton difficultyButton2; // 난이도 '중' 버튼
	private JButton difficultyButton3; // 난이도 '상' 버튼

	public TG_Init() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TypingGame");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 242, 230));

		initLabel = new JLabel("플레이어명을 입력하고 난이도를 선택해주세요.");
		initLabel.setFont(new Font("굴림", Font.BOLD, 20));
		initLabel.setBounds(90, 80, 600, 50);
		getContentPane().add(initLabel);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(190, 190, 250, 60);
		usernameTextField.setFont(new Font("굴림", Font.BOLD, 20));
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(15);

		difficultyButton1 = new JButton("하");
		difficultyButton1.setBounds(190, 300, 81, 81);
		difficultyButton1.setBackground(new Color(250, 244, 192));
		difficultyButton1.setForeground(Color.BLACK);
		difficultyButton1.setFont(new Font("굴림", Font.BOLD, 20));
		getContentPane().add(difficultyButton1);

		difficultyButton2 = new JButton("중");
		difficultyButton2.setBounds(290, 300, 81, 81);
		difficultyButton2.setBackground(new Color(250, 244, 192));
		difficultyButton2.setForeground(Color.BLACK);
		difficultyButton2.setFont(new Font("굴림", Font.BOLD, 20));
		getContentPane().add(difficultyButton2);

		difficultyButton3 = new JButton("상");
		difficultyButton3.setBounds(390, 300, 81, 81);
		difficultyButton3.setBackground(new Color(250, 244, 192));
		difficultyButton3.setForeground(Color.BLACK);
		difficultyButton3.setFont(new Font("굴림", Font.BOLD, 20));
		getContentPane().add(difficultyButton3);

		usernameLabel = new JLabel("<username>");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("굴림", Font.BOLD, 20));
		usernameLabel.setBounds(15, 200, 188, 47);
		getContentPane().add(usernameLabel);

		difficultyLabel = new JLabel("<난이도>");
		difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		difficultyLabel.setFont(new Font("굴림", Font.BOLD, 20));
		difficultyLabel.setBounds(15, 320, 188, 47);
		getContentPane().add(difficultyLabel);
		setVisible(true);

		difficultyButton1.addActionListener(this);
		difficultyButton2.addActionListener(this);
		difficultyButton3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 난이도 "하" 버튼 눌렀을 때 이벤트 처리
		if (e.getSource().equals(difficultyButton1)) {
			// username이 입력되었을 때
			if (!usernameTextField.getText().equals("")) {
				username = usernameTextField.getText().toString(); // username에 입력된 Text를 저장
				difficulty = "하"; // difficulty에 "하"를 저장
				main.startGameView(this, username, difficulty); // (현재 객체, username, difficulty)를 인수로 main의 startGameView 메소드 실행
			}
			// username이 입력되지 않았을 때
			else {
				initLabel.setText("닉네임을 입력해주세요."); // initLabel에 "닉네임을 입력해주세요" 문구 설정
			}

		}
		// 난이도 "중" 버튼 눌렀을 때 이벤트 처리
		else if (e.getSource().equals(difficultyButton2)) {
			// username이 입력되었을 때
			if (!usernameTextField.getText().equals("")) {
				username = usernameTextField.getText().toString(); // username에 입력된 Text를 저장
				difficulty = "중"; // difficulty에 "중"를 저장
				main.startGameView(this, username, difficulty); // (현재 객체, username, difficulty)를 인수로 main의 startGameView 메소드 실행
			}
			// username이 입력되지 않았을 때
			else {
				initLabel.setText("닉네임을 입력해주세요."); // initLabel에 "닉네임을 입력해주세요" 문구 설정
			}
		}
		// 난이도 "상" 버튼 눌렀을 때 이벤트 처리
		else if (e.getSource().equals(difficultyButton3)) {
			// username이 입력되었을 때
			if (!usernameTextField.getText().equals("")) {
				username = usernameTextField.getText().toString(); // username에 입력된 Text를 저장
				difficulty = "상"; // difficulty에 "중"를 저장
				main.startGameView(this, username, difficulty); // (현재 객체, username, difficulty)를 인수로 main의 startGameView 메소드 실행
			}
			// username이 입력되지 않았을 때
			else {
				initLabel.setText("닉네임을 입력해주세요."); // initLabel에 "닉네임을 입력해주세요" 문구 설정
			}
		}
	}

	public void setTGMain(TG_Main main) {
		this.main = main; // 현재 객체의 main에 매개변수로 받은 되돌아갈 main을 set
	}
}