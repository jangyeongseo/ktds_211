package com.ktdsuniversity.edu.member.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.member.vo.MemberVO;

public class ShipResultVO {
	private List<MemberVO> result;
	private int count;

	public List<MemberVO> getResult() {
		return this.result;
	}

	public void setResult(List<MemberVO> result) {
		this.result = result;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
