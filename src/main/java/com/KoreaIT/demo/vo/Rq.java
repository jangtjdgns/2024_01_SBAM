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
	private HttpServletRequest req;
	private HttpServletResponse res;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse res) {
		
		this.req = req;
		
		this.res = res;
		
		this.session = req.getSession();
		
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
	}

	// Interceptor에서 사용, controller 까지 넘어갈 필요가 없을 때
	public void jsPrintHistoryBack(String msg) {
		res.setContentType("text/html; charset=UTF-8;");
		
		try {
			res.getWriter().append(Util.jsHistoryBack(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// controller에서 사용
	public String jsReturnOnView(String msg) {
		this.req.setAttribute("msg", msg);
		return "usr/common/js";
	}

	// 세션 로그인
	public void login(Member member) {
		this.session.setAttribute("loginedMemberId", member.getId());
	}
	
	// 세션 로그아웃
	public void logout() {
		this.session.removeAttribute("loginedMemberId");
	}
}
