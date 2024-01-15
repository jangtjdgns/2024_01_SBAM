package com.KoreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.demo.vo.Member;

@Mapper
public interface MemberDao {

	@Select("SELECT * FROM `member`")
	public List<Member> getMembers();

	@Insert("""
			INSERT INTO `member`
				SET regDate = NOW()
					, updateDate = NOW()
					, loginId = #{loginId}
					, loginPw = #{loginPw}
					, `name` = #{name}
					, nickname = #{nickname}
					, cellphoneNum = #{cellphoneNum}
					, email = #{email};
			""")
	public void joinMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("""
			SELECT LAST_INSERT_ID();
			""")
	public int getLastInsertId();

	@Select("""
			SELECT * FROM `member`
			WHERE id = #{id}
			""")
	public Member getMemberById(int id);

}
