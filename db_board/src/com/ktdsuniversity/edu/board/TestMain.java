package com.ktdsuniversity.edu.board;

import java.util.List;

import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.service.BoardService;
import com.ktdsuniversity.edu.board.service.ReplyService;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.ReplyVO;

public class TestMain {

	public static void main(String[] args) {
		long now = System.currentTimeMillis();

		// 게시글 작성(DB 게시글 생성)
		BoardVO newArticle = new BoardVO();
		newArticle.setTitle("새로운 게시글입니다.");
		newArticle.setContent("새로운 게시글 내용입니다.");

		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");

		BoardService boardService = new BoardService(dah);
		boardService.createNewArticle2(newArticle);
//		int insertCount = boardService.createNewArticle(newArticle);
//		System.out.println(insertCount + "개의 게시글이 생성되었습니다.");

		BoardVO modifyArticle = new BoardVO();
		modifyArticle.setId("BO-20260303-000004");
		modifyArticle.setTitle("제목 수정");
		modifyArticle.setContent("내용이 변경");
		boardService.modifyArticle(modifyArticle);

		boardService.deleteArticle("BO-20260303-000008");

		// 게시글 조회. (BO-20260303-000016)
		BoardVO article = boardService.readArticle("BO-20260303-000016");
		System.out.println(article);

		// 전체 조회
		List<BoardVO> result = boardService.readAllArticle();
		System.out.println(result);

		// 댓글
		ReplyVO reply = new ReplyVO();
		reply.setContent("게시글에 관한 댓글"); // 댓글 출력
		reply.setBoardId("BO-20260303-000006");
		reply.setTopId("RP-20260304-000005");

		// 댓글 입력
		ReplyService replyService = new ReplyService(dah);
		replyService.contentInsert(reply);

		// 댓글 수정
		ReplyVO replyUpdate = new ReplyVO();
		replyUpdate.setBoardId("BO-20260303-000023");
		replyUpdate.setContent("댓글 내용 수정 확인용");
		replyUpdate.setId("RP-20260304-000010");
		replyService.contentUpdate(replyUpdate);

		// 댓글 삭제
		// replyService.deleteId("RP-20260304-000001");
		replyService.deleteHierarchy("RP-20260304-000002");

		// 게시글에 등록된 모든 댓글 조회(대댓글 포함) - 계층 조회
		List<ReplyVO> result2 = replyService.selectAllList("BO-20260303-000023");
		System.out.println(result2);

		// 댓글 조회
		ReplyVO replyVo = replyService.seletIdContent("RP-20260304-000003");
		System.out.println(replyVo);

		// 대댓글 조회(대댓글 포함) - 계층 조회 필요
		List<ReplyVO> replyId = replyService.selectReplyId("RP-20260304-000010");
		System.out.println(replyId);

		dah.close();
		long end = System.currentTimeMillis();

		System.out.println(end - now);

	}

}
