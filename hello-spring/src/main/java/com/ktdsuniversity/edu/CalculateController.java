package com.ktdsuniversity.edu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculateController {
///**
// * <pre>	
// * 엔드포인트 = /calc
// * 반환시키는 템플릿 이름 = calc.jsp
// * 템플릿으로 반환시키는 데이터
// *	- 1. firstNum
// * 	-2. secondNum
// *  -3. result(firstNum + secondNum의 결과)
// * 템플릿의 결과는 
// * 	- <div>
// * 	- <span>firstNum의 값</span>
// * 	- <span>secondNum의 값</span>
// * 	- <span> = result의 값</span>
// * 	- </div>
// * 
// *  
// */

	@GetMapping("/calc")
	public String firstNumAndsecondNum(Model model) {
		int firstNum = 2;
		int secondNum = 3;

		model.addAttribute("firstNum", firstNum);
		model.addAttribute("secondNum", secondNum);
		model.addAttribute("result", firstNum + secondNum);

		return "calc";
	}

//	/**
//	 * Browser에서 Endpoint로 파라미터를 보내는 3가지 방법.
//	 * 	1. Query Srting Parameter Endpoint 뒤에 "?"를 이용해서 보내는 방법.
//	 * 		ex> /endpoint?key=value&key2=value2&....
//	 * 		ex> /calc2?firstNum=3&secondNum=3
//	 * 
//	 * 	2. form Parameter => <form></form> 를 이용해서 보내는 방법.
//	 * 		ex> <form action="/endpoint">
//	 * 				<input name="key" /> 
//	 * 				<select name="key">
//	 * 					<option value="value2">Text</option>
//	 * 					<option value="value3">Other Text</option>
//	 * 				</select> 
//	 * 				<textarea name="key">Value</textarea>
//	 * 			</form>
//	 * 
//	 * 	3. Request Body - Http Request Body 영역에 파라미터를 보내는 방법
//	 * 		ex> 파일 업로드, AJAX 파일로 전송
//	 * 
//	 * 	4. Hybrid (Altermative - Conplex)
//	 * 		=> Query String Parameter + Form Parameter
//	 * 		=> Query String Parameter + Request body
//	 * 		* Spring 전용 Parameter
//	 * 			=> Path(URL) Variable
//	 * 				=> Query String Parameter 외의 파라미터를 URL로 보내는 방법.
//	 *
//	 * Spring ENdpoint 에서 파라미터를 받아오는 4가지 방법
//	 * 	1. HttpServletRequest 객체를 이용하는 방법(Spring 에서는 잘 사용되지 않는다.)
//	 * 		=> Query Sring Parameter, Form Parameter, Request Body
//	 * 		=> HTTP header 정보 취득 가능. 요청자(브라우저)의 IP 취득 가능.
//	 * 			=> Endpoint를 호출한 위치(Referrer) 취득 가능.
//	 * => "요청자(브라우저)의 IP 취득 가능"과 "위치(Referrer) 취득 가능"을 알고 싶을때 필요
//	 * 
//	 *  2. @RequestParam 을 이용하는 방법(종종 쓰인다. - 파라미터의 개수가 적을 때 - 2개 이하정도 일경우)
//	 *  	=> Query String Parameter, Form Parameter
//	 *  
//	 * 	3. @ModelAttribute 를 이용하는 방법(가장 많이 사용됨 - 파라미터의 개수가 많을 때)
//	 * 		=> Commend Object, @ModelAttribute Annotation은 생략 가능.
//	 * 		=> Query String Parameter, Form Parameter
//	 * 
//	 *  4. @RequestBody 를 이용하는 방법 (API-AJAX 기반의 프로젝트에서 주로 사용됨.)
//	 *  	=> Request Body 취득.
//	 *  
//	 *  - @PathVariable 를 이용하는 방법(가장 많이 사용됨 - Path(URL) Variable 취득).
//	 * 
//	 * @param model
//	 * @return
//	 */

	// /calc2?f=1&s=3 - Query String 파라미터
	// /calc2?f=null&s=null - Query String 파라미터
	@GetMapping("/calc2")
	public String viewParamCalcPage(@RequestParam(required = false, defaultValue = "0") int f,
			@RequestParam(required = false, defaultValue = "0") int s, Model model) {
		// required = false - 필수 파라미터를 true 가 아닌 false 로 해서 필수가 아니도록 만듬.
		// defaultValue = "0" - String 값으로 보내서 int 값이 없을 경우 null 이 아닌 0으로 보내져라
		int result = f + s;
		model.addAttribute("firstNum", f);
		model.addAttribute("secondNum", s);
		model.addAttribute("result", result);

		return "calc";
	}

}
