package com.KoreaIT.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.KoreaIT.demo.vo.Article;

@Component // DB 연결을 하지 않았을 경우 DAO에서 @Componenet 어노테이션 사용 (관리만 하는 상태)
public class ArticleDao {
	private int lastArticleId;
	List<Article> articles;

	public ArticleDao() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
		makeTestData();
	}

	// 테스트 게시물 생성 메서드
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			writeArticle("제목" + i, "내용" + i);
		}
	}

	// 게시물 추가 메서드
	public Article writeArticle(String title, String body) {
		lastArticleId++;

		Article article = new Article(lastArticleId, title, body);

		articles.add(article);

		return article;
	}

	// id로 게시물 가져오는 메서드
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	// 삭제 메서드
	public void deleteArticle(Article article) {
		articles.remove(article);
	}

	// 수정 메서드
	public void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}

	public List<Article> showList() {
		return articles;
	}
}
