package com.ktdsuniversity.edu.replies.web;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.exception.HelloSpringApiException;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.replies.service.RepliesService;
import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;

import jakarta.validation.Valid;

@Controller
public class RepliesController {
	private static final Logger logger = LoggerFactory.getLogger(RepliesController.class);

	@Autowired
	private RepliesService repliesService;

	// AJAX(API) 요청/ 반환.
	// 요청 데이터 + 반환 데이터 => JSON
	// @RequestBody : 요청 데이터를 JSON으로 @ResponseBody: 반환 데이터를 JSON으로 변경
	@ResponseBody
	@PostMapping("/api/replies")
	public RepliesVO doCreateNewReplyAction(@RequestBody @Valid CreateVO createVO,
			BindingResult bindingResult,
			@SessionAttribute("__LOGIN_DATA__") MemberVO loginMember) {
		if(bindingResult.hasErrors()) {
			// bindingResult.getAllErrors(); // 어떤 에러가 존재하는지를 알 수 있다.
			List<FieldError> errors = bindingResult.getFieldErrors(); // field : 멤버변수를 의미, 멤버변수에 에러가 존재하는지 알 수 있다.
			throw new HelloSpringApiException("파라미터가 충분하지 않습니다.", HttpStatus.BAD_REQUEST.value(), errors, "errors");
			
		}
		
		// 전체정보를 반환시킨다.
		createVO.setEmail(loginMember.getEmail());
		logger.debug("reply {}", createVO.getReply());
		logger.debug("email {}", createVO.getEmail());
		logger.debug("articleId {}", createVO.getArticleId());
		logger.debug("parentReplyId {}", createVO.getReply());

		RepliesVO createReply = this.repliesService.createNewReply(createVO);

		return createReply;
	}

}
