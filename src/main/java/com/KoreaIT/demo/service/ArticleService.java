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

	public void writeArticle(int loginedMemberId, String title, String body) {
		articleDao.writeArticle(loginedMemberId, title, body);
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

	public List<Article> getArticles(int limitFrom, int itemsInAPage, String searchKeyword) {
		return articleDao.getArticles(limitFrom, itemsInAPage, searchKeyword);
	}

	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}
	
	public Article forPrintArticle(int id) {
		return articleDao.forPrintArticle(id);
	}

	public int getTotalCount(String searchKeyword) {
		return articleDao.getTotalCount(searchKeyword);
	}
}
