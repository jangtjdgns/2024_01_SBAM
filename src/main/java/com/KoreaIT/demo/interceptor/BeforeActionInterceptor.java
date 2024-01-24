package com.KoreaIT.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.KoreaIT.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor  {
	
	private Rq rq;
	
	public BeforeActionInterceptor(Rq rq){
		this.rq = rq;
	}
	
	@Override
	public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		
		// BeforeActionInterceptor 에서 첫 실행을 통해 객체를 완성함
		// 이런식으로 첫 실행을 하지않으면 객체가 완성되지 않는다고함 
		rq.init();
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
