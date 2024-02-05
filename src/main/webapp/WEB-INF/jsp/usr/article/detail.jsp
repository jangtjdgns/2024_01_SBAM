<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="DETAIL"/>

<%@ include file="../common/header.jsp" %>
<!-- dompurify -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/dompurify/2.3.0/purify.min.js"></script>

<!-- 토스트 UI 에디터 코어 -->
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

<!-- 토스트 UI 에디터 플러그인, 컬러피커 -->
<link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.css" />
<script src="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.js"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css" />
<script src="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.js"></script>

<script>
	function ToastEditorView__init() {
	    const $initialValueEl = $("#body");
	    const initialValue = $initialValueEl.val();

	    var viewer = new toastui.Editor.factory({
	      	el: document.querySelector("#viewer"),
	      	initialValue: initialValue,
	      	viewer:true,
	      	plugins: [
	    	  toastui.Editor.plugin.colorSyntax,
	    	]
	    });
		
	    $("#viewer").data('viewer', viewer);
	}
	

$(document).ready(function(){
	ToastEditorView__init();
	getRecommendPoint();
	
	// 좋아요 버튼 클릭
	$('#recommendBtn').click(function(){
		let recommendBtn = $('#recommendBtn').hasClass('btn-active');
		
		console.log(recommendBtn);
		
		$.ajax({
			url : "../recommendPoint/doRecommendPoint",
			method : "get",
			data : {
				"relTypeCode" : "article",
				"relId" : ${article.id },
				"recommendBtn" : recommendBtn
			},
			dataType : "text",
			success : function(data){
			},
			error : function(xhr, status, error){
				console.error("ERROR : " + status + " - " + error);
			}
		})
		
		location.reload();
	})
})

// 좋아요 조회
const getRecommendPoint = function(){
	$.ajax({
		url : "../recommendPoint/getRecommendPoint",
		method : "get",
		data : {
			"relTypeCode" : "article",
			"relId" : ${article.id }
		},
		dataType : "json",
		success : function(data){
			if(data.success) {
				$('#recommendBtn').addClass('btn-active');
			}
		},
		error : function(xhr, status, error){
			console.error("ERROR : " + status + " - " + error);
		}
	})
}

// 댓글 입력 검사
const replyFormOnSubmit = function(form){
	form.body.value = form.body.value.trim();
	
	if (form.body.value.length < 2) {
		alert('2글자 이상 입력해주세요');
		form.body.focus();
		return;
	}
	
	form.submit();
}

// 전역 변수
let originalForm = null;		// 댓글 수정 전의 form 백업용
let originalId = null;			// 댓글 수정 전의 댓글 id 백업용

// 댓글 수정 form 생성 함수
const replyModifyGetForm = function(replyId){
	
	if (originalForm != null) {				// 백업용 originalForm 변수가 null이 아닌경우, 어떠한 댓글이 백업 되있는 경우
		replyModifyCancle(originalId);		// 수정 취소, 여기선 초기화 용도
	}
	
	console.log(replyId);
	
	$.ajax({
		url : "../reply/getReplyContent",
		method : "get",
		data : {
			"id" : replyId
		},
		dataType : "json",
		success : function(data){
			
			const reply = data.data;
			
			let replyContent = $('#' + replyId);
			
			originalId = replyId;
			originalForm = replyContent.html();
			
			let addHtml = `
				<form action="../reply/doModify" method="post" onsubmit="replyFormOnSubmit(this); return false;">
					<input type="hidden" name="id" value="\${reply.id }" />
					<input type="hidden" name="boardId" value="${board.id}" />
					<div class="mt-4">
						<textarea class="textarea textarea-bordered w-full resize-none" name="body" placeholder="Reply Here">\${reply.convertBrToN}</textarea>
						<div class="flex justify-end">
							<button onclick="replyModifyCancle(\${reply.id});" class="btn btn-outline btn-sm mr-2">취소</button>
							<button class="btn btn-outline btn-sm">수정</button>
						</div>
					</div>
				</form>
			`;
			
			replyContent.empty().html(addHtml);
		},
		error : function(xhr, status, error){
			console.error("ERROR : " + status + " - " + error);
		}
	})
}

// 수정 취소 함수
const replyModifyCancle = function(replyId){
	let replyContent = $('#' + replyId)
	
	replyContent.html(originalForm);
	
	originalId = null;
	originalForm = null;
}
</script>


