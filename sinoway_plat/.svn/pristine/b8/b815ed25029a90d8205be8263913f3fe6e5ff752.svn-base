function changeHeight(){
	var inputHeight =0;
	var liHeight = 0;
	$(".input-group").each(function(n){
		inputHeight +=54;
	});
	if($("li").length %3 ==0){
		liHeight = 80*($("li").length /3 );
	}else{
		liHeight = 80*($("li").length /3  + 1);
	}
	if(liHeight + inputHeight +720<900){
		window.parent.parent.parent.document.body.style.height= 900;
		$(".report_right").height($("#secondMenu",window.parent.left.document).height());
	}else{
		window.parent.parent.parent.document.body.style.height= liHeight+ inputHeight +720;
		$(".report_right").height($("#secondMenu",window.parent.left.document).height());
	}
}