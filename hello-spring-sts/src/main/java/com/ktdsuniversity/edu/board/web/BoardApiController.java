package com.ktdsuniversity.edu.board.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.board.enums.ReadType;
import com.ktdsuniversity.edu.board.service.BoardService;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.request.SearchListVO;
import com.ktdsuniversity.edu.board.vo.request.UpdateVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;
import com.ktdsuniversity.edu.board.vo.response.SearchResultVO;
import com.ktdsuniversity.edu.exceptions.HelloSpringApiException;
import com.ktdsuniversity.edu.members.vo.MembersVO;

import jakarta.validation.Valid;

@Controller
public class BoardApiController {

	private static final Logger logger = LoggerFactory.getLogger(BoardApiController.class);
	
	/**
	 * 빈 컨테이너에 들어있는 객체 중 타입이 일치하는 객체를 할당 받는다.
	 */
	@Autowired
	private BoardService boardService;
	
	// http://192.168.211.11:8080/?pageNo=0&listSize=10&searchType=&searchKeyword
	@ResponseBody
	@GetMapping("/api/articles") // 모든것들을 보여주겠다.
	public Map<String, Object> viewListPage(SearchListVO searchListVO) {
		
		SearchResultVO searchResult = this.boardService.findAllBoard(searchListVO);
		
		Map<String, Object> jsonResult = new HashMap<>();
		jsonResult.put("result", searchResult);
		jsonResult.put("pagination", searchListVO);
		
		return jsonResult;
	}

	// 게시글을 등록하는 EndPoint
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@PostMapping("/api/articles") // 메핑하는 방식이 달라서 url 같이 해도 괜춘
	public Map<String, Boolean> doWriteAction(@Valid WriteVO writeVO,
								// multipart(파일 업로드)가 포함된 요청에서는 @RequestBody를 사용할 수 없다
							    BindingResult bindingResult,
							    Authentication authentication) {
		// 사용자의 입력값을 검증 했을 때, 에러가 있다면
		if (bindingResult.hasErrors()) {
			throw new HelloSpringApiException("글쓰기 실패", HttpStatus.BAD_REQUEST.value()	, bindingResult.getFieldErrors());
		}
		
		MembersVO loginUser = (MembersVO) authentication.getPrincipal();
		writeVO.setEmail(loginUser.getEmail());
		
		boolean createResult = this.boardService.createNewBoard(writeVO);
		
		return Map.of("result", createResult);
	}
	
	@ResponseBody
	@GetMapping("/api/articles/{articleId}")
	public BoardVO viewDetailPage(@PathVariable String articleId) {
		BoardVO findResult = this.boardService.findBoardByArticleId(articleId, ReadType.VIEW);
		
		return findResult;
	}
	
	/**
	 * 삭제하려는 게시글의 작성자가 본인이거나 슈퍼관리자이거나 관리자 일 경우만 삭제를 수행한다.
	 * 슈퍼관리자, 관리자도 아니고 본인이 작성하지 않은 게시글일 경우 HelloSpringException을 던진다.
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@DeleteMapping("/api/articles/{id}")
	public Map<String, Boolean> doDeleteAction(@PathVariable String id) {
		boolean deleteResult = this.boardService.deleteBoardByArticleId(id);
		logger.debug("삭제 결과? {}", deleteResult);
		
		return Map.of("result", deleteResult);
		
	}
	
	@PreAuthorize("hasRole('RL-20260414-000001')")
	@ResponseBody
	@DeleteMapping("/api/articles")
	public Map<String, Boolean> doDeleteAllAction() {
		boolean deleteResult = this.boardService.deleteAllBoard();
		logger.debug("삭제 결과? {}", deleteResult);
		
		return Map.of("result", deleteResult);
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	@PutMapping("/api/articles/{articleId}")
	public Map<String, Boolean> doUpdateAction(@PathVariable String articleId,
			UpdateVO updateVO,
			// multipart(파일 업로드)가 포함된 요청에서는 @RequestBody를 사용할 수 없다
			Authentication authentication) {
		
		updateVO.setId(articleId);
		
		MembersVO loginUser = (MembersVO) authentication.getPrincipal();
		updateVO.setEmail(loginUser.getEmail());
		
		boolean updateResult = this.boardService.updateBoardByArticleId(updateVO);
		logger.debug("수정 성공? {}", updateResult);
		
		return Map.of("result", updateResult);
	}
	
}
