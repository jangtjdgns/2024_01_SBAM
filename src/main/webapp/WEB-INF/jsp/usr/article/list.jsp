<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="LIST"/>

<%@ include file="../common/header.jsp" %>

	<section>
		<div class="max-w-5xl mx-auto my-2.5 h-20 text-sm breadcrumbs px-2 flex flex-row justify-between items-end">
			<ul>
				<li><a href="/">Home</a></li> 
				<li><a href="/usr/article/list">List</a></li>
			</ul>
			<form action="list" method="get">
				<div class="flex justify-around items-center max-w-md h-9">
					<select class="select select-bordered min-h-0 h-9">
						<option value="10" selected>10개씩</option>
						<option value="15">15개씩</option>
						<option value="20">20개씩</option>
						<option value="30">30개씩</option>
						<option value="40">40개씩</option>
						<option value="50">50개씩</option>
					</select>
				
					<input type="text" name="searchKeyword" placeholder="Search here" class="input input-bordered w-3/5 h-9" value="${searchKeyword }" />
					<button><i class="fa-solid fa-magnifying-glass text-2xl"></i></button>
				
				</div>
			</form>
		</div>
	</section>
	
	<section>
		<div class="max-w-5xl mx-auto">
			<table class="table">
				<thead class="bg-gray-100 text-center">
					<tr>
						<th class="w-5">번호</th>
						<th class="w-auto">제목</th>
						<th class="w-20">작성자</th>
						<th class="w-48">작성일</th>
						<th class="w-5">추천</th>
						<th class="w-5">조회수</th>
					</tr>
				</thead>
			
				<tbody>
					<c:if test="${articles.size() == 0 }">
						<tr>
							<td class="text-center" colspan="6">등록된 게시물이 없습니다 <button class="underline text-red-600" onclick="history.back()">돌아가기</button></td>
						</tr>
					</c:if>
					
					<c:forEach var="article" items="${articles }">
						<tr>
							<td>${article.id }</td>
							<td class="hover:underline"><a href="detail?id=${article.id}">${article.title }</a></td>
							<td>${article.writerName }</td>
							<td class="text-center">${article.regDate.substring(2, 16) }</td>
							<td>0</td>
							<td>0</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<section>
		<div class="text-center">
			<div class="join">
				<c:if test="${from ne 1 }">
					<a href="list?page=1&searchKeyword=${searchKeyword }" class="join-item btn btn-sm">«</a>
					<a href="list?page=${from - 1}&searchKeyword=${searchKeyword }" class="join-item btn btn-sm">&lt;</a>
				</c:if>
				
				<c:forEach begin="${from }" end="${end }" step="1" var="i">
					<a href="list?page=${i }&searchKeyword=${searchKeyword }" class="join-item btn btn-sm <c:if test="${page eq i }">btn-active</c:if>">${i }</a>
				</c:forEach>
				
				<c:if test="${end ne totalPageCnt}">
					<a href="list?page=${end + 1 }&searchKeyword=${searchKeyword }" class="join-item btn btn-sm">&gt;</a>
					<a href="list?page=${totalPageCnt }&searchKeyword=${searchKeyword }" class="join-item btn btn-sm">»</a>
				</c:if>
			</div>
		</div>
	</section>

<%@ include file="../common/footer.jsp" %>