package com.ktdsuniversity.edu.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.WriteVO;
import com.ktdsuniversity.edu.member.vo.response.LoginVO;

@Mapper
public interface MemberDao {
	int insertNewMember(WriteVO writeVO);

	MemberVO selectMemberArticleId(String articleEmail);

	int updateMemberById(MemberVO memberVO);

	int deleteMemberbyId(String email);

	List<MemberVO> selectMemberList();

	int selectMemberCount();

	MemberVO selectMemberByEmail(String string);

	int updateIncreaseLoginFailCount(String email);

	int updateBlock(String email);

	int updateSuccessLogin(LoginVO loginVO);

}
