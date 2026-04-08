package com.ktdsuniversity.edu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// end point를 생성하는 역할 // 20260325 - 설명
@Controller // 해당 클래스가 endpoint (엔드포인트)를 만들 수 있도록 지원
public class TestEndPointController {
	private static final Logger logger = LoggerFactory.getLogger(TestEndPointController.class);
	
	// 20260325 - 설명
	// Spring APplication이 시작이 될때
	// @Controller가 적용된 모든 클래스를 찾아
	// 해당 클래스들을 인스턴스로 생성한다.
	// 생성된 인스턴스들은 Bean Container에 저장된다.
	public TestEndPointController() {
		logger.debug("TestEndPoinController 인스턴스 만들어짐! {}", this);
	}

	// "/jsp" 엔드포인트
	// -hellojsp.jsp 파일을 일어서 html로 반환시킨 후 결과를 반환.
	@GetMapping("/root")
	public String viewsHellpJspPage(Model model) {
		// 20260326
		// Model model parameter
		// => Template Engine(JSP)에게 데이터를 전송시키는 객체
		logger.debug("{}", model);
		// myname이라는 키(변수명)로 "장민창" 할당해서 템플릿에게 전달.
		model.addAttribute("myname", "장");
		model.addAttribute("age", "26");
		logger.debug("{}", model);// 데이터를 보낸 후의 상태

		// 20260325 - 설명
		// spring.mvc.view.prefix + hellojsp + spring
		// /WEB-INF/views/ + hellojsp + .jsp
		// /WEB-INF/views/hellojsp.jsp
		// Controller 에서 String 값을 반환시켰을 경우
		// prefix + 반환걊 + suffix 의 경로를
		// /src/main/webapp 아래에서 탐색한다.

		// 탐색한 jsp 파일을 hTML로 변환시켜 브라우저에게 전달.
		return "hellojsp";
	}

	// 20260325 - 설명
	// 사용자가 "/" 엔드포인트에 접근하면
	// "첫 페이지입니다. 환영합니다."를 브라우저에 보내주는 코드 작성.
//	@GetMapping("/")
//	@ResponseBody
//	public String test() {
//		return "첫 페이지입니다. 환영합니다.";
//	}

	// "/hello" 엔드포인트 생선
	@GetMapping("/hello")
	// 사용자가 "/hello" 엔드포인트를 요청할 경우. 사용자에게 보여줄 html 페이지 생성
	public ResponseEntity<String> viewHelloHtml() {
		// 사용자에게 보여줄 HTML을 보요준다.
		return new ResponseEntity<>("Hello 안녕", HttpStatus.OK);
	}

}
