<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN" />

<%@ include file="../common/header.jsp"%>

<script>
	$(function() {
		// 메인페이지에만 효과 적용
		if (window.location.pathname === '/usr/home/main') {
			$("body").css('overflow-y', 'hidden');
		}
		
		let page = 1;					// 현재 페이지 번호
		let lastPage = 4; 				// 마지막 페이지 번호, 높이가 300vh이므로 3, + footer 높이 178px 1
		let posTop = 0;					// 페이지의 상단 위치, footer 높이 고려
		
		// 시작 시 맨위로
		$("html").animate({
			scrollTop : 0
		}, 700);

		// 스탭 스타일 지정, 화면 이동 애니메이션
		function setStepStyle(){
			posTop = page == 4 ? 2 * $(window).height() + 178 : (page - 1) * $(window).height();
			
			$(".steps").children().each(function(idx, item) {
				if(idx != page - 1){
					$(item).removeClass("step-primary");
				} else if(idx == page - 1){
					$(item).addClass("step-primary");
				}
			});
			
			$("html").animate({
				scrollTop : posTop
			}, 700);
		}
		
		
		// == 스크롤에 따라 화면 위치 변경 ==
		$(window).on("wheel", function(e) {
			if ($("html").is(":animated")) { // 애니메이션 진행 중 추가적인 애니메이션효과를 방지
				return;
			}

			if (e.originalEvent.deltaY > 0) {				// 스크롤 내릴 때
				if (page == lastPage) { // 현재페이지와 마지막 페이지가 같을때는 리턴
					return;
				}
				
				page++;

			} else if (e.originalEvent.deltaY < 0) {		// 스크롤 올릴 때
				if (page == 1) { // 현재페이지가 1과 같을때는 리턴
					return;
				}
				
				page--;
			}
			
			setStepStyle();
		});
		
		
		// == 최상단, 최하단 이동 ==
		$(".tooltip").click(function(){
			if ($("html").is(":animated")) {
				return;
			}
			
			// top 버튼 클릭시
			page = 1;
			let posTop = 0;
			
			// 하단 버튼 클릭 시
			if($(this).attr("data-tip") == 'Bottom'){
				posTop = 2 * $(window).height() + 178;
				page = 4;
			}
			
			setStepStyle();
		});
		
		
		// == 스탭 버튼 클릭 ==
		let beforePage = 0;
		$(".step").click(function(){
			
			if ($("html").is(":animated")) {
				return;
			}
			
			page = $(this).index() + 1;
			
			setStepStyle();
		});
		
	});
</script>

<section>
	<div class="hero h-body"
		style="background-image: url(https://daisyui.com/images/stock/photo-1507358522600-9f71e620c44e.jpg);">
		<div class="hero-overlay bg-opacity-60"></div>
		<div class="hero-content text-center text-neutral-content content-box cb-2">
			<div class="max-w-md">
				<h1 class="mb-5 text-5xl font-bold">Hello there</h1>
				<p class="mb-5">Provident cupiditate voluptatem et in. Quaerat
					fugiat ut assumenda excepturi exercitationem quasi. In deleniti
					eaque aut repudiandae et a id nisi.</p>
				<button class="btn btn-primary">Get Started</button>
			</div>
		</div>
	</div>
</section>

<section>
	<div class="hero bg-base-200 flex justify-around min-h-screen">
		<div class="hero-content text-center">
			<div class="content-box cb-1 w-96">
				<h1 class="text-5xl font-bold">NOTICE</h1>
				<p class="py-6">공지사항</p>
				<a href="../article/list?boardId=1" class="btn btn-primary">Get
					Started</a>
			</div>
		</div>

		<div class="hero-content text-center">
			<div class="content-box cb-2 w-96">
				<h1 class="text-5xl font-bold">FREE</h1>
				<p class="py-6">자유게시판</p>
				<a href="../article/list?boardId=2" class="btn btn-primary">바로가기</a>
			</div>
		</div>
	</div>
</section>

<section>
	<div class="hero bg-base-200 flex justify-around min-h-screen">
		<div class="hero-content text-center">
			<div class="content-box cb-1 w-96">
				<h1 class="text-5xl font-bold">NOTICE</h1>
				<p class="py-6">공지사항</p>
				<a href="../article/list?boardId=1" class="btn btn-primary">Get
					Started</a>
			</div>
		</div>

		<div class="hero-content text-center">
			<div class="content-box cb-2 w-96">
				<h1 class="text-5xl font-bold">FREE</h1>
				<p class="py-6">자유게시판</p>
				<a href="../article/list?boardId=2" class="btn btn-primary">바로가기</a>
			</div>
		</div>
	</div>
</section>

<ul class="steps steps-vertical text-xs fixed right-2 top-1/2 -translate-y-2/4 scale-75">
  <li class="step step-primary"></li>
  <li class="step"></li>
  <li class="step"></li>
  <li class="step"></li>
</ul>

<ul class="menu bg-base-200 bg-gray-200 rounded-box fixed right-4 bottom-4 scale-75">
	<li>
		<button class="tooltip tooltip-left" data-tip="Top">
			<i class="fa-solid fa-chevron-up"></i>
		</button>
	</li>
	
	<li>
		<button class="tooltip tooltip-left" data-tip="Bottom">
			<i class="fa-solid fa-chevron-down"></i>
		</button>
	</li>
</ul>


<%@ include file="../common/footer.jsp"%>