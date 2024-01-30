package com.KoreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.demo.vo.Reply;

@Mapper
public interface ReplyDao {
	
	@Select("""
			SELECT R.*, M.nickname writerName
				FROM reply R
				INNER JOIN `member` M
				ON R.memberId = M.id
				WHERE R.relTypeCode = #{relTypeCode}
				AND R.relId = #{relId}
				ORDER BY R.id DESC;
			""")
	public List<Reply> getReply(int relId, String relTypeCode);

	@Insert("""
			INSERT INTO reply
				SET regDate = NOW(),
				updateDate = NOW(),
				memberId = #{memberId},
				relTypeCode = #{relTypeCode},
				relId = #{relId},
				`body` = #{body}
			""")
	public void writeReply(int memberId, int relId, String relTypeCode, String body);
}