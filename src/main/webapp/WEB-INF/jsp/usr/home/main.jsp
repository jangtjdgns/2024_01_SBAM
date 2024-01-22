<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/header.jsp"%>

<div class="hero h-body bg-base-200 flex justify-around">
	<div class="hero-content text-center">
		<div class="content-box cb-1 max-w-md">
			<h1 class="text-5xl font-bold">LIST</h1>
			<p class="py-6">Lorem ipsum dolor sit amet, consectetur
				adipisicing elit. Sunt dolores illum alias labore error pariatur
				laudantium natus fuga mollitia quis quidem ut quae ea magnam
				recusandae dolorum harum dolore architecto.</p>
			<button class="btn btn-primary">Get Started</button>
		</div>
	</div>
	<div class="hero-content text-center">
		<div class="content-box cb-2 max-w-md">
			<h1 class="text-5xl font-bold">LOGIN</h1>
			<p class="py-6">Lorem ipsum dolor sit amet, consectetur
				adipisicing elit. Sunt dolores illum alias labore error pariatur
				laudantium natus fuga mollitia quis quidem ut quae ea magnam
				recusandae dolorum harum dolore architecto.</p>
			<button class="btn btn-primary">Get Started</button>
		</div>
	</div>
</div>
<%@ include file="../common/footer.jsp"%>