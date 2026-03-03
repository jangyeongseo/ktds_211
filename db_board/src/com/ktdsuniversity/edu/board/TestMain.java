package com.ktdsuniversity.edu.board;

import com.ktdsuniversity.edu.board.dao.BoardDao;
import com.ktdsuniversity.edu.board.vo.BoardVO;

public class TestMain {

	public static void main(String[] args) {
		// 게시글 작성(DB 게시글 생성)
		BoardVO newArticle = new BoardVO();
		newArticle.setTitle("새로운 게시글입니다.");
		newArticle.setContent("새로운 게시글 내용입니다.");

		BoardDao boardDao = new BoardDao();
		int insertCount = boardDao.createNewArticle(newArticle);
		System.out.println(insertCount + "개의 게시글이 생성되었습니다.");

		boardDao.createNewArticle2(newArticle);

		BoardVO modifyArticle = new BoardVO();
		modifyArticle.setId("BO-20260303-000004");
		modifyArticle.setTitle("제목 수정");
		modifyArticle.setContent("내용이 변경");
		boardDao.modifyArticle(modifyArticle);

		boardDao.deleteArticle("BO-20260303-000008");

		// 게시글 조회. (BO-20260303-000016)
		BoardVO article = boardDao.readArticle("BO-20260303-000016");
		System.out.println(article);
	}

}
