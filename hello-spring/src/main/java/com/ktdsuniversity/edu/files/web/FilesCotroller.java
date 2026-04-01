package com.ktdsuniversity.edu.files.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ktdsuniversity.edu.files.service.FilesService;
import com.ktdsuniversity.edu.files.vo.request.SearchFileVO;
import com.ktdsuniversity.edu.files.vo.response.DownloadVO;

@Controller
public class FilesCotroller {

	@Autowired
	private FilesService filesService;

	private Map<String, String> mimeTypeMap;

	public FilesCotroller() {
		this.mimeTypeMap = new HashMap<>();
		this.mimeTypeMap.put("txt", "text/plain"); // 확장자가 txt라면 text/plain

		// images
		this.mimeTypeMap.put("jpg", "image/jpeg");
		this.mimeTypeMap.put("png", "image/png");
		this.mimeTypeMap.put("webp", "image/png");
		this.mimeTypeMap.put("gif", "image/gif");
		this.mimeTypeMap.put("svg", "image/svg");

		// Ms Office
		this.mimeTypeMap.put("csv", "text/csv");
		this.mimeTypeMap.put("xls", "application/vnd.ms-excel");
		this.mimeTypeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		this.mimeTypeMap.put("ppt", "application/vnd.ms-powerpoint");
		this.mimeTypeMap.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
		this.mimeTypeMap.put("pdf", "application/pdf");

		// Archive
		this.mimeTypeMap.put("zip", "application/zip");

	}

	// ResponseEntity에 넣을 것이 객체면 FileVO, 문자면 String, 숫자면 intger
	@GetMapping("/file/{fileGroupId}/{fileNum}")
	public ResponseEntity<Resource> viewDownloadAction(@PathVariable String fileGroupId, @PathVariable int fileNum) {
		SearchFileVO searchFileVO = new SearchFileVO();
		searchFileVO.setFileGroupId(fileGroupId);
		searchFileVO.setFileNum(fileNum);

		// 여러개의 파라미터 값을 전달할 경우 VO에 넣고 그걸 전달하는 것이 좋다.
		// 다운로드를 위한 정보와 파일 찾아오기
		DownloadVO downloadVO = this.filesService.findAttachFile(searchFileVO);

		// 다운로드 시작
		// HTTP Response 셋팅
		// HTTP Response Header 셋팅
		HttpHeaders headers = new HttpHeaders();
		// Content-Disposition : 다운로드할 파일의 이름 작성
		// "attachment;" -> 파일을 첨부해서 보내꼣다.
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =" + downloadVO.getDisplayName());
		// Content-Length : 다운로드할 파일의 크기(bytes) 지정
		headers.set(HttpHeaders.CONTENT_LANGUAGE, downloadVO.getFileLength() + "");
		// Content-Type : 다운로드할 파일의 마임타입(Mime-type) 작성
		headers.set(HttpHeaders.CONTENT_TYPE,
				this.mimeTypeMap.getOrDefault(downloadVO.getDisplayName().toLowerCase(), "application/octet-steam"));
		// 키가 존재하지 않는 다면 application/octet-steam 이걸로 반환 시켜라

		return ResponseEntity.ok().headers(headers).body(downloadVO.getResource());
	}

}
