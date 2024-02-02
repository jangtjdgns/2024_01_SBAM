package com.KoreaIT.demo.controller;

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
	
	// 해당 댓글 정보 가져오기
	@RequestMapping("/usr/reply/getReplyContent")
 	@ResponseBody
 	public ResultData<Reply> getReplyContent(int id) {
		
		Reply reply = replyService.getReplyById(id);
		
		return ResultData.from("S-1", "댓글 조회 성공", reply);
	
	}
	 
	
	// 댓글 작성
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(int relId, String relTypeCode, String body, int boardId) {
		
		replyService.doWrite(rq.getLoginedMemberId(), relId, relTypeCode, body);
		
		return Util.f("<script>alert('댓글이 작성되었습니다.'); location.replace('../article/detail?id=%d&boardId=%d')</script>", relId, boardId);
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
		
		if(Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요.");
		}
		
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