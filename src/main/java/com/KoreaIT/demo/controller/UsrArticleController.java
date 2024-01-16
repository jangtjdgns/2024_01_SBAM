package com.KoreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.demo.service.ArticleService;
import com.KoreaIT.demo.util.Util;
import com.KoreaIT.demo.vo.Article;
import com.KoreaIT.demo.vo.ResultData;

@Controller
public class UsrArticleController {

	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	// write, 작성
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {

		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요.");
		}

		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요.");
		}

		articleService.writeArticle(title, body);

		int id = articleService.getLastInsertId();

		Article article = articleService.getArticleById(id);

		return ResultData.from("S-1", Util.f("%d번 게시물이 작성되었습니다.", id), article);
	}

	// list, 목록
	@RequestMapping("/usr/article/list")
	@ResponseBody
	public ResultData<List<Article>> showList() {

		List<Article> articles = articleService.showList();

		if (articles.size() == 0) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}

		return ResultData.from("S-1", "게시물 목록", articles);
	}

	// detail, 상세보기
	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public ResultData<Article> showDetail(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		return ResultData.from("S-1", Util.f("%d번 게시물 상세보기", id), article);
	}

	// modify, 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(int id, String title, String body) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		articleService.modifyArticle(id, title, body);

		return ResultData.from("S-1", Util.f("%d번 게시물이 수정되었습니다.", id), articleService.getArticleById(id));
	}

	// delete, 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		articleService.deleteArticleById(id);

		return ResultData.from("S-1", Util.f("%d번 게시물이 삭제되었습니다.", id));
	}
}
