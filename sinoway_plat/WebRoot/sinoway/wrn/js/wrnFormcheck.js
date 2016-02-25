function checkForm(){
		 if(
				 document.getElementById('prsnnam').value==''||
				 document.getElementById('prsncod').value==''||
				 document.getElementById('tel').value==''||
				 document.getElementById('loantyp').value==''||
				 document.getElementById('loanamt').value==''||
				 document.getElementById('loanamt').value==''||
				 document.getElementById('loanlmt').value==''||
				 document.getElementById('loansrtdte').value==''||
				 document.getElementById('loanenddte').value==''||
				 document.getElementById('repaytyp').value==''||
				 document.getElementById('repaydte').value==''||
				 document.getElementById('repayamt').value==''||
				 document.getElementById('repaytyp').value=='-1'
					 ) {
		  alert('信息必须输入完整！');		  
		 }
		 else{
			 document.getElementById('myform').submit();
		 }
	}