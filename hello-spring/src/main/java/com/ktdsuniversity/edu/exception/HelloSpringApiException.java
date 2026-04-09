package com.ktdsuniversity.edu.exception;

public class HelloSpringApiException extends RuntimeException {

	/**
	 * 동기화를 위한 고유한 아이디
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 예외가 발생했을 때, 사용자에게 보여주고 싶은 페이지(템플릿 혹은 view)의 이름.
	 */
	private int errorStatus;

	/**
	 * 사용자에데 보여주고 싶은 페이지(템플릿 혹은 view)에 보내줄 모델 데이터. 무엇을 보낼지 모르기에 error 이다.
	 */
	private Object error;
	private String modelName;

	public HelloSpringApiException(String message, int errorStatus) {
		super(message);
		this.errorStatus = errorStatus;
	}

	public HelloSpringApiException(String message, int errorStatus, Object error, String modelName) {
		super(message);
		this.errorStatus = errorStatus;
		this.error = error;
		this.modelName = modelName;
	}

	public String getModelName() {
		return modelName;
	}

	public Object geterror() {
		return error;
	}

	public int geterrorStatus() {
		return errorStatus;
	}

}
