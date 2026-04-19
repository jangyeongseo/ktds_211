package com.ktdsuniversity.edu.member.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.common.utils.ServletUtils;
import com.ktdsuniversity.edu.member.service.MemberService;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.MemberSearchListVO;
import com.ktdsuniversity.edu.member.vo.request.WriteVO;
import com.ktdsuniversity.edu.member.vo.response.DuplicateResultVO;
import com.ktdsuniversity.edu.member.vo.response.LoginVO;
import com.ktdsuniversity.edu.member.vo.response.MembershipResultVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * EndPoint 생성/관리 + Validation Check
 */
@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@ResponseBody // JSON 형식으로 반환
	@GetMapping("/regist/check/duplicate/{email}")
	public DuplicateResultVO doCheckDuplicateEmailAction(@PathVariable String email) {
		// email이 이미 사용 중인지 확인한다.
		MemberVO memberVO = this.memberService.findMemberArticleId(email);

		// 확인된 결과를 JSON으로 전송한다.
		// 이미 사용중 => {email: "test@gmail", duplicate: true}
		// 사용중이지 않다 => {email: "test@gmail", duplicate: false}
		DuplicateResultVO result = new DuplicateResultVO();
		result.setEmail(email);
		result.setDuplicate(memberVO != null);
		return result;

	}

	// 회원가입 등록 화면 보여주기
	@GetMapping("/regist")
	public String viewMemberPage() {
		return "member/sign";
	}

	// 회원이 입력한 정보
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/regist")
	public String doMemberPage(@Valid @ModelAttribute WriteVO writeVO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("inputModel", writeVO);
			return "member/sign";
		}

		// 성공 여부
		boolean createResult = this.memberService.createNewMember(writeVO);
		logger.debug("결과 : createResult={}, writeVO={}", createResult, writeVO);

		return "redirect:/login";
	}

	// 로그인 화면
//	@GetMapping("/login")
//	public String viewMemberLoginPage() {
//		return "member/login";
//	}

	@GetMapping("/login")
	public String viewMemberLoginPage(Model model) {
		model.addAttribute("loginVO", new LoginVO());
		return "member/login";
	}

	// /member => 회원들의 목록이 조회되도록 코드를 작성
	// => 회원 목록 조회
	// => member/list.jsp
	// => 회원 목록 반복, 회원의 수 출력, 회원의 수가 없을 때, "등록된 회원이 없습니다.", 목록
	// 아래에는 새로운 회원 등록" 링크 추가
	@PreAuthorize("hasRole('RL-20260414-000001')")
	@GetMapping("/member")
	public String viewMemberListPage(Model model, MemberSearchListVO memberSearchListVO) {
		MembershipResultVO memberResult = this.memberService.findAllMember(memberSearchListVO);
		List<MemberVO> memberlist = memberResult.getResult();
		int memberCount = memberResult.getCount();

		model.addAttribute("memberResult", memberlist);
		model.addAttribute("memberCount", memberCount);

		return "member/list";
	}

	// member/view/사용자아이디 ⇒ 회원정보 조회 하기
	@PreAuthorize("isAuthenticated() and #articleEmail == authentication.principal.email")
	@GetMapping("/member/view/{articleEmail}")
	public String viewDetailPage(Model model, @PathVariable String articleEmail) {
		MemberVO findeResult = this.memberService.findMemberArticleId(articleEmail);
		model.addAttribute("member", findeResult);

		return "member/view";
	}

	// member/update/사용자 아이디 ⇒회원 정보 수정 페이지 보기
	@PreAuthorize("isAuthenticated() and #articleEmail == authentication.principal.email")
	@GetMapping("/member/update/{articleEmail}")
	public String viewUpdatePage(Model model, @PathVariable String articleEmail) {
		MemberVO update = this.memberService.findMemberArticleId(articleEmail);
		model.addAttribute("article", update);

		return "member/update";
	}

	// member/update/사용자 아이디 ⇒ 회원 정보 수정 하기
	@PreAuthorize("isAuthenticated() and #articleEmail == authentication.principal.email") 
	@PostMapping("/member/update/{articleEmail}")
	public String doUpdatePage(@PathVariable String articleEmail, MemberVO memberVO) {
		memberVO.setEmail(articleEmail);
		boolean updateResult = this.memberService.updateMemberArticleById(memberVO);
		logger.debug("성공: {}", updateResult);

		return "redirect:/member/view/" + articleEmail;
	}

	// member/delete?id=사용자 아이디 ⇒ 회원 정보 삭제 하기
	@PreAuthorize("isAuthenticated() and #email == authentication.principal.email")
	@GetMapping("/member/delete")
	public String doDeletePage(@RequestParam String email) {
		boolean delete = this.memberService.deleteMemberById(email);
		logger.debug("{}", delete);

		return "redirect:/login";
	}

	// 로그아웃
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/logout")
	public String doLogoutPage(Authentication authentication) {
		LogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(
				ServletUtils.getRequest(), 
				ServletUtils.getResponse(), 
				authentication);

		return "redirect:/login";
	}

	// 회원 탈퇴
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete-me")
	public String doDeleteAction(Authentication authentication) {
		MemberVO loginUser = (MemberVO) authentication.getPrincipal();
		String email = loginUser.getEmail();
		
		boolean deleteSuccess = this.memberService.deleteMemberById(email);
		logger.debug("탈퇴 성공? {}", deleteSuccess);
		
		// 3. 현재 로그인된 사용자를 로그아웃 시킨다.
		LogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(
				ServletUtils.getRequest(), 
				ServletUtils.getResponse(), 
				authentication);
		
		return "member/deletesuccess";

	}

}
