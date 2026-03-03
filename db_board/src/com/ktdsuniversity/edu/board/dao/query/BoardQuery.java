package com.ktdsuniversity.edu.board.dao.query;

public class BoardQuery {

	// 조회한 게시글 내용 가져오기
	public static String makeSelectOneQuery() {
		StringBuffer query = new StringBuffer();

		query.append(" SELECT                                                                                ");
		query.append(" ID,                                                                                   ");
		query.append(" TITLE,                                                                                ");
		query.append(" CONTENT,                                                                              ");
		query.append(" VIEW_COUNT,                                                                           ");
		query.append(" TO_CHAR(WRITE_DATE, 'YYYY-MM-DD HH24:MI:SS') AS WRITE_DATE,                           ");
		query.append(" TO_CHAR(LATEST_MODIFY_DATE, 'YYYY-MM-DD HH24:MI:SS') AS LATEST_MODIFY_DATE            ");
		query.append(" FROM                                                                                  ");
		query.append(" 	BOARD                                                                                ");
		query.append(" WHERE                                                                                 ");
		query.append(" ID = ?                                                             ");

		return query.toString();
	}

	// 조회수 1증가
	public static String makeUpdateViewCount() {
		StringBuffer query = new StringBuffer();

		query.append(" UPDATE BOARD                         ");
		query.append(" SET VIEW_COUNT = VIEW_COUNT + 1      ");
		query.append(" WHERE ID = ?      ");

		return query.toString();
	}

	// 작성 내용 전달
	public static String makeInsertQuery() {
		// INSERT QUERY 작성
		StringBuffer query = new StringBuffer();
		query.append(" INSERT ");
		query.append(" INTO ");
		query.append(" BOARD(ID, TITLE, CONTENT, WRITE_DATE) ");
		query.append(
				" VALUES('BO-' || TO_CHAR(SYSDATE, 'YYYYMMDD-') || LPAD(SEQ_BOARD_PK.NEXTVAL, 6, 0), ?, ?, SYSDATE) ");

		return query.toString();

	}

	// 내용 변경
	public static String makeUpdateQuery() {
		StringBuffer query = new StringBuffer();
		query.append(" UPDATE                               ");
		query.append(" BOARD                                ");
		query.append(" SET                                  ");
		query.append("  TITLE = ?,                          ");
		query.append("  CONTENT = ?,                        ");
		query.append("  LATEST_MODIFY_DATE = SYSDATE        ");
		query.append(" WHERE                                ");
		query.append("  ID = ?            ");

		return query.toString();
	}

	// 내용 삭제
	public static String makeDeleteQuery() {
		StringBuffer query = new StringBuffer();
		query.append(" DELETE FROM BOARD ");
		query.append(" WHERE ID= ?      ");

		return query.toString();
	}
}
