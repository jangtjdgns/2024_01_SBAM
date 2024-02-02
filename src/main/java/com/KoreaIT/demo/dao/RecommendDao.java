package com.KoreaIT.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.demo.vo.RecommendPoint;

@Mapper
public interface RecommendDao {

	@Select("""
			SELECT *
				FROM recommendPoint
				WHERE memberId = #{memberId}
				AND relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	public RecommendPoint getRecommendPoint(int memberId, int relId, String relTypeCode);
	
	@Insert("""
			INSERT INTO recommendPoint
				SET memberId = #{memberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
					, `point` = 1
			""")
	public void insertPoint(int memberId, int relId, String relTypeCode);
	

	@Delete("""
			DELETE FROM recommendPoint
				WHERE memberId = #{memberId}
				AND relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	public void deletePoint(int memberId, int relId, String relTypeCode);
}

