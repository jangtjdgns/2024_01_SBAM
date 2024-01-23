<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="WRITE" />

<%@ include file="../common/header.jsp"%>

<script>
	const writeFormOnSubmit = function(form){
		form.title.value = form.title.value.trim();
		form.body.value = form.body.value.trim();
		
		if (form.title.value.length == 0) {
			alert('제목을 입력해주세요');
			form.title.focus();
			return;
		}
		
		if (form.body.value.length == 0) {
			alert('내용을 입력해주세요');
			form.body.focus();
			return;
		}
		
		form.submit();
	}
</script>

<section class="h-body py-5">
	<div class="breadcrumbs max-w-5xl mx-auto text-sm h-20 px-2 flex flex-row justify-between items-end">
		<ul>
			<li><a href="/">Home</a></li> 
			<li><a href="list">List</a></li>
			<li><a href="write">write</a></li>
		</ul>
	</div>

	<div class="w-full max-w-5xl mx-auto">
		<form action="doWrite" method="post" onsubmit="writeFormOnSubmit(this); return false;">
		<input type="hidden" name="id" value="${article.id }" />
			<div>
				<table class="table">
					<tr>
						<th>제목</th>
						<td><input name="title" type="text" placeholder="Type here" value="${article.title }" class="input input-bordered w-full max-w-xs" /></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="body" placeholder="내용을 입력하세요." class="textarea textarea-bordered textarea-md w-full max-w-xs">${article.body }</textarea></td>
					</tr>
				</table>
			</div>
	
			<div>
				<button class="btn btn-priamry">작성</button>
				<button type="button" class="btn" onclick="history.back()">Back</button>
			</div>
		</form>
	</div>
</section>

<%@ include file="../common/footer.jsp"%>