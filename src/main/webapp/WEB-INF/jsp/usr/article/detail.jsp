<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="LIST"/>

<%@ include file="../common/header.jsp" %>
	
	<div class="text-sm breadcrumbs max-w-5xl mx-auto">
		<ul>
			<li><a href="/">Home</a></li> 
			<li><a href="/usr/article/list">List</a></li> 
		</ul>
	</div>
	
	<section class="w-full max-w-5xl mx-auto">
		<div>
			<table class="table">
				<tr>
					<th>번호</th>
					<td>${article.id }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${article.title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${article.writerName }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${article.regDate.substring(2, 16) }</td>
				</tr>
			</table>
		</div>
		
		<div>
			<button onclick="history.back()">뒤로가기</button>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>