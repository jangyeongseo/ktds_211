package com.ktdsuniversity.edu.board.vo.request;

/**
 * 게시글을 검색 사용. 게시글 페이지네이션 사용
 */
public class SearchListVO {

	private int pageNo; // 페이지 목록을 보여줄 번호(0-base)
	private int listSize; // 하나에 페이지에 보여줄 게시글의 개수

	// 총 몇개의 페이지가 생성되느냐.
	// (boolean)(게시글의 개수 / listSiz)
	private int pageCount;

	/**
	 * 페이지 그룹 만들기 위해 필요
	 */
	// 하나의 페이지 그룹에 보여줄 페이지 개수
	private int pageCountInGroup;

	// 페이지 그룹위 개수 : 올림(페이지 개수 / pageCountInGroup)
	private int pageGroupCount;

	// 현재 노출도고 있는 펟이지 번호가 속한 그룹의 번호
	private int groupNo;

	// 현재 노출되고 있는 페이지 그룹의 시작 페이지 번호
	private int groupStartPageNo;

	// 현재 노출되고 있는 페이지 그룹의 마지막 페이지 번호
	private int groupEndPageNo;

	// 현재 노출되고 있는 페이지 그룹의 다음 그룹이 있는지 여부
	private boolean hasNextPageGroup;

	// 현재 노출되고 있는 페이지 그룹의 이전 그룹이 있는지 여부
	private boolean hasPrevPageGroup;

	// 현재 노출되고 있는 페이지 그룹의 다음 그룹 페이지 시작 번호
	private int nextPageGroupStartPageNo;

	// 현재 노출되고 있는 페이지 그룹의 이전 그룹 페이지 마지막 번호
	private int prevPageGroupStartPageNo;

	// listSize의 기본값 할당을 위한 생성자
	public SearchListVO() {
		// 한 페이지에 10개의 게시글이 노출되도록 설정
		this.listSize = 10;
		// 한 페이지 그룹에 10개의 게시글이 노출되록 설정
		this.pageCountInGroup = 10;
	}

	public synchronized int getPageNo() {
		return this.pageNo;
	}

	public synchronized void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public synchronized int getListSize() {
		return this.listSize;
	}

	public synchronized void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public synchronized int getPageCount() {
		return this.pageCount;
	}

	public synchronized void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public synchronized int getPageCountInGroup() {
		return this.pageCountInGroup;
	}

	public synchronized void setPageCountInGroup(int pageCountInGroup) {
		this.pageCountInGroup = pageCountInGroup;
	}

	public synchronized int getPageGroupCount() {
		return this.pageGroupCount;
	}

	public synchronized void setPageGroupCount(int pageGroupCount) {
		this.pageGroupCount = pageGroupCount;
	}

	public synchronized int getGroupNo() {
		return this.groupNo;
	}

	public synchronized void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public synchronized int getGroupStartPageNo() {
		return this.groupStartPageNo;
	}

	public synchronized void setGroupStartPageNo(int groupStartPageNo) {
		this.groupStartPageNo = groupStartPageNo;
	}

	public synchronized int getGroupEndPageNo() {
		return this.groupEndPageNo;
	}

	public synchronized void setGroupEndPageNo(int groupEndPageNo) {
		this.groupEndPageNo = groupEndPageNo;
	}

	public synchronized boolean getHasNextPageGroup() {
		return this.hasNextPageGroup;
	}

	public synchronized void setHasNextPageGroup(boolean hasNextPageGroup) {
		this.hasNextPageGroup = hasNextPageGroup;
	}

	public synchronized boolean getHasPrevPageGroup() {
		return this.hasPrevPageGroup;
	}

	public synchronized void setHasPrevPageGroup(boolean hasPrevPageGroup) {
		this.hasPrevPageGroup = hasPrevPageGroup;
	}

	public synchronized int getNextPageGroupStartPageNo() {
		return this.nextPageGroupStartPageNo;
	}

	public synchronized void setNextPageGroupStartPageNo(int nextPageGroupStartPageNo) {
		this.nextPageGroupStartPageNo = nextPageGroupStartPageNo;
	}

	public synchronized int getPrevPageGroupStartPageNo() {
		return this.prevPageGroupStartPageNo;
	}

	public synchronized void setPrevPageGroupStartPageNo(int prevPageGroupStartPageNo) {
		this.prevPageGroupStartPageNo = prevPageGroupStartPageNo;
	}

	/**
	 * 조회된 게시글의 개수와 listSize를 이용해 총 몇 개의 페이지가 필요한지 계산.
	 * 
	 * @param articleCount 게시글의 개수
	 */
	public void computePagination(int articleCount) {
		// 페이지의 개수 계산
		this.pageCount = (int) Math.ceil(articleCount / (double) this.listSize);
		// 예 > 11/ 10.0 = Math.ceil(1.1)반올림 = 2.0 -> int 로 변경 => 2

		// 페이지를 페이지네이션하기 위한 계산
		// 페이지 그룹의 개수 계산
		// (올림)(페이지 개수 / 페이지 그룹당 페이지 개수)
		this.pageGroupCount = (int) Math.ceil(this.pageCount / (double) this.pageCountInGroup);

		// 현재 페이지 그룹 번호 계산
		// 현재 페이지 번호 / 페이지 그훕당 페이지 개수
		this.groupNo = this.pageNo / this.pageCountInGroup;

		// 현재 페이지 그룹의 시작 페이지 번호 계산
		// 현재 페이지 그룹 번호 * 페이지 그룹당 페이지 개수
		this.groupStartPageNo = this.groupNo * this.pageCountInGroup;

		// 현재 페이지 그룹의 마지막 페이지 번호 계싼
		// (현재 페이지 그룹 번호 +1) * 페이지 그룹당 페이지 개수 -1
		this.groupEndPageNo = (this.groupNo + 1) * this.pageCountInGroup - 1;

		// 마지맞 페이지 번호 보정
		// 현재 페이지 그룹의 마지막 페이지 번호가 총페이지 개수보다 클 경우 보정 필요
		if (this.groupEndPageNo > this.pageCount) {
			this.groupEndPageNo = this.pageCount - 1;
		}

		// 다름 그룹이 존재하는지 계산
		// 현재 페이지 그룹이 < 총 페이지 그룹 개수 -1 보다
		this.hasNextPageGroup = this.groupNo < this.pageGroupCount - 1;

		// 이전 그룹이 존재하는지 계산
		// 현재 페이지 그룹 > 0
		this.hasPrevPageGroup = this.groupNo > 0;

		// 다음 그룹의 시작 페이지 번호 계산
		// 현재 페이지 그룹의 마지막 페이지 번호 +1
		this.nextPageGroupStartPageNo = this.groupEndPageNo + 1;

		// 이전 그릅의 시작 페이지 번호 계산
		// 현재 페이지 그룹의 시작 페이지 번호 - 페이지 그룹당 페이지 개수
		this.prevPageGroupStartPageNo = this.groupStartPageNo - this.pageCountInGroup;

	}

}
