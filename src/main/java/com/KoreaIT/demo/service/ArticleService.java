package com.KoreaIT.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.KoreaIT.demo.dao.ArticleDao;
import com.KoreaIT.demo.vo.Article;

@Service
public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void writeArticle(int loginedMemberId, String title, String body, int boardId) {
		articleDao.writeArticle(loginedMemberId, title, body, boardId);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public void deleteArticleById(int id) {
		articleDao.deleteArticleById(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
	}

	public List<Article> getArticles(int limitFrom, int itemsInAPage, String searchKeyword, int boardId, int searchType) {
		return articleDao.getArticles(limitFrom, itemsInAPage, searchKeyword, boardId, searchType);
	}

	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}
	
	public Article forPrintArticle(int id) {
		return articleDao.forPrintArticle(id);
	}

	public int getTotalCount(String searchKeyword, int boardId, int searchType) {
		return articleDao.getTotalCount(searchKeyword, boardId, searchType);
	}

	public void increaseHitCnt(int id) {
		articleDao.increaseHitCnt(id);
	}
}
