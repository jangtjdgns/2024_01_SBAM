package com.KoreaIT.demo.service;

import org.springframework.stereotype.Service;

import com.KoreaIT.demo.dao.RecommendDao;
import com.KoreaIT.demo.vo.RecommendPoint;

@Service
public class RecommendService {
	private RecommendDao recommendDao;
	
	public RecommendService(RecommendDao recommendDao) {
		this.recommendDao = recommendDao;
	}

	public RecommendPoint getRecommendPoint(int memberId, int relId, String relTypeCode) {
		return recommendDao.getRecommendPoint(memberId, relId, relTypeCode);
	}
	
	public void insertPoint(int memberId, int relId, String relTypeCode) {
		recommendDao.insertPoint(memberId, relId, relTypeCode);
	}

	public void deletePoint(int memberId, int relId, String relTypeCode) {
		recommendDao.deletePoint(memberId, relId, relTypeCode);
	}
}
