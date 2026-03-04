package com.ktdsuniversity.edu.board.dao;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.board.dao.query.ReplyQuery;
import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.db.helper.SQLType;
import com.ktdsuniversity.edu.board.vo.ReplyVO;

public class ReplyDao {

	private DataAccessHelper dah;

	public ReplyDao(DataAccessHelper dah) {
		this.dah = dah;
	}

	// List<> 댓글 목록 조회(게시글 아이디)
	// 게시글에 등록된 모든 댓글 조회(대댓글 포함) - 계층 조회
	public List<ReplyVO> selectAllList(String boardId) {
		List<ReplyVO> result = new ArrayList<>();
		this.dah.preparedStatement(ReplyQuery.selectAllList(), (pstmt) -> {
			pstmt.setString(1, boardId);
		});

		this.dah.executeQuery(SQLType.SELECT, rs -> {
			ReplyVO reply = new ReplyVO();
			reply.setId(rs.getString("ID"));
			reply.setBoardId(rs.getString("BOARD_ID"));
			reply.setTopId(rs.getString("TOP_ID"));
			reply.setContent(rs.getString("CONTENT"));
			reply.setWriteDate(rs.getString("WRITE_DATE"));
			result.add(reply);
		});

		return result;
	}

	// ReplyVO 댓글 조회(댓글 아이디)
	// 댓글 조회 - 댓글 내용만 조회
	public ReplyVO seletIdContent(String reply) {
		ReplyVO replyVo = new ReplyVO();
		this.dah.preparedStatement(ReplyQuery.selectReplyById(), (pstmt) -> {
			pstmt.setString(1, reply);
		});
		this.dah.executeQuery(SQLType.SELECT, rs -> {
			replyVo.setId(rs.getString("ID"));
			replyVo.setContent(rs.getString("CONTENT"));
			replyVo.setTopId(rs.getString("TOP_ID"));
			replyVo.setContent(rs.getString("CONTENT"));
			replyVo.setWriteDate(rs.getString("WRITE_DATE"));
		});

		return replyVo;
	}

	// List<> 대댓글 조회(댓글 아이디)
	// 대댓글 조회(대댓글 포함) - 계층 조회 필요
	public List<ReplyVO> selectReplyId(String replyId) {
		List<ReplyVO> result = new ArrayList<>();
		this.dah.preparedStatement(ReplyQuery.selectReplyId(), (pstmt) -> {
			pstmt.setString(1, replyId);
		});
		this.dah.executeQuery(SQLType.SELECT, rs -> {
			ReplyVO reply = new ReplyVO();
			reply.setId(rs.getString("ID"));
			reply.setBoardId(rs.getString("BOARD_ID"));
			reply.setTopId(rs.getString("TOP_ID"));
			reply.setContent(rs.getString("CONTENT"));
			reply.setWriteDate(rs.getString("WRITE_DATE"));
			result.add(reply);
		});

		return result;
	}

	// void 댓글 등록
	public void contentInsert(ReplyVO reply) {
		this.dah.preparedStatement(ReplyQuery.contentInsert(), (pstmt) -> {
			pstmt.setString(1, reply.getBoardId()); // 게시글 아이디
			pstmt.setString(2, reply.getTopId()); // NULL이면 일반댓글
			pstmt.setString(3, reply.getContent()); // 댓글 내용
		});

		this.dah.executeQuery(SQLType.INSERT, null);
	}

	// 댓글 수정
	public void contentUpdate(ReplyVO reply) {
		this.dah.preparedStatement(ReplyQuery.contentUpdate(), (pstmt) -> {
			pstmt.setString(2, reply.getContent());
			pstmt.setString(3, reply.getId());
		});

		this.dah.executeQuery(SQLType.UPDATE, null);
	}

	// 댓글 삭제 - 일반 댓글 삭제
	public void deleteId(String reply) {
		this.dah.preparedStatement(ReplyQuery.deleteId(), (pstmt) -> {
			pstmt.setString(1, reply);
		});
		this.dah.executeQuery(SQLType.DELETE, null);
	}

	// 댓글 삭제 - 상위 top_id와 그냥 id 까지 그러니까 대댓글까지 삭제
	public void deleteHierarchy(String reply) {
		this.dah.preparedStatement(ReplyQuery.deleteHierarchy(), (pstmt) -> {
			pstmt.setString(1, reply);
		});
		this.dah.executeQuery(SQLType.DELETE, null);

	}

}
