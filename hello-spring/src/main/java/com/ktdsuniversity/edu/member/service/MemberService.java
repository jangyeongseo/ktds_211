package com.ktdsuniversity.edu.member.service;

import com.ktdsuniversity.edu.member.vo.MemberVO;

public interface MemberService {
	boolean createNewMember(MemberVO memberVO);

	MemberVO findMemberArticleId(String articleEmail);

	boolean updateMemberArticleById(MemberVO memberVO);

	boolean deleteMemberById(String email);

}
