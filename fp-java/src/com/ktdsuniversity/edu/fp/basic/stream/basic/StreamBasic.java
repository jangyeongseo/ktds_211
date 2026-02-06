package com.ktdsuniversity.edu.fp.basic.stream.basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ktdsuniversity.edu.fp.basic.stream.object.Dish;
import com.ktdsuniversity.edu.fp.basic.stream.object.DishList;
import com.ktdsuniversity.edu.fp.basic.stream.object.DishType;
import com.ktdsuniversity.edu.fp.basic.stream.object.FoodType;

public class StreamBasic {
	public void printDishUseFor() {
		// 전체의 basic를 출력
		System.out.println("전체 요리 목록 - 칼로리사 500 미만인 && FoodType이 MEAT && DishType이 FISH인 - for");
		List<Dish> dishList = DishList.makeDishList();
		for (Dish d : dishList) {
			if (d.getCalories() < 500 && d.getFoodType() == FoodType.MEAT.MEAT && d.getDishType() == DishType.FISH) {
				System.out.println(d);
			}
		}

	}

	public void printDishUseStream() {
		System.out.println("\n전체 요리 목록 - 칼로리사 500 미만인 && FoodType이 MEAT - List.forEach");
		List<Dish> dishList = DishList.makeDishList();
		// public interface Consumer<T> { void accept(T t);}
		// Consumer<? super Dish>;
		// dishList.forEach(dish -> System.out.println(dish)); 전체 요리 목록
		// dishList.forEach(System.out::println); // 잘 사용안함
		dishList.forEach(dish -> {
			if (dish.getCalories() < 500 && dish.getFoodType() == FoodType.MEAT.MEAT
					&& dish.getDishType() == DishType.FISH) {
				System.out.println(dish);
			}
		});

		/*
		 * - dishList.stream() → List<Dish>를 Stream<Dish>로 변환 - .filter(dish ->
		 * dish.getCalories() < 500) → 칼로리 500 미만인 요리만 걸러냄 - .forEach(dish ->
		 * System.out.println(dish)) → 필터링된 요리를 출력
		 */
		System.out.println("\n전체 요리 목록 - 칼로리사 500 미만인 && FoodType이 MEAT - Stream");
		// Stream 을 사용할 수 있는 대상 -> List, Set
		dishList // List<Dish>
				.stream() // Stream<Dish>
//				.peek(dish -> {
//					System.out.println("첫번째 file를 수행하기 이전에 인스턴스 값");
//					System.out.println("1. " + dish.getName());
//					System.out.println("1. " + dish.getCalories());
//					System.out.println("1. " + dish.getFoodType());
//					System.out.println("1. " + dish.getDishType() + "\n");
//				}) // Stream<Dish> (현재 반복중인 인스턴스를 확인 - 디버깅 용도)

				.filter(dish -> dish.getCalories() < 500).filter(dish -> dish.getFoodType() == FoodType.MEAT.MEAT)
				.filter(dish -> dish.getDishType() == DishType.FISH)
				// Stream<Dish> - Stream<T> filter(Predicate<? super T> predicate); -> T : Dish
				// 이고 Predicate : boolean 값
				// filter 는 여러번 사용이 가능
				.forEach(dish -> System.out.println(dish));

	}

	public void printEvenNumbers() {
		List<Integer> number = Arrays.asList(1, 15, 46, 45, 78, 2, 45, 26, 16, 14, 19, 34, 2, 15, 18, 54, 97, 61, 15,
				48, 1, 23, 55);

		// 1. numbers 에 있는 값을 전부 2를 곱해서 짝수로 만들어서 출력
		number.stream().map(numbers -> numbers * 2).forEach(numbers -> System.out.println("2를 곱해서 짝수 : " + numbers));

		System.out.println("=".repeat(40));

		// 2. numbers 에 있는 값에서 중복된 숫자는 모두 제거하고 나머지 숫자에 전부 2를 곱해서 짝수로 만들어 출력
		number.stream() // stream<Integer>
				.distinct() // 중복 제거 stream<Integer>
				.map(numbers -> numbers * 2) // stream<Integer>
				.forEach(numbers -> System.out.println("중복 제거 후 2를 곱함 : " + numbers));

	}

	public String makeString() {
		// 모든 VEGETABLES 메뉴의 이름들을 ", "로 연결한 문자열을 반환
		List<Dish> dishList = DishList.makeDishList();
		System.out.println("=".repeat(40));
		String dishesName = dishList.stream() // Stream<Dish>
				.filter(dish -> dish.getFoodType() == FoodType.VEGETABLES) // Stream<Dish>
				.map(Dish::getName) // Stream<String>
				.collect(Collectors.joining(", "));

		return dishesName;
	}

	public List<Dish> getHealthyDishes() {
		List<Dish> dishes = DishList.makeDishList();

		System.out.println("=".repeat(40));
		List<Dish> result = dishes.stream() // Stream<Dish>
				.filter(dish -> dish.getCalories() < 400) // Stream<Dish>
				.toList();
		// 변경이 불가능한 리스트 (add불가)
		return result;
	}

	public List<Dish> getHealthyDishes2() {
		List<Dish> dishes = DishList.makeDishList();

		System.out.println("=".repeat(40));
		List<Dish> result = dishes.stream() // Stream<Dish>
				.filter(dish -> dish.getCalories() < 400) // Stream<Dish>
				.collect(Collectors.toList()); // 변경가능한 리스트
		// 변경이 가능한 리스트 (add가능)
		return result;
	}

	public static void main(String[] args) {
		StreamBasic basic = new StreamBasic();
		basic.printDishUseFor();
		basic.printDishUseStream();
		basic.printEvenNumbers();
		String dishString = basic.makeString();
		System.out.println(dishString);

		List<Dish> result = basic.getHealthyDishes();
		System.out.println(result);
//		result.add(new Dish("곱창", FoodType.MEAT, 3000, DishType.MEAT));

		List<Dish> result2 = basic.getHealthyDishes2();
		System.out.println(result2);
		result2.add(new Dish("곱창", FoodType.MEAT, 100, DishType.MEAT));
		System.out.println(result2);

	}
}
