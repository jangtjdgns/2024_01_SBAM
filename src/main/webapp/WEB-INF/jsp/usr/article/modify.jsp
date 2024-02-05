<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MODIFY" />

<%@ include file="../common/header.jsp"%>

<%@ include file="../common/toastUi.jsp"%>

<section class="h-body py-5 max-w-4xl mx-auto">
	<div class="breadcrumbs text-sm h-16 px-2 flex flex-row justify-between items-end">
		<ul>
			<li><a href="/">Home</a></li> 
			<li><a href="list">List</a></li>
			<li>Modify</li>
		</ul>
	</div>

	<form action="doModify" method="post" onsubmit="writeFormOnSubmit(this); return false;">
		<input name="id" type="hidden" value="${article.id }" />
		<textarea name="body" class="hidden">${article.body }</textarea>
		<div>
			<input name="title" class="input input-bordered input-md w-2/5" placeholder="제목을 입력해주세요" type="text" value="${article.title }">
			<select name="boardId" class="select select-bordered select-md">
				<c:if test="${rq.loginedMemberId == 1 }">
					<option value="1" selected>공지사항</option>
				</c:if>
				<option value="2" ${article.boardId == 2 ? 'selected' : ''}>자유게시판</option>
			</select>
		</div>
		<div>
			<span>내용</span>
			<div id="editor" class="toast-ui-editor">
				<script type="text/x-template">
					
				</script>
			</div>
		</div>
		<div>
			<button class="btn btn-priamry">작성</button>
			<button type="button" class="btn" onclick="history.back()">Back</button>
		</div>
	</form>
</section>

<%@ include file="../common/footer.jsp"%>