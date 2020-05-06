package TypingGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class makeRanking {
	static private ArrayList<String> rankList = new ArrayList<>(); // ���� ��ŷ�� �����ϴ� ArrayList
	static private ArrayList<String> scoreList = new ArrayList<>(); // �� ������ �ش��ϴ� �÷��̾��� score�� �����ϴ� ArrayList

	// ���� ��ŷ����Ʈ�� �� tuple �߰�
	void addRanking(String username, int score, String difficulty) {
		String scoreString; // score�� �����ϴ� string ����
		if (score < 10)
			scoreString = "00" + (Integer.toString(score));
		else if (score < 100)
			scoreString = "0" + (Integer.toString(score));
		else
			scoreString = (Integer.toString(score));

		rankList.add(username + scoreString + difficulty); // ���� ��ŷ����Ʈ�� ���ο� (username, scoreString, difficulty)�� "ȫ�浿80��" �������� �߰�
		scoreList.add(scoreString); // ���� scoreList�� ���ο� score �߰�
		updateRanking(); // ��ŷ�� ������Ʈ�ϴ� updateRanking �޼ҵ� ����
	}

	void updateRanking() {
		ArrayList<String> rankListTmp = new ArrayList<>(); // ��ŷ�� ������Ʈ�ϱ� ���� ����� �ӽ� ArrayList

		Collections.sort(scoreList, Comparator.reverseOrder()); // scoreList�� ������������ ����

		for (String s1 : scoreList) {
			for (String s2 : rankList) {
				String s2_sub = s2.substring(s2.length() - 4, s2.length() - 1); // substring�� �̿��Ͽ� s2_sub�� ��ŷ����Ʈ�� ���� �ε����� �ش��ϴ� ������ score ����
				
				// ���ĵ� scoreList�� ���� �ε����� �ش��ϴ� ������, s2���� �������� ������ s2_sub���� ���� ��
				if (s1.equals(s2_sub)) {
					rankListTmp.add(s2); // rankListTmp�� �ش� (username + scoreString + difficulty) ����
					rankList.remove(s2); // rankList���� �ش� (username + scoreString + difficulty) ����
					break;
				}
			}
		}

		rankList = rankListTmp; // rankListTmp�� rankList�� ����
	}

	ArrayList<String> getRankList() {
		return rankList; // ���� ��ŷ����Ʈ ��ȯ
	}

}