package com.KoreaIT.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.KoreaIT.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor  {
	
	@Override
	public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		
		
		Rq rq = (Rq) request.getAttribute("rq");
		
		if (rq.getLoginedMemberId() == 0) {
			rq.jsPrintHistoryBack("로그인 후 이용해주세요.");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
