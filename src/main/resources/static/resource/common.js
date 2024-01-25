$("select[data-value]").each(function(idx, item){
	
	const items = $(item);
	
	const itemValue = items.attr("data-value").trim();
	
	if (itemValue > 0) {
		items.val(itemValue);
	}
});
	
// jsp 내에서 js를 사용하면 jstl 사용이 가능하지만,
// js를 파일을 따로 관리하는 경우 사용이 불가능하다.
// console.log(${searchType });