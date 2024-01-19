package com.KoreaIT.demo.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.KoreaIT.demo.interceptor.BeforeActionInterceptor;
import com.KoreaIT.demo.interceptor.NeedLoginInterceptor;

public class MyWebMvcConfigurer implements WebMvcConfigurer {
	
	private BeforeActionInterceptor beforeActionInterceptor;
	private NeedLoginInterceptor needLoginInterceptor;
	
	public MyWebMvcConfigurer(BeforeActionInterceptor beforeActionInterceptor, NeedLoginInterceptor needLoginInterceptor) {
		this.beforeActionInterceptor = beforeActionInterceptor;
		this.needLoginInterceptor = needLoginInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// "/resource/**" 이 경로를 제외하고 모든 경로는 beforeActionInterceptor 를 거침
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");
		
		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/doWrite")
				.addPathPatterns("/usr/article/doModify").addPathPatterns("/usr/article/doDelete");
	}
}
