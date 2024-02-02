package com.KoreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.RecommendService;
import com.KoreaIT.demo.vo.RecommendPoint;
import com.KoreaIT.demo.vo.ResultData;
import com.KoreaIT.demo.vo.Rq;

@Controller
public class UsrRecommendPointController {
	private RecommendService recommendSerivce;
	private Rq rq;
	
	public UsrRecommendPointController(RecommendService recommendSerivce, Rq rq) {
		this.recommendSerivce = recommendSerivce;
		this.rq = rq;
	}
	
	// 좋아요 조회
	@RequestMapping("/usr/recommendPoint/getRecommendPoint")
	@ResponseBody
	public ResultData<RecommendPoint> getRecommendPoint(int relId, String relTypeCode) {
		RecommendPoint recommendPoint = recommendSerivce.getRecommendPoint(rq.getLoginedMemberId(), relId, relTypeCode);
		
		if (recommendPoint == null) {
			return ResultData.from("F-1", "좋아요 기록 없음");
		}
		
		return ResultData.from("S-1", "좋아요 조회 성공", recommendPoint);
	}
	
	// 좋아요 추가, 삭제
	@RequestMapping("/usr/recommendPoint/doRecommendPoint")
	@ResponseBody
	public String insertPoint(int relId, String relTypeCode, boolean recommendBtn) {
		
		if (recommendBtn) {
			recommendSerivce.deletePoint(rq.getLoginedMemberId(), relId, relTypeCode);
			return "좋아요 취소 성공";
		}
		
		
		recommendSerivce.insertPoint(rq.getLoginedMemberId(), relId, relTypeCode);
		return "좋아요 성공";
	}
}