<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="LOGIN" />

<%@ include file="../common/header.jsp"%>

<script>
	const loginFormOnSubmit = function(form){
		form.loginId.value = form.loginId.value.trim();
		form.loginPw.value = form.loginPw.value.trim();
		
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return;
		}
		
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return;
		}
		
		form.submit();
	}
</script>

<!-- required -->
<section class="h-body py-40" style="background-image: url(https://cdn.pixabay.com/photo/2019/04/02/17/55/haweswater-reservoir-4098338_1280.jpg); background-size:cover; background-position: center;">
	<div class="card shrink-0 w-full mx-auto max-w-sm shadow-2xl bg-base-100 opacity-85">
		<form class="card-body" action="doLogin" method="post" onsubmit="loginFormOnSubmit(this); return false;">
			<div class="form-control">
				<label class="label"> <span class="label-text">User</span></label>
				<input type="text" name="loginId" placeholder="User" class="input input-bordered" />
			</div>
			<div class="form-control">
				<label class="label"> <span class="label-text">Password</span></label>
				<input type="password" name="loginPw" placeholder="password" class="input input-bordered" /> <label class="label">
					<a href="#" class="label-text-alt link link-hover">Forgot password?</a>
				</label>
			</div>
			<div class="form-control mt-6">
				<button class="btn btn-primary">Login</button>
			</div>
			<div class="form-control mt-6">
				<button type="button" class="btn" onclick="history.back()">Back</button>
			</div>
		</form>
	</div>
</section>

<%@ include file="../common/footer.jsp"%>