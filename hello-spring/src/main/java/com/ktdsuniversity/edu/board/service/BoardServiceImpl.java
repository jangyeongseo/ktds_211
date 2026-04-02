package com.ktdsuniversity.edu.board.service;

import java.io.File;
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
import com.ktdsuniversity.edu.files.helpers.MultipartFileHandler;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired // Dependency Injection (의존성 주입)
	private BoardDao boardDao;

	@Autowired
	private MultipartFileHandler multipartFileHandler;
	// 업로드는 이걸로 처리할 것이다.

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
		this.multipartFileHandler.upload(attachFiles, writeVO.getId());

		return insertCount == 1;
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

		// 첨부파일 업로드
		List<MultipartFile> attachFiles = updateVO.getAttachFiles();
		this.multipartFileHandler.upload(attachFiles, updateVO.getId());

		// 선택한 파일들만 삭제
		if (updateVO.getDeleteFileNum() != null && updateVO.getDeleteFileNum().size() > 0) {
			// 선택한 파일들의 정보를 조회 -> 파일의 경로 -> 실제 파일을 제거
			List<String> deleteTargets = this.filesDao.selectFilesPathByFilesGroupIdAndFileNums(updateVO);
			for (String target : deleteTargets) {
				new File(target).delete();
			}

			// 선택한 파일들을 FILES 테이블에서 제거
			int deleteCount = this.filesDao.deleteFilesByFileGroupIdAndFileNums(updateVO);
			System.out.println("삭제한 파일 데이터의 수 : " + deleteCount);
		}

		return updateId == 1;
	}

	// 삭제
	@Override
	public boolean findBoarDelectArticleId(String id) {
		int deleteId = this.boardDao.deleteViewById(id);
		System.out.println(deleteId);

		// 삭제하려는 게시글에 첨부된 파일 목록을 가져온다.
		List<String> filePaths = this.filesDao.selectFilePathByFileGroupId(id);
		if (filePaths != null && filePaths.size() > 0) {
			// 파일 목록이 존재하면, 모든 파일들을 제거한다.
			for (String path : filePaths) {
				new File(path).delete();
			}
		}

		// 파일 목록을 제거한 이후에 "FILES" 테이블에서 해당 파일 전보를 모두 삭제한다.
		int deleteFileCount = this.filesDao.deleteFileByFileGroupId(id);
		System.out.println("삭제한 파일 데이터의 수 : " + deleteFileCount);

		return deleteId == 1;
	}

}
