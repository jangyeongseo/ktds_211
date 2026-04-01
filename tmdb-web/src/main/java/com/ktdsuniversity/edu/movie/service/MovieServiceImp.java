package com.ktdsuniversity.edu.movie.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.vo.response.UploadVO;
import com.ktdsuniversity.edu.movie.dao.MovieDao;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.MovieWriteVO;
import com.ktdsuniversity.edu.movie.vo.response.MovieSearchResultVO;

@Service
public class MovieServiceImp implements MovieService {

	@Autowired
	private MovieDao movieDao;

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
		if (attachFile != null && attachFile.size() > 0) {
			for (int i = 0; i < attachFile.size(); i++) {
				File storeFiles = new File("C:\\movieUpload", attachFile.get(i).getOriginalFilename());

				if (!storeFiles.getParentFile().exists()) {
					storeFiles.getParentFile().mkdirs();
				}

				try {
					attachFile.get(i).transferTo(storeFiles);
					UploadVO uploadVO = new UploadVO();
					String filename = attachFile.get(i).getOriginalFilename();
					String ext = filename.substring(filename.lastIndexOf(".") + 1);

					uploadVO.setFileGroupId(movieWriteVO.getMovieId());
					uploadVO.setObfuscateName(filename);
					uploadVO.setDisplayName(filename);
					uploadVO.setExtendName(ext);
					uploadVO.setFileLength(storeFiles.length());
					uploadVO.setFilePath(storeFiles.getAbsolutePath());

					int result = this.filesDao.insertAttachFile(uploadVO);
					System.out.println("결과 : " + result);

				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}

		return insert == 1;
	}

	// 영화 디테일 화면
	@Override
	public MovieVO findMovieById(String articleMovieID) {
		MovieVO result = movieDao.selectMovieById(articleMovieID);

		return result;
	}

}
