<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="DETAIL"/>

<%@ include file="../common/header.jsp" %>

<section class="h-body py-5"">
	<div class="breadcrumbs max-w-5xl mx-auto text-sm h-20 px-2 flex flex-row justify-between items-end">
		<ul>
			<li><a href="/">Home</a></li> 
			<li><a href="list">List</a></li>
			<li><a href="list?boardId=${board.id }">${board.name }</a></li>
			<li>${article.id }번</li>
		</ul>
	</div>

	<div class="w-full max-w-5xl mx-auto">
		<div>
			<table class="table">
				<tr>
					<th>번호</th>
					<td>${article.id }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${article.hitCnt }</td>
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
			<a href="list?boardId=${board.id }" class="btn">List</a>
			<button class="btn" onclick="history.back()">Back</button>
			<c:if test="${loginedMemberId == article.memberId }">
				<a href="modify?id=${article.id }" class="btn">수정</a>
				<a href="doDelete?id=${article.id }" class="btn btn-error" onclick="if(!confirm('정말 삭제하시겠습니까?')) return false;">삭제</a>
			</c:if>
		</div>
	</div>
</section>
	
<%@ include file="../common/footer.jsp" %>