package com.ktdsuniversity.edu.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.LoginVO;
import com.ktdsuniversity.edu.member.vo.request.MemberWriteVO;

import jakarta.validation.Valid;

@Mapper
public interface MemberDao {

    int insertNewMember(MemberWriteVO memberWriteVO);

    MemberVO selectMemberByEmail(String email);

    int deleteMemberByEmail(String email);

	int updateMemberByEmail(String articleEmail);

	void updateBlock(String email);

	void updateIncreaseLoginFailCount(String email);

	void updateSuccessLogin(@Valid LoginVO loginVO);

}
