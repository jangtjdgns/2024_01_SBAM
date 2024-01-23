<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/header.jsp"%>

<div class="hero h-body bg-base-200 flex justify-around">
	<div class="hero-content text-center">
		<div class="content-box cb-1 w-96">
			<h1 class="text-5xl font-bold">NOTICE</h1>
			<p class="py-6">공지사항</p>
			<a href="../article/list?boardId=1" class="btn btn-primary">Get Started</a>
		</div>
	</div>
	
	<div class="hero-content text-center">
		<div class="content-box cb-2 w-96">
			<h1 class="text-5xl font-bold">FREE</h1>
			<p class="py-6">자유게시판</p>
			<a href="../article/list?boardId=2" class="btn btn-primary">바로가기</a>
		</div>
	</div>
</div>

<%@ include file="../common/footer.jsp"%>