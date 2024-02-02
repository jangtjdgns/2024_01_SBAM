<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="WRITE" />
<%@ include file="../common/header.jsp"%>

<script>
	const writeFormOnSubmit = function(form) {
		form.title.value = form.title.value.trim();
		form.body.value = form.body.value.trim();

		if (form.title.value.length == 0) {
			alert('제목을 입력해주세요');
			form.title.focus();
			return;
		}

		if (form.body.value.length == 0) {
			alert('내용을 입력해주세요');
			form.body.focus();
			return;
		}

		form.submit();
	}
</script>

<section class="h-body py-5">
	<div class="breadcrumbs max-w-5xl mx-auto text-sm h-20 px-2 flex flex-row justify-between items-end">
		<ul>
			<li><a href="/">Home</a></li> 
			<li><a href="list">List</a></li>
			<li><a href="write">write</a></li>
		</ul>
	</div>
    
	<!-- 토스트 UI -->
	<div id="editor"></div>
	
	<button id="btn-getHtml">내용 가져오기</button>

	<button id="btn-setHtml">내용 넣기</button>
	
	<script>
		const Editor = toastui.Editor;
		
		window.dataStorage = {
		    _storage: new WeakMap(),
		    put: function (element, key, obj) {
		        if (!this._storage.has(element)) {
		            this._storage.set(element, new Map());
		        }
		        this._storage.get(element).set(key, obj);
		    },
		    get: function (element, key) {
		        return this._storage.get(element).get(key);
		    },
		    has: function (element, key) {
		        return this._storage.has(element) && this._storage.get(element).has(key);
		    },
		    remove: function (element, key) {
		        var ret = this._storage.get(element).delete(key);
		        if (!this._storage.get(element).size === 0) {
		            this._storage.delete(element);
		        }
		        return ret;
		    }
		}
		
		function Editor__init(){
		  const editorEl = document.querySelector('#editor');
		  const editor = new Editor({
		    el: editorEl,
		    height: '500px',
		    initialEditType: 'markdown',
		    previewStyle: 'tab'
		  });
		  
		  dataStorage.put(editorEl, 'editor', editor);
		}
		
		function Viewer__init(){
		  const editorEl = document.querySelector('#viewer');
		  const editor = new Editor({
		    el: editorEl,
		    viewer:true,
		    initialValue:'#zzzzzzzzz'
		  });
		  
		  dataStorage.put(editorEl, 'editor', editor);
		}
		
		const btnGetHtmlEl = document.querySelector('#btn-getHtml');
		
		btnGetHtmlEl.addEventListener('click', () => {
		  const editorEl = document.querySelector('#editor');
		  const editor = dataStorage.get(editorEl, 'editor');
		  
		  alert(editor.getHTML());
		  alert(editor.getMarkdown());
		})
		
		const btnSetHtmlEl = document.querySelector('#btn-setHtml');
		
		btnSetHtmlEl.addEventListener('click', () => {
		  const editorEl = document.querySelector('#editor');
		  const editor = dataStorage.get(editorEl, 'editor');
		  
		  editor.setHTML('<h2>zasd</h2>');
		})
		
		// editor.getMarkdown();
		Editor__init();
		Viewer__init();
	</script>
	
	<%-- <div class="w-full max-w-5xl mx-auto">
		<form action="doWrite" method="post" onsubmit="writeFormOnSubmit(this); return false;">
		<input type="hidden" name="id" value="${article.id }" />
			<div>
				<table class="table">
					<tr>
						<th>게시판</th>
						<td>
							<select class="select select-bordered w-36 min-h-0 h-9" name="boardId">
								<!-- 공지는 어드민만 가능하도록 변경예정, 임시로 memberId가 1인경우에만 가능 -->
								<c:if test="${rq.loginedMemberId == 1 }"><option value="1" selected>공지사항</option></c:if>
								<option value="2">자유게시판</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input name="title" type="text" placeholder="제목을 입력하세요." class="input input-bordered w-full max-w-xs" /></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="body" placeholder="내용을 입력하세요." class="textarea textarea-bordered textarea-md w-full max-w-xs"></textarea></td>
					</tr>
				</table>
			</div>
	
			<div>
				<button class="btn btn-priamry">작성</button>
				<button type="button" class="btn" onclick="history.back()">Back</button>
			</div>
		</form>
	</div> --%>
</section>

<%@ include file="../common/footer.jsp"%>