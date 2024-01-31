package com.KoreaIT.demo.vo;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.KoreaIT.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)		// http 요청이 들어올때마다 새로만들어낸다, 이미있는 경우 이어서 사용한다.
public class Rq {
	
	@Getter
	private int loginedMemberId;
	@Getter
	private String loginedMemberNickname;
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
			loginedMemberNickname = (String) session.getAttribute("loginedMemberNickname");
		}
		
		this.loginedMemberId = loginedMemberId;
		
		this.req.setAttribute("rq", this);	// 객체를 자동으로 생성하게 했으므로 값으로 들어가야하는 곳에는 객체 자신인 this를 넣어줌
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
		this.session.setAttribute("loginedMemberNickname", member.getNickname());
	}
	
	// 세션 로그아웃
	public void logout() {
		this.session.removeAttribute("loginedMemberId");
		this.session.removeAttribute("loginedMemberNickname");
	}

	// 객체를 완성하기 위한 메서드
	public void init() {
		
	};
}
