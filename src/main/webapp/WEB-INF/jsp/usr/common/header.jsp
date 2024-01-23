<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
<!-- 데이지UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.6.0/dist/full.min.css" rel="stylesheet" type="text/css" />
<!-- 테일윈드치트시트 -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- 제이쿼리 jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- 폰트어썸 font-awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="/resource/common.css" />
</head>
<body>
<!-- shadow-xl rounded-box -->
	<div class="mx-auto max-w-5xl navbar h-header bg-base-100">
	  <div class="navbar-start">
	    <div class="dropdown">
	      <div tabindex="0" role="button" class="btn btn-ghost btn-circle">
	        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h7" /></svg>
	      </div>
	      <ul tabindex="0" class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52">
	        <li><a href="/">Home</a></li>
	        <li><a href="/usr/article/list">List</a></li>
	        <li><a>???</a></li>
	      </ul>
	    </div>
	  </div>
	  <div class="navbar-center">
	    <a href="/" class="btn btn-ghost text-xl">Logo</a>
	  </div>
	  <div class="navbar-end">
	    <button class="btn btn-ghost btn-circle">
	      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>
	    </button>
	    <button class="btn btn-ghost btn-circle">
	      <div class="indicator">
	        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" /></svg>
	        <span class="badge badge-xs badge-primary indicator-item"></span>
	      </div>
	    </button>
	    
	    <c:if test="${rq.loginedMemberId == 0}">
	    	<a href="../member/login" class="btn btn-ghost">LOGIN</a>
	    </c:if>
	    
	    <c:if test="${rq.loginedMemberId != 0 }">
		    <div class="avatar placeholder">
				<div class="bg-neutral rounded-full self-center text-white w-8 h-8">
					<span class="text-xs">${rq.getNickName() }</span>
				</div>
	    		<a href="../member/doLogout" class="btn btn-ghost">LOGOUT</a>
			</div> 
	    </c:if>

	    <!-- <a class="tooltip" data-tip="Home">
	      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" /></svg>
	    </a> -->
	  </div>
	</div>