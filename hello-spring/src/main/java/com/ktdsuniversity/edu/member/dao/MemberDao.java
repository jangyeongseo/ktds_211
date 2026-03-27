package com.ktdsuniversity.edu.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.member.vo.MemberVO;

@Mapper
public interface MemberDao {
	int insertNewMember(MemberVO memberVO);

}
