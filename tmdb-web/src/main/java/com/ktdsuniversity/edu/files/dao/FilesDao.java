package com.ktdsuniversity.edu.files.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.files.vo.request.SearchFileVO;
import com.ktdsuniversity.edu.files.vo.response.DownloadVO;
import com.ktdsuniversity.edu.files.vo.response.UploadVO;

@Mapper
public interface FilesDao {

	int insertAttachFile(UploadVO uploadVO);

	DownloadVO selectFindAttachFileGroupIdAndFileNum(SearchFileVO seartchFileVO);
	

}
