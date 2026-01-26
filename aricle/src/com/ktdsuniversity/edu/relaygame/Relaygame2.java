package com.ktdsuniversity.edu.relaygame;

import java.util.Scanner;

public class Relaygame2 {
	private Scanner keyboard;
	private String startWord; // 시작 단어
	private String nextWord; // 다음 단어

	public Relaygame2() {
		this.keyboard = new Scanner(System.in);
	}

	public void startRelaygame() {
		System.out.println("게임을 시작합니다.\n");
		System.out.print("시작 단어를 입력 : ");
		this.startWord = this.keyboard.nextLine(); // 시작 단어

		String lastLetter;

		while (true) {
			// 다음단어를 시작해
			System.out.print("다음 단어를 입력 : ");
			this.nextWord = this.keyboard.nextLine(); // 다음 단어
			this.nextWord = this.nextWord.trim(); // 공백 제거

			lastLetter = this.startWord.charAt(this.startWord.length() - 1) + ""; // 시작의 마지막 단어 추출
			if (this.nextWord.startsWith(lastLetter)) {
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
		Relaygame2 relaygame2 = new Relaygame2();
		relaygame2.startRelaygame();

	}

}
