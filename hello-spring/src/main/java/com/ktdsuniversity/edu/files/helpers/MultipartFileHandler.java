package com.ktdsuniversity.edu.files.helpers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.vo.response.UploadVO;

// String 으로 만들어서 
//빈컨테이너한테 넣는다
@Component
public class MultipartFileHandler {

	@Autowired
	private FilesDao filesDao;

	public void upload(List<MultipartFile> attachFiles, String fileGroupId) {
		if (attachFiles != null && attachFiles.size() > 0) {
			for (int i = 0; i < attachFiles.size(); i++) {
				// 업로드를 하지 않았는데 했다고 판단한 경우에는 다음 반복으로 넘어가라.
				if (attachFiles.get(i).isEmpty()) {
					continue;
				}
				// UUID => 현재 시간을 기준으로 난수화 된 값을 가져오는 방법
				// 전세계에서 동시에 발급받더라도 절대로 중복이 일어나지 않는다.
				String obfuscateName = UUID.randomUUID().toString();

				// 업로드한 파일이 서버컴퓨터의 파일 시스템에 저장되도록 한다.
				File storeFiles = new File("C:\\uploadFiles", obfuscateName);
				// "C:\\uploadFiles" 폴더가 없으면 생성하라!
				if (!storeFiles.getParentFile().exists()) {
					storeFiles.getParentFile().mkdirs();
				}
				try {
					attachFiles.get(i).transferTo(storeFiles);
					// FILES 테이블에 첨부파일 데이터를 insert
					UploadVO uploadVO = new UploadVO();
					String filename = attachFiles.get(i).getOriginalFilename();
					String ext = filename.substring(filename.lastIndexOf(".") + 1); // 확장자

					uploadVO.setFileGroupId(fileGroupId);
					uploadVO.setObfuscateName(obfuscateName);
					uploadVO.setDisplayName(filename);
					uploadVO.setExtendName(ext);
					uploadVO.setFileLength(storeFiles.length()); // 파일의 크기를 구해라
					uploadVO.setFilePath(storeFiles.getAbsolutePath()); // 실제 경로

					int result = this.filesDao.insertAttachFile(uploadVO);
					System.out.println("파일 insert 결과: " + result);

				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
