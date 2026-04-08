package com.ktdsuniversity.edu.movie.service;

import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.MovieUpdateVO;
import com.ktdsuniversity.edu.movie.vo.request.MovieWriteVO;
import com.ktdsuniversity.edu.movie.vo.response.MovieSearchResultVO;

public interface MovieService {
	MovieSearchResultVO selectMovieList();

	boolean insertMovie(MovieWriteVO movieWriteVO);

	MovieVO findMovieById(String articleMovieID);

	boolean deleteMovieByArticleId(String id);

	boolean updateMovieByArticleId(MovieUpdateVO movieUpdateVO);

}