<section class="h-body py-5 max-w-4xl mx-auto">
	
	<!-- 경로, 제목 -->
	<div class="w-full">
		<!-- 경로 -->
		<div class="breadcrumbs text-sm h-16 px-2 flex flex-row justify-between items-end border-b-2 border-gray-200">
			<ul>
				<li><a href="/">Home</a></li>
				<li><a href="list">List</a></li>
				<li><a href="list?boardId=${board.id }">${board.name }</a></li>
				<li>${article.id }번</li>
			</ul>
		</div>

		<!-- 제목  -->
		<div class="border-b-2 border-gray-200 px-1.5 py-2 flex items-center">

			<div class="text-xs text-gray-700 w-1/5 flex flex-col justify-end items-start">
				<span>${article.writerName }</span>
				<span>${article.formatRegDate }</span>
			</div>
		
			<div class="text-2xl font-bold w-3/5 text-center">
				<span>${article.title }</span>
			</div>
			<div class="w-1/5">
				<c:if test="${rq.loginedMemberId == article.memberId }">
					<div class="dropdown dropdown-end flex align-start justify-end">
						<div tabindex="0" role="button" class="btn btn-xs btn-ghost rounded-btn">
							<i class="fa-solid fa-gear text-gray-700"></i>
						</div>
						<ul tabindex="0" class="menu dropdown-content z-[1] p-2 shadow bg-base-100 rounded-box w-22 mt-7">
							<li>
								<a href="modify?id=${article.id }" class="btn btn-sm btn-ghost border-green-200">
									<i class="fa-regular fa-pen-to-square"></i>
								</a>
							</li>
							<li class="pt-1.5">
								<a href="doDelete?id=${article.id }" class="btn btn-sm btn-ghost border-red-200" onclick="if(!confirm('정말 삭제하시겠습니까?')) return false;">
									<i class="fa-regular fa-trash-can"></i>
								</a>
							</li>
						</ul>
					</div>
				</c:if>
				
				<div class="text-xs text-gray-700 flex justify-end items-end">
					<span><i class="fa-regular fa-eye"></i> ${article.hitCnt }</span>
					<span class="px-1.5"><i class="fa-regular fa-thumbs-up"></i> ${article.point }</span>
					<span><i class="fa-regular fa-comment-dots"></i> ${article.replyCnt }</span>
				</div>
			</div>
		</div>
	</div>

	<!-- 글, 좋아요 -->
	<div class="w-full p-4 border-b-2 border-gray-200">
		<textarea id="body" class="hidden">${article.body}</textarea>
		<div id="viewer"></div>
		<%-- <div style="min-height: 20vh;">
			${article.convertNToBr }
		</div> --%>
		<div class="flex justify-center pt-4">
			<button id="recommendBtn" class="btn" ${rq.loginedMemberId != 0 ? '' : 'disabled'}>좋아요👍 ${article.point }</button>
		</div>
	</div>
	
	
	<!-- 댓글 -->
	<div class="w-full pt-4">
		<div class="border-b-2 pb-4">
			<div>댓글 (${article.replyCnt })</div>
			
			<!-- 댓글 입력 form -->
			<form action="../reply/doWrite" method="post" onsubmit="replyFormOnSubmit(this); return false;">
				<input type="hidden" name="relId" value="${article.id }"/>
				<input type="hidden" name="relTypeCode" value="article"/>
				<input type="hidden" name="boardId" value="${article.boardId }" />
				<textarea name="body" placeholder="Reply Here" class="textarea textarea-bordered textarea-md w-full resize-none" ${rq.loginedMemberId == 0 ? "disabled" : "" } ></textarea>
				<div class="flex justify-end">
					<button class="btn btn-md w-20 text-sm block" ${rq.loginedMemberId == 0 ? "disabled" : "" }>작성</button>
				</div>
			</form>
		</div>
		
		<div id="replies" class="text-sm px-1.5">
			<c:forEach var="reply" items="${replies }">
				<div class="flex justify-between border-b py-4" style="min-height: 144px;">
					<div class="avatar px-2">
						<div class="w-10 h-10 rounded-full">
							<img src="http://placehold.it/50x50" />
						</div>
					</div>
					<div class="w-full px-2">
						<div>
							<span class="font-bold">${reply.writerName }</span>
							<span>| ${reply.formatRegDate }</span>
						</div>
						<div id="${reply.id }" class="p-1.5">${reply.convertNToBr }</div>			
					</div>
					
					<c:if test="${rq.loginedMemberId == reply.memberId }">
						<div class="dropdown dropdown-end">
							<button class="btn btn-circle btn-ghost btn-sm">
						    	<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-5 h-5 stroke-current"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path></svg>
						    </button>
					    	<ul tabindex="0" class="z-[1] p-2 shadow menu menu-sm dropdown-content bg-base-100 rounded-box w-24">
					    		<li><button onclick="replyModifyGetForm(${reply.id});">수정</button></li>
					    		<li><a href="../reply/doDelete?id=${reply.id }&boardId=${article.boardId}" onclick="if(!confirm('정말 삭제하시겠습니까?')) return false;">삭제</a></li>
					    	</ul>
					    </div>
					</c:if>
				</div>
			</c:forEach>
		</div>
	</div>
</section>
	
<%@ include file="../common/footer.jsp" %>