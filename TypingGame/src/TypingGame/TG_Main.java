package TypingGame;

public class TG_Main {
	public static void main(String[] args) {
		TG_Main main = new TG_Main(); // TG_Main 객체 생성
		main.goMenuView(main); // 현재 main을 인수로 goMenuView 메소드 실행
	}

	public void goMenuView(TG_Main main) {
		// 초기 메뉴 화면 생성 및 이동
		TG_Menu menu = new TG_Menu(); // TG_Menu 객체 생성
		menu.setTGMain(main); // 현재 main을 init의 main에 set
	}

	public void startInitView(TG_Menu menu) {
		// 플레이어명 입력 및 난이도 선택 화면 생성 및 이동
		menu.dispose(); // 현재 보여지고 있는 menu 화면 종료
		TG_Main main = new TG_Main(); // TG_Main 객체 생성
		TG_Init init = new TG_Init(); // TG_Init 객체 생성
		init.setTGMain(main); // 현재 main을 init의 main에 set
	}

	public void startGameView(TG_Init init, String username, String difficulty) {
		// 게임 화면 생성 및 이동
		init.dispose(); // 현재 보여지고 있는 init 화면 종료
		TG_Game game = new TG_Game(username, difficulty); // 입력된 플레이어명과 선택된 난이도를 인수로 가지는 game 객체 생성
	}

	public void showRankingView(TG_Menu menu) {
		// 랭킹 화면 생성 및 이동
		menu.dispose(); // 현재 보여지고 있는 menu 화면 종료
		TG_Ranking ranking = new TG_Ranking(); // ranking 객체 생성
	}

	public void endGameView() {
		// 프로그램 종료
		System.exit(0);
	}

}
