package com.ktdsuniversity.edu.movie.web;

import java.lang.reflect.Member;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.movie.service.MovieService;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.MovieUpdateVO;
import com.ktdsuniversity.edu.movie.vo.request.MovieWriteVO;
import com.ktdsuniversity.edu.movie.vo.response.MovieSearchResultVO;

import jakarta.validation.Valid;

@Controller
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	// 영화 목록 조회
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
	public String doMoviePage(@Valid @ModelAttribute MovieWriteVO movieWriteVO, 
			BindingResult bindingResult, @RequestParam MultipartFile attachFile, 
			Model model, @SessionAttribute("__LOGIN_DATA__") MemberVO loginMember) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("movieWriteVO", movieWriteVO);
			return "movie/write";
		}
		
		// 이런식으로 xss 를 처리하는 것이 있다.
		// 디비에 인설트와 업데이트를 할 때 문제가 생길 수 있다.
		// 에러가 나는 상황을 어떻게 할 수 없다.
		// 가장 안전하지만 문제가 null 포인트와 에러 발생이 생긴다.
		/*
		 * String posterUrl = movieWriteVO.getPosterUrl(); posterUrl =
		 * posterUrl.replace("<", "&lt;").replace(">", "&gt;");
		 * movieWriteVO.setPosterUrl(posterUrl);
		 * 
		 * String title = movieWriteVO.getTitle(); title = title.replace("<",
		 * "&lt;").replace(">", "&gt;"); movieWriteVO.setTitle(title);
		 */
		
		if (!attachFile.isEmpty()) {
			String fileName = attachFile.getOriginalFilename();
			// 실제로는 서버에 저장해야 함
			// 예: /upload/파일명
			String savedPath = "/upload/" + fileName;
			movieWriteVO.setPosterUrl(savedPath);
		}

		// movieWriteVO.set
		boolean createMovie = this.movieService.insertMovie(movieWriteVO);
		logger.debug("영화 조회: {}",createMovie);

		return "redirect:/";
	}

	// 영화 view
	@GetMapping("/view/{articleMovieID}")
	public String viewMovieIdPage(Model model, @PathVariable String articleMovieID) {
		MovieVO findResult = this.movieService.findMovieById(articleMovieID);
		model.addAttribute("articleMovieID", findResult);

		return "movie/view";
	}
	
	// 영화 수정
	@GetMapping("/update/{articleMovieID}")
	public String viewUpdatePage(@PathVariable String articleMovieID, 
			Model model, @SessionAttribute("__LOGIN_DATA__") MemberVO loginMember) {
		
		return "movie/update";
	}
	
	@PostMapping("/update/{articleMovieID}")
	public String doUpdateArticle(@PathVariable String articleMovieID, 
			MovieUpdateVO movieUpdateVO, @SessionAttribute("__LOGIN_DATA__") MemberVO loginMember) {
		movieUpdateVO.setMovieId(articleMovieID);
		boolean update = this.movieService.updateMovieByArticleId(movieUpdateVO);
		logger.debug("수정 성공 : {}", update);
		
		return "redirect:/view/" + articleMovieID;
	}
	

	// 영화 삭제
	@GetMapping("/delete")
	public String doDeleteArticle(@RequestParam String id) {
		boolean delete = this.movieService.deleteMovieByArticleId(id);
		logger.debug("삭제 결과 : {}", delete);
		
		return "redirect:/";
	}

	
}
