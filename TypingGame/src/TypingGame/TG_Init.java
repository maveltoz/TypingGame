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

	public String username; // �Է¹��� �÷��̾���� �����ϴ� ����
	public String difficulty; // �Է¹��� ���̵��� �����ϴ� ����

	private JLabel initLabel; // ��� ���� ��
	private JLabel usernameLabel; // username ���� ��
	private JTextField usernameTextField; // �÷��̾���� �Է��� TextField
	private JLabel difficultyLabel; // ���̵� ���� ��
	private JButton difficultyButton1; // ���̵� '��' ��ư
	private JButton difficultyButton2; // ���̵� '��' ��ư
	private JButton difficultyButton3; // ���̵� '��' ��ư

	public TG_Init() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TypingGame");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 242, 230));

		initLabel = new JLabel("�÷��̾���� �Է��ϰ� ���̵��� �������ּ���.");
		initLabel.setFont(new Font("����", Font.BOLD, 20));
		initLabel.setBounds(90, 80, 600, 50);
		getContentPane().add(initLabel);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(190, 190, 250, 60);
		usernameTextField.setFont(new Font("����", Font.BOLD, 20));
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(15);

		difficultyButton1 = new JButton("��");
		difficultyButton1.setBounds(190, 300, 81, 81);
		difficultyButton1.setBackground(new Color(250, 244, 192));
		difficultyButton1.setForeground(Color.BLACK);
		difficultyButton1.setFont(new Font("����", Font.BOLD, 20));
		getContentPane().add(difficultyButton1);

		difficultyButton2 = new JButton("��");
		difficultyButton2.setBounds(290, 300, 81, 81);
		difficultyButton2.setBackground(new Color(250, 244, 192));
		difficultyButton2.setForeground(Color.BLACK);
		difficultyButton2.setFont(new Font("����", Font.BOLD, 20));
		getContentPane().add(difficultyButton2);

		difficultyButton3 = new JButton("��");
		difficultyButton3.setBounds(390, 300, 81, 81);
		difficultyButton3.setBackground(new Color(250, 244, 192));
		difficultyButton3.setForeground(Color.BLACK);
		difficultyButton3.setFont(new Font("����", Font.BOLD, 20));
		getContentPane().add(difficultyButton3);

		usernameLabel = new JLabel("<username>");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("����", Font.BOLD, 20));
		usernameLabel.setBounds(15, 200, 188, 47);
		getContentPane().add(usernameLabel);

		difficultyLabel = new JLabel("<���̵�>");
		difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		difficultyLabel.setFont(new Font("����", Font.BOLD, 20));
		difficultyLabel.setBounds(15, 320, 188, 47);
		getContentPane().add(difficultyLabel);
		setVisible(true);

		difficultyButton1.addActionListener(this);
		difficultyButton2.addActionListener(this);
		difficultyButton3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ���̵� "��" ��ư ������ �� �̺�Ʈ ó��
		if (e.getSource().equals(difficultyButton1)) {
			// username�� �ԷµǾ��� ��
			if (!usernameTextField.getText().equals("")) {
				username = usernameTextField.getText().toString(); // username�� �Էµ� Text�� ����
				difficulty = "��"; // difficulty�� "��"�� ����
				main.startGameView(this, username, difficulty); // (���� ��ü, username, difficulty)�� �μ��� main�� startGameView �޼ҵ� ����
			}
			// username�� �Էµ��� �ʾ��� ��
			else {
				initLabel.setText("�г����� �Է����ּ���."); // initLabel�� "�г����� �Է����ּ���" ���� ����
			}

		}
		// ���̵� "��" ��ư ������ �� �̺�Ʈ ó��
		else if (e.getSource().equals(difficultyButton2)) {
			// username�� �ԷµǾ��� ��
			if (!usernameTextField.getText().equals("")) {
				username = usernameTextField.getText().toString(); // username�� �Էµ� Text�� ����
				difficulty = "��"; // difficulty�� "��"�� ����
				main.startGameView(this, username, difficulty); // (���� ��ü, username, difficulty)�� �μ��� main�� startGameView �޼ҵ� ����
			}
			// username�� �Էµ��� �ʾ��� ��
			else {
				initLabel.setText("�г����� �Է����ּ���."); // initLabel�� "�г����� �Է����ּ���" ���� ����
			}
		}
		// ���̵� "��" ��ư ������ �� �̺�Ʈ ó��
		else if (e.getSource().equals(difficultyButton3)) {
			// username�� �ԷµǾ��� ��
			if (!usernameTextField.getText().equals("")) {
				username = usernameTextField.getText().toString(); // username�� �Էµ� Text�� ����
				difficulty = "��"; // difficulty�� "��"�� ����
				main.startGameView(this, username, difficulty); // (���� ��ü, username, difficulty)�� �μ��� main�� startGameView �޼ҵ� ����
			}
			// username�� �Էµ��� �ʾ��� ��
			else {
				initLabel.setText("�г����� �Է����ּ���."); // initLabel�� "�г����� �Է����ּ���" ���� ����
			}
		}
	}

	public void setTGMain(TG_Main main) {
		this.main = main; // ���� ��ü�� main�� �Ű������� ���� �ǵ��ư� main�� set
	}
}