package com.ktdsuniversity.edu.board.vo;

public class ReplyVO {
	private String id;
	private String boardId;
	private String topId;
	private String content;
	private String writeDate;

	public ReplyVO() {

	}

	/**
	 * @param id        댓글 아이디
	 * @param boardId   댓글 작성한 게시글의 아이디
	 * @param topId     대댓글의 상위 댓글 아이디
	 * @param content   댓글의 내용
	 * @param writeDate 댓글을 작성한 날짜 및 시간
	 */
	public ReplyVO(String id, String boardId, String topId, String content, String writeDate) {
		this.id = id;
		this.boardId = boardId;
		this.topId = topId;
		this.content = content;
		this.writeDate = writeDate;
	}

	public String getId() {
		return this.id;
	}

	public String getBoardId() {
		return boardId;
	}

	public String getTopId() {
		return this.topId;
	}

	public String getContent() {
		return this.content;
	}

	public String getWriteDate() {
		return this.writeDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public void setTopId(String topId) {
		this.topId = topId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "ReplyVO [id=" + id + ", boardId=" + boardId + ", topId=" + topId + ", content=" + content
				+ ", writeDate=" + writeDate + "]";
	}

}
