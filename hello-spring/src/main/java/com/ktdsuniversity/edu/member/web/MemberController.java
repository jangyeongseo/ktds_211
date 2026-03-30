package com.ktdsuniversity.edu.member.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.member.service.MemberService;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.request.WriteVO;
import com.ktdsuniversity.edu.member.vo.response.MembershipResultVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 회원가입 등록 화면 보여주기
	@GetMapping("/sign")
	public String viewMemberPage() {
		return "member/sign";
	}

	// 회원이 입력한 정보
	@PostMapping("/regist")
	public String doMemberPage(WriteVO writeVO) {
		// 성공 여부
		boolean createResult = this.memberService.createNewMember(writeVO);
		System.out.println(writeVO);

		return "redirect:/login";
	}

	// 로그인 화면
	@GetMapping("/login")
	public String viewMemberLoginPage() {
		return "member/login";
	}

	// /member => 회원들의 목록이 조회되도록 코드를 작성
	// => 회원 목록 조회
	// => member/list.jsp
	// => 회원 목록 반복, 회원의 수 출력, 회원의 수가 없을 때, "등록된 회원이 없습니다.", 목록
	// 아래에는 새로운 회원 등록" 링크 추가
	@GetMapping("/member")
	public String viewMemberListPage(Model model) {
		MembershipResultVO memberResult = this.memberService.findAllMember();
		List<MemberVO> memberlist = memberResult.getResult();
		int memberCount = memberResult.getCount();

		model.addAttribute("memberResult", memberlist);
		model.addAttribute("memberCount", memberCount);

		return "member/list";
	}

	// member/view/사용자아이디 ⇒ 회원정보 조회 하기
	@GetMapping("/member/view/{articleEmail}")
	public String viewDetailPage(Model model, @PathVariable String articleEmail) {
		MemberVO findeResult = this.memberService.findMemberArticleId(articleEmail);
		model.addAttribute("articleEmail", findeResult);

		return "member/view";
	}

	// member/update/사용자 아이디 ⇒회원 정보 수정 페이지 보기
	@GetMapping("/member/update/{articleEmail}")
	public String viewUpdatePage(Model model, @PathVariable String articleEmail) {
		MemberVO update = this.memberService.findMemberArticleId(articleEmail);
		model.addAttribute("article", update);

		return "member/update";
	}

	// member/update/사용자 아이디 ⇒ 회원 정보 수정 하기
	@PostMapping("/member/update/{articleEmail}")
	public String doUpdatePage(@PathVariable String articleEmail, MemberVO memberVO) {
		memberVO.setEmail(articleEmail);
		boolean updateResult = this.memberService.updateMemberArticleById(memberVO);
		System.out.println("성공: " + updateResult);

		return "redirect:/member/view/" + articleEmail;
	}

	// member/delete?id=사용자 아이디 ⇒ 회원 정보 삭제 하기
	@GetMapping("/member/delete")
	public String doDeletePage(@RequestParam String email) {
		boolean delete = this.memberService.deleteMemberById(email);
		System.out.println(delete);

		return "redirect:/sign";
	}

}
