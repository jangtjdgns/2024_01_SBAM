package com.KoreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.ArticleService;
import com.KoreaIT.demo.util.Util;
import com.KoreaIT.demo.vo.Article;
import com.KoreaIT.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrArticleController {

	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	// write, 작성
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요.");
		}

		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요.");
		}
		
		articleService.writeArticle(rq.getLoginedMemberId(), title, body);

		int id = articleService.getLastInsertId();

		return Util.jsReplace(Util.f("%d번 게시물이 작성되었습니다.", id), Util.f("detail?id=%s", id));
	}

	// list, 목록
	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
	
		List<Article> articles = articleService.getArticles();
	
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}

	// detail, 상세보기
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.forPrintArticle(id);
		
		model.addAttribute("article", article);
		
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		return "usr/article/detail";
	}

	// modify, 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		// 권한 체크
		if(article.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 대한 권한이 없습니다.", id));
		}

		articleService.modifyArticle(id, title, body);

		return Util.jsReplace(Util.f("%d번 게시물이 수정되었습니다.", id), Util.f("detail?id=%s", id));
	}

	// delete, 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		// 권한 체크
		if(article.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 대한 권한이 없습니다.", id));
		}

		articleService.deleteArticleById(id);
		
		return Util.jsReplace(Util.f("%d번 게시물이 삭제되었습니다.", id), "list");
	}
}
