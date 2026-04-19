package com.ktdsuniversity.edu.member.vo.request;

import com.ktdsuniversity.edu.common.vo.PaginationVO;

/**
 * 게시글을 검색 사용. 게시글 페이지네이션 사용
 */
public class MemberSearchListVO extends PaginationVO {
	/**
	 * 검색용
	 */
	private String searchType;
	private String searchKeyWord;

	public synchronized String getSearchType() {
		return this.searchType;
	}

	public synchronized void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public synchronized String getSearchKeyWord() {
		return this.searchKeyWord;
	}

	public synchronized void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}

}
