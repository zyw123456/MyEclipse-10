function changeHeight(){
	if($("#tb1").height()+ 500 < 900){
		window.parent.parent.parent.document.body.style.height= 900;
		$("#queryForm").height($("#secondMenu",window.parent.left.document).height());
	}else{
		window.parent.parent.parent.document.body.style.height=$("#tb1").height()+ 500;
		$("#queryForm").height($("#secondMenu",window.parent.left.document).height());
	}
}