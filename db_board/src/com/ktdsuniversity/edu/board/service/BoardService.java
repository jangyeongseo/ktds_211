package com.ktdsuniversity.edu.board.service;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.board.dao.BoardDao;
import com.ktdsuniversity.edu.board.dao.query.BoardQuery;
import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.db.helper.SQLType;
import com.ktdsuniversity.edu.board.vo.BoardVO;

// 트랜젝션 처리
public class BoardService {
	private DataAccessHelper dah;
	private BoardDao boardDao;

	// DB 연결 객체 전달
	public BoardService(DataAccessHelper dah) {
		this.dah = dah;
		this.boardDao = new BoardDao(this.dah);
	}

	// 전체 조회
	// select 는 변화를 주지 않기 때문에 commit 과 rollback 이 필요없다.
	public List<BoardVO> readAllArticle() {
		List<BoardVO> result = this.boardDao.readAllArticle();
		return result;
	}

	// 조회수 및 조회한 게시글 내용
	public BoardVO readArticle(String articleId) {
		try {
			// 순서 중요 - 조회수를 먼저 해야 변한값을 가져올 수 있다.
			this.boardDao.updateViewCount(articleId);
			BoardVO result = this.boardDao.readArticle(articleId);
			this.dah.commit();
			return result;

		} catch (RuntimeException re) {
			this.dah.rollback();
		}
		return null;
	}

	// 삭제
	public void deleteArticle(String articleId) {
		try {
			this.boardDao.deleteArticle(articleId);
			this.dah.commit();
		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}

	// 내용 수정
	public void modifyArticle(BoardVO modifyArticle) {
		try {
			this.boardDao.modifyArticle(modifyArticle);
			this.dah.commit();
		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}

	// 내용 디비에 전달
	public void createNewArticle2(BoardVO newArticle) {
		try {
			this.boardDao.createNewArticle2(newArticle);
			this.dah.commit();
		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}

}
