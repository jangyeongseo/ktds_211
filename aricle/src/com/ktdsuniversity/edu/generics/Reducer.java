package com.ktdsuniversity.edu.generics;

public interface Reducer<INPUT, OUTPUT> {
	// reduce 메서드는 입력값(INPUT)을 받아서 출력값(OUTPUT)을 반환하는 역할.
	// - 즉, 어떤 타입 T를 받아서 SUM_RESULT 타입으로 변환하는 로직을 외부에서 정의할 수 있게 해줍니다.
	OUTPUT reduce(INPUT input, OUTPUT output);
	
}