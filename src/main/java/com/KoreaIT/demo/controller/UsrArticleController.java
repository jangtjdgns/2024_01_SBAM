package com.KoreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.ArticleService;
import com.KoreaIT.demo.vo.Article;

@Controller
public class UsrArticleController {

	// 의존성 주입
	private ArticleService articleService;
	
	// 스프링이 알아서 추가해줌, @Autowired 어노테이션이 생략됨
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	// write, 작성
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {

		articleService.writeArticle(title, body);
		
		// LAST_INSERT_ID(), 마지막 id(pk) 를 가져옴
		int id = articleService.getLastInsertId();
		
		Article article = articleService.getArticleById(id);

		return article;
	}

	// list, 목록
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {

		return articleService.showList();
	}

	// delete, 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}
		
		articleService.deleteArticleById(id);

		return id + "번 게시물이 삭제되었습니다.";
	}

	// modify, 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		} 

		articleService.modifyArticle(id, title, body);

		return id + "번 게시물이 수정되었습니다.";
	}

	// detail, 상세보기
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Object showDetail(int id) {

		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다.";
		}
		
		return article;
	}
}
