package com.ktdsuniversity.edu.generics.collections.map;

public class Student {

	private String name;
	private int number;

	/**
	 * @param name
	 * @param number
	 */
	public Student(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}

	@Override
	public String toString() {
		return "Student[number = " + this.number + ", name = " + this.name + "]";
	}

}
