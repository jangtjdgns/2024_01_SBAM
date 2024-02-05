<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="JOIN" />

<%@ include file="../common/header.jsp"%>
<script>
let validLoginId = '';

const joinFormOnSubmit = function(form){
	/* $(form).find('input').each(function(idx, item){
		if($(item).val().trim().length == 0){
			$(this).addClass("input-error");
		}
	}); */
	
	form.loginId.value = form.loginId.value.trim();
	form.loginPw.value = form.loginPw.value.trim();
	
	if (form.loginId.value.length == 0) {
		alert('아이디를 입력해주세요');
		form.loginId.focus();
		return;
	}
	
	if (form.loginId.value != validLoginId) {
		alert(form.loginId.value + '은(는) 사용할 수 없는 아이디입니다');
		form.loginId.value = '';
		form.loginId.focus();
		return;
	}
	
	if (form.loginPw.value.length == 0) {
		alert('비밀번호를 입력해주세요');
		form.loginPw.focus();
		return;
	}
	
	if (form.name.value.length == 0) {
		alert('이름을 입력해주세요');
		form.name.focus();
		return;
	}
	
	if (form.nickname.value.length == 0) {
		alert('닉네임을 입력해주세요');
		form.nickname.focus();
		return;
	}
	
	if (form.cellphoneNum.value.length == 0) {
		alert('전화번호를 입력해주세요');
		form.cellphoneNum.focus();
		return;
	}
	
	if (form.email.value.length == 0) {
		alert('이메일을 입력해주세요');
		form.email.focus();
		return;
	}
	
	form.submit();
}  

function dupCheck(loginId){
	
	$.ajax({
		url : "../member/loginIdDupChk",
		method : "get",
		data : {
			"loginId" : loginId.val().trim(),
		},
		dataType : "json",
		success : function(data){
			if(data.success){				
				loginId.removeClass("input-error");
				validLoginId = loginId.val().trim();
			} else {
				loginId.addClass("input-error");
				validLoginId = '';
			}
		},
		error : function(xhr, status, error){
			console.error("ERROR : " + status + " - " + error);
		}
	})
}

$(function(){
	$(".dupCheckBtn").click(function(){
		dupCheck($("#loginId"));
	})
	
	$("input").blur(function(){
		if($(this).val().trim().length == 0){
			$(this).addClass("input-error");
		} else {
			$(this).removeClass("input-error");
		}
	})
})
</script>


<section class="h-body py-5 max-w-4xl mx-auto">
	<form action="doJoin" method="post" class="flex flex-col" onsubmit="joinFormOnSubmit(this); return false;">
		<div>		
			<label>
				<span>아이디: </span> 
				<input name="loginId" id="loginId" type="text" placeholder="Type here" class="input input-bordered input-xs" />
			</label>
			<button type="button" class="dupCheckBtn btn btn-ghost btn-xs">중복확인</button>
		</div>
		
		<label>
			<span>비밀번호: </span> 
			<input name="loginPw" id="loginPw" type="password" placeholder="Type here" class="input input-bordered input-xs" />
		</label>
		<!-- <label>
			<span>비밀번호확인: </span> 
			<input type="password" id="loginPwChk" placeholder="Type here" class="input input-bordered input-xs" />
		</label> -->
		
		<label>
			<span>이름: </span> 
			<input name="name" type="text" placeholder="Type here" class="input input-bordered input-xs" />
		</label>
		
		<div>		
			<label>
				<span>닉네임: </span> 
				<input name="nickname" type="text" placeholder="Type here" class="input input-bordered input-xs" />
			</label>
			<!-- <button type="button" class="dupCheckBtn btn btn-ghost btn-xs">중복확인</button> -->
		</div>
		
		<label>
			<span>전화번호: </span> 
			<input name="cellphoneNum" type="text" placeholder="Type here" class="input input-bordered input-xs" />
		</label>
		
		<label>
			<span>이메일: </span> 
			<input name="email" type="email" placeholder="Type here" class="input input-bordered input-xs" />
		</label>
		
		<div>
			<button class="btn btn-priamry">가입</button>
			<button type="button" class="btn" onclick="history.back()">취소</button>
		</div>
	</form>
</section>

<%@ include file="../common/footer.jsp"%>
