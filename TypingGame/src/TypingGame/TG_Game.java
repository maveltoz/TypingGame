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
	private int life = 5; // ���� ���� life�� �����ϴ� ����
	private int score = 0; // ���� score�� �����ϴ� ����
	private int createWordsCount = 0; // ������� ������ �ܾ���� �����ϴ� ����
	private int speed; // ���� �ܾ �������� �ӵ��� �����ϴ� ����
	private int remainWordCount; // ���� ������������ ���� �ܾ� ������ �����ϴ� ����
	private int stage = 1; // ���� ���������� �����ϴ� ����
	private int nowWordsCount = 0; // ���� ������ �ܾ �ܾ��Ʈ�� �� ��° �ܾ������� �����ϴ� ����
	private boolean nowStarting = false; // ���� ������ ���� �������� �����ϴ� ����

	private String username; // ���� �÷��̾���� �����ϴ� ����
	private String difficulty; // ���� ���̵��� �����ϴ� ����
	private String[] nowDataSet; // ���� ���̵��� �ش��ϴ� dataSet�� �����ϴ� �迭
	private String[] dataSet1 = { "�ڹ�", "�ڵ�", "����", "��å", "�޷�", "�ູ" }; // ���̵� '��'�� �ش��ϴ� �ܾ��Ʈ
	private String[] dataSet2 = { "�ڹ�", "�ڵ�", "����", "��å", "�޷�", "�ູ", "������", "�չ㻧", "�Ƹ���Ƽ��", "�̲���Ʋ", "���̾", "�Ѹ�Ŭ",
			"��⽾", "elephant", "science" }; // ���̵� '��'�� �ش��ϴ� �ܾ��Ʈ
	private String[] dataSet3 = { "�ڹ�", "�ڵ�", "����", "��å", "�޷�", "�ູ", "������", "�չ㻧", "�Ƹ���Ƽ��", "�̲���Ʋ", "���̾", "�Ѹ�Ŭ",
			"��⽾", "elephant", "science", "���ձ���", "��ϴ�", "�Ը�����", "particular", "anthology", "�����ڴ�", "��������", "��ǳ���",
			"�����ݷ�", "�ҵ��ҵ�" }; // ���̵� '��'�� �ش��ϴ� �ܾ��Ʈ

	private JPanel screenPanel; // ���� ���� ȭ�� Panel
	private JPanel lifePanel1; // ������ Panel
	private JPanel lifePanel2; // ������ Panel
	private JPanel lifePanel3; // ������ Panel
	private JPanel lifePanel4; // ������ Panel
	private JPanel lifePanel5; // �������� ��Ÿ���� Panel
	private JPanel gameOverPanel; // ���� ���� ȭ�� Panel
	private JPanel deadLinePanel; // ������� Panel

	private JTextField inputTextField; // �ܾ �Է��� TextField
	private JButton startGameButton; // ���� ���� ��ư
	private JButton goMenuButton; // �ʱ� �޴��� �̵��ϴ� ��ư

	private JLabel wordLabel[] = new JLabel[10000]; // ������ �ܾ���� ��Ÿ���� ��
	private JLabel usernameLabel; // username ���� ��
	private JLabel difficultyLabel; // ���̵� ��
	private JLabel stageLabel; // stage ���� ��
	private JLabel stageUpLabel; // stage ��� �� �˷��ִ� ���� ��
	private JLabel scoreLabel; // score ���� ��
	private JLabel correctCheckLabel; // �������� ���������� �˷��ִ� ���� ��
	private JLabel gameOverLabel; // ���� ���� �� ��� ���� ��
	private JLabel gameOver_scoreLabel; // ���� ���� �� ȹ���� score�� �˷��ִ� ��
	private JLabel gameOver_usernameLabel; // ���� ���� �� username�� �˷��ִ� ��

	public TG_Game(String username, String difficulty) {
		this.username = username; // ���� ��ü�� username�� �Ű������� ���� username�� ����
		this.difficulty = difficulty; // ���� ��ü�� difficulty�� �Ű������� ���� difficulty�� ����

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
		startGameButton.setFont(new Font("����", Font.BOLD, 23));
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

		correctCheckLabel = new JLabel("����!");
		correctCheckLabel.setFont(new Font("����", Font.BOLD, 23));
		correctCheckLabel.setForeground(Color.red);
		correctCheckLabel.setBounds(340, 512, 150, 50);
		screenPanel.add(correctCheckLabel);
		correctCheckLabel.setVisible(false); // �������� ���������� �˷��ִ� correctCheckLabel�� �ʱ⿡�� �Ⱥ��̰� ��

		usernameLabel = new JLabel("Username : " + username);
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("����", Font.BOLD, 18));
		usernameLabel.setBounds(830, 80, 300, 30);
		getContentPane().add(usernameLabel);

		difficultyLabel = new JLabel("���̵� : " + difficulty);
		difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		difficultyLabel.setFont(new Font("����", Font.BOLD, 18));
		difficultyLabel.setBounds(900, 180, 150, 50);
		getContentPane().add(difficultyLabel);

		stageLabel = new JLabel("Stage : " + Integer.toString(stage));
		stageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stageLabel.setFont(new Font("����", Font.BOLD, 18));
		stageLabel.setBounds(900, 280, 150, 50);
		getContentPane().add(stageLabel);

		stageUpLabel = new JLabel("Stage Up!");
		stageUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stageUpLabel.setFont(new Font("����", Font.BOLD, 15));
		stageUpLabel.setForeground(Color.red);
		stageUpLabel.setBounds(1000, 281, 150, 50);
		getContentPane().add(stageUpLabel);
		stageUpLabel.setVisible(false); // stage ����� �˷��ִ� stageUpLabel�� �ʱ⿡�� �Ⱥ��̰� ��

		scoreLabel = new JLabel("Score : " + score + "��");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("����", Font.BOLD, 18));
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
		gameOverPanel.setVisible(false); // ���� ���� ȭ���� ��Ÿ���� gameOverPanel�� �ʱ⿡�� �Ⱥ��̰� ��
		getContentPane().add(gameOverPanel);
		gameOverPanel.setLayout(null);

		gameOverLabel = new JLabel("Game Over!");
		gameOverLabel.setBounds(0, 40, 800, 60);
		gameOverPanel.add(gameOverLabel);
		gameOverLabel.setForeground(Color.RED);
		gameOverLabel.setFont(new Font("����", Font.BOLD, 50));
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);

		gameOver_usernameLabel = new JLabel();
		gameOver_usernameLabel.setBounds(0, 120, 800, 40);
		gameOverPanel.add(gameOver_usernameLabel);
		gameOver_usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOver_usernameLabel.setForeground(Color.BLACK);
		gameOver_usernameLabel.setFont(new Font("����", Font.BOLD, 40));

		gameOver_scoreLabel = new JLabel("Score");
		gameOver_scoreLabel.setBounds(0, 200, 800, 40);
		gameOverPanel.add(gameOver_scoreLabel);
		gameOver_scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOver_scoreLabel.setForeground(Color.BLACK);
		gameOver_scoreLabel.setFont(new Font("����", Font.BOLD, 40));

		goMenuButton = new JButton("Menu");
		goMenuButton.setFont(new Font("����", Font.BOLD, 30));
		goMenuButton.setBounds(900, 600, 200, 80);
		goMenuButton.setBackground(new Color(237, 160, 227));
		getContentPane().add(goMenuButton);
		goMenuButton.setVisible(false); // ���� ���� �� �ʱ� �޴��� �̵��ϴ� ��ư�� goMenuButton�� �ʱ⿡�� �Ⱥ��̰� ��

		setVisible(true);

		startGameButton.addActionListener(this);
		goMenuButton.addActionListener(this);
		inputTextField.addKeyListener(this);
		inputTextField.requestFocus();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// "Start!" ��ư ������ �� �̺�Ʈ ó��
		if (e.getSource().equals(startGameButton)) {
			// ���� ������ ���� �������� Ȯ��
			if (!nowStarting) {
				nowStarting = true; // ���� ���� ��
				inputTextField.requestFocus();
				new makeWords().start(); // �ܾ �����ϴ� makeWords ������ ����
			}
		}
		// ���� ���� �� "Menu" ��ư ������ �� �̺�Ʈ ó��
		else if (e.getSource().equals(goMenuButton)) {
			makeRanking nowRankingList = new makeRanking(); // makeRanking ��ü�� ����
			nowRankingList.addRanking(username, score, difficulty); // ������ (username, score, difficulty)�� �μ��� nowRankingList�� addRanking �޼ҵ� ����

			this.dispose(); // ���� �������� �ִ� ���� ���� ȭ�� ����
			TG_Main main2 = new TG_Main(); // TG_Main ��ü ����
			main2.goMenuView(main2); // main2�� goMenuView �޼ҵ� ����
		}
	}

	// �ܾ �����ϴ� ������
	class makeWords extends Thread {
		@Override
		public void run() {
			screenPanel.setVisible(true);

			// ���̵� '��' �϶�
			if (difficulty.equals("��")) {
				nowDataSet = dataSet1; // nowDataSet�� ���̵� '��'�� �ش��ϴ� dataSet1�� ����
			}
			// ���̵� '��' �϶�
			else if (difficulty.equals("��")) {
				nowDataSet = dataSet2; // nowDataSet�� ���̵� '��'�� �ش��ϴ� dataSet2�� ����
			}
			// ���̵� '��' �϶�
			else if (difficulty.equals("��")) {
				nowDataSet = dataSet3; // nowDataSet�� ���̵� '��'�� �ش��ϴ� dataSet3�� ����
			}

			remainWordCount = nowDataSet.length; // remainWordCount�� ���� �ܾ��Ʈ�� �ܾ� ������ ����

			for (createWordsCount = 0; createWordsCount < 10000; createWordsCount++) {
				try {
					
					// �ܾ��Ʈ�� �ִ� �ܾ �� ���� �� �������� ��
					if (nowWordsCount == nowDataSet.length) {
						nowWordsCount = 0; // �ٽ� �ܾ��Ʈ�� 0��° �ܾ���� �����ϵ��� ��
					}
					
					stageUpLabel.setVisible(false); // ���������� �����ߴٴ� ������ ǥ���ϴ� stageUpLabel�� �Ⱥ��̰� ��
					
					// remainWordCount�� 0�� ��, ��, ���� �ܾ��Ʈ�� �ִ� �ܾ���� �� ���� �� �Է����� �� �������� ���
					if (remainWordCount == 0) {
						remainWordCount = nowDataSet.length; // remainWordCount�� �ٽ� ���� �ܾ��Ʈ�� �ܾ� ������ ����
						stage++; // �������� ���
						
						// �������� 1���� �������� 2�� ����� ��, �������� 2���� �������� 3���� ����� ��
						if (stage < 4)
							stageUpLabel.setVisible(true); // ���������� ����ߴٴ� ������ ǥ��
					}

					// �������� 3���� ���� ���� ��
					if (stage == 4) {
						stage--; // ���������� 3���� ����
						screenPanel.setVisible(false); // ���� ���� ȭ���� ����
						gameOverPanel.setVisible(true); // ���� ���� ȭ���� ������
						goMenuButton.setVisible(true); // �ʱ� �޴��� ���� ��ư�� ������
						gameOver_usernameLabel.setForeground(Color.BLUE);
						gameOverLabel.setText("All Stage Clear!"); // "All Stage Clear!" ������ ������
						gameOver_usernameLabel.setText(usernameLabel.getText()); // username�� ������
						gameOver_scoreLabel.setText(scoreLabel.getText()); // ���� score�� ������

						new makeWords().interrupt(); // makeWords �����带 interrupt
						new moveWords().interrupt(); // moveWords �����带 interrupt
					}

					stageLabel.setText("�������� : " + Integer.toString(stage)); // stageLabel�� ���� ���������� ǥ��

					// �������� 1 �ϋ�
					if (stage == 1)
						speed = 900; // �ܾ �������� �ӵ��� ��Ÿ���� speed ������ 900�� ����
					// �������� 2 �϶�
					else if (stage == 2)
						speed = 700; // �ܾ �������� �ӵ��� ��Ÿ���� speed ������ 700�� ����
					// �������� 3 �϶�
					else if (stage == 3)
						speed = 500; // �ܾ �������� �ӵ��� ��Ÿ���� speed ������ 500�� ����
					else if (stage == 4)
						speed = 300;

					Random random = new Random(); // Random ��ü ����

					wordLabel[createWordsCount] = new JLabel(nowDataSet[nowWordsCount++]); // ���ο� �ܾ ����
					wordLabel[createWordsCount].setBounds(0, 0, 130, 70);
					screenPanel.add(wordLabel[createWordsCount]); // ������ �ܾ ���� ���� ȭ�鿡 ��Ÿ��
					wordLabel[createWordsCount].setLocation(random.nextInt(678), 2); // �����Ǵ� �ܾ�� ������ x��ǥ�� ������ ����

					new moveWords().start(); // �ܾ �����̰� �ϴ� moveWords ������ ����

					Thread.sleep(speed); // speed�� �� ��ŭ �����带 sleep ���� �ӵ��� ����

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// �ܾ �����̴� ������
	class moveWords extends Thread {
		@Override
		public void run() {
			for (int word = 0; word <= createWordsCount; word++) {
				// �ܾ ���� ���� ���� ȭ�鿡 ���� ��
				if (wordLabel[word].isVisible()) {
					int xPosition = wordLabel[word].getX(); // �ܾ��� x��ǥ
					int yPosition = wordLabel[word].getY(); // �ܾ��� y��ǥ

					wordLabel[word].setLocation(xPosition, yPosition + 15); // �ܾ��� y��ǥ�� 15 ������Ŵ���ν�, �ܾ �������� ��ó�� ���̰� ��
				}

				// �ܾ ��������� �Ѿ��� ��
				if (wordLabel[word].isVisible() && wordLabel[word].getY() > 470) {
					wordLabel[word].setVisible(false); // ���� ���� ���� ȭ�鿡�� �ܾ �Ⱥ��̰� ��
					life--; // �������� 1 �پ��
				}
			}

			// �� ������ ���� �� gui �� ����
			switch (life) {
			// ���� ���� �������� 5 �϶�
			case 5:
				// �������� ��Ÿ���� ��� lifePanel�� ������
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.RED);
				lifePanel3.setBackground(Color.RED);
				lifePanel4.setBackground(Color.RED);
				lifePanel5.setBackground(Color.RED);
				break;
			// ���� ���� �������� 4 �϶�
			case 4:
				// �������� ��Ÿ���� lifePanel �� �� ���� ���������� �Ͽ�, �������� 1 ������ ���� ��Ÿ��
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.RED);
				lifePanel3.setBackground(Color.RED);
				lifePanel4.setBackground(Color.RED);
				lifePanel5.setBackground(Color.BLACK);
				break;
			// ���� ���� �������� 3 �϶�
			case 3:
				// �������� ��Ÿ���� lifePanel �� �� ���� ���������� �Ͽ�, �������� 2 ������ ���� ��Ÿ��
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.RED);
				lifePanel3.setBackground(Color.RED);
				lifePanel4.setBackground(Color.BLACK);
				lifePanel5.setBackground(Color.BLACK);
				break;
			// ���� ���� �������� 2 �϶�
			case 2:
				// �������� ��Ÿ���� lifePanel �� �� ���� ���������� �Ͽ�, �������� 3 ������ ���� ��Ÿ��
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.RED);
				lifePanel3.setBackground(Color.BLACK);
				lifePanel4.setBackground(Color.BLACK);
				lifePanel5.setBackground(Color.BLACK);
				break;
			// ���� ���� �������� 1 �϶�
			case 1:
				// �������� ��Ÿ���� lifePanel �� �� ���� ���������� �Ͽ�, �������� 4 ������ ���� ��Ÿ��
				lifePanel1.setBackground(Color.RED);
				lifePanel2.setBackground(Color.BLACK);
				lifePanel3.setBackground(Color.BLACK);
				lifePanel4.setBackground(Color.BLACK);
				lifePanel5.setBackground(Color.BLACK);
				break;
			// ���� ���� �������� 1 �϶�
			case 0:
				// �������� ��Ÿ���� ��� lifePanel�� ������
				lifePanel1.setBackground(Color.BLACK);
				lifePanel2.setBackground(Color.BLACK);
				lifePanel3.setBackground(Color.BLACK);
				lifePanel4.setBackground(Color.BLACK);
				lifePanel5.setBackground(Color.BLACK);

				gameOverLabel.setText(stage + " Stage Fail!"); // ���� ���� �� ���� ���������� "Stage Fail!" ������ ������
				screenPanel.setVisible(false); // ���� ���� ȭ�� ����
				gameOverPanel.setVisible(true); // ���� ���� ȭ�� ������
				gameOver_usernameLabel.setText(usernameLabel.getText()); // username�� ������
				gameOver_scoreLabel.setText(scoreLabel.getText()); // ���� score�� ������
				goMenuButton.setVisible(true); // �ʱ� �޴��� �̵��ϴ� ��ư�� ������
				
				nowStarting = false; // ������ ���� ������ ����

				new makeWords().interrupt(); // makeWords �����带 interrupt
				new moveWords().interrupt(); // moveWords �����带 interrupt
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// ����Ű�� ������ ��
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// TextField�� �Է��� ���� ��
			if (!inputTextField.getText().equals("")) {
				String inputWord = inputTextField.getText().toString(); // inputWord�� �Է��� ����

				for (int i = 0; i <= wordLabel.length; i++) {
					// �Է��� �ܾ ������� ������ �ܾ�� �߿� ���� ��
					if (inputWord.equals(wordLabel[i].getText())) {
						// �Է��� �ܾ ���� ���� ���� ȭ�鿡 �������� ���� ��
						if (wordLabel[i].isVisible()) {
							score += 10; // score�� 10 ����
							remainWordCount--; // ���� ������������ ���� ������ �����ϴ� ������ remainWordCount�� 1 ����
							scoreLabel.setText("score : " + score + "��"); // ���� ������ ����
							wordLabel[i].setVisible(false); // �Էµ� �ܾ ���� ���� ���� ȭ�鿡�� ������� ��
							inputTextField.setText(""); // �Է� TextField�� ���
							correctCheckLabel.setText("����!"); // correctCheckLabel�� "����!" �̶�� ������ ����
							correctCheckLabel.setVisible(true); // "����!" ������ ǥ��
							break;
						}
					}
					// �Էµ� �ܾ ������� ������ �ܾ� �߿� ���� ��
					else {
						inputTextField.setText(""); // �Է� TextField�� ���
						correctCheckLabel.setText("����!"); // correctCheckLabel�� "����!" �̶�� ������ ����
						correctCheckLabel.setVisible(true); // "����!" ������ ǥ��
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