package com.ktdsuniversity.edu.board.vo;

// 테이블을 기반한다 / 테이블과 똑같이 만든다.

// 대문자를 소문자로 ctrl + shift + y

public class BoardVO {
	private String id;
	private String title;
	private String content;
	private int viewCount; // NUMBER(8,0) : int / 9자리 이상인 경우 long으로 / 소수점이 있을 경우
	private String writeDate;
	private String latestModifyDate;

	public BoardVO() {

	}

	/**
	 * @param id               게시글 아이디
	 * @param title            게시글 제목
	 * @param content          게시글 내용
	 * @param viewCount        게시글 조회수
	 * @param writeDate        게시글 작성 날짜 및 시간
	 * @param latestModifyDate 게시글 마지막으로 수정한 날짜 및 시간
	 */
	public BoardVO(String id, String title, String content, int viewCount, String writeDate, String latestModifyDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.writeDate = writeDate;
		this.latestModifyDate = latestModifyDate;
	}

	public String getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getContent() {
		return this.content;
	}

	public int getViewCount() {
		return this.viewCount;
	}

	public String getWriteDate() {
		return this.writeDate;
	}

	public String getLatestModifyDate() {
		return this.latestModifyDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public void setLatestModifyDate(String latestModifyDate) {
		this.latestModifyDate = latestModifyDate;
	}

	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", title=" + title + ", content=" + content + ", viewCount=" + viewCount
				+ ", writeDate=" + writeDate + ", latestModifyDate=" + latestModifyDate + "]";
	}

}
