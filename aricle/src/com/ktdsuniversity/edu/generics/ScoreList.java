package com.ktdsuniversity.edu.generics;

/**
 * 제네릭
 */
public class ScoreList<T, SUM_RESULT> {
	// ScoreList<T> : T 에 원하는 type 이 들어가는지
	// T에는 Object 가 들어가 있다.

	// T의 타입으로 파라미터 값을 받겠다.
//	public void add(T score) {
//
//	}

	// ScoreList에서 사용할 멤버변수
	// 점수들의 배열
	// private T[] scoreArray; 무슨 타입인지 몰라서 메모리 할당을 못해서 이렇게 사용 불가
	private Object[] scoreArray;

	// 배열에 몇개의 데이터가 있는지 확인.
	private int size;

	public ScoreList() {
		// 제네릭을 제외한 public ScoreList<T>(){} 사용 불가
		// public ScoreList() 사용 가능
		this.scoreArray = new Object[2];

	}

	public void add(T score) {
		if (this.size >= this.scoreArray.length) {
			// 배열의 길이를 늘린다.
			// 1. 기존 배열의 길이보다 2개 더 많은 인덱스를 가진 배열을 새롭게 만든다.
			Object[] newArray = new Object[this.scoreArray.length + 2];

			// 2. 기존 배열의 데이터를 새로운 배열에 복사하낟.
			System.arraycopy(this.scoreArray, 0, newArray, 0, this.scoreArray.length);
			// System.arraycopy(원본 배열, 복사를 시작할 원본 배열의 인덱스 번호, 복사를 받을 새로운 배열, 새로운 배열의 복사를 받을
			// 시작 인덱스 번호, 복사할 개수)

			// 3. 새로운 배열을 기존의 배열에 할당
			this.scoreArray = newArray;
		}
		this.scoreArray[this.size++] = score;
	}

	public T get(int index) {
		if (this.size <= index) {
			throw new IndexOutOfBoundsException(this.size + "보다 크거나 같은 인덱스 x");
		}

		T value = (T) this.scoreArray[index];
		return value;
	}

	/**
	 * ScoreList에 저장된 모든 요소들을 Reducer를 이용해 하나의 결과값으로 축약(reduce)합니다.
	 *
	 * @param reducer      요소(T)와 현재까지의 결과(SUM_RESULT)를 받아 새로운 결과를 반환하는 함수형 인터페이스
	 * @param defaultValue 초기 결과값(누적 시작값)
	 * @return 모든 요소를 reducer로 처리한 최종 결과값
	 */
	public SUM_RESULT sum(Reducer<T, SUM_RESULT> reducer, SUM_RESULT defaultValue) {
		// 결과값을 defaultValue로 초기화한다.
		SUM_RESULT result = defaultValue;

		T t = null;
		// ScoreList에 저장된 모든 요소를 순회한다.
		for (int i = 0; i < size; i++) {
			// Object 배열에 저장된 값을 T 타입으로 캐스팅한다.
			t = (T) scoreArray[i];

			// reducer 를 호출하여 현재 요소 t와 누적된 result 를 합쳐 새로운 result 를 만든다.
			result = reducer.reduce(t, result);
		}

		// 모든 요소를 처리한 후 최종 결과를 반환한다.
		return result;
	}

}
