<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/header.jsp"%>

<div class="hero bg-base-200 flex justify-around">
	<div class="hero-content text-center">
		<div class="content-box cb-1 max-w-md">
			<h1 class="text-5xl font-bold">Hello there</h1>
			<p class="py-6">Lorem ipsum dolor sit amet, consectetur
				adipisicing elit. Sunt dolores illum alias labore error pariatur
				laudantium natus fuga mollitia quis quidem ut quae ea magnam
				recusandae dolorum harum dolore architecto.</p>
			<button class="btn btn-primary">Get Started</button>
		</div>
	</div>
	<div class="hero-content text-center">
		<div class="content-box cb-2 max-w-md">
			<h1 class="text-5xl font-bold">Hello there</h1>
			<p class="py-6">Lorem ipsum dolor sit amet, consectetur
				adipisicing elit. Sunt dolores illum alias labore error pariatur
				laudantium natus fuga mollitia quis quidem ut quae ea magnam
				recusandae dolorum harum dolore architecto.</p>
			<button class="btn btn-primary">Get Started</button>
		</div>
	</div>
	<div class="card shrink-0 w-full max-w-sm shadow-2xl bg-base-100 content-box cb-3">
		<form class="card-body">
			<div class="form-control">
				<label class="label"> <span class="label-text">Email</span>
				</label> <input type="email" placeholder="email"
					class="input input-bordered" required />
			</div>
			<div class="form-control">
				<label class="label"> <span class="label-text">Password</span>
				</label> <input type="password" placeholder="password"
					class="input input-bordered" required /> <label class="label">
					<a href="#" class="label-text-alt link link-hover">Forgot
						password?</a>
				</label>
			</div>
			<div class="form-control mt-6">
				<button class="btn btn-primary">Login</button>
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footer.jsp"%>