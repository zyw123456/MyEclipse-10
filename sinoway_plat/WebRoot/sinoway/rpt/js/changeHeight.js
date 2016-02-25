function changeHeight(){
	if($(".report_right").height() + 450 < 900){
		window.parent.parent.parent.document.body.style.height= 900;
		$(".report_right").height($("#secondMenu",window.parent.left.document).height());
	}else{
		window.parent.parent.parent.document.body.style.height=$(".report_right").height() + 450;
		$(".report_right").height($("#secondMenu",window.parent.left.document).height());
	}
}