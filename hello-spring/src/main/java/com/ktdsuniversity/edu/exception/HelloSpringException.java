package com.ktdsuniversity.edu.exception;

public class HelloSpringException extends RuntimeException {

	/**
	 * 동기화를 위한 고유한 아이디
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 예외가 발생했을 때, 사용자에게 보여주고 싶은 페이지(템플릿 혹은 view)의 이름.
	 */
	private String errorPage;

	/**
	 * 사용자에데 보여주고 싶은 페이지(템플릿 혹은 view)에 보내줄 모델 데이터. 무엇을 보낼지 모르기에 Object 이다.
	 */
	private Object object;
	private String modelName;

	public HelloSpringException(String message, String errorPage) {
		super(message);
		this.errorPage = errorPage;
	}

	public HelloSpringException(String message, String errorPage, Object object, String modelName) {
		super(message);
		this.errorPage = errorPage;
		this.object = object;
		this.modelName = modelName;
	}

	public String getModelName() {
		return modelName;
	}

	public Object getObject() {
		return object;
	}

	public String getErrorPage() {
		return errorPage;
	}

}