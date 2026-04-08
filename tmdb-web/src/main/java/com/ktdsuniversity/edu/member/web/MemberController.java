package com.ktdsuniversity.edu.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.member.service.MemberService;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.MemberWriteVO;
import com.ktdsuniversity.edu.member.vo.response.DuplicateResultVO;
import com.ktdsuniversity.edu.member.vo.response.LoginVO;

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
		return "member/sign";
	}

	// 회원 정보 입력
	@PostMapping("/sign")
	public String doMemberPage(@Valid @ModelAttribute MemberWriteVO memberWriteVO, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("memberWriteVO", memberWriteVO);
			return "member/sing";
		}

		boolean cteateResult = this.memberService.createNewMember(memberWriteVO);
		System.out.println("결과 : " + cteateResult);
		return "redirect:/login";
	}

	// 영화 로그인
	@GetMapping("/login")
	public String viewLoginPage() {
		return "member/login";
	}

	@PostMapping("/login")
	public String doLoginAction(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("loginVO", loginVO);
			return "member/login";
		}

		String ip = request.getRemoteAddr();
		loginVO.setIp(ip);

		MemberVO memberVO = this.memberService.findMemberByEmailAndPassword(loginVO);
		System.out.println("이메일 확인 : " + memberVO.getEmail());
		
		request.getSession().invalidate();

		return "redirect:/";
	}

	// 마이페이지
	@GetMapping("/member/view/{articleEmail}")
	public String viewMyPage(Model model, @PathVariable String articleEmail, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("__LOGIN_DATA__") == null) {
			return "redirect:/login";
		}

		MemberVO findResult = this.memberService.findMemberArticleEmail(articleEmail);
		model.addAttribute("articleEmail", findResult);

		return "member/mypage";
	}

	// 사용자 회원 정보 수정
	@GetMapping("/member/update/{articleEmail}")
	public String viewUpdatePage(@PathVariable String articleEmail, MemberVO memberVO) {
		memberVO.setEmail(articleEmail);
		boolean updateResult = this.memberService.updateMameberArticleEmail(articleEmail);
		System.out.println("업데이트 : " + updateResult);
		
		return "member/update";
	}
	

	// 로그아웃
	@GetMapping("/logout")
	public String doLogoutPage(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}

	// 사용자 탈퇴
	@GetMapping("/member/delete")
	public String doDeletePage(@SessionAttribute MemberVO loginMember, HttpSession session) {
		boolean delelte = this.memberService.deleteMemberByEmail(loginMember.getEmail());
		System.out.println("삭제 성공: " + delelte);

		session.invalidate();

		return "redirect:/";
	}

}
