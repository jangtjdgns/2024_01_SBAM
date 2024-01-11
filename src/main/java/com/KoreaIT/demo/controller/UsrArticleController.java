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
	
	public UsrArticleController(){
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
	}
	
	// 액션 메서드
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		
		this.lastArticleId++;
		
		Article article = new Article(lastArticleId, title, body);
		
		this.articles.add(article);
		
		return article;
	}
	
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList () {
		
		return articles;
	}
	
	// 서비스 메서드
	private void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			lastArticleId++;
			
			String title = "제목" + i;
			String body = "내용" + i;
			
			Article article = new Article(lastArticleId, title, body);
			
			articles.add(article);
		}
	}
}
