package com.ktdsuniversity.edu.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.request.UpdateVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;

@Mapper
public interface BoardDao {
	List<BoardVO> selectBoardList();
	int selectBoardCount();
	int insertNewBoard(WriteVO writeVO);

	int updateViewCntIncreaseById(String articleId);
	BoardVO selectBoardById(String articleId);
	int deleteViewById();
	int deleteViewById(String id);
	int updateViewById(UpdateVO updateVO);

}
