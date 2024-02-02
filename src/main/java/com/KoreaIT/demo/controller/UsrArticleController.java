package com.KoreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.ArticleService;
import com.KoreaIT.demo.service.BoardService;
import com.KoreaIT.demo.service.ReplyService;
import com.KoreaIT.demo.util.Util;
import com.KoreaIT.demo.vo.Article;
import com.KoreaIT.demo.vo.Board;
import com.KoreaIT.demo.vo.Reply;
import com.KoreaIT.demo.vo.Rq;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsrArticleController {

	private ArticleService articleService;
	private BoardService boardService;
	private ReplyService replyService;
	private Rq rq;

	public UsrArticleController(ArticleService articleService, BoardService boardService, ReplyService replyService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.replyService = replyService;
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
	public String showList(Model model
			, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int itemsInAPage
			, @RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "0") int boardId
			, @RequestParam(defaultValue = "1") int searchType) {
		
		// 페이지 번호가 0이하일 때
		if (page <= 0){
			return rq.jsReturnOnView("페이지 번호가 올바르지 않습니다.");
		}
				
		Board board = boardService.getBoardById(boardId);
		
		// DB limit 시작 부분
		int limitFrom = (page - 1) * itemsInAPage;
		
		// 게시물의 전체 수
		int articlesCnt = articleService.getTotalCount(searchKeyword, boardId, searchType);
		
		// 전체 페이지 수
		int totalPageCnt = (int) Math.ceil((double) articlesCnt / itemsInAPage);
		
		// 페이징 버튼 시작
		int from = ((page - 1) / 10) * 10 + 1;
		
		// 페이징 버튼 끝
		int end = ((page - 1) / 10 + 1) * 10;
		end = end > totalPageCnt ? totalPageCnt : end;
		
		List<Article> articles = articleService.getArticles(limitFrom, itemsInAPage, searchKeyword, boardId, searchType);
		
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("page", page);
		model.addAttribute("from", from);
		model.addAttribute("end", end);
		model.addAttribute("itemsInAPage", itemsInAPage);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "usr/article/list";
	}

	
	// detail, 상세보기
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, HttpServletResponse res, Model model, int id, int boardId) {
		
		if (articleService.getArticleById(id) == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		// 쿠키
		Cookie oldCookie = null;							
		Cookie[] cookies = req.getCookies();				// 여러개의 쿠키를 사용할 수 있기에 배열로 저장-> 브라우저의 Cookies에 있는 쿠키정보들을 가져옴

		if (cookies != null) {								// 쿠키배열이 null이 아닐때
			for (Cookie cookie : cookies) {					// 반복
				if (cookie.getName().equals("hitCnt")) {	// hitCnt란 이름의 쿠키가 있는 경우
					oldCookie = cookie;						// oldCookie에 저장
				}
			}
		}
		
		if(oldCookie != null) {												// oldCookie가 null이 아닐 때 -> hitCnt란 쿠키가 있는 경우
			if(!oldCookie.getValue().contains("[" + id + "]")) {			// [id] 형태의 문자열이 포함되어 있지 않다면
				articleService.increaseHitCnt(id);							// 해당 게시물의 hitCnt 조회수 증가
				oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");	// 기존쿠키 + _[id] 형태의 문자열 추가 -> 다른 게시물들을 들어간 경우
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60 * 60);								// 만료시점, 초 / 24시간(60 * 60 * 24)
				res.addCookie(oldCookie);									// 쿠키 추가
			}
		} else {															// oldCookie가 null일 때
			articleService.increaseHitCnt(id);								// 해당 게시물의 hitCnt 조회수 증가
			Cookie newCookie = new Cookie("hitCnt", "[" + id + "]");		// 새로운 쿠키 생성, ("hitCnt", [id])
			newCookie.setPath("/");
			newCookie.setMaxAge(60 * 60);									// 만료시점, 초
			res.addCookie(newCookie);										// 쿠키 추가
		}
		
		
		Article article = articleService.forPrintArticle(id);
		
		Board board = boardService.getBoardById(boardId);
		
		List<Reply> replies = replyService.getReplies(id, "article");
		
		model.addAttribute("article", article);
		model.addAttribute("board", board);
		model.addAttribute("replies", replies);
		
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
