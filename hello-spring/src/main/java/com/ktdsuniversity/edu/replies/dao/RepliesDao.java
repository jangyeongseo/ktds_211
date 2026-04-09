package com.ktdsuniversity.edu.replies.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;

@Mapper
public interface RepliesDao {

	int insertNewReply(CreateVO createVO);

	RepliesVO selectReplyByReplyId();

}
