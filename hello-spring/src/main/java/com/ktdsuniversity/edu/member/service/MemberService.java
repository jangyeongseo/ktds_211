package com.ktdsuniversity.edu.member.service;

import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.WriteVO;
import com.ktdsuniversity.edu.member.vo.response.LoginVO;
import com.ktdsuniversity.edu.member.vo.response.MembershipResultVO;

import jakarta.validation.Valid;

public interface MemberService {
	boolean createNewMember(WriteVO writeVO);

	MemberVO findMemberArticleId(String articleEmail);

	boolean updateMemberArticleById(MemberVO memberVO);

	boolean deleteMemberById(String email);

	MembershipResultVO findAllMember();

	MemberVO findMemberByEmailAndPassword(LoginVO loginVO);

}
