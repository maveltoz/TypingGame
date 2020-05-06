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

	private JLabel selectMenuLabel; // ��� ���� ��
	private JButton startGameButton; // "���ӽ���" ��ư
	private JButton showRankingButton; // "��������" ��ư
	private JButton endGameButton; // "��������" ��ư

	public TG_Menu() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TypingGame");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 242, 230));

		selectMenuLabel = new JLabel("�޴��� �������ּ���.");
		selectMenuLabel.setBounds(130, 130, 400, 40);
		selectMenuLabel.setFont(new Font("����", Font.BOLD, 35));
		getContentPane().add(selectMenuLabel);

		startGameButton = new JButton("���ӽ���");
		startGameButton.setBounds(60, 280, 160, 60);
		startGameButton.setBackground(new Color(250, 244, 192));
		startGameButton.setForeground(Color.BLACK);
		startGameButton.setFont(new Font("����", Font.BOLD, 20));
		getContentPane().add(startGameButton);

		showRankingButton = new JButton("��������");
		showRankingButton.setBounds(210, 280, 160, 60);
		showRankingButton.setBackground(new Color(250, 244, 192));
		showRankingButton.setForeground(Color.BLACK);
		showRankingButton.setFont(new Font("����", Font.BOLD, 20));
		getContentPane().add(showRankingButton);

		endGameButton = new JButton("��������");
		endGameButton.setBounds(360, 280, 160, 60);
		endGameButton.setBackground(new Color(250, 244, 192));
		endGameButton.setForeground(Color.BLACK);
		endGameButton.setFont(new Font("����", Font.BOLD, 20));
		getContentPane().add(endGameButton);

		setVisible(true);

		startGameButton.addActionListener(this);
		showRankingButton.addActionListener(this);
		endGameButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// "���ӽ���" ��ư ������ �� �̺�Ʈ ó��
		if (e.getSource().equals(startGameButton)) {
			main.startInitView(this); // ���� ��ü�� �μ��� main�� startInitView �޼ҵ� ����
		}
		// "��������" ��ư ������ �� �̺�Ʈ ó��
		else if (e.getSource().equals(showRankingButton)) {
			main.showRankingView(this); // ���� ��ü�� �μ��� main�� showRankingView �޼ҵ� ����
		}
		// "��������" ��ư ������ �� �̺�Ʈ ó��
		else if (e.getSource().equals(endGameButton)) {
			main.endGameView(); // main�� endGameView �޼ҵ� ����
		}
	}

	public void setTGMain(TG_Main main) {
		this.main = main; // ���� ��ü�� main�� �Ű������� ���� �ǵ��ư� main�� set
	}
}