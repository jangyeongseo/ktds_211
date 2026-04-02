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
public class FilesController {

	@Autowired
	private FilesService filesService;

	private Map<String, String> mimeTypeMap;

	public FilesController() {
		this.mimeTypeMap = new HashMap<>();
		this.mimeTypeMap.put("txt", "text/plain"); // 확장자가 txt라면 text/plain

		// images
		this.mimeTypeMap.put("jpg", "image/jpeg");
		this.mimeTypeMap.put("png", "image/png");
		this.mimeTypeMap.put("webp", "image/webp");
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
	
	// 다운로드
	@GetMapping("/file/{fileGroupId}/{fileNum}")
	public ResponseEntity<Resource> viewDownloadAction(@PathVariable String fileGroupId, @PathVariable int fileNum){
		SearchFileVO seartchFileVO = new SearchFileVO();
		seartchFileVO.setFileGroupId(fileGroupId);
		seartchFileVO.setFileNum(fileNum);
		
		DownloadVO downloadVO = this.filesService.findAttachFile(seartchFileVO);
		System.out.println(downloadVO);
		
		HttpHeaders headers = new HttpHeaders();
		// inline : 부라우저에서 바로 볼수 있음, attachment : 파일을 무조건 다운로드 받고 싶을 때
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + downloadVO.getDisplayName());
		headers.set(HttpHeaders.CONTENT_LANGUAGE, downloadVO.getFileLength() + "");
		headers.set(HttpHeaders.CONTENT_TYPE, this.mimeTypeMap.getOrDefault(downloadVO.getDisplayName().toLowerCase(), "application/octet-stream"));
		
		return ResponseEntity.ok().headers(headers).body(downloadVO.getResource());
	}

}
