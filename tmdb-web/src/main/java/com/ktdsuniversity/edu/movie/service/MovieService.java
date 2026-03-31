package com.ktdsuniversity.edu.movie.service;

import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.response.MovieSearchResultVO;

public interface MovieService {
	MovieSearchResultVO selectMovieList();

	boolean insertMovie(MovieVO movieVO);

	MovieVO findMovieById(String articleMovieID);

}
