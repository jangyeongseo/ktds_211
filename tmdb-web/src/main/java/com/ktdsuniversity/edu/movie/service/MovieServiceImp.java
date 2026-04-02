package com.ktdsuniversity.edu.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.helpers.MultipartFileHandler;
import com.ktdsuniversity.edu.movie.dao.MovieDao;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.MovieWriteVO;
import com.ktdsuniversity.edu.movie.vo.response.MovieSearchResultVO;

@Service
public class MovieServiceImp implements MovieService {

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private MultipartFileHandler multipartFileHandler;
	
	@Autowired
	private FilesDao filesDao;

	// 영화 목록 조회
	@Override
	public MovieSearchResultVO selectMovieList() {
		List<MovieVO> list = movieDao.selectMovieList();

		MovieSearchResultVO result = new MovieSearchResultVO();
		result.setList(list);

		return result;
	}

	// 영화 등록 및 영화 이미지 조회
	@Override
	public boolean insertMovie(MovieWriteVO movieWriteVO) {
		int insert = movieDao.insertNewMovie(movieWriteVO);

		// 영화 리스트 조회
		List<MultipartFile> attachFile = movieWriteVO.getAttachFile();
		this.multipartFileHandler.upload(attachFile, movieWriteVO.getMovieId());

		return insert == 1;
	}

	// 영화 디테일 화면
	@Override
	public MovieVO findMovieById(String articleMovieID) {
		MovieVO result = movieDao.selectMovieById(articleMovieID);

		return result;
	}

}
