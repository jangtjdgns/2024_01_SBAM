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
	
	public Reply getReplyById(int id) {
		return replyDao.getReplyById(id);
	}
	
	public List<Reply> getReplies(int relId, String relTypeCode) {
		return replyDao.getReplies(relId, relTypeCode);
	}

	public void doWrite(int memberId, int relId, String relTypeCode, String body) {
		replyDao.doWrite(memberId, relId, relTypeCode, body);
	}

	public void doDelete(int id) {
		replyDao.doDelete(id);
	}

	public void doModify(int id, String body) {
		replyDao.doModify(id, body);
	}

}
