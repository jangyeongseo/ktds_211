package com.ktdsuniversity.edu.board.vo.request;

import java.util.List;

// 상속을 하여 WriteVO에 있는것을 모두 사용할 수 있다.
public class UpdateVO extends WriteVO {
	private List<Integer> deleteFileNum;

	public List<Integer> getDeleteFileNum() {
		return this.deleteFileNum;
	}

	public void setDeleteFileNum(List<Integer> deleteFileNum) {
		this.deleteFileNum = deleteFileNum;
	}

}
