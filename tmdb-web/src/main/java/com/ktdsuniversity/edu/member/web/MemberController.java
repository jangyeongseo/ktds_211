package com.ktdsuniversity.edu.member.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.member.service.MemberService;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.LoginVO;
import com.ktdsuniversity.edu.member.vo.request.MemberUpdateVO;
import com.ktdsuniversity.edu.member.vo.request.MemberWriteVO;
import com.ktdsuniversity.edu.member.vo.response.DuplicateResultVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

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
	public String viewSignPage(Model model) {
		model.addAttribute("memberWriteVO", new MemberWriteVO());
		return "member/sign";
	}

	// 회원 정보 입력
	@PostMapping("/sign")
	public String doMemberPage(@Valid @ModelAttribute MemberWriteVO memberWriteVO, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("memberWriteVO", memberWriteVO);
			return "member/sign";
		}

		boolean cteateResult = this.memberService.createNewMember(memberWriteVO);
		logger.debug("결과 : {}", cteateResult);
		return "redirect:/login";
	}

	// 영화 로그인
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginVO", new LoginVO());
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

		MemberVO member = this.memberService.findMemberByEmailAndPassword(loginVO);
		logger.debug("이메일 확인 : {}", member.getEmail());

		request.getSession().invalidate();
		HttpSession session = request.getSession();
		session.setAttribute("__LOGIN_DATA__", member);

		// return "redirect:" + go; @RequestParam(required = false, defaultValue = "/") String go
		return "redirect:/";
	}

	// 마이페이지
	@GetMapping("/member/view/{articleEmail}")
	public String viewMyPage(Model model, @PathVariable String articleEmail) {
		MemberVO findResult = this.memberService.findMemberArticleEmail(articleEmail);
		model.addAttribute("articleEmail", findResult);

		return "member/mypage";
	}

	// 사용자 회원 정보 수정
	@GetMapping("/member/update/{articleEmail}")
	public String viewUpdatePage(@PathVariable String articleEmail, Model model) {
		MemberVO update = this.memberService.findMemberArticleEmail(articleEmail);
		model.addAttribute("article", update);

		return "member/update";
	}

	@PostMapping("/member/update/{articleEmail}")
	public String doUpdateArticleEmail(@PathVariable String articleEmail, MemberUpdateVO memberUpdateVO) {
		memberUpdateVO.setEmail(articleEmail);
		boolean updateResult = this.memberService.updateMameberArticleEmail(articleEmail);
		logger.debug("업데이트 : {}", updateResult);

		return "redirect:/member/view/" + articleEmail;
	}

	// 로그아웃
	@GetMapping("/logout")
	public String doLogoutPage(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}

	// 사용자 탈퇴
	@GetMapping("/member/delete")
	public String doDeletePage(@SessionAttribute("__LOGIN_DATA__") MemberVO loginMember, HttpSession session) {
		boolean delelte = this.memberService.deleteMemberByEmail(loginMember.getEmail());
		logger.debug("삭제 성공: {}", delelte);

		session.invalidate();

		return "redirect:/";
	}

}
