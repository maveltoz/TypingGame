package TypingGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class makeRanking {
	static private ArrayList<String> rankList = new ArrayList<>(); // 현재 랭킹을 저장하는 ArrayList
	static private ArrayList<String> scoreList = new ArrayList<>(); // 각 순위에 해당하는 플레이어의 score를 저장하는 ArrayList

	// 현재 랭킹리스트에 새 tuple 추가
	void addRanking(String username, int score, String difficulty) {
		String scoreString; // score를 저장하는 string 변수
		if (score < 10)
			scoreString = "00" + (Integer.toString(score));
		else if (score < 100)
			scoreString = "0" + (Integer.toString(score));
		else
			scoreString = (Integer.toString(score));

		rankList.add(username + scoreString + difficulty); // 현재 랭킹리스트에 새로운 (username, scoreString, difficulty)를 "홍길동80하" 형식으로 추가
		scoreList.add(scoreString); // 현재 scoreList에 새로운 score 추가
		updateRanking(); // 랭킹을 업데이트하는 updateRanking 메소드 실행
	}

	void updateRanking() {
		ArrayList<String> rankListTmp = new ArrayList<>(); // 랭킹을 업데이트하기 위해 사용한 임시 ArrayList

		Collections.sort(scoreList, Comparator.reverseOrder()); // scoreList를 내림차순으로 정렬

		for (String s1 : scoreList) {
			for (String s2 : rankList) {
				String s2_sub = s2.substring(s2.length() - 4, s2.length() - 1); // substring을 이용하여 s2_sub에 랭킹리스트의 현재 인덱스에 해당하는 원소의 score 저장
				
				// 정렬된 scoreList의 현재 인덱스에 해당하는 점수와, s2에서 점수만을 추출한 s2_sub값이 같을 시
				if (s1.equals(s2_sub)) {
					rankListTmp.add(s2); // rankListTmp에 해당 (username + scoreString + difficulty) 저장
					rankList.remove(s2); // rankList에서 해당 (username + scoreString + difficulty) 저장
					break;
				}
			}
		}

		rankList = rankListTmp; // rankListTmp를 rankList에 복사
	}

	ArrayList<String> getRankList() {
		return rankList; // 현재 랭킹리스트 반환
	}

}