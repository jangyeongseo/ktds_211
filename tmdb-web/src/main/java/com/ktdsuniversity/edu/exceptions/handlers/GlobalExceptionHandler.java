package com.ktdsuniversity.edu.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ktdsuniversity.edu.exceptions.TmdbWebException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(TmdbWebException.class)
	public String viewErrorPage(TmdbWebException twe, Model model) {
		logger.error(twe.getMessage(), twe);

		String message = twe.getMessage();
		model.addAttribute(message);

		String errorPage = twe.getErrorPage();
		
		Object modelData = twe.getObject();
		if (modelData != null) {
			model.addAttribute(twe.getModelName() , modelData);
		}

		return errorPage;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String viewSystemErrorPage(RuntimeException re) {
		logger.error(re.getMessage(), re);
		
		return "errors/500";
	}
}
