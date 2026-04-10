package com.ktdsuniversity.edu.replies.vo;

import java.util.List;

import com.ktdsuniversity.edu.files.vo.FilesVO;
import com.ktdsuniversity.edu.member.vo.MemberVO;

public class RepliesVO {

	private String id;
	private String reply;
	private int recommendCnt;
	private String crtDt;
	private String mdfyDt;
	private String email;
	private String articleId;
	private String parentReplyId;
	private String fileGroupId;

	private MemberVO memberVO;

	private int level;
	private List<FilesVO> files;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public int getRecommendCnt() {
		return recommendCnt;
	}

	public void setRecommendCnt(int recommendCnt) {
		this.recommendCnt = recommendCnt;
	}

	public String getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getMdfyDt() {
		return mdfyDt;
	}

	public void setMdfyDt(String mdfyDt) {
		this.mdfyDt = mdfyDt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getParentReplyId() {
		return parentReplyId;
	}

	public void setParentReplyId(String parentReplyId) {
		this.parentReplyId = parentReplyId;
	}

	public String getFileGroupId() {
		return fileGroupId;
	}

	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}

	public synchronized MemberVO getMemberVO() {
		return this.memberVO;
	}

	public synchronized void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public synchronized int getLevel() {
		return this.level;
	}

	public synchronized void setLevel(int level) {
		this.level = level;
	}

	public synchronized List<FilesVO> getFiles() {
		return this.files;
	}

	public synchronized void setFiles(List<FilesVO> files) {
		this.files = files;
	}
}