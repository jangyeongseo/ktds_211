package com.ktdsuniversity.edu.movie.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.movie.service.MovieService;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.response.MovieSearchResultVO;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;

	// 영화 목록
	@GetMapping("/")
	public String viewMovieList(Model model) {

		MovieSearchResultVO movieSearchResult = this.movieService.selectMovieList();

		List<MovieVO> movieList = movieSearchResult.getList();

		model.addAttribute("movieList", movieList);

		return "movie/index";
	}

	// 영화 등록 화면
	@GetMapping("/write")
	public String viewMoviePage() {
		return "movie/write";
	}

	// 영화 등록
	@PostMapping("/write")
	public String doMoviePage(MovieVO movieVO) {

		boolean createMovie = this.movieService.insertMovie(movieVO);
		System.out.println("결과 : " + createMovie);

		return "redirect:/";
	}
}
