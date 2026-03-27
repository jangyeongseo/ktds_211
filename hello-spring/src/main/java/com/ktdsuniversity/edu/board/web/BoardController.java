package com.ktdsuniversity.edu.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.HelloSpringApplication;
import com.ktdsuniversity.edu.board.service.BoardService;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;
import com.ktdsuniversity.edu.board.vo.response.SearchResultVO;

@Controller
public class BoardController {

	private final HelloSpringApplication helloSpringApplication;
	/**
	 * 빈 컨테이너에 들어있는 객체 중 탕비이 일치하는 객체를 할당 받는다.
	 */
	@Autowired
	private BoardService boardService;

	BoardController(HelloSpringApplication helloSpringApplication) {
		this.helloSpringApplication = helloSpringApplication;
	}

	@GetMapping("/")
	public String viewListPage(Model model) {
		SearchResultVO searchResult = this.boardService.findAllBoard();

		// 게시글의 목록을 조회
		List<BoardVO> list = searchResult.getResult();
		// 게시글의 개수를 조회
		int searchCount = searchResult.getCount();
		model.addAttribute("searchResult", list);
		model.addAttribute("searchCount", searchCount);
		return "board/list";
	}

	// 게시글 등록 화면 보여주는 EndPoint
	@GetMapping("/write")
	public String viewWritePage() {
		return "board/write";
	}

	// public String doWritePage(@ModelAttribute WriteVO writeVO) 이렇게도 괜찮고
	// public String doWritePage(WriteVO writeVO) { 이렇게도 괜찮다
	// @ModelAttribute - 생략이 가능하다.
	@PostMapping("/write")
	public String doWritePage(@ModelAttribute WriteVO writeVO) {
		System.out.println(writeVO.getSubject());
		System.out.println(writeVO.getContent());
		System.out.println(writeVO.getEmail());

		// create, update, delete => 성공/실패 여부 반환
		boolean createResult = this.boardService.createNewBoard(writeVO);
		System.out.println("게시글 생성 성골?" + createResult);

		// redirect: 브라우저에게 다음 End Point를 요청하도록 지시.
		// redirect:/ => 브라우저에게 "/" endpoint 로 이동하도록 지시
		return "redirect:/";
	}

}
