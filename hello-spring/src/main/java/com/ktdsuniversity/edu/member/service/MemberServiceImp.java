package com.ktdsuniversity.edu.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.helpers.SHA256Util;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.WriteVO;
import com.ktdsuniversity.edu.member.vo.response.MembershipResultVO;

@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public boolean createNewMember(WriteVO writeVO) {
		MemberVO memberVO = this.memberDao.selectMemberArticleId(writeVO.getEmail());
		if (memberVO != null) {
			throw new IllegalArgumentException(writeVO.getEmail() + "은 이미 사용 중입니다.");
		}

		// 암호화를 위한 비밀키 생성
		String newSalt = SHA256Util.generateSalt();
		String UsersPassword = writeVO.getPassword();

		// 사용자가 입력한 비밀번호를 newSalt를 이용해 암호화
		// 비밀번호와 newSalt의 값이 일치하면, 항상 같은 값의 암호화 결과가 생성된다.
		UsersPassword = SHA256Util.getEncrypt(UsersPassword, newSalt);

		// 비밀키 저장
		writeVO.setSalt(newSalt);
		// 암호화된 비밀번호 저장
		writeVO.setPassword(UsersPassword);

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
