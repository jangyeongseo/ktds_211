package com.ktdsuniversity.edu.relaygame;

import java.util.Scanner;

/**
 * 실습
 */
public class Relaygame {
	private Scanner keyboard;
	private String startWord;
	private String nextWord;

	public Relaygame() {
		this.keyboard = new Scanner(System.in);
	}

	public void startGame() {
		System.out.println("게임 시작");
		System.out.print("사작 단어 : ");
		this.startWord = this.keyboard.nextLine(); // 시작

		String lastLetter;
		String firstLetter;

		// 무한 반복
		while (true) {
			System.out.print("다음 단어 : ");
			this.nextWord = this.keyboard.nextLine(); // 다음
			this.nextWord = this.nextWord.trim(); // 제거된 결과 할당

			// 첫 번째 글자의 마지막 글자 추출
			// 기찻길
			// 시작 단어의 마지막 글자
			lastLetter = this.startWord.charAt(this.startWord.length() - 1) + "";

			// 다음 단어의 첫 글자
			firstLetter = this.nextWord.substring(0, 1);

			if (lastLetter.equals(firstLetter)) {
				if (this.nextWord.length() >= 3) {
					this.startWord = this.nextWord; // 다음 단어를 새로운 시작 단어로 설정
				} else {
					System.out.println("게임 종료 (3글자 미만)");
					break;
				}
			} else {
				System.out.println("게임 종료 (글자 불일치)");
				break;
			}

		}

	}

	public static void main(String[] args) {
		Relaygame game = new Relaygame();
		game.startGame();

	}

}
