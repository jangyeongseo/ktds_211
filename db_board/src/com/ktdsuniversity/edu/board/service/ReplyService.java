package com.ktdsuniversity.edu.board.service;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.board.dao.ReplyDao;
import com.ktdsuniversity.edu.board.dao.query.ReplyQuery;
import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.db.helper.SQLType;
import com.ktdsuniversity.edu.board.vo.ReplyVO;

public class ReplyService {
	private DataAccessHelper dah;
	private ReplyDao replyDao;

	public ReplyService(DataAccessHelper dah) {
		this.dah = dah;
		this.replyDao = new ReplyDao(dah);
	}

	// List<> 댓글 목록 조회(게시글 아이디)
	public List<ReplyVO> selectAllList(String boardId) {
		List<ReplyVO> result = this.replyDao.selectAllList(boardId);
		return result;
	}

	// ReplyVO 댓글 조회(댓글 아이디)
	public ReplyVO seletIdContent(String reply) {
		ReplyVO replyVo = this.replyDao.seletIdContent(reply);
		return replyVo;
	}

	// List<> 대댓글 조회(댓글 아이디)
	// 대댓글 조회(대댓글 포함) - 계층 조회 필요
	public List<ReplyVO> selectReplyId(String replyId) {
		List<ReplyVO> result = this.replyDao.selectReplyId(replyId);
		return result;
	}

	// void 댓글 등록
	public void contentInsert(ReplyVO reply) {
		try {
			this.replyDao.contentInsert(reply);
			this.dah.commit();
		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}

	// 댓글 수정
	public void contentUpdate(ReplyVO reply) {
		try {
			this.replyDao.contentUpdate(reply);
			this.dah.commit();
		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}

	// 댓글 삭제 - 일반 댓글 삭제
	public void deleteId(String reply) {
		try {
			this.replyDao.deleteId(reply);
			this.dah.commit();

		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}

	// 댓글 삭제 - 상위 top_id와 그냥 id 까지 그러니까 대댓글까지 삭제
	public void deleteHierarchy(String reply) {
		try {
			this.replyDao.deleteHierarchy(reply);
			this.dah.commit();

		} catch (RuntimeException re) {
			this.dah.rollback();
		}
	}

}
