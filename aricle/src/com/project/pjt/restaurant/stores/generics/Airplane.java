package com.project.pjt.restaurant.stores.generics;

import java.util.List;

/**
 * Airplane - 비행기 편명과 좌석 상태를 관리하는 클래스 - 좌석 예약 가능 여부 확인 및 예약 처리 담당
 * 
 */
public class Airplane {

	private String name; // 비행기 현
	private List<Boolean> seats; // 좌석 상태 목록 true = 예약됨(X) / false = 예약 가능(O)

	/**
	 * Airplane 생성자
	 *
	 * @param name  비행기 편명
	 * @param seats 좌석 상태 목록
	 */
	public Airplane(String name, List<Boolean> seats) {
		this.name = name;
		this.seats = seats;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Boolean> getSeats() {
		return this.seats;
	}

	public void setSeats(List<Boolean> seats) {
		this.seats = seats;
	}

	/**
	 * 좌석 현황 출력 예) 1: O, 2: X, 3: O
	 */
	public void printSeats() {
		for (int i = 0; i < seats.size(); i++) {
			System.out.print((i + 1) + ": " + (seats.get(i) ? "X" : "O"));

			if (i < seats.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	/**
	 * 좌석 번호가 유효한 범위인지 확인
	 *
	 * @param seatNumber 좌석 번호 (1부터 시작)
	 * @return 유효하면 true
	 */
	public boolean isValidSeat(int seatNumber) {
		return seatNumber >= 1 && seatNumber <= seats.size();
	}

	/**
	 * 해당 좌석이 예약 가능한지 확인
	 *
	 * @param seatNumber 좌석 번호
	 * @return 예약 가능하면 true
	 */
	public boolean isAvailable(int seatNumber) {
		return !seats.get(seatNumber - 1);
	}

	/**
	 * 좌석 예약 처리
	 *
	 * @param seatNumber 좌석 번호
	 */
	public void reserve(int seatNumber) {
		seats.set(seatNumber - 1, true);
	}

	/**
	 * 예약 가능한 좌석이 하나라도 존재하는지 확인
	 *
	 * @return 예약 가능한 좌석이 있으면 true
	 */
	public boolean hasAvailableSeat() {
		for (boolean seat : seats) {
			if (!seat) {
				return true;
			}
		}
		return false;
	}
}
