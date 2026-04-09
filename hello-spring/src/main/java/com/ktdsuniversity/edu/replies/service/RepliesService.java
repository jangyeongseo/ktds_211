package com.ktdsuniversity.edu.replies.service;

import com.ktdsuniversity.edu.replies.vo.RepliesVO;
import com.ktdsuniversity.edu.replies.vo.request.CreateVO;

public interface RepliesService {

	RepliesVO createNewReply(CreateVO createVO);

}
