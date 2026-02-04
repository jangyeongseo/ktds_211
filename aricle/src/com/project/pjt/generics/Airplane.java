package com.project.pjt.restaurant.stores.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Airplane - 비행기 편명과 좌석 상태를 관리하는 클래스 - 좌석 예약 가능 여부 확인 및 예약 처리 담당
 * 
 */
public class Airplane {

	private String name;
	private List<Reservation> seats; // 좌석 상태 목록

	public Airplane(String name, List<Reservation> seats) {
		this.name = name;
		this.seats = new ArrayList<>(seats);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** 좌석 현황 출력 예) 1: O, 2: X, 3: O */
	public void printSeats() {
		for (int i = 0; i < seats.size(); i++) {
			String status = (seats.get(i) == Reservation.POSSIBLE) ? "O" : "X";
			System.out.print((i + 1) + ": " + status);

			if (i < seats.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	/** 좌석 번호가 유효한 범위인지 확인 */
	public boolean isValidSeat(int seatNumber) {
		return seatNumber >= 1 && seatNumber <= seats.size();
	}

	/** 해당 좌석이 예약 가능한지 확인 */
	public boolean isAvailable(int seatNumber) {
		return seats.get(seatNumber - 1) == Reservation.POSSIBLE;
	}

	/** 좌석 예약 처리 */
	public void reserve(int seatNumber) {
		seats.set(seatNumber - 1, Reservation.IMPOSSIBLE);
	}

	/** 예약 가능한 좌석이 하나라도 존재하는지 확인 */
	public boolean hasAvailableSeat() {
		for (Reservation seat : seats) {
			if (seat == Reservation.POSSIBLE) {
				return true;
			}
		}
		return false;
	}
}