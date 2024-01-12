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

	public Article writeArticle(String title, String body) {
		return articleDao.writeArticle(title, body);
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

	public List<Article> showList() {
		return articleDao.showList();
	}
}
