package com.KoreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.ReplyService;
import com.KoreaIT.demo.util.Util;
import com.KoreaIT.demo.vo.Reply;
import com.KoreaIT.demo.vo.ResultData;
import com.KoreaIT.demo.vo.Rq;

@Controller
public class UsrReplyController {
	private ReplyService replyService;
	private Rq rq;
	
	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}
	
	// 댓글 조회
	@RequestMapping("/usr/reply/getReply")
	@ResponseBody
	public ResultData<List<Reply>> getReplies(int relId, String relTypeCode) {
		List<Reply> replies = replyService.getReplies(relId, relTypeCode);
		
		if(replies == null) {
			return ResultData.from("F-1", "댓글 기록 없음");
		}
		
		return ResultData.from("S-1", "댓글 조회 성공", replies);
	}
	
	// 댓글 작성
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(int relId, String relTypeCode, String body) {
		
		replyService.doWrite(rq.getLoginedMemberId(), relId, relTypeCode, body);

		return "댓글 작성 성공";
	}
	
	// 댓글 삭제
	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id, int boardId) {
		
		Reply reply = replyService.getReplyById(id);
		
		if(reply == null) {
			return Util.jsHistoryBack(Util.f("%d번 댓글은 존재하지 않습니다.", id));
		}
		
		if(rq.getLoginedMemberId() != reply.getMemberId()) {
			return Util.jsHistoryBack(Util.f("%번 댓글에 대한 권한이 없습니다.", id));
		}
		 
		replyService.doDelete(id);

		return Util.jsReplace(Util.f("%d번 댓글이 삭제되었습니다.", id), Util.f("../article/detail?id=%d&boardId=%d", reply.getRelId(), boardId));
	}
	
	// 댓글 수정
	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, String body, int boardId) {
		
		Reply reply = replyService.getReplyById(id);
		
		if(reply == null) {
			return Util.jsHistoryBack("해당 댓글은 존재하지 않습니다.");
		}
		
		if(rq.getLoginedMemberId() != reply.getMemberId()) {
			return Util.jsHistoryBack("해당 댓글에 대한 권한이 없습니다.");
		}
		
		replyService.doModify(id, body);

		return Util.jsReplace("댓글이 수정되었습니다.", Util.f("../article/detail?id=%d&boardId=%d", reply.getRelId(), boardId));
	}
}