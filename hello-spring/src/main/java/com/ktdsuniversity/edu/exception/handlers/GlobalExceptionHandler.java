package com.ktdsuniversity.edu.exception.handlers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.common.utils.AuthUtils;
import com.ktdsuniversity.edu.exception.HelloSpringApiException;
import com.ktdsuniversity.edu.exception.HelloSpringException;

/**
 * Spring Application 에서 던져진 catch 되지 않은 예외들을 처리하는 클래스
 * 
 * @Controller와 유사한 형태 => URL이 endpoint
 * 
 * @ControllerAdive => Exception이 endpoint
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 권한 거부 - AuthorizationDeniedException 발생 시 실행되는 예외 처리 메서드
	 */
	@ExceptionHandler(AuthorizationDeniedException.class)
	public String viewLoginPage(AuthorizationDeniedException ade, Model model) {

	    // 1️. 현재 사용자가 로그인 상태인지 확인
	    // SecurityContext에 인증 정보가 있는지 체크
	    boolean isAuthenticated = AuthUtils.isAuthenticated();

	    // 2️. 로그인은 되어 있지만 권한이 없는 경우
	    if (isAuthenticated) {
	        // 사용자에게 보여줄 에러 메시지 전달
	        model.addAttribute("errorMessage", "잘못된 접근입니다");
	        // 403 에러 페이지로 이동
	        return "errors/403";
	    }

	    // 3️. 로그인이 안 된 상태에서 접근한 경우
	    // -> 인증 자체가 안 된 상태
	    logger.error(ade.getMessage(), ade);

	    /**
	     * forward vs redirect 차이
	     * 
	     * redirect:/login
	     * -> 브라우저 URL이 /login으로 변경됨
	     * -> 새로운 요청 발생
	     * 
	     * forward:/login
	     * -> URL은 그대로 유지
	     * -> 서버 내부에서 /login 페이지를 보여줌
	     */
	    
		// 로그인 페이지로 이동 - URL은 유지
	    return "forward:/login";
	}

	/**
	 * HelloSpringException이 던져지면, viewErrorPage가 실행된다.
	 * 
	 * @return 사용자에게 보여줄 템플릿의 이름
	 */
	@ExceptionHandler(HelloSpringException.class)
	public String viewErrorPage(HelloSpringException hse, Model model) {
		logger.error(hse.getMessage(), hse); // 에러 추적을 위해서 적는 거다.

		String message = hse.getMessage();
		model.addAttribute("errorMessage", message);

		String errorPage = hse.getErrorPage();

		Object modelData = hse.getObject();
		if (modelData != null) {
			model.addAttribute(hse.getModelName(), modelData);
			// form:form modelAttribute랑 이름이 같아야 나옴.
		}

		return errorPage;
	}

	@ResponseBody
	@ExceptionHandler(HelloSpringApiException.class)
	public Map<String, Object> returnErrorJson(HelloSpringApiException hsae) {
		logger.error(hsae.getMessage(), hsae);

		int status = hsae.getErrorStatus();
		Object errorObjet = hsae.getError();

		Map<String, Object> responseData = new HashMap<>();
		responseData.put("status", status);
		responseData.put("error", errorObjet);

		return responseData;
	}

	/**
	 * null 포인트 값이 이것인지 확인하고 맞으면 동작을 한다.
	 * 
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public String viewSystemErrorPage(RuntimeException re) {
		logger.error(re.getMessage(), re);
		return "errors/500";
	}
}
