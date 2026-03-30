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

	// 회원 정보 조회
	@Override
	public MemberVO findMemberArticleId(String articleEmail) {
		MemberVO member = this.memberDao.selectMemberArticleId(articleEmail);
		return member;
	}

	// 수정
	@Override
	public boolean updateMemberArticleById(MemberVO memberVO) {
		int update = this.memberDao.updateMemberById(memberVO);
		System.out.println(update);

		return update > 0;
	}

	// 삭제
	@Override
	public boolean deleteMemberById(String email) {
		int delete = this.memberDao.deleteMemberbyId(email);
		System.out.println(delete);

		return false;
	}

}
