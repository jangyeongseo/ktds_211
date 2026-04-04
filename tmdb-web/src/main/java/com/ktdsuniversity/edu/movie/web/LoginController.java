package com.ktdsuniversity.edu.movie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.movie.service.MovieService;

@Controller
public class LoginController {
	@Autowired
	private MovieService movieService;

	// 영화 회원가입
	@GetMapping("/sign")
	public String viewSignPage() {
		return "movie/sign";
	}

	// 영화 로그인
	@GetMapping("/login")
	public String viewLoginPage() {
		return "movie/login";
	}

}
