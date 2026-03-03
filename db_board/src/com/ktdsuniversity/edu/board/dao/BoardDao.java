package com.ktdsuniversity.edu.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ktdsuniversity.edu.board.dao.query.BoardQuery;
import com.ktdsuniversity.edu.board.db.helper.DataAccessHelper;
import com.ktdsuniversity.edu.board.db.helper.SQLType;
import com.ktdsuniversity.edu.board.vo.BoardVO;

/*
 * Dao : Data Access Odject
 *  Java에서 DB로 데이터 생성, 수정,삭제, 조회하기 위한 클래스
 */
public class BoardDao {
	// 조회수 및 조회한 게시글 내용
	public BoardVO readArticle(String articleId) {
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");

		try {
			// UPDATE => 조회수를 1증가
			dah.preparedStatement(BoardQuery.makeUpdateViewCount(), (pstmt) -> {
				pstmt.setString(1, articleId);
			});
			dah.executeQuery(SQLType.UPDATE, null);

			// SELECT => 게시글의 내용을 조회
			BoardVO result = new BoardVO();
			dah.preparedStatement(BoardQuery.makeSelectOneQuery(), (pstmt) -> {
				pstmt.setString(1, articleId);
			});
			dah.executeQuery(SQLType.SELECT, rs -> {
				result.setId(rs.getString("ID"));
				result.setTitle(rs.getString("TITLE"));
				result.setContent(rs.getString("CONTENT"));
				result.setViewCount(rs.getInt("VIEW_COUNT"));
				result.setWriteDate(rs.getString("WRITE_DATE"));
				result.setLatestModifyDate(rs.getString("LATEST_MODIFY_DATE"));
			});

			dah.commit();
			return result;

		} catch (RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());

		} finally {
			dah.close();
		}

		return null;
	}

	// 삭제
	public void deleteArticle(String articleId) {
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");

		try {
			dah.preparedStatement(BoardQuery.makeDeleteQuery(), (pstmt) -> {
				pstmt.setString(1, articleId);
			});
			dah.executeQuery(SQLType.DELETE, null); // SQLType이 실행하길 원한다.
			dah.commit();

		} catch (RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());

		} finally {
			dah.close();
		}
	}

	// 내용 수정
	public void modifyArticle(BoardVO modifyArticle) {
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");

		try {
			dah.preparedStatement(BoardQuery.makeUpdateQuery(), (pstmt) -> {
				pstmt.setString(1, modifyArticle.getTitle());
				pstmt.setString(2, modifyArticle.getContent());
				pstmt.setString(3, modifyArticle.getId());
			});
			dah.executeQuery(SQLType.UPDATE, null); // SQLType이 실행하길 원한다.
			dah.commit();

		} catch (RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());

		} finally {
			dah.close();
		}
	}

	// 내용 디비에 전달
	public void createNewArticle2(BoardVO newArticle) {
		DataAccessHelper dah = new DataAccessHelper("localhost", 1521, "XE", "BOARD", "BOARD");

		try {
			dah.preparedStatement(BoardQuery.makeInsertQuery(), (pstmt) -> {
				pstmt.setString(1, newArticle.getTitle());
				pstmt.setString(2, newArticle.getContent());
			});
			dah.executeQuery(SQLType.INSERT, null); // SQLType이 실행하길 원한다.
			dah.commit();

		} catch (RuntimeException re) {
			dah.rollback();
			System.out.println(re.getMessage());

		} finally {
			dah.close();
		}

	}

	public int createNewArticle(BoardVO newArticle) {
		// 1. ojdbc11.jar 파일이 프로젝트에 존재하는지 확인.
		try {
			// oracle.jdbc.driver.OracleDriver 클래스를 불러온다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// oracle.jdbc.driver.OracleDriver를 찾지 못했을 때 실행되는 예외 처리 블럭.
			System.out.println("오라클 데이터베이스에 접속하기 위한 라이브러리가 없습니다.");
			return 0;
		}

		// 2. oracleDB에 접속
		Connection connection = null; // 자바 ↔ DB 사이의 통로(파이프)
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "BOARD", "BOARD");
			// "jdbc:oracle:thin:@localhost:1521:XE" 이 패턴은 반드시 외워야함.
			// connection 을 통해서 DB와 연동

			// MANUL COMMIT 으로 변경
			// 사본에다가 변경 한것을 연동 시켜주겠다.
			connection.setAutoCommit(false); // 자동 커밋 끄기

		} catch (SQLException e) {
			// SQLException : 연결을 지정된 시간안에 기다렸는데 안될때 / 위에 DB연동이 틀렸다면
			System.out.println("데이터베이스 연결을 할 수 없습니다.");
			System.out.println("사유 : " + e.getMessage());
			return 0;
		}

		// 3. INSERT QUERY 작성
		StringBuffer query = new StringBuffer();
		query.append(" INSERT ");
		query.append(" INTO ");
		query.append(" BOARD(ID, TITLE, CONTENT, WRITE_DATE) ");
		query.append(
				" VALUES('BO-' || TO_CHAR(SYSDATE, 'YYYYMMDD-') || LPAD(SEQ_BOARD_PK.NEXTVAL, 6, 0), ?, ?, SYSDATE) ");

		// 3-1. ?에 데이터 할당하기
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(query.toString());
			pstmt.setString(1, newArticle.getTitle());
			pstmt.setString(2, newArticle.getContent());

		} catch (SQLException e) {
			// connection 이 닫기 전에
			// pstmt 의 파이프가 만들어져있는 상태에서 예외가 발생했다면 닫는다.
			try {
				pstmt.close();
			} catch (SQLException e1) {
			}

			// 연결된 connection 을 닫는다.
			try {
				connection.close();
				pstmt.close();
			} catch (SQLException e1) {
			}

			// 데이터베이스와 연결이 끝어져있을 때
			// 작성된 쿼리의 내용에 잘목되었을때
			System.out.println("쿼리 내용에 문제가 있습니다.");
			System.out.println("사유 : " + e.getMessage());
			return 0;
		}

		// 4. INSERT QUERY 실행
		try {
			int insertCount = pstmt.executeUpdate();
			// Commit 수행.
			// 인설트가 잘 된다면 그때 커밋을 해라.
			connection.commit();
			return insertCount;

		} catch (SQLException e) {
			try {
				connection.rollback(); // 하나라도 실패하묜 취소
			} catch (SQLException e1) {
			}

			// 1. Insert 쿼리의 파라미터 할당이 잘못되었을 때 (예 > ?는 2개인데, 할당한 데이터는 1개일 때)
			// 2. PK가 중복되었을 때
			// 3. 컬럼의 타입과 insert 하는 값이 다를 때
			// 4. 컬럼이 허용하는 최대 길이보다 값의 길이가 더 클 때
			System.out.println("쿼리 실행을 실패했습니다.");
			System.out.println("사유 : " + e.getMessage());
			return 0;
		}

		finally {
			// 파이프 연결 끊는 순서
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}

}
