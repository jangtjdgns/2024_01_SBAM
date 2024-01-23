<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="LIST"/>

<%@ include file="../common/header.jsp" %>
	<section class="h-body py-5">
		<!-- 검색 -->
		<div class="breadcrumbs max-w-5xl mx-auto h-20 text-sm px-2 flex flex-row justify-between items-end">
			<ul>
				<li><a href="/">Home</a></li> 
				<li><a href="list">List</a></li>
				<li>${board == null ? 'All' : board.name }</li>
			</ul>
			
			<form action="list" method="get">
				<input type="hidden" value="${board == null ? 0 : board.id }" name="boardId" />
				<div class="flex justify-around items-center max-w-md h-9">
					<select class="select select-bordered min-h-0 h-9" name="itemsInAPage">
						<c:forEach begin="${10 }" end="${50 }" step="5" var="i">
							<option value="${i }" <c:if test="${itemsInAPage eq i }">selected</c:if>>${i }개씩</option>
						</c:forEach>
					</select>
				
					<input type="text" name="searchKeyword" placeholder="Search here" class="input input-bordered w-3/5 h-9" value="${searchKeyword }" />
					<button><i class="fa-solid fa-magnifying-glass text-2xl"></i></button>
				</div>
			</form>
		</div>
		
		<div role="tablist" class="tabs tabs-bordered h-10 max-w-5xl mx-auto">
			<a href="list" role="tab" class="tab <c:if test="${board.id == 0 }">tab-active</c:if>">All</a>
			<a href="list?boardId=1" role="tab" class="tab <c:if test="${board.id == 1 }">tab-active</c:if>">Notice</a>
			<a href="list?boardId=2" role="tab" class="tab <c:if test="${board.id == 2 }">tab-active</c:if>">Free</a>
		</div>
		
		<!-- 게시물 -->
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
							<td class="hover:underline"><a href="detail?boardId=${board.id}&id=${article.id }">${article.title }</a></td>
							<td>${article.writerName }</td>
							<td class="text-center">${article.regDate.substring(2, 16) }</td>
							<td>0</td>
							<td>0</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<!-- 페이징버튼 -->
		<div class="pt-7 max-w-5xl mx-auto flex">
			<div class="w-1/5"></div>
			<div class="join w-3/5 justify-center">
				<c:if test="${from ne 1 }">
				
					<a href="list?page=1&searchKeyword=${searchKeyword }&itemsInAPage=${itemsInAPage }&boardId=${board.id }" class="join-item btn btn-sm">«</a>
					<a href="list?page=${from - 1 }&searchKeyword=${searchKeyword }&itemsInAPage=${itemsInAPage }&boardId=${board == null ? 0 : board.id }" class="join-item btn btn-sm">&lt;</a>
				</c:if>
				
				<c:forEach begin="${from }" end="${end }" step="1" var="i">
					<a href="list?page=${i }&searchKeyword=${searchKeyword }&itemsInAPage=${itemsInAPage }&boardId=${board.id }" class="<c:if test="${end ne 1 }">join-item</c:if> btn btn-sm <c:if test="${page eq i }">btn-active</c:if>">${i }</a>
				</c:forEach>
				
				<c:if test="${end ne totalPageCnt}">
					<a href="list?page=${end + 1 }&searchKeyword=${searchKeyword }&itemsInAPage=${itemsInAPage }&boardId=${board.id }" class="join-item btn btn-sm">&gt;</a>
					<a href="list?page=${totalPageCnt }&searchKeyword=${searchKeyword }&itemsInAPage=${itemsInAPage }&boardId=${board.id }" class="join-item btn btn-sm">»</a>
				</c:if>
			</div>
			
			<c:if test="${rq.loginedMemberId != 0}">
				<div class="w-1/5 text-right">
					<a href="write" class="btn btn-sm">글작성</a>
				</div>
			</c:if>
		</div>
	</section>

<%@ include file="../common/footer.jsp" %>