<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="DETAIL"/>

<%@ include file="../common/header.jsp" %>
<script>
$(document).ready(function(){
	getRecommendPoint();
	getReply();
	
	// 조회수
	$('#recommendBtn').click(function(){
		let recommendBtn = $('#recommendBtn').hasClass('btn-active');
		
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
	
	//댓글 작성
	$('#writeBtn').click(function(){
		const body = $("#replyBody").val().trim();
		
		if(body.length == 0){
			alert("댓글을 입력해주세요.")
			return $("#replyBody").focus();
		}
		
		$.ajax({
			url : "../reply/doWrite",
			method : "get",
			data : {
				"relId" : ${article.id },
				"relTypeCode" : "article",
				"body" : body
			},
			dataType : "text",
			success : function(data){
				getReply();
				alert("댓글이 작성되었습니다.")
				$("#replyBody").val("");
			},
			error : function(xhr, status, error){
				console.error("ERROR : " + status + " - " + error);
			}
		})
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
			if (data.success) {
				$('#recommendBtn').addClass('btn-active');
			}
		},
		error : function(xhr, status, error){
			console.error("ERROR : " + status + " - " + error);
		}
	})
}


// 댓글 가져오기
function getReply(){
	$.ajax({
		url : "../reply/getReply",
		method : "get",
		data : {
			"relTypeCode" : "article",
			"relId" : ${article.id }
		},
		dataType : "json",
		success : function(data){
			const results = data.data;
			
			if (data.success) {
				$("#replies>ul").remove();
				ShowReplies(results);
			}
		},
		error : function(xhr, status, error){
			console.error("ERROR : " + status + " - " + error);
		}
	})
}

// 댓글 표시
function ShowReplies(rsReplies){
	let replies = $("#replies");
	
	for(let i = 0; i < rsReplies.length; i++){
		console.log(rsReplies[i]);
		replies.append(`
			<ul class="pt-5">
				<li class="flex justify-center">
					<input class="replyId border-t-2" type="hidden" value=\${rsReplies[i].id} />
					<div class="replyText w-4/5 flex flex-col">
						<div class="border-b">\${rsReplies[i].writerName} | \${rsReplies[i].regDate}</div>
						<div class="replyContent">\${rsReplies[i].forPrintBody}</div>
						<form action="../reply/doModify" method="post ">
							<input type="hidden" name="id" value="\${rsReplies[i].id}" />
							<input type="hidden" name="boardId" value="${article.boardId}" />
							<textarea name="body" placeholder="Reply Here" class="replyBody textarea textarea-bordered textarea-md w-full hidden">\${rsReplies[i].body}</textarea>
							<button class="replyModifyBtn btn btn-sm hidden" onclick="if(!confirm('댓글을 수정하시겠습니까?')) return false;">저장</button>
						</form>
					</div>
					<div class="reply-option-btn w-1/5 text-center flex flex-col">
					
					</div>
				</li>
			</ul>
		`);
		
		if(${rq.loginedMemberId} == rsReplies[i].memberId){
			$(".reply-option-btn").eq(i).append(`
				<div class="flex justify-between items-end">
					<div>${reply.writerName }</div>
					<div class="dropdown dropdown-end">
						<button class="btn btn-circle btn-ghost btn-sm">
					    	<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-5 h-5 stroke-current"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path></svg>
					    </button>
				    	<ul tabindex="0" class="z-[1] p-2 shadow menu menu-sm dropdown-content bg-base-100 rounded-box w-24">
				    		<li><button onclick="modifyReply(\${ i })">수정</button></li>
				    		<li><a href="../reply/doDelete?id=\${rsReplies[i].id}&boardId=${article.boardId}" onclick="if(!confirm('댓글을 삭제하시겠습니까?')) return false;">삭제</a></li>
				    	</ul>
				    </div>
				</div>
			`);
		}
	}
}

// 댓글 수정을 위한 입력란 표시
function modifyReply(replyId){
	$(".replyContent").eq(replyId).toggleClass("hidden");
	$(".replyBody").eq(replyId).toggleClass("hidden");
	$(".replyModifyBtn").eq(replyId).toggleClass("hidden");
}

</script>


<section class="h-body py-5">
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
					<th>작성일</th>
					<td>${article.regDate.substring(2, 16) }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${article.hitCnt }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${article.writerName }</td>
				</tr>
				<tr>
					<th>추천</th>
					<td><span>${article.point }개</span></td>
					<td>
						<c:if test="${rq.loginedMemberId == 0}">
							<span>${article.point }개</span>
						</c:if>
						
						<c:if test="${rq.loginedMemberId != 0}">
							<button id="recommendBtn" class="btn">좋아요👍</button>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${article.title }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${article.getForPrintBody() }</td>
				</tr>
			</table>
		</div>
	
		<div>
			<a href="list?boardId=${board.id }" class="btn">List</a>
			<c:if test="${rq.loginedMemberId == article.memberId }">
				<a href="modify?id=${article.id }" class="btn btn-accent btn-sm"><i class="fa-regular fa-pen-to-square" style="color: #ffffff;"></i></a>
				<a href="doDelete?id=${article.id }" class="btn btn-error btn-sm" onclick="if(!confirm('정말 삭제하시겠습니까?')) return false;"><i class="fa-regular fa-trash-can" style="color: #ffffff;"></i></a>
			</c:if>
		</div>
	</div>
	
	
	<!-- 댓글 -->
	<div class="w-full max-w-5xl mx-auto pt-10">
		<div class="w-4/5 mx-auto">
			<div>댓글</div>
			<textarea id="replyBody" placeholder="Reply Here" class="textarea textarea-bordered textarea-md w-full" ${rq.loginedMemberId == 0 ? "disabled" : "" } ></textarea>
			<div class="flex justify-end">
				<button id="writeBtn" class="btn w-16 h-5 text-xs block" ${rq.loginedMemberId == 0 ? "disabled" : "" }>작성</button>
			</div>
		</div>
		<div id="replies" class="text-xs mx-auto w-4/5 pt-6"></div>
	</div>
</section>
	
<%@ include file="../common/footer.jsp" %>