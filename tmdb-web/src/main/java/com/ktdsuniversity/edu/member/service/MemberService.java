package com.ktdsuniversity.edu.member.service;

import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.MemberWriteVO;
import com.ktdsuniversity.edu.member.vo.response.LoginVO;

import jakarta.validation.Valid;

public interface MemberService {

	boolean createNewMember(MemberWriteVO memberWriteVO);

	boolean deleteMemberByEmail(String email);

	MemberVO findMemberArticleEmail(String email);

	MemberVO findMemberByEmailAndPassword(@Valid LoginVO loginVO);

	boolean updateMameberArticleEmail(String articleEmail);

}
