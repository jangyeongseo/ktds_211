package com.ktdsuniversity.edu.fp.basic;

// 이건 함수형으로 사용할 수 없다는걸 표시 및 람다식을 사용했다는 표시
@FunctionalInterface
public interface PrintSomething {
	void print(String message);

}
