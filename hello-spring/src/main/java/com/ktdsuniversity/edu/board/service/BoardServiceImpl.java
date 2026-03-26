package com.ktdsuniversity.edu.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.board.dao.BoardDao;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.SearchResultVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired // Dependency Injection (의존성 주입)
	private BoardDao boardDao;

	@Override
	public SearchResultVO findAllBoard() {
		// 게시글 목록 조회
		List<BoardVO> list = this.boardDao.selectBoardList();
		// 게시글 개수 조회
		int count = this.boardDao.selectBoardCount();

		SearchResultVO result = new SearchResultVO();
		result.setResult(list);
		result.setCount(count);

		return result;
	}

}
