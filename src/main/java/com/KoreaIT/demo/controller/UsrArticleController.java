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

	// 액션 메서드, write
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {

		Article article = writeArticle(title, body);

		return article;
	}

	// 액션 메서드, list
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {

		return articles;
	}

	// 액션 메서드, delete
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(id - 1);
				return id + "번 게시물이 삭제되었습니다.";
			}
		}

		return id + "번 게시물은 존재하지 않습니다.";
	}

	// 액션 메서드, 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		for (Article article : articles) {
			if (article.getId() == id) {
				article.setTitle(title);
				article.setBody(body);
				
				return id + "번 게시물이 수정되었습니다.";
			}
		}

		return id + "번 게시물은 존재하지 않습니다.";
	}
	
	// 액션 메서드, 상세보기
		@RequestMapping("/usr/article/detail")
		@ResponseBody
		public String showDetail(int id) {

			for (Article article : articles) {
				if (article.getId() == id) {
					return "번호: " + id + " | 제목: " + article.getTitle() + " | 내용: " + article.getBody();
				}
			}

			return id + "번 게시물은 존재하지 않습니다.";
		}

	// 서비스 메서드
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			writeArticle("제목" + i, "내용" + i);
		}
	}

	// 서비스 메서드
	private Article writeArticle(String title, String body) {
		lastArticleId++;

		Article article = new Article(lastArticleId, title, body);

		articles.add(article);

		return article;
	}
}
