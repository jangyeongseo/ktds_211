package com.ktdsuniversity.edu.files.helpers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.vo.response.UploadVO;

// 다운로드 및 수정
@Component
public class MultipartFileHandler {
	private static final Logger logger = LoggerFactory.getLogger(MultipartFileHandler.class);

	@Autowired
	private FilesDao filesDao;

	public void upload(List<MultipartFile> attachFile, String fileGroupId) {
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

					uploadVO.setFileGroupId(fileGroupId);
					uploadVO.setObfuscateName(filename);
					uploadVO.setDisplayName(filename);
					uploadVO.setExtendName(ext);
					uploadVO.setFileLength(storeFiles.length());
					uploadVO.setFilePath(storeFiles.getAbsolutePath());

					int result = this.filesDao.insertAttachFile(uploadVO);
					logger.debug("결과 : {}", result);

				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
