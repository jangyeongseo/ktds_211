package com.ktdsuniversity.edu.exceptions;

public class TmdbWebException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorPage;
	private Object object;
	private String modelName;

	public TmdbWebException(String messfage, String errorPage) {
		super(messfage);
		this.errorPage = errorPage;
	}

	public TmdbWebException(String messfage, String errorPage, Object object, String modelName) {
		super(messfage);
		this.errorPage = errorPage;
		this.object = object;
		this.modelName = modelName;
	}

	public synchronized String getErrorPage() {
		return this.errorPage;
	}

	public synchronized Object getObject() {
		return this.object;
	}

	public synchronized String getModelName() {
		return this.modelName;
	}

}
