package com.KoreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.ArticleService;
import com.KoreaIT.demo.util.Util;
import com.KoreaIT.demo.vo.Article;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {

	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	// write, 작성
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpSession session, String title, String body) {

		if(session.getAttribute("loginedMemberId") == null) {
			return Util.jsReplace("로그인 후 이용해주세요.", "/usr/member/login");
		}
		
		if (Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요.");
		}

		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요.");
		}
		
		articleService.writeArticle((int) session.getAttribute("loginedMemberId"), title, body);

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
	public String showDetail(HttpSession session, Model model, int id) {

		Article article = articleService.forPrintArticle(id);

		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		model.addAttribute("article", article);
		
		model.addAttribute("loginedMemberId", loginedMemberId);
		
		return "usr/article/detail";
	}

	// modify, 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpSession session, int id, String title, String body) {

		if(session.getAttribute("loginedMemberId") == null) {
			return Util.jsReplace("로그인 후 이용해주세요.", "/usr/member/login");
		}
		
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		// 권한 체크
		if(article.getMemberId() != (int) session.getAttribute("loginedMemberId")) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 대한 권한이 없습니다.", id));
		}

		articleService.modifyArticle(id, title, body);

		return Util.jsReplace(Util.f("%d번 게시물이 수정되었습니다.", id), Util.f("detail?id=%s", id));
	}

	// delete, 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpSession session, int id) {

		if(session.getAttribute("loginedMemberId") == null) {
			return Util.jsReplace("로그인 후 이용해주세요.", "/usr/member/login");
		}
		
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		// 권한 체크
		if(article.getMemberId() != (int) session.getAttribute("loginedMemberId")) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 대한 권한이 없습니다.", id));
		}

		articleService.deleteArticleById(id);
		
		return Util.jsReplace(Util.f("%d번 게시물이 삭제되었습니다.", id), "list");
	}
}
