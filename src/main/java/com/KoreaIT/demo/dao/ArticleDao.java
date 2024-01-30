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
					, boardId = #{boardId }
					, title = #{title}
					, `body` = #{body}
			""")
	public void writeArticle(int loginedMemberId, String title, String body, int boardId);
	
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
			<script>
			SELECT A.*, M.nickname writerName
				, IFNULL(SUM(R.point), 0) `point`
				FROM article A
				INNER JOIN `member` M
			    ON A.memberId = M.id
			    LEFT JOIN recommendPoint R
				ON A.id = R.relId
				LEFT JOIN board B
			    ON A.boardId = B.id
				WHERE 1 = 1
				<if test="boardId != 0">
					AND A.boardId = #{boardId}
				</if>
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchType == 1">
							AND A.title LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchType == 2">
							AND A.body LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<otherwise>
							AND (
								A.title LIKE CONCAT('%', #{searchKeyword}, '%')
								OR A.body LIKE CONCAT('%', #{searchKeyword}, '%')
							)
						</otherwise>
					</choose>
				</if>
				GROUP BY A.id
				ORDER BY A.id DESC
				Limit #{limitFrom}, #{itemsInAPage}
			</script>
			""")
	public List<Article> getArticles(int limitFrom, int itemsInAPage, String searchKeyword, int boardId, int searchType);

	@Select("""
			SELECT LAST_INSERT_ID();
			""")
	public int getLastInsertId();

	@Select("""
			SELECT A.*
				, M.nickname AS writerName
				, IFNULL(SUM(R.point), 0) `point`
				FROM article AS A
				INNER JOIN `member` AS M
				ON A.memberId = M.id
				LEFT JOIN recommendPoint R
				ON A.id = R.relId
				WHERE A.id = #{id}
				GROUP BY A.id
			""")
	public Article forPrintArticle(int id);

	@Select("""
			<script>
				SELECT COUNT(*)
				FROM article
				WHERE 1 = 1
				<if test="boardId != 0">
					AND boardId = #{boardId}
				</if>
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchType == 1">
							AND title LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchType == 2">
							AND `body` LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<otherwise>
							AND (
								title LIKE CONCAT('%', #{searchKeyword}, '%')
								OR `body` LIKE CONCAT('%', #{searchKeyword}, '%')
							)
						</otherwise>
					</choose>
				</if>
			</script>
			""")
	public int getTotalCount(String searchKeyword, int boardId, int searchType);

	@Update("""
			UPDATE article
				SET hitCnt = hitCnt + 1
				WHERE id = #{id}
			""")
	public void increaseHitCnt(int id);
}
