package com.KoreaIT.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.demo.vo.Board;

@Mapper
public interface BoardDao {

	@Select("""
			SELECT *
				FROM board
				WHERE id = #{boardId}
			""")
	public Board getBoardById(int boardId);
	
}
