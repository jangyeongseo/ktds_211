package com.ktdsuniversity.edu.movie.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.movie.service.MovieService;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.MemberWriteVO;
import com.ktdsuniversity.edu.movie.vo.response.MovieSearchResultVO;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;

	// 영화 목록 조회
	@GetMapping("/list")
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
	public String doMoviePage(MemberWriteVO memberWriteVO) {
		boolean createMovie = this.movieService.insertMovie(memberWriteVO);
		System.out.println("결과 : " + createMovie);

		return "redirect:/list";
	}

	// 영화 view
	@GetMapping("/view/{articleMovieID}")
	public String viewMovieIdPage(Model model, @PathVariable String articleMovieID) {
		MovieVO findResult = this.movieService.findMovieById(articleMovieID);
		model.addAttribute("articleMovieID", findResult);

		return "movie/view";
	}

	// 영화 삭제
	@GetMapping("/delete")
	public String doDeletePage() {
		return null;
	}

	// 영화 수정
}
