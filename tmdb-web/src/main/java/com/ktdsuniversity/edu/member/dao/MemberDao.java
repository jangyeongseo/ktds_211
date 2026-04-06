package com.ktdsuniversity.edu.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.MemberWriteVO;

@Mapper
public interface MemberDao {

    int insertNewMember(MemberWriteVO memberWriteVO);

    MemberVO selectMemberByEmail(String email);

    int deleteMemberByEmail(String email);

}
