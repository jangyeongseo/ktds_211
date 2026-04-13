package com.ktdsuniversity.edu.replies.vo.response;

public class UpdateResultVO {
	private String replyId;
	private boolean update;

	public synchronized String getReplyId() {
		return this.replyId;
	}

	public synchronized void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public synchronized boolean getUpdate() {
		return this.update;
	}

	public synchronized void setUpdate(boolean update) {
		this.update = update;
	}

}
