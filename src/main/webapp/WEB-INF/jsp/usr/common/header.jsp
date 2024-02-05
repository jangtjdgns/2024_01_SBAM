<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pageTitle }</title>
	<link rel="shuirtcut icon" type="icon" sizes="16x16" href="/resource/images/favicon.ico">
	<%@ include file="../common/head.jsp"%>
</head>
<body>
<!-- shadow-xl rounded-box -->
<div class="border-b">
	<div class="mx-auto max-w-4xl navbar h-header bg-base-100">
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
			<div class="dropdown dropdown-end w-20 text-center">
				<div tabindex="0" role="button" class="btn btn-ghost btn-circle avatar">
					<div class="w-10 rounded-full">
					<img alt="Tailwind CSS Navbar component" src="http://placehold.it/40x40" />
					</div>
				</div>
				<ul tabindex="0" class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52">
					<li>
						<a class="justify-between">
							Profile
							<span class="badge">New</span>
						</a>
					</li>
					<li><a>Settings</a></li>
					<li><a href="../member/doLogout">Logout</a></li>
				</ul>
			</div>
		</c:if>
	  </div>
	</div>
</div>