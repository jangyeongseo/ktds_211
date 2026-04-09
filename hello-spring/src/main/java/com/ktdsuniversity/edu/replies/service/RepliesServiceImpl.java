package com.ktdsuniversity.edu.replies.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.replies.dao.RepliesDao;
import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;

@Service
public class RepliesServiceImpl implements RepliesService {
	private static final Logger logger = LoggerFactory.getLogger(RepliesServiceImpl.class);

	@Autowired
	private RepliesDao repliesDao;

	@Transactional
	@Override
	public RepliesVO createNewReply(CreateVO createVO) {
		int insertCount = this.repliesDao.insertNewReply(createVO);
		if (insertCount == 1) {
			RepliesVO insertResult = this.repliesDao.selectReplyByReplyId();
			return insertResult;
		}

		return null;
	}

}
