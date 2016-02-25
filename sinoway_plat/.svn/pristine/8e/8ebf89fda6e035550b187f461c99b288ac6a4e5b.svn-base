
$(document).ready(function() {
	try{
	    var iframe = document.getElementById("rightFrame");
	    if(iframe.attachEvent){
	      iframe.attachEvent("onload", function(){
	        window.parent.document.body.style.height =  iframe.contentWindow.document.documentElement.scrollHeight+300;
	      });
	      return;
	    }else{
	      iframe.onload = function(){
	    	 // window.parent.document.body.style.height = iframe.contentDocument.body.scrollHeight+300;
	      };
	      return;				 
	    }	 
	  }catch(e){
	    throw new Error('setIframeHeight Error');
	  }
});
