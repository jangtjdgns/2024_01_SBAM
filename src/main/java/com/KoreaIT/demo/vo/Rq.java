package com.KoreaIT.demo.vo;

import java.io.IOException;

import com.KoreaIT.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Rq {
	
	@Getter
	private int loginedMemberId;
	private HttpServletResponse res;
	
	public Rq(HttpServletRequest req, HttpServletResponse res) {
		
		this.res = res;
		
		HttpSession session = req.getSession();
		
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
	}

	public void jsPrintHistoryBack(String msg) {
		res.setContentType("text/html; charset=UTF-8;");
		
		// 예외 처리가 필요함
		try {
			res.getWriter().append(Util.jsHistoryBack(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
