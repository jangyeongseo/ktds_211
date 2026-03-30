package com.ktdsuniversity.edu.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.WriteVO;
import com.ktdsuniversity.edu.member.vo.response.MembershipResultVO;

@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public boolean createNewMember(WriteVO writeVO) {
		int insertMember = this.memberDao.insertNewMember(writeVO);

		return insertMember > 0;
	}

	// 회원 수와 회원들의 목록 조회
	@Override
	public MembershipResultVO findAllMember() {
		List<MemberVO> list = this.memberDao.selectMemberList();
		int count = this.memberDao.selectMemberCount();

		MembershipResultVO result = new MembershipResultVO();
		result.setCount(count);
		result.setResult(list);

		return result;
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

		return update == 1;
	}

	// 삭제
	@Override
	public boolean deleteMemberById(String email) {
		int delete = this.memberDao.deleteMemberbyId(email);
		System.out.println(delete);

		return delete == 1;
	}

}
