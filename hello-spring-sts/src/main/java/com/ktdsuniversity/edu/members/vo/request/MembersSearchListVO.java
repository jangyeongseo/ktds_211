package com.ktdsuniversity.edu.members.vo.request;

import com.ktdsuniversity.edu.common.vo.PaginationVO;

public class MembersSearchListVO extends PaginationVO {

	private String searchType;
	private String searchKeyword;

	public synchronized String getSearchType() {
		return this.searchType;
	}

	public synchronized void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public synchronized String getSearchKeyword() {
		return this.searchKeyword;
	}

	public synchronized void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

}
