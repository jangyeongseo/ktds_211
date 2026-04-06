package com.ktdsuniversity.edu.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.helpers.SHA256Util;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.MemberWriteVO;

@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberDao memberDao;

	// 회원이 입력한 정보
	@Override
	public boolean createNewMember(MemberWriteVO memberWriteVO) {
		MemberVO memberVO = this.memberDao.selectMemberByEmail(memberWriteVO.getEmail());
		if (memberVO != null) {
			throw new IllegalArgumentException(memberWriteVO.getEmail() + "은 이미 사용 중입니다.");
		}

		// 비밀키 생성
		String newSalt = SHA256Util.generateSalt();
		String UsersPassWord = memberWriteVO.getPassword();

		UsersPassWord = SHA256Util.getEncrypt(UsersPassWord, newSalt); // 암호화 과정

		memberWriteVO.setSalt(newSalt); // 비밀키
		memberWriteVO.setPassword(UsersPassWord); // 암호화

		int insertMember = this.memberDao.insertNewMember(memberWriteVO);

		return insertMember == 1;
	}

	// 마이페이지 회원 아이디 조회
	@Override
	public MemberVO findMemberArticleEmail(String articleEmail) {
		MemberVO memberVO = this.memberDao.selectMemberByEmail(articleEmail);

		return memberVO;
	}

	// 회원 탈퇴
	@Override
	public boolean deleteMemberByEmail(String email) {
		int delete = this.memberDao.deleteMemberByEmail(email);

		return delete == 1;
	}

}
