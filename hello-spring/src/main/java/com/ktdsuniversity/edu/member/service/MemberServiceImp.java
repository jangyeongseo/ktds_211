package com.ktdsuniversity.edu.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public boolean createNewMember(MemberVO memberVO) {
		int insertMember = this.memberDao.insertNewMember(memberVO);

		return insertMember > 0;
	}

}
