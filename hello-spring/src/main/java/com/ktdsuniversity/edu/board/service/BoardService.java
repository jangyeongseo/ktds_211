package com.ktdsuniversity.edu.board.service;

import com.ktdsuniversity.edu.board.enums.ReadType;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.request.SearchListVO;
import com.ktdsuniversity.edu.board.vo.request.UpdateVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;
import com.ktdsuniversity.edu.board.vo.response.SearchResultVO;

/**
 * 상황별 - 회원의 등급이 다르다 - 일반 사용자, 관리자, 슈퍼 관리자, 운영자 / 애플리케이션의 버전이 다르다) - 애플리케이션의 버전이
 * 다르다 => 0.0.1, 1.0.2
 */
public interface BoardService {
	SearchResultVO findAllBoard(SearchListVO searchListVO);

	boolean createNewBoard(WriteVO writeVO);

	BoardVO findBoardByArticleId(String articleId, ReadType readType);

	boolean deleteBoardByArticleId(String id);

	boolean updateBoardByArticleId(UpdateVO updateVO);
}
