package com.ktdsuniversity.edu.board.vo.request;

// 상속을 하여 WriteVO에 있는것을 모두 사용할 수 있다.
public class UpdateVO extends WriteVO {
	private String id; // update ~ where에 사용할 변수

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
