package com.KoreaIT.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.KoreaIT.demo.dao.ReplyDao;
import com.KoreaIT.demo.vo.Reply;

@Service
public class ReplyService {
	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	public List<Reply> getReply(int relId, String relTypeCode) {
		return replyDao.getReply(relId, relTypeCode);
	}

	public void writeReply(int memberId, int relId, String relTypeCode, String body) {
		replyDao.writeReply(memberId, relId, relTypeCode, body);
	}
}
