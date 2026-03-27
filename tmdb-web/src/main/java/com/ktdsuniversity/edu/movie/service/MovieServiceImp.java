package com.ktdsuniversity.edu.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.movie.dao.MovieDao;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.response.MovieSearchResultVO;

@Service
public class MovieServiceImp implements MovieService {

	@Autowired
	private MovieDao movieDao;

	@Override
	public MovieSearchResultVO selectMovieList() {
//		영화 목록 조회
		List<MovieVO> list = movieDao.selectMovieList();

		MovieSearchResultVO result = new MovieSearchResultVO();
		result.setList(list);

		return result;
	}

	@Override
	public boolean insertMovie(MovieVO movieVO) {
		int insert = movieDao.insertNewMovie(movieVO);

		return insert > 0;
	}

}
