package com.ktdsuniversity.edu.exception.handlers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
