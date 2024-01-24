package com.KoreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.ArticleService;
import com.KoreaIT.demo.service.BoardService;
import com.KoreaIT.demo.util.Util;
import com.KoreaIT.demo.vo.Article;
import com.KoreaIT.demo.vo.Board;
import com.KoreaIT.demo.vo.Rq;

@Controller
public class UsrArticleController {

	private ArticleService articleService;
	private BoardService boardService;
	private Rq rq;

	public UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}

	@RequestMapping("/usr/article/write")
	public String write() {
		
		return "usr/article/write";
	}
	
	// write, 작성
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(String title, String body, int boardId) {
		
		if (Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요.");
		}

		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요.");
		}
		
		articleService.writeArticle(rq.getLoginedMemberId(), title, body, boardId);

		int id = articleService.getLastInsertId();

		return Util.jsReplace(Util.f("%d번 게시물이 작성되었습니다.", id), Util.f("detail?boardId=0&id=%s", id));
	}

	// list, 목록
	@RequestMapping("/usr/article/list")
	public String showList(Model model, Integer page, Integer itemsInAPage, String searchKeyword, Integer boardId) {
		
		// 현재 페이지가 0 이하 일 경우 1로 초기화
		if(page == null || page <= 0) {
			page = 1;
		}
		
		// 검색어
		if(searchKeyword == null) {
			searchKeyword = "";
		}
		searchKeyword = searchKeyword.trim();
		
		// boardId
		if (boardId == null) {
			boardId = 0;
		}
				
		Board board = boardService.getBoardById(boardId);
		
		// 한 페이지에 표시될 게시물의 수 itemsInAPage null
		if(itemsInAPage == null) {
			itemsInAPage = 10;
		}
		
		// DB limit 시작 부분
		int limitFrom = (page - 1) * itemsInAPage;
		
		// 게시물의 전체 수
		int articlesCnt = articleService.getTotalCount(searchKeyword, boardId);
		
		// 전체 페이지 수
		int totalPageCnt = (int) Math.ceil((double) articlesCnt / itemsInAPage);
		
		// 페이징 버튼 시작
		int from = ((page - 1) / 10) * 10 + 1;
		
		// 페이징 버튼 끝
		int end = ((page - 1) / 10 + 1) * 10;
		end = end > totalPageCnt ? totalPageCnt : end;
		
		List<Article> articles = articleService.getArticles(limitFrom, itemsInAPage, searchKeyword, boardId);
		
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("page", page);
		model.addAttribute("from", from);
		model.addAttribute("end", end);
		model.addAttribute("itemsInAPage", itemsInAPage);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "usr/article/list";
	}

	// detail, 상세보기
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id, int boardId) {
		
		Article article = articleService.forPrintArticle(id);
		
		if (article == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		Board board = boardService.getBoardById(boardId);
		
		
		
		model.addAttribute("article", article);
		model.addAttribute("board", board);
		
		return "usr/article/detail";
	}

	// modify, 수정 페이지
	@RequestMapping("/usr/article/modify")
	public String modify(Model model, int id) {
		
		Article article = articleService.forPrintArticle(id);

		if (article == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		if(article.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsReturnOnView(Util.f("%d번 게시물에 대한 권한이 없습니다.", id));
		}
		
		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}
	
	// doModify, 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		if(article.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack(Util.f("%d번 게시물에 대한 권한이 없습니다.", id));
		}

		articleService.modifyArticle(id, title, body);

		return Util.jsReplace(Util.f("%d번 게시물이 수정되었습니다.", id), Util.f("detail?boardId=0&id=%s", id));
	}

	// delete, 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
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
