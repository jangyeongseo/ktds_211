package com.ktdsuniversity.edu.movie.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.MovieWriteVO;

@Mapper
public interface MovieDao {
	List<MovieVO> selectMovieList();
	int insertNewMovie(MovieWriteVO movieWriteVO);
	MovieVO selectMovieById(String articleMovieID);

}
