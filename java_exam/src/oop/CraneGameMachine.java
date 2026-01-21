package oop;

import java.util.Random;

public class CraneGameMachine {
	boolean isInsertCoin; // 게임 할지 여부
	int dolls; // 인형 봅은 결과 값

	/**
	 * 인형뽑기 기계에 동전을 넣습니다. 동전을 넣지 않으면 doGame()은 실행되지 않아야 합니다. 동전을 넣으면 isInsertCoin
	 * 변수는 true가 되어야 합니다. dolls의 값이 0보다 작거나 같을 경우 isInsertCoin은 true로 변하지 않습니다.
	 * 
	 */

	public void isSertCoin() {
		if (dolls > 0) {
			isInsertCoin = true;
			System.out.println("게임 시작!");
		} else {
			System.out.println("게임 시작 전 동전을 넣어주세요.");

		}
	}

	/**
	 * isInsertCoin이 true 일 때 만 게임을 진행합니다. 만약, 인형을 뽑았다면 1을 반환하고 뽑지 못했다면 0을 반환합니다.
	 * 반환하는 숫자는 랜덤하게 선정합니다. 1을 반환했을 경우, dolls 변수에서 1을 빼야 하합니다. 값을 반환하기 직전에
	 * isInsertCoin을 false로 변경해야 합니다.
	 * 
	 * @return
	 */

	public int doGame() {
		if (isInsertCoin) {
			Random random = new Random();

			int result = random.nextInt(2);
			dolls -= result;
			isInsertCoin = false;
			return result;
		}
		return 0;
	}

}
