package com.ktdsuniversity.edu.datetime;

import java.util.Calendar;

/**
 * 데이터 타입 : 날짜
 */
public class CalenderExam {
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		System.out.println(now);

		System.out.println();
		System.out.println(now.get(Calendar.YEAR)); // 현재 연도
		System.out.println(now.get(Calendar.MONTH)); // 현재 월
		System.out.println(now.get(Calendar.DAY_OF_MONTH)); // 현재 일

		System.out.println(now.get(Calendar.DAY_OF_WEEK));
		// 현재 무슨 요일 : 1 - 일, 2 - 월, 3 - 화, ..... 7 -토

		String[] weekDays = { "일", "월", "화", "수", "목", "금", "토" };
		System.out.println(weekDays[now.get(Calendar.DAY_OF_WEEK) - 1] + "요일\n");

		// 2001-05-31 (목)
		Calendar birthdate = Calendar.getInstance();
		birthdate.set(2001, 5 - 1, 31);
		int week = birthdate.get(Calendar.DAY_OF_WEEK);
		System.out.println(weekDays[week - 1] + "요일");

		// 오늘 날짜부터 56일 후는 몇월 며칠 무슨 요일일까?
		Calendar now2 = Calendar.getInstance();
		now2.add(Calendar.DAY_OF_MONTH, 56);
		// now2에서 연,월,일,요일 추출 후 출력
		int year = now2.get(Calendar.YEAR);
		int month = now2.get(Calendar.MONTH) + 1; // Calendar.MONTH는 0부터 시작
		int day = now2.get(Calendar.DAY_OF_MONTH);
		System.out.println(
				year + "년 " + month + "월 " + day + "일 " + weekDays[now.get(Calendar.DAY_OF_WEEK) - 1] + "요일\n");

		// 2025년 2월 4일은 2026년 2월 4일보다 과거인가?
		Calendar now3 = Calendar.getInstance();
		Calendar past = Calendar.getInstance();
		past.set(2025, 2 - 1, 4);

		// now3의 시간이 1970년 1월 1일 0시 0분 0초 부터 얼마나 흘렀나?
		long nowTime = now3.getTimeInMillis();
		System.out.println(nowTime);

		long pastTime = past.getTimeInMillis();
		System.out.println(pastTime);

		if (pastTime < nowTime) {
			System.out.println("과거\n");
		}

		System.out.println();

		// 2026년 2월 4일 부터 2026년 7월 14일까지 며칠이 걸리나?
		Calendar now4 = Calendar.getInstance();

		int dayeCount = 0;
		while (true) {
			now4.add(Calendar.DAY_OF_MONTH, 1);
			dayeCount++;

			if (now4.get(Calendar.YEAR) == 2026 && now4.get(Calendar.MONTH) == 6
					&& now4.get(Calendar.DAY_OF_MONTH) == 14) {
				break;

			}
		}
		System.out.println(dayeCount + "일 결렸습니다.\n");

		// 오늘부터 7영업일 이후는 몇년 몇월 며칠 무슨 요일일까?
		Calendar now5 = Calendar.getInstance();

		int dDay = 7; // 7 영업일
		while (dDay > 0) {
			now5.add(Calendar.DAY_OF_MONTH, 1);
			int dayOfWeek = now5.get(Calendar.DAY_OF_WEEK);
			// 토, 일인 공휴일은 제거하고
			// Calendar.SUNDAY = 1, Calendar.SATURDAY = 7
			if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
				dDay--;
			}
		}

		int year1 = now5.get(Calendar.YEAR);
		int month1 = now5.get(Calendar.MONTH) + 1; // Calendar.MONTH는 0부터 시작
		int newday = now5.get(Calendar.DAY_OF_MONTH);

		System.out.println(
				year1 + "년 " + month1 + "월 " + newday + "일 " + weekDays[now5.get(Calendar.DAY_OF_WEEK) - 1] + "요일");
		

	}
}
