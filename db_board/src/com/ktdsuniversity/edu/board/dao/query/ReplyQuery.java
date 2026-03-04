package com.ktdsuniversity.edu.board.dao.query;

public class ReplyQuery {

	// 댓글 등록
	public static String contentInsert() {
		StringBuffer query = new StringBuffer();

		query.append(" INSERT INTO REPLY (                 ");
		query.append(" ID, BOARD_ID,TOP_ID, CONTENT, WRITE_DATE) ");
		query.append(" VALUES (                           ");
		query.append(" 'RP-' || TO_CHAR(SYSDATE, 'YYYYMMDD-') || ");
		query.append(" LPAD(SEQ_REPLY_PK.NEXTVAL, 6, 0),  ");
		query.append(" ?, ?, ?, SYSDATE)");

		return query.toString();
	}

	// 댓글 수정
	public static String contentUpdate() {
		StringBuffer query = new StringBuffer();

		query.append(" UPDATE              ");
		query.append(" REPLY               ");
		query.append(" SET                 ");
		query.append(" 	CONTENT = ?       ");
		query.append(" WHERE               ");
		query.append(" 	ID = ?            ");

		return query.toString();

	}

	// 댓글 삭제
	public static String deleteId() {
		StringBuffer query = new StringBuffer();

		query.append(" DELETE FROM REPLY ");
		query.append(" WHERE               ");
		query.append(" 	ID = ?            ");

		return query.toString();
	}

	// 댓글 삭제2 - 계층을 이용하여 top_id 포함 삭제
	public static String deleteHierarchy() {
		StringBuffer query = new StringBuffer();

		query.append(" DELETE FROM REPLY ");
		query.append(" WHERE ID IN ( ");
		query.append("     SELECT ID FROM REPLY ");
		query.append("     START WITH ID = ? ");
		query.append("     CONNECT BY PRIOR ID = TOP_ID ");
		query.append(" ) ");

		return query.toString();
	}

	// 게시글에 등록된 모든 댓글 조회(대댓글 포함) - 계층 조회
	public static String selectAllList() {
		StringBuffer query = new StringBuffer();

		query.append(" SELECT                                                             ");
		query.append(" ID,                                                                ");
		query.append(" BOARD_ID,                                                          ");
		query.append(" TOP_ID,                                                            ");
		query.append(" CONTENT,                                                           ");
		query.append(" TO_CHAR(WRITE_DATE, 'YYYY-MM-DD HH24:MI:SS') AS WRITE_DATE,        ");
		query.append(" LEVEL                                                              ");
		query.append(" FROM                                                               ");
		query.append(" 	REPLY                                                             ");
		query.append(" WHERE                                                              ");
		query.append(" 	BOARD_ID = ?                                   ");
		query.append(" START WITH                                                         ");
		query.append(" 	TOP_ID IS NULL                                                    ");
		query.append(" CONNECT BY                                                         ");
		query.append(" 	PRIOR ID = TOP_ID                                                 ");
		query.append(" ORDER SIBLINGS BY WRITE_DATE                                       ");

		return query.toString();
	}

	// 댓글 조회
	public static String selectReplyById() {
		StringBuffer query = new StringBuffer();

		query.append(" SELECT ID, BOARD_ID,TOP_ID, CONTENT, "
				+ "TO_CHAR(WRITE_DATE, 'YYYY-MM-DD HH24:MI:SS') AS WRITE_DATE ");
		query.append(" FROM         ");
		query.append(" 	REPLY       ");
		query.append(" WHERE        ");
		query.append(" 	ID = ?      ");

		return query.toString();
	}

	// 대댓글 조회(대댓글 포함) - 계층 조회 필요
	public static String selectReplyId() {
		StringBuffer query = new StringBuffer();

		query.append(" SELECT                                                           ");
		query.append(" ID,                                                              ");
		query.append(" BOARD_ID,                                                        ");
		query.append(" TOP_ID,                                                          ");
		query.append(" CONTENT,                                                         ");
		query.append(" TO_CHAR(WRITE_DATE, 'YYYY-MM-DD HH24:MI:SS') AS WRITE_DATE,      ");
		query.append(" LEVEL                                                            ");
		query.append(" FROM                                                             ");
		query.append(" 	REPLY                                                           ");
		query.append(" START WITH                                                       ");
		query.append(" 	ID = ?                                       ");
		query.append(" CONNECT BY                                                       ");
		query.append(" 	PRIOR ID = TOP_ID                                               ");
		query.append(" ORDER SIBLINGS BY WRITE_DATE                                     ");

		return query.toString();
	}

}
