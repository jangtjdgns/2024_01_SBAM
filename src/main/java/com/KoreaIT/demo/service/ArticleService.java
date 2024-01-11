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

	// 게시물 추가 메서드
	public Article writeArticle(String title, String body) {
		return articleDao.writeArticle(title, body);
	}

	// id로 게시물 가져오는 메서드
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	// 삭제 메서드
	public void deleteArticle(Article article) {
		articleDao.deleteArticle(article);
	}

	// 수정 메서드
	public void modifyArticle(Article article, String title, String body) {
		articleDao.modifyArticle(article, title, body);
	}

	public List<Article> showList() {
		return articleDao.showList();
	}
}
