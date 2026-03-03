package com.ktdsuniversity.edu.board.db.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface FetchRow {
	// 람다로 사용이 가능
	public void fetch(ResultSet rs) throws SQLException;

}
