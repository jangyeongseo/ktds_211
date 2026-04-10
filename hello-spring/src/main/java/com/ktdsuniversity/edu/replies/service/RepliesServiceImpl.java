package com.ktdsuniversity.edu.replies.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.files.helpers.MultipartFileHandler;
import com.ktdsuniversity.edu.replies.dao.RepliesDao;
import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;
import com.ktdsuniversity.edu.replies.vo.response.DeleteResultVO;
import com.ktdsuniversity.edu.replies.vo.response.RecommendResultVO;
import com.ktdsuniversity.edu.replies.vo.response.SearchResultVO;

@Service
public class RepliesServiceImpl implements RepliesService {
	private static final Logger logger = LoggerFactory.getLogger(RepliesServiceImpl.class);

	@Autowired
	private RepliesDao repliesDao;

	@Autowired
	private MultipartFileHandler multipartFileHandler;

	@Transactional
	@Override
	public RepliesVO createNewReply(CreateVO createVO) {

		String fileGroupId = this.multipartFileHandler.upload(createVO.getAttachFile());
		createVO.setFileGroupId(fileGroupId);

		int insertCount = this.repliesDao.insertNewReply(createVO);
		if (insertCount == 1) {
			RepliesVO insertResult = this.repliesDao.selectReplyByReplyId(createVO.getId());
			return insertResult;
		}

		return null;
	}

	@Transactional
	@Override
	public SearchResultVO findRepliseByArticleId(String articleId) {

		SearchResultVO searchResultVO = new SearchResultVO();

		int count = this.repliesDao.selectRepliesCountByArticleId(articleId);
		searchResultVO.setCount(count);

		if (count > 0) {
			List<RepliesVO> searchList = this.repliesDao.selectRepliesByArticleId(articleId);
			searchResultVO.setResult(searchList);
		}

		return searchResultVO;
	}

	// 추천
	@Transactional
	@Override
	public RepliesVO findReplyByReplyId(String replyId) {
		RepliesVO replies = this.repliesDao.selectReplyByReplyId(replyId);
		
		return replies;
	}

	// 수정
	@Transactional
	@Override
	public RecommendResultVO updateRecommendByReplyId(String replyId) {
		int update = this.repliesDao.updateRecommendByReplyId(replyId);
		if(update == 1) {
			RepliesVO replies = this.repliesDao.selectReplyByReplyId(replyId);
			
			RecommendResultVO result = new RecommendResultVO();
			result.setReplyId(replyId);
			result.setRecommendCount(replies.getRecommendCnt());
			
			return result;
		}
		
		return null;
	}

	// 삭제
	@Transactional
	@Override
	public DeleteResultVO deleteReplyByReplyId(String replyId) {
		int delete = this.repliesDao.deleteRelpyByReplyId(replyId);
		if(delete == 1) {
			DeleteResultVO result = new DeleteResultVO();
			result.setReplyId(replyId);
			
			return result;
		}
		
		return null;
	}

}
