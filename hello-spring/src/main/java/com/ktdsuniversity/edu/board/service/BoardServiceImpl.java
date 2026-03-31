package com.ktdsuniversity.edu.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.board.dao.BoardDao;
import com.ktdsuniversity.edu.board.enums.ReadType;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.request.UpdateVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;
import com.ktdsuniversity.edu.board.vo.response.SearchResultVO;
import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.vo.response.UploadVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired // Dependency Injection (의존성 주입)
	private BoardDao boardDao;

	@Autowired
	private FilesDao filesDao;

	@Override
	public SearchResultVO findAllBoard() {
		SearchResultVO result = new SearchResultVO();

		// 게시글 개수 조회
		int count = this.boardDao.selectBoardCount();
		result.setCount(count);

		if (count == 0) {
			return result;
		}

		// 게시글 목록 조회
		List<BoardVO> list = this.boardDao.selectBoardList();
		result.setResult(list);

		return result;
	}

	// 파일 업로드 및 게시글 등록
	@Override
	public boolean createNewBoard(WriteVO writeVO) {
		// dao => insert 요청
		// mybatis 는 insert, update, delete 를 수행했을 때
		// 영향을 받은 row 의 수를 반환시킨다.
		// ex>
		// insert => insert 된 row 의 개수 반환
		// update => update 된 row 의 개수 반환
		// delete => delete 된 row 의 개수 반환
		int insertCount = this.boardDao.insertNewBoard(writeVO);
		System.out.println("생성된 게시글의 개수?" + insertCount);

		// 첨부파일 업로드
		List<MultipartFile> attachFiles = writeVO.getAttachFiles();
		if (attachFiles != null && attachFiles.size() > 0) {
			for (int i = 0; i < attachFiles.size(); i++) {
				// 업로드한 파일이 서버컴퓨터의 파일 시스템에 저장되도록 한다.
				File storeFiles = new File("C:\\uploadFiles", attachFiles.get(i).getOriginalFilename());
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

					uploadVO.setFileNum(i + 1);
					uploadVO.setFileGroupId(writeVO.getId());
					uploadVO.setObfuscateName(filename);
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
		return insertCount > 0;
	}

	@Override
	public BoardVO findBoardArticleId(String articleId, ReadType readType) {
		if (readType == ReadType.VIEW) {
			// 조회수 증가
			int updateCount = this.boardDao.updateViewCntIncreaseById(articleId);
			System.out.println("조회수가 증가된 게시글의 수 : " + updateCount);

			if (updateCount == 0) {
				// 존재하지 않는 게시글을 조회하려 했다.
				return null;
				// throw new RuntimeException("존재하지 않는 게시글입니다."); - 존재하지 않는 화면으로나옴 오류 메세지가.
			}

		}

		// 게시글 조회.
		BoardVO board = this.boardDao.selectBoardById(articleId);

		// 조회한 게시글을 반환.
		return board;
	}

	// 수정
	@Override
	public boolean updateBoardArticleId(UpdateVO updateVO) {
		int updateId = this.boardDao.updateViewById(updateVO);
		System.out.println(updateId);

		return false;
	}

	// 삭제
	@Override
	public boolean findBoarDelectArticleId(String id) {
		int deleteId = this.boardDao.deleteViewById(id);
		System.out.println(deleteId);

		return deleteId > 0;
	}

}
