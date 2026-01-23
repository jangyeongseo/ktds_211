package com.ktdsuniversity.edu.member.management;

public class Member {

	// public : 모든 클래스에서 접근 가능
	private String id;
	private String name;

	public Member(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public void printInOf() {
		System.out.println();
		System.out.println("작성자ID : " + this.name);
		System.out.println("작성자명 : " + this.id);
	}

	// id getter / setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// name getter / setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
