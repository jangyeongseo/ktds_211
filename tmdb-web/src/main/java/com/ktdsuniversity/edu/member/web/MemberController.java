package com.ktdsuniversity.edu.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.member.service.MemberService;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.MemberWriteVO;
import com.ktdsuniversity.edu.member.vo.response.DuplicateResultVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 회원 정보 josn으로 변결
	@ResponseBody
	@GetMapping("/sign/check/duplicate/{email}")
	public DuplicateResultVO doCheckDuplicateIdAction(@PathVariable String email) {
		MemberVO memberVO = this.memberService.findMemberArticleEmail(email);

		DuplicateResultVO result = new DuplicateResultVO();
		result.setEmail(email);
		result.setDuplicate(memberVO != null);

		return result;
	}

	// 영화 회원가입
	@GetMapping("/sign")
	public String viewSignPage() {
		return "movie/sign";
	}

	// 회원 정보 입력
	@PostMapping("/sign")
	public String doMemberPage(@Valid @ModelAttribute MemberWriteVO memberWriteVO, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("memberWriteVO", memberWriteVO);
			return "movie/sing";
		}

		boolean cteateResult = this.memberService.createNewMember(memberWriteVO);
		return "redirect:/login";
	}

	// 영화 로그인
	@GetMapping("/login")
	public String viewLoginPage() {
		return "movie/login";
	}

	// 마이페이지
	@GetMapping("/member/view/{articleEmail}")
	public String viewMyPage(Model model, @PathVariable String articleEmail, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("__LOGIN_DATA__") == null) {
			return "redirect:/login";
		}
		
		MemberVO findResult = this.memberService.findMemberArticleEmail(articleEmail);
		model.addAttribute("articleEmail", findResult);

		return "movie/mypage";
	}

	// 사용자 회원 정보 수정
	// 회원 탈퇴
	@GetMapping("/member/delete")
	public String doDeletePage(@RequestParam String email) {
		boolean delelte = this.memberService.deleteMemberByEmail(email);
		System.out.println("삭제 성공: " + delelte);

		return "redirect:/";
	}

}
