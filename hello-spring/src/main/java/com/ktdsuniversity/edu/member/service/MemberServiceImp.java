package com.ktdsuniversity.edu.member.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.member.dao.MemberDao;
import com.ktdsuniversity.edu.member.helpers.SHA256Util;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.WriteVO;
import com.ktdsuniversity.edu.member.vo.response.LoginVO;
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

		return insertMember == 1;
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

	// 로그인
	@Override
	public MemberVO findMemberByEmailAndPassword(LoginVO loginVO) {
		// 1. 이메일로 회원 조회
		MemberVO member = this.memberDao.selectMemberByEmail(loginVO.getEmail());

		// 2. 회원 없으면 예외
		if (member == null) {
			throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다");
		}

		if (member.getBlockYn().equals("Y")) {
			// 로그인 Block 된 시간으로부터 120분이 지나면 다시 로그인 가능한 상태로 변경한다.
			// 이 경우엔 예외를 던지지 않도록 한다.
			String latestLoginFailDate = member.getLatestLoginFailDate();

			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime lastestBlockDateTime = LocalDateTime.parse(latestLoginFailDate, dateTimeFormatter);

			if (lastestBlockDateTime.isAfter(LocalDateTime.now().minusMinutes(120))) {
				throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다");
			}

			throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다");
		}

		// 3. 비밀번호 암호화
		String salt = member.getSalt();
		String encryptedPassword = SHA256Util.getEncrypt(loginVO.getPassword(), salt);

		// 4. 비밀번호 비교
		if (!encryptedPassword.equals(member.getPassword())) {
			// 해당 이메일의 로그인 실패 횟수를 1 증가시키고
			// 최근 로그인 실패 날짜를 현재 날짜와 시간으로 변경
			this.memberDao.updateIncreaseLoginFailCount(loginVO.getEmail());

			// 최근 로그인 실패 횟수가 5이상이라면 block-yn을 Y로 변경한다.
			this.memberDao.updateBlock(loginVO.getEmail());

			throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다");
		}

		// 로그인 성공처리
		// 1. login_fail_count 를 0으로 초기회
		// 2. latest_login_ip 를 현재 아이피로 변경
		// 3. login_date를 현재 시간으로 변경
		// 4. block_yn을 'N' 으로 변경
		this.memberDao.updateSuccessLogin(loginVO);

		// 5. 로그인 성공
		return member;
	}

}
