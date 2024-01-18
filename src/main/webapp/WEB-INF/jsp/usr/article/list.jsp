<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="LIST"/>

<%@ include file="../common/header.jsp" %>
	<section class="w-full max-w-5xl mx-auto">
		<div>
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
	
	<div class="text-center py-8">
		<div class="join">
			<button class="join-item btn btn-sm">«</button>
			<button class="join-item btn btn-sm btn-active">1</button>
			<button class="join-item btn btn-sm">2</button>
			<button class="join-item btn btn-sm">3</button>
			<button class="join-item btn btn-sm">4</button>
			<button class="join-item btn btn-sm">»</button>
		</div>
	</div>

<%@ include file="../common/footer.jsp" %>