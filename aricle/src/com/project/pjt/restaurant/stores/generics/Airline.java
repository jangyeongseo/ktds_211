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
		Scanner sn = new Scanner(System.in);
		List<Airplane> airplanes = new ArrayList<>();

		// 비행기 데이터 초기화 false = 예약 가능(O) / true = 예약됨(X)
		// 0002편 : 5번 좌석만 예약된 상태
		airplanes.add(new Airplane("0002",
				new ArrayList<>(List.of(Reservation.POSSIBLE, Reservation.POSSIBLE, Reservation.POSSIBLE,
						Reservation.POSSIBLE, Reservation.IMPOSSIBLE, Reservation.POSSIBLE, Reservation.POSSIBLE,
						Reservation.POSSIBLE, Reservation.POSSIBLE))));

		// 0003편 : 모든 좌석이 예약된 상태
		airplanes.add(new Airplane("0003",
				new ArrayList<>(List.of(Reservation.IMPOSSIBLE, Reservation.IMPOSSIBLE, Reservation.IMPOSSIBLE,
						Reservation.IMPOSSIBLE, Reservation.IMPOSSIBLE, Reservation.IMPOSSIBLE, Reservation.IMPOSSIBLE,
						Reservation.IMPOSSIBLE, Reservation.IMPOSSIBLE))));

		// 프로그램 종료 조건이 없으므로 무한 반복
		while (true) {

			// 비행기 편명 입력
			System.out.print("비행기 편의 이름을 입력하세요: ");
			String input = sn.nextLine();

			// 입력받은 편명에 해당하는 비행기 객체
			Airplane selected = null;

			// 비행기 목록에서 편명 검색
			for (Airplane a : airplanes) {
				if (a.getName().equals(input)) {
					selected = a;
					break;
				}
			}

			// 존재하지 않는 비행기 편일 경우
			if (selected == null) {
				System.out.println("\"" + input + "\" 편은 존재하지 않습니다.");
				continue; // 다시 편명 입력으로
			}

			// 좌석 현황 출력
			System.out.println("\"" + input + "\" 편의 좌석 현황입니다. (O: 예약 가능, X: 예약 불가능)");
			selected.printSeats();

			// 예약 가능한 좌석이 하나도 없을 경우
			if (!selected.hasAvailableSeat()) {
				System.out.println("예약 가능한 좌석이 없습니다.");
				continue;
			}

			// 좌석 번호 입력
			System.out.print("좌석 번호를 입력하세요: ");
			int seat = Integer.parseInt(sn.nextLine());

			// 좌석 번호 범위 검사
			if (!selected.isValidSeat(seat)) {
				System.out.println("존재하지 않는 좌석입니다.");
				continue;
			}

			// 이미 예약된 좌석인지 확인
			if (!selected.isAvailable(seat)) {
				System.out.println("이미 예약된 좌석입니다.");
				continue;
			}

			// 예약 확인
			System.out.print(seat + "번 좌석을 예약하시겠습니까? (y/n): ");
			if (sn.nextLine().equalsIgnoreCase("y")) {
				selected.reserve(seat);
				System.out.println(seat + "번 좌석이 예약되었습니다.");
			}
		}
	}
}