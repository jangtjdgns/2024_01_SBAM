package com.KoreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.ReplyService;
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
	public ResultData<List<Reply>> getReply(int relId, String relTypeCode) {
		List<Reply> replies = replyService.getReply(relId, relTypeCode);
		
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
	public String doDelete(int id) {
		
		replyService.doDelete(id);

		return "댓글 삭제 성공";
	}
	
	// 댓글 수정
	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, String body) {
			
		replyService.doModify(id, body);

		return "댓글 수정 성공";
	}
}