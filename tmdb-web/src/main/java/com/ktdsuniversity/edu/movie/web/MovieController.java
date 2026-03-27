package com.ktdsuniversity.edu.movie.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.movie.service.MovieService;
import com.ktdsuniversity.edu.movie.vo.MovieSearchResultVO;
import com.ktdsuniversity.edu.movie.vo.MovieVO;

@Controller
public class MovieController {
	@Autowired
	private MovieService movieService;

	@GetMapping("/")
	public String movieList(Model model) {
		MovieSearchResultVO movieSearchResult = this.movieService.selectMovieList();
//		영화 목록 조회
		List<MovieVO> movieList = movieSearchResult.getList();
		model.addAttribute("movieList", movieList);

		return "index";
	}

}
