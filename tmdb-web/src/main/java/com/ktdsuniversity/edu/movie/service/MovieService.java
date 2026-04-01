package com.ktdsuniversity.edu.movie.service;

import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.MemberWriteVO;
import com.ktdsuniversity.edu.movie.vo.response.MovieSearchResultVO;

public interface MovieService {
	MovieSearchResultVO selectMovieList();

	boolean insertMovie(MemberWriteVO memberWriteVO);

	MovieVO findMovieById(String articleMovieID);

}
