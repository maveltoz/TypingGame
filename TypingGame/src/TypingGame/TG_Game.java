package TypingGame;

import TypingGame.TG_Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TG_Game extends JFrame implements ActionListener, KeyListener {
	private int life = 5; // 현재 남은 life를 저장하는 변수
	private int score = 0; // 현재 score를 저장하는 변수
	private int createWordsCount = 0; // 현재까지 생성된 단어갯수를 저장하는 변수
	private int speed; // 현재 단어가 내려오는 속도를 저장하는 변수
	private int remainWordCount; // 다음 스테이지까지 남은 단어 갯수를 저장하는 변수
	private int stage = 1; // 현재 스테이지를 저장하는 변수
	private int nowWordsCount = 0; // 현재 생성될 단어가 단어리스트의 몇 번째 단어인지를 저장하는 변수
	private boolean nowStarting = false; // 현재 게임이 실행 중인지를 저장하는 변수

	private String username; // 현재 플레이어명을 저장하는 변수
	private String difficulty; // 현재 난이도를 저장하는 변수
	private String[] nowDataSet; // 현재 난이도에 해당하는 dataSet을 저장하는 배열
	private String[] dataSet1 = { "자바", "코딩", "계산기", "공책", "달력", "행복" }; // 난이도 '하'에 해당하는 단어리스트
	private String[] dataSet2 = { "자바", "코딩", "계산기", "공책", "달력", "행복", "충전기", "왕밤빵", "아르헨티나", "미끄럼틀", "다이어리", "뿌링클",
			"산기슭", "elephant", "science" }; // 난이도 '중'에 해당하는 단어리스트
	private String[] dataSet3 = { "자바", "코딩", "계산기", "공책", "달력", "행복", "충전기", "왕밤빵", "아르헨티나", "미끄럼틀", "다이어리", "뿌링클",
			"산기슭", "elephant", "science", "물잇구럭", "어씃하다", "게르마늄", "particular", "anthology", "지르코늄", "촌초춘휘", "즐풍목우",
			"세미콜론", "뚝딱뚝딱" }; // 난이도 '상'에 해당하는 단어리스트

	private JPanel screenPanel; // 게임 진행 화면 Panel
	private JPanel lifePanel1; // 라이프 Panel
	private JPanel lifePanel2; // 라이프 Panel
	private JPanel lifePanel3; // 라이프 Panel
	private JPanel lifePanel4; // 라이프 Panel
	private JPanel lifePanel5; // 라이프를 나타내는 Panel
	private JPanel gameOverPanel; // 게임 종료 화면 Panel
	private JPanel deadLinePanel; // 데드라인 Panel

	private JTextField inputTextField; // 단어를 입력할 TextField
	private JButton startGameButton; // 게임 시작 버튼
	private JButton goMenuButton; // 초기 메뉴로 이동하는 버튼

	private JLabel wordLabel[] = new JLabel[10000]; // 생성된 단어들을 나타내는 라벨
	private JLabel usernameLabel; // username 문구 라벨
	private JLabel difficultyLabel; // 난이도 라벨
	private JLabel stageLabel; // stage 문구 라벨
	private JLabel stageUpLabel; // stage 상승 시 알려주는 문구 라벨
	private JLabel scoreLabel; // score 문구 라벨
	private JLabel correctCheckLabel; // 정답인지 오답인지를 알려주는 문구 라벨
	private JLabel gameOverLabel; // 게임 종료 시 상단 문구 라벨
	private JLabel gameOver_scoreLabel; // 게임 종료 시 획득한 score를 알려주는 라벨
	private JLabel gameOver_usernameLabel; // 게임 종료 시 username을 알려주는 라벨

	public TG_Game(String username, String difficulty) {
		this.username = username; // 현재 객체의 username에 매개변수로 받은 username을 저장
		this.difficulty = difficulty; // 현재 객체의 difficulty에 매개변수로 받은 difficulty을 저장

		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("TypingGame");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 239, 213));

		inputTextField = new JTextField();
		inputTextField.setBounds(190, 630, 270, 40);
		getContentPane().add(inputTextField);
		inputTextField.setColumns(10);

		startGameButton = new JButton("Start!");
		startGameButton.setFont(new Font("굴림", Font.BOLD, 23));
		startGameButton.setBounds(480, 630, 105, 40);
		startGameButton.setBackground(new Color(250, 250, 178));
		getContentPane().add(startGameButton);

		screenPanel = new JPanel();
		screenPanel.setBackground(new Color(250, 250, 178));
		screenPanel.setForeground(new Color(250, 250, 178));
		screenPanel.setBounds(40, 23, 750, 570);
		getContentPane().add(screenPanel);
		screenPanel.setLayout(null);

		deadLinePanel = new JPanel();
		deadLinePanel.setBackground(Color.RED);
		deadLinePanel.setBounds(0, 500, 740, 10);
		screenPanel.add(deadLinePanel);

		correctCheckLabel = new JLabel("정답!");
		correctCheckLabel.setFont(new Font("굴림", Font.BOLD, 23));
		correctCheckLabel.setForeground(Color.red);
		correctCheckLabel.setBounds(340, 512, 150, 50);
		screenPanel.add(correctCheckLabel);
		correctCheckLabel.setVisible(false); // 정답인지 오답인지를 알려주는 correctCheckLabel은 초기에는 안보이게 함

		usernameLabel = new JLabel("Username : " + username);
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("굴림", Font.BOLD, 18));
		usernameLabel.setBounds(830, 80, 300, 30);
		getContentPane().add(usernameLabel);

		difficultyLabel = new JLabel("난이도 : " + difficulty);
		difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		difficultyLabel.setFont(new Font("굴림", Font.BOLD, 18));
		difficultyLabel.setBounds(900, 180, 150, 50);
		getContentPane().add(difficultyLabel);

		stageLabel = new JLabel("Stage : " + Integer.toString(stage));
		stageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stageLabel.setFont(new Font("굴림", Font.BOLD, 18));
		stageLabel.setBounds(900, 280, 150, 50);
		getContentPane().add(stageLabel);

		stageUpLabel = new JLabel("Stage Up!");
		stageUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stageUpLabel.setFont(new Font("굴림", Font.BOLD, 15));
		stageUpLabel.setForeground(Color.red);
		stageUpLabel.setBounds(1000, 281, 150, 50);
		getContentPane().add(stageUpLabel);
		stageUpLabel.setVisible(false); // stage 상승을 알려주는 stageUpLabel은 초기에는 안보이게 함

		scoreLabel = new JLabel("Score : " + score + "점");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("굴림", Font.BOLD, 18));
		scoreLabel.setBounds(900, 380, 150, 50);
		getContentPane().add(scoreLabel);

		lifePanel1 = new JPanel();
		lifePanel1.setBackground(Color.RED);
		lifePanel1.setBounds(890, 480, 25, 25);
		getContentPane().add(lifePanel1);

		lifePanel2 = new JPanel();
		lifePanel2.setBackground(Color.RED);
		lifePanel2.setBounds(930, 480, 25, 25);
		getContentPane().add(lifePanel2);

		lifePanel3 = new JPanel();
		lifePanel3.setBackground(Color.RED);
		lifePanel3.setBounds(970, 480, 25, 25);
		getContentPane().add(lifePanel3);

		lifePanel4 = new JPanel();
		lifePanel4.setBackground(Color.RED);
		lifePanel4.setBounds(1010, 480, 25, 25);
		getContentPane().add(lifePanel4);

		lifePanel5 = new JPanel();
		lifePanel5.setBackground(Color.RED);
		lifePanel5.setBounds(1050, 480, 25, 25);
		getContentPane().add(lifePanel5);

		gameOverPanel = new JPanel();
		gameOverPanel.setBackground(Color.PINK);
		gameOverPanel.setBounds(15, 150, 800, 300);
		gameOverPanel.setVisible(false); // 게임 종료 화면을 나타내는 gameOverPanel은 초기에는 안보이게 함
		getContentPane().add(gameOverPanel);
		gameOverPanel.setLayout(null);

		gameOverLabel = new JLabel("Game Over!");
		gameOverLabel.setBounds(0, 40, 800, 60);
		gameOverPanel.add(gameOverLabel);
		gameOverLabel.setForeground(Color.RED);
		gameOverLabel.setFont(new Font("굴림", Font.BOLD, 50));
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);

		gameOver_usernameLabel = new JLabel();
		gameOver_usernameLabel.setBounds(0, 120, 800, 40);
		gameOverPanel.add(gameOver_usernameLabel);
		gameOver_usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOver_usernameLabel.setForeground(Color.BLACK);
		gameOver_usernameLabel.setFont(new Font("굴림", Font.BOLD, 40));

		gameOver_scoreLabel = new JLabel("Score");
		gameOver_scoreLabel.setBounds(0, 200, 800, 40);
		gameOverPanel.add(gameOver_scoreLabel);
		gameOver_scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOver_scoreLabel.setForeground(Color.BLACK);
		gameOver_scoreLabel.setFont(new Font("굴림", Font.BOLD, 40));

		goMenuButton = new JButton("Menu");
		goMenuButton.setFont(new Font("굴림", Font.BOLD, 30));
		goMenuButton.setBounds(900, 600, 200, 80);
		goMenuButton.setBackground(new Color(237, 160, 227));
		getContentPane().add(goMenuButton);
		goMenuButton.setVisible(false); // 게임 종료 시 초기 메뉴로 이동하는 버튼인 goMenuButton은 초기에는 안보이게 함

		setVisible(true);

		startGameButton.addActionListener(this);
		goMenuButton.addActionListener(this);
		inputTextField.addKeyListener(this);
		inputTextField.requestFocus();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// "Start!" 버튼 눌렀을 때 이벤트 처리
		if (e.getSource().equals(startGameButton)) {
			// 현재 게임이 실행 중인지를 확인
			if (!nowStarting) {
				nowStarting = true; // 게임 실행 중
				inputTextField.requestFocus();
				new makeWords().start(); // 단어를 생성하는 makeWords 스레드 실행
			}
		}
		// 게임 종료 후 "Menu" 버튼 눌렀을 때 이벤트 처리
		else if (e.getSource().equals(goMenuButton)) {
			makeRanking nowRankingList = new makeRanking(); // makeRanking 객체를 생성
			nowRankingList.addRanking(username, score, difficulty); // 현재의 (username, score, difficulty)를 인수로 nowRankingList의 addRanking 메소드 실행

			this.dispose(); // 현재 보여지고 있는 게임 종료 화면 종료
			TG_Main main2 = new TG_Main(); // TG_Main 객체 생성
			main2.goMenuView(main2); // main2의 goMenuView 메소드 실행
		}
	}

	// 단어를 생성하는 스레드
	class makeWords extends Thread {
		@Override
		public void run() {
			screenPanel.setVisible(true);

			// 난이도 '하' 일때
			if (difficulty.equals("하")) {
				nowDataSet = dataSet1; // nowDataSet에 난이도 '하'에 해당하는 dataSet1을 저장
			}
			// 난이도 '중' 일때
			else if (difficulty.equals("중")) {
				nowDataSet = dataSet2; // nowDataSet에 난이도 '중'에 해당하는 dataSet2을 저장
			}
			// 난이도 '상' 일때
			else if (difficulty.equals("상")) {
				nowDataSet = dataSet3; // nowDataSet에 난이도 '상'에 해당하는 dataSet3을 저장
			}

			remainWordCount = nowDataSet.length; // remainWordCount에 현재 단어리스트의 단어 갯수를 저장

			for (createWordsCount = 0; createWordsCount < 10000; createWordsCount++) {
				try {
					
					// 단어리스트에 있는 단어를 한 번씩 다 생성했을 시
					if (nowWordsCount == nowDataSet.length) {
						nowWordsCount = 0; // 다시 단어리스트의 0번째 단어부터 생성하도록 함
					}
					
					stageUpLabel.setVisible(false); // 스테이지가 증가했다는 문구를 표시하는 stageUpLabel을 안보이게 함
					
					// remainWordCount가 0일 떄, 즉, 현재 단어리스트에 있는 단어들을 한 번씩 다 입력했을 시 스테이지 상승
					if (remainWordCount == 0) {
						remainWordCount = nowDataSet.length; // remainWordCount에 다시 현재 단어리스트의 단어 갯수를 저장
						stage++; // 스테이지 상승
						
						// 스테이지 1에서 스테이지 2로 상승할 때, 스테이지 2에서 스테이지 3으로 상승할 때
						if (stage < 4)
							stageUpLabel.setVisible(true); // 스테이지가 상승했다는 문구를 표시
					}

					// 스테이지 3까지 성공 했을 시
					if (stage == 4) {
						stage--; // 스테이지를 3으로 만듬
						screenPanel.setVisible(false); // 게임 진행 화면을 숨김
						gameOverPanel.setVisible(true); // 게임 종료 화면을 보여줌
						goMenuButton.setVisible(true); // 초기 메뉴로 가는 버튼을 보여줌
						gameOver_usernameLabel.setForeground(Color.BLUE);
						gameOverLabel.setText("All Stage Clear!"); // "All Stage Clear!" 문구를 보여줌
						gameOver_usernameLabel.setText(usernameLabel.getText()); // username을 보여줌
						gameOver_scoreLabel.setText(scoreLabel.getText()); // 최종 score을 보여줌

						new makeWords().interrupt(); // makeWords 스레드를 interrupt
						new moveWords().interrupt(); // moveWords 스레드를 interrupt
					}

					stageLabel.setText("스테이지 : " + Integer.toString(stage)); // stageLabel에 현재 스테이지를 표시

					// 스테이지 1 일떄
					if (stage == 1)
						speed = 900; // 단어가 내려오는 속도를 나타내는 speed 변수에 900을 저장
					// 스테이지 2 일때
					else if (stage == 2)
						speed = 700; // 단어가 내려오는 속도를 나타내는 speed 변수에 700을 저장
					// 스테이지 3 일때
					else if (stage == 3)
						speed = 500; // 단어가 내려오는 속도를 나타내는 speed 변수에 500을 저장
					else if (stage == 4)
						speed = 300;

					Random random = new Random(); // Random 객체 생성

					wordLabel[createWordsCount] = new JLabel(nowDataSet[nowWordsCount++]); // 새로운 단어를 생성
					wordLabel[createWordsCount].setBounds(0, 0, 130, 70);
					screenPanel.add(wordLabel[createWordsCount]); // 생성된 단어가 게임 진행 화면에 나타남
					wordLabel[createWordsCount].setLocation(random.nextInt(678), 2); // 생성되는 단어는 랜덤한 x좌표를 가지고 나옴

					new moveWords().start(); // 단어를 움직이게 하는 moveWords 스레드 실행

					Thread.sleep(speed); // speed의 값 만큼 스레드를 sleep 시켜 속도를 조절

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 단어를 움직이는 스레드
	class moveWords extends Thread {
		@Override
		public void run() {
			for (int word = 0; word <= createWordsCount; word++) {
				// 단어가 현재 게임 진행 화면에 존재 시
				if (wordLabel[word].isVisible()) {
					int xPosition = wordLabel[word].getX(); // 단어의 x좌표
					int yPosition = wordLabel[word].getY(); // 단어의 y좌표

					wordLabel[word].setLocation(xPosition, yPosition + 15); // 단어의 y좌표를 15 증가시킴으로써, 단어가 내려가는 것처럼 보이게 함
				}

				// 단어가 데드라인을 넘었을 시
				if (wordLabel[word].isVisible() && wordLabel[word].getY() > 470) {
					wordLabel[word].setVisible(false); // 현재 게임 진행 화면에서 단어를 안보이게 함
					life--; // 라이프가 1 줄어듬
				}
			}

			// 각 라이프 갯수 별 gui 및 동작
			switch (life) {
			// 현재 남은 라이프가 5 일때
			case 5:
				// 라이프를 나타내는 모든 lifePanel이 빨간색
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.RED);
				lifePanel3.setBackground(Color.RED);
				lifePanel4.setBackground(Color.RED);
				lifePanel5.setBackground(Color.RED);
				break;
			// 현재 남은 라이프가 4 일때
			case 4:
				// 라이프를 나타내는 lifePanel 중 한 개를 검은색으로 하여, 라이프가 1 감소한 것을 나타냄
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.RED);
				lifePanel3.setBackground(Color.RED);
				lifePanel4.setBackground(Color.RED);
				lifePanel5.setBackground(Color.BLACK);
				break;
			// 현재 남은 라이프가 3 일때
			case 3:
				// 라이프를 나타내는 lifePanel 중 두 개를 검은색으로 하여, 라이프가 2 감소한 것을 나타냄
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.RED);
				lifePanel3.setBackground(Color.RED);
				lifePanel4.setBackground(Color.BLACK);
				lifePanel5.setBackground(Color.BLACK);
				break;
			// 현재 남은 라이프가 2 일때
			case 2:
				// 라이프를 나타내는 lifePanel 중 세 개를 검은색으로 하여, 라이프가 3 감소한 것을 나타냄
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.RED);
				lifePanel3.setBackground(Color.BLACK);
				lifePanel4.setBackground(Color.BLACK);
				lifePanel5.setBackground(Color.BLACK);
				break;
			// 현재 남은 라이프가 1 일때
			case 1:
				// 라이프를 나타내는 lifePanel 중 네 개를 검은색으로 하여, 라이프가 4 감소한 것을 나타냄
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.BLACK);
				lifePanel3.setBackground(Color.BLACK);
				lifePanel4.setBackground(Color.BLACK);
				lifePanel5.setBackground(Color.BLACK);
				break;
			// 현재 남은 라이프가 1 일때
			case 0:
				// 라이프를 나타내는 모든 lifePanel이 검은색
				lifePanel1.setBackground(Color.BLACK);
				lifePanel2.setBackground(Color.BLACK);
				lifePanel3.setBackground(Color.BLACK);
				lifePanel4.setBackground(Color.BLACK);
				lifePanel5.setBackground(Color.BLACK);

				gameOverLabel.setText(stage + " Stage Fail!"); // 게임 종료 시 최종 스테이지와 "Stage Fail!" 문구를 보여줌
				screenPanel.setVisible(false); // 게임 진행 화면 숨김
				gameOverPanel.setVisible(true); // 게임 종료 화면 보여줌
				gameOver_usernameLabel.setText(usernameLabel.getText()); // username을 보여줌
				gameOver_scoreLabel.setText(scoreLabel.getText()); // 최종 score를 보여줌
				goMenuButton.setVisible(true); // 초기 메뉴로 이동하는 버튼을 보여줌
				
				nowStarting = false; // 게임이 실행 중이지 않음

				new makeWords().interrupt(); // makeWords 스레드를 interrupt
				new moveWords().interrupt(); // moveWords 스레드를 interrupt
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 엔터키가 눌렸을 시
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// TextField에 입력이 있을 시
			if (!inputTextField.getText().equals("")) {
				String inputWord = inputTextField.getText().toString(); // inputWord에 입력을 저장

				for (int i = 0; i <= wordLabel.length; i++) {
					// 입력한 단어가 현재까지 생성된 단어들 중에 있을 시
					if (inputWord.equals(wordLabel[i].getText())) {
						// 입력한 단어가 현재 게임 진행 화면에 보여지고 있을 시
						if (wordLabel[i].isVisible()) {
							score += 10; // score가 10 증가
							remainWordCount--; // 다음 스테이지까지 남은 갯수를 저장하는 변수인 remainWordCount를 1 감소
							scoreLabel.setText("score : " + score + "점"); // 현재 점수를 갱신
							wordLabel[i].setVisible(false); // 입력된 단어를 현재 게임 진행 화면에서 사라지게 함
							inputTextField.setText(""); // 입력 TextField를 비움
							correctCheckLabel.setText("정답!"); // correctCheckLabel에 "정답!" 이라는 문구를 넣음
							correctCheckLabel.setVisible(true); // "정답!" 문구를 표시
							break;
						}
					}
					// 입력된 단어가 현재까지 생성된 단어 중에 없을 시
					else {
						inputTextField.setText(""); // 입력 TextField를 비움
						correctCheckLabel.setText("오답!"); // correctCheckLabel에 "오답!" 이라는 문구를 넣음
						correctCheckLabel.setVisible(true); // "오답!" 문구를 표시
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}