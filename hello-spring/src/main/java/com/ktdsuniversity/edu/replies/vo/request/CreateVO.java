package com.ktdsuniversity.edu.replies.vo.request;

import jakarta.validation.constraints.NotBlank;

public class CreateVO {
	private String id;

	@NotBlank(message = "댓글 내용을 작성해 주세요.")
	private String reply;
	private String email;

	@NotBlank(message = "댓글을 작성할 게시글의 아이디가 없습니다.")
	private String articleId;
	private String parentReplyId;

	public synchronized String getId() {
		return this.id;
	}

	public synchronized void setId(String id) {
		this.id = id;
	}

	public synchronized String getReply() {
		return this.reply;
	}

	public synchronized void setReply(String reply) {
		this.reply = reply;
	}

	public synchronized String getEmail() {
		return this.email;
	}

	public synchronized void setEmail(String email) {
		this.email = email;
	}

	public synchronized String getArticleId() {
		return this.articleId;
	}

	public synchronized void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public synchronized String getParentReplyId() {
		return this.parentReplyId;
	}

	public synchronized void setParentReplyId(String parentReplyId) {
		this.parentReplyId = parentReplyId;
	}

}
