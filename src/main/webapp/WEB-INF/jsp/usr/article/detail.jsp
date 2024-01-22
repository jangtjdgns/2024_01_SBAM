<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="DETAIL"/>

<%@ include file="../common/header.jsp" %>
	<section>
		<div class="max-w-5xl mx-auto my-2.5 h-20 text-sm breadcrumbs px-2 flex flex-row justify-between items-end">
			<ul>
				<li><a href="/">Home</a></li> 
				<li><a href="/usr/article/list">List</a></li>
				<li><a href="detail?id=${article.id }">${article.id }번</a></li>
			</ul>
		</div>
	</section>
	
	<section>
		<div class="w-full max-w-5xl mx-auto">
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
					<tr>
						<th>내용</th>
						<td>${article.body }</td>
					</tr>
				</table>
			</div>
		
			<div>
				<button class="btn" onclick="history.back()">Back</button>
				
				<c:if test="${loginedMemberId == article.memberId }">
					<a href="modify?id=${article.id }" class="btn">수정</a>
					<a href="doDelete?id=${article.id }" class="btn btn-error" onclick="if(!confirm('정말 삭제하시겠습니까?')) return false;">삭제</a>
				</c:if>
			</div>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>