<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="LOGIN" />

<%@ include file="../common/header.jsp"%>

<script>
	const loginFormSubmit = function(form){
		form.loginId.value = form.loginId.value.trim();
		form.loginPw.value = form.loginPw.value.trim();
		
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return;
		}
		
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요');
			form.loginId.focus();
			return;
		}
		
		form.submit();
	}
</script>

<section class="h-body py-44">
	<div class="card shrink-0 w-full mx-auto max-w-sm shadow-2xl bg-base-100 scale-110">
		<form class="card-body" action="doLogin" method="post" onsubmit="loginFormSubmit(this); return false;">
			<div class="form-control">
				<label class="label"> <span class="label-text">User</span>
				</label> <input type="text" name="loginId" placeholder="User" class="input input-bordered" required />
			</div>
			<div class="form-control">
				<label class="label"> <span class="label-text">Password</span>
				</label> <input type="password" name="loginPw" placeholder="password" class="input input-bordered" required /> <label class="label">
					<a href="#" class="label-text-alt link link-hover">Forgot password?</a>
				</label>
			</div>
			<div class="form-control mt-6">
				<button class="btn btn-primary">Login</button>
			</div>
		</form>
	</div>
</section>

<%@ include file="../common/footer.jsp"%>