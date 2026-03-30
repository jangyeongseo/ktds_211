package com.ktdsuniversity.edu.member.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.member.vo.MemberVO;

public class MembershipResultVO {
//	마이페이지나 그런거 있을 때 필요할듯
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
