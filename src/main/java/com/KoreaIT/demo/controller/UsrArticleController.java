package com.KoreaIT.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.vo.Article;

@Controller
public class UsrArticleController {

	private int lastArticleId;
	List<Article> articles;

	public UsrArticleController() {
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
	private Article writeArticle(String title, String body) {
		lastArticleId++;

		Article article = new Article(lastArticleId, title, body);

		articles.add(article);

		return article;
	}

	// id로 게시물 가져오는 메서드
	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	// 삭제 메서드
	private void deleteArticle(Article article) {
		articles.remove(article);
	}
	
	// 수정 메서드
	private void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
	
	// write, 작성
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {

		Article article = writeArticle(title, body);

		return article;
	}

	// list, 목록
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {

		return articles;
	}

	// delete, 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}
		
		deleteArticle(article);

		return id + "번 게시물이 삭제되었습니다.";
	}

	// modify, 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}

		modifyArticle(article, title, body);

		return id + "번 게시물이 수정되었습니다.";
	}

	// detail, 상세보기
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Object showDetail(int id) {

		Article article = getArticleById(id);
		
		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}
		
		return article;
	}
}
