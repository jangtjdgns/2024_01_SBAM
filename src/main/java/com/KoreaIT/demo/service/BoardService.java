package com.KoreaIT.demo.service;

import org.springframework.stereotype.Service;

import com.KoreaIT.demo.dao.BoardDao;
import com.KoreaIT.demo.vo.Board;

@Service
public class BoardService {
	private BoardDao boardDao;
	
	public BoardService(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	public Board getBoardById(int boardId) {
		
		if(boardId == 0) {
			return new Board(0, "", "", "all", "All");
		}
		
		return boardDao.getBoardById(boardId);
	}
}
