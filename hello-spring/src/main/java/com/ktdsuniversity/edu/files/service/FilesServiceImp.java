package com.ktdsuniversity.edu.files.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.vo.request.SearchFileVO;
import com.ktdsuniversity.edu.files.vo.response.DownloadVO;

@Service
public class FilesServiceImp implements FilesService {

	@Autowired
	private FilesDao filesDao;

	@Override
	public DownloadVO findAttachFile(SearchFileVO searchFileVO) {
		DownloadVO result = this.filesDao.selectFilesByFileGroupIdAndFileNum(searchFileVO);

		return result;
	}

	// 파일에 넣으면 디비에도 넣어져야한다.
	// 삭제할려면 디비도 지우고 파일애도 삭제되어야한다.

}
