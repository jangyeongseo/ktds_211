package com.project.pjt.restaurant.stores.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 비행기 편의 이름을 입력하면, 좌석 현황을 볼 수 있습니다. 비행기 편의 이름을 입력하세요: 0001 "0001" 편은 존재하지 않습니다.
 * 다른 비행기 편의 이름을 입력하세요: 0002 "0002" 편의 좌석 현황입니다. (O: 예약 가능, X: 예약 불가능) 1: O,
 * 2:O, 3: O, 4: O, 5: X, 6: O, 7: O, 8: O, 9: O
 * 
 * 좌석 예약을 하려면 번호를 입력하세요: 5 "5"번 좌석은 이미 예약된 좌석입니다. 다른 좌석을 입력하세요: 3 "3"번 좌석을
 * 예약하시겠습니까? (y/N): y "3"번 좌석이 예약되었습니다.
 * 
 * "0002" 편의 좌석 현황입니다. (O: 예약 가능, X: 예약 불가능) 1: O, 2: O, 3: X, 4: O, 5: X, 6: O,
 * 7: O, 8: O, 9: O
 * 
 * 비행기 편의 이름을 입력하면, 좌석 현황을 볼 수 있습니다. 비행기 편의 이름을 입력하세요: 0003 "0003" 편의 좌석
 * 현황입니다.(O: 예약 가능, X: 예약 불가능) 1: X, 2: X, 3: X, 4: X, 5: X, 6: X, 7: X, 8: X,
 * 9: X
 * 
 * 예약 가능한 좌석이 없습니다. 다른 비행기 편을 이용해 주세요. 비행기 편의 이름을 입력하면, 좌석 현황을 볼 수 있습니다. 비행기 편의
 * 이름을 입력하세요: 0004
 */
public class Airline {
	public static void main(String[] args) {
		List<Airplane> airplane = new ArrayList<>();
		Scanner sn = new Scanner(System.in);

		System.out.print("입력한 비행기 편 이름을 입력하세요: ");
		boolean airline = true;// 입력받은 문자열을 airline 변수에 저장

		while (airline) {
			System.out.print(airline + "편의 좌석 현황입니다. (O: 예약 가능, X: 예약 불가능)");

			for (int i = 0; i < airplane.size(); i++) {
				String a = (i + 1) + ". " + airplane.get(i); // * 2:O, 3: O, 4: O, 5: X, 6: O, 7: O, 8: O, 9: O
				System.out.println(a);
				if (a.equals("o")) {
					System.out.print("좌석 예약을 하려면 번호를 입력하세요: ");
					String num = sn.nextLine();
					System.out.print(num + "번 좌석을 예약하시겠습니까? (y/n) : ");
					String answer = sn.nextLine();

					if (answer.equals("y")) {
						System.out.println(num + "번 좌석이 예약되었습니다.");
					} else {
						continue;
					}

				}
			}

		}

		System.out.println(airplane.get(0).getName() + "편은 존재하지 않습니다.\n");
		System.out.print("다른 비행기 편의 이름을 입력하세요 : ");
		String aviation = sn.nextLine(); // 입력받은 문자열을 aviation 변수에 저장

	}

}
