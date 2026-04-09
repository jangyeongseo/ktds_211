package com.ktdsuniversity.edu.member.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.exceptions.TmdbWebException;
import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.helpers.SHA256Util;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.LoginVO;
import com.ktdsuniversity.edu.member.vo.request.MemberWriteVO;

import jakarta.validation.Valid;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	// 로그인 정보
	@Transactional(noRollbackFor = TmdbWebException.class)
	@Override
	public MemberVO findMemberByEmailAndPassword(@Valid LoginVO loginVO) {
		MemberVO member = this.memberDao.selectMemberByEmail(loginVO.getEmail());
		if (member == null) {
			throw new TmdbWebException("이메일 또는 비밀번호가 잘못되었습니다", "member/login", loginVO, "loginVO");
		}

		if (member.getBlockYn().equals("Y")) {
			String latestLoginFailDate = member.getLatestLoginFailDate();

			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(latestLoginFailDate, dateTimeFormatter);

			if (localDateTime.isAfter(LocalDateTime.now().minusMinutes(60))) {
				throw new TmdbWebException("이메일 또는 비밀번호가 잘못되었습니다", "member/login", loginVO, "loginVO");
			}
			throw new TmdbWebException("이메일 또는 비밀번호가 잘못되었습니다", "member/login", loginVO, "loginVO");
		}

		String salt = member.getSalt();
		String encryptedPassword = SHA256Util.getEncrypt(loginVO.getPassword(), salt);

		if (!encryptedPassword.equals(member.getPassword())) {
			this.memberDao.updateIncreaseLoginFailCount(loginVO.getEmail());
			this.memberDao.updateBlock(loginVO.getEmail());

			throw new TmdbWebException("이메일 또는 비밀번호가 잘못되었습니다", "member/login", loginVO, "loginVO");
		}

		this.memberDao.updateSuccessLogin(loginVO);

		return member;
	}

	// 회원이 입력한 정보
	@Transactional
	@Override
	public boolean createNewMember(MemberWriteVO memberWriteVO) {
		MemberVO memberVO = this.memberDao.selectMemberByEmail(memberWriteVO.getEmail());
		if (memberVO != null) {
			throw new TmdbWebException("이미 사용 중인 이메일입니다.", "member/sign", memberWriteVO, "memberWriteVO");
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
	@Transactional
	@Override
	public MemberVO findMemberArticleEmail(String articleEmail) {
		MemberVO member = this.memberDao.selectMemberByEmail(articleEmail);

		return member;
	}

	// 회원 정보 수정
	@Transactional
	@Override
	public boolean updateMameberArticleEmail(String articleEmail) {
		int update = this.memberDao.updateMemberByEmail(articleEmail);

		return update == 1;
	}

	// 회원 탈퇴
	@Transactional
	@Override
	public boolean deleteMemberByEmail(String email) {
		int delete = this.memberDao.deleteMemberByEmail(email);

		return delete == 1;
	}

}
