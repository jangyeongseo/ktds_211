package com.ktdsuniversity.edu.member.service;

import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.MemberWriteVO;

public interface MemberService {

	boolean createNewMember(MemberWriteVO memberWriteVO);

	boolean deleteMemberByEmail(String email);

	MemberVO findMemberArticleEmail(String email);

}
