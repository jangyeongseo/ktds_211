package com.ktdsuniversity.edu.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.member.service.MemberService;
import com.ktdsuniversity.edu.member.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 회원가입 등록 화면 보여주기
	@GetMapping("/sign")
	public String viewMemberPage() {
		return "member/member";
	}

	// 회원이 입력한 정보
	@PostMapping("/regist")
	public String doMemberPage(MemberVO memberVO) {
		// 성공 여부
		boolean createResult = this.memberService.createNewMember(memberVO);
		System.out.println(memberVO);

		return "redirect:/write";
	}

}
