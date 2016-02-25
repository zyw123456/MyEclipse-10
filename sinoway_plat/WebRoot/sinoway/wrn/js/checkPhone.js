function phoneNumber_checkValidate(){
	var phoneNumber= document.getElementsByName("phoneNum")[0].value
//	手机号码验证规则：1+3、4、5、6、7、8+九位随机数；
	 var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
	 if (reg.test(phoneNumber)) {
	      alert("号码正确~");
	      document.getElementsByName("phoneNum").style.borderColor="green";
	 }else{
	      alert("号码有误~");
	      document.getElementsByName("phoneNum").style.borderColor="red";
	 };
}