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
	<div>
		<div>
			<a href="/">로고</a>
		</div>

		<ul>
			<li><a href="/">HOME</a></li>
			<li><a href="/usr/article/list">List</a></li>
		</ul>
	</div>

	<section>
		<div>
			<h1>${pageTitle } PAGE</h1>
		</div>
	</section>