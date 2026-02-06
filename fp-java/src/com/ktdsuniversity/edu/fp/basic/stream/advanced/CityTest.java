package com.ktdsuniversity.edu.fp.basic.stream.advanced;

import java.util.List;
import java.util.Optional;

public class CityTest {
	public static void main(String[] args) {
		List<City> cities = City.loadCityData();
		// cities.forEach(System.out::println);

		// stateId 가 3907번인 City의 CountryName을 출력
//		for (City city : cities) {
//			if (city.getStateId() == 3907) {
//				System.out.println(city.getCountryName());
//			}
//		}

		// => Stream Code 로 구현
		cities.stream() // Stream<City>
				.filter(city -> city.getStateId() == 3907)// Stream<City>
				.map(City::getCountryName) // Stream<String>
				.distinct() // 중복 제거 // Stream<String>
				.forEach(city -> System.out.println("stateId 가 3907번인 City : " + city));

		System.out.println("=".repeat(40));

		// CountryName 가 "South Korea"인 City 의 _native 을 출력
		cities.stream().filter(city -> city.getCountryName().equals("South Korea"))
				.forEach(city -> System.out.println(city.get_native()));

		System.out.println("=".repeat(40));

		// CountryName 가 "South Korea"인 _native 의 길이가 3이상인 City 의 _native 을 출력
		cities.stream().filter(city -> city.getCountryName().equals("South Korea"))
				.filter(city -> city.get_native().length() >= 3).forEach(city -> System.out.println(city.get_native()));

		System.out.println("=".repeat(40));

		cities.stream().filter(city -> city.getCountryName().equals("South Korea"))
				.filter(city -> city.get_native().length() >= 3).skip(3).limit(3)
				.forEach(city -> System.out.println(city.get_native()));

		System.out.println("=".repeat(40));

		// _native 의 값이 한글로만 이루어진 값 중에서 _native 의 길이가 4글자 이상인 것의 name을 중복없이 조회
		cities.stream() // stream<City>
				.filter(city -> city.get_native().matches("^[가-힣]{4,}$")) // stream<City>
				.map(City::getName) // stream<String>
				.distinct() // stream<String>
				.map(String::length) // stream<Integer>
				.filter(length -> length >= 10) // stream<Integer>
				.distinct() // stream<Integer>
				.forEach(System.out::println);

		System.out.println("=".repeat(40));

		// 예> 한글로만 이루어져 있는가?
		String name = "이름";
		System.out.println(name + "은 한글로만 이루어진 4글자 이상의 이름 있다? " + name.matches("^[가-힣]{4,}$")); // 띄어쓰기 작성시 false

		// 애월읍의 stateNmwe 을 출력한다.
		Optional<City> found = cities.stream() // stream<City>
				.filter(city -> city.getName().equals("Gaigeturi")) // stream<City>
				.findFirst(); // Optional<City>

		System.out.println(found.orElse(null).getStateName()); // orElse 이걸 작성해야 안전하게 출력을 할 수 있다.

		Optional<City> found2 = cities.stream() // stream<City>
				.filter(city -> city.getName().equals("djlfaj")) // stream<City>
				.findFirst(); // Optional<City>

		// System.out.println(found2.orElse(null).getStateName());

		// Optional 사용하는 case1
		City city = found2.orElse(null);
		if (city != null) {
			System.out.println(city.getStateName());
		}

		// Optional 사용하는 case2
		if (found2.isPresent()) {
			System.out.println(found2.get().getStateName());
		}

		// Optional 사용하는 case3
		City city2 = found2.orElse(new City(""));
		System.out.println(city2.getStateName());

		System.out.println("=".repeat(40));

		// Stream 함수는 중간함수들을 최종함수가 있을 때만 반복을 시작함 - 실습
		cities.stream().peek(city3 -> System.out.println(city3.getCountryCode())).filter(city3 -> true)
				.map(city3 -> city.get_native());

		System.out.println("=".repeat(40));

	}
}
