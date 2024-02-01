package com.KoreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KoreaIT.demo.vo.Reply;

@Mapper
public interface ReplyDao {
	
	@Select("""
			SELECT * FROM reply
				WHERE id = #{id}
			""")
	public Reply getReplyById(int id);
	
	@Select("""
			SELECT R.*, M.nickname writerName
				FROM reply R
				INNER JOIN `member` M
				ON R.memberId = M.id
				WHERE R.relTypeCode = #{relTypeCode}
				AND R.relId = #{relId}
				ORDER BY R.id DESC;
			""")
	public List<Reply> getReplies(int relId, String relTypeCode);

	@Insert("""
			INSERT INTO reply
				SET regDate = NOW()
				, updateDate = NOW()
				, memberId = #{memberId}
				, relTypeCode = #{relTypeCode}
				, relId = #{relId}
				, `body` = #{body}
			""")
	public void doWrite(int memberId, int relId, String relTypeCode, String body);

	@Delete("""
			DELETE FROM reply
				WHERE id = #{id};
			""")
	public void doDelete(int id);

	@Update("""
			UPDATE reply
				SET updateDate = NOW()
				, `body` = #{body}
				WHERE id = #{id}
			""")
	public void doModify(int id, String body);
}