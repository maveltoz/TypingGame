package TypingGame;

public class TG_Main {
	public static void main(String[] args) {
		TG_Main main = new TG_Main(); // TG_Main ��ü ����
		main.goMenuView(main); // ���� main�� �μ��� goMenuView �޼ҵ� ����
	}

	public void goMenuView(TG_Main main) {
		// �ʱ� �޴� ȭ�� ���� �� �̵�
		TG_Menu menu = new TG_Menu(); // TG_Menu ��ü ����
		menu.setTGMain(main); // ���� main�� init�� main�� set
	}

	public void startInitView(TG_Menu menu) {
		// �÷��̾�� �Է� �� ���̵� ���� ȭ�� ���� �� �̵�
		menu.dispose(); // ���� �������� �ִ� menu ȭ�� ����
		TG_Main main = new TG_Main(); // TG_Main ��ü ����
		TG_Init init = new TG_Init(); // TG_Init ��ü ����
		init.setTGMain(main); // ���� main�� init�� main�� set
	}

	public void startGameView(TG_Init init, String username, String difficulty) {
		// ���� ȭ�� ���� �� �̵�
		init.dispose(); // ���� �������� �ִ� init ȭ�� ����
		TG_Game game = new TG_Game(username, difficulty); // �Էµ� �÷��̾��� ���õ� ���̵��� �μ��� ������ game ��ü ����
	}

	public void showRankingView(TG_Menu menu) {
		// ��ŷ ȭ�� ���� �� �̵�
		menu.dispose(); // ���� �������� �ִ� menu ȭ�� ����
		TG_Ranking ranking = new TG_Ranking(); // ranking ��ü ����
	}

	public void endGameView() {
		// ���α׷� ����
		System.exit(0);
	}

}
