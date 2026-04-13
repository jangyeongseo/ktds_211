package com.ktdsuniversity.edu.replies.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UpdateVO {
	private String replyId;

	private String content;
	private List<Integer> delFileNum;
	private List<MultipartFile> newAttachFiles;

	private String fileGroupId;

	public synchronized String getReplyId() {
		return this.replyId;
	}

	public synchronized void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public synchronized String getContent() {
		return this.content;
	}

	public synchronized void setContent(String content) {
		this.content = content;
	}

	public synchronized List<Integer> getDelFileNum() {
		return this.delFileNum;
	}

	public synchronized void setDelFileNum(List<Integer> delFileNum) {
		this.delFileNum = delFileNum;
	}

	public synchronized List<MultipartFile> getNewAttachFiles() {
		return this.newAttachFiles;
	}

	public synchronized void setNewAttachFiles(List<MultipartFile> newAttachFiles) {
		this.newAttachFiles = newAttachFiles;
	}

	public synchronized String getFileGroupId() {
		return this.fileGroupId;
	}

	public synchronized void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}

}
