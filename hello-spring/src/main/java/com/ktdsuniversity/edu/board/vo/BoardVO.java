package com.ktdsuniversity.edu.board.vo;

import java.util.List;

import com.ktdsuniversity.edu.files.vo.FilesVO;
import com.ktdsuniversity.edu.member.vo.MemberVO;

public class BoardVO {
	private String id;
	private String subject;
	private String content;
	private String email;
	private int viewCnt;
	private String crtDt;
	private String mdfyDt;
	private String fileName;
	private String originFileName;
	private String fileGroupId;

	// 게시글을 작성한 한명의 멤버
	private MemberVO memberVO;

	private List<FilesVO> files;
	// 하나의 게시글은 여러개의 게시글을 가지고 있다 - DB join 을 한경우 java 에서 has a 로 표현해 줘야한다.

	public MemberVO getMemberVO() {
		return this.memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public List<FilesVO> getFiles() {
		return this.files;
	}

	public void setFiles(List<FilesVO> files) {
		this.files = files;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getFileGroupId() {
		return this.fileGroupId;
	}

	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}

}