package com.KoreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KoreaIT.demo.vo.Article;

@Mapper
public interface ArticleDao {

	@Insert("""
			INSERT INTO article
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{loginedMemberId}
					, title = #{title}
					, `body` = #{body}
			""")
	public void writeArticle(int loginedMemberId, String title, String body);
	
	@Select(""" 
			SELECT *
				FROM article
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);

	@Delete("""
			DELETE 
				FROM article
				WHERE id = #{id}
			""")
	public void deleteArticleById(int id);
	
	@Update("""
			<script>
			UPDATE article
				SET updateDate = NOW()
					<if test="title != null and title != ''">
						, title = #{title}
					</if>
					<if test="body != null and title != ''">
						, `body` = #{body}
					</if>
				WHERE id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);

	@Select("""
			SELECT *
				FROM article
				ORDER BY id DESC
			""")
	public List<Article> showList();

	@Select("""
			SELECT LAST_INSERT_ID();
			""")
	public int getLastInsertId();
}
