package com.KoreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.demo.vo.Article;

@Mapper
public interface ArticleDao {

	public Article writeArticle(String title, String body);
	
	@Select(""" 
			SELECT *
				FROM article
				WHERE id = #{id}
			""")
	
	public Article getArticleById(int id);

	public void deleteArticle(Article article);

	public void modifyArticle(Article article, String title, String body);

	public List<Article> showList();
}
