package com.ktdsuniversity.edu.files.vo.request;

import java.util.List;

public class SaerchFileGroupVO {
	private String fileGroupId;
	private List<Integer> delFileNum;

	public synchronized String getFileGroupId() {
		return this.fileGroupId;
	}

	public synchronized void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}

	public synchronized List<Integer> getDelFileNum() {
		return this.delFileNum;
	}

	public synchronized void setDelFileNum(List<Integer> delFileNum) {
		this.delFileNum = delFileNum;
	}

}
