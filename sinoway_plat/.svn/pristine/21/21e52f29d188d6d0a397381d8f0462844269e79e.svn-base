/**
 * 页面元素检查工具类 银之杰科技股份有限公司
 * 
 * @Date 2012-1-24
 * @author 陈皇
 * @version 1.0.0
 */

/**
 * 判断该字符串是否为空
 * 
 * @param str
 * @returns {Boolean}
 */
function isNull(str) {
	var trimStr = $.trim(str);
	if (trimStr.length == 0) {
		return true;
	} else {
		for ( var i = 0; i < trimStr.length; i++) {
			if (trimStr.charAt(i) != " ")
				return false;
		}
		return true;
	}
}
/**
 * 判断字符串是否为电话号码
 * 
 * @param str
 *            字符串
 * @returns {Boolean}
 */
function isPhone(str){
	if (isNull(str)) {
		return false;
	} else {
		var emailRegex = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	    return emailRegex.test(str);
	}
}

/**
 * 判断该字符串是否为英文字符
 * 
 * @param str
 * @returns {Boolean}
 */
function isCharacter(str) {
	if (isNull(str)) {
		return false;
	} else {
		for ( var i = 0; i < str.length; i++) {
			var u = str.charCodeAt(i);
			if ((u > 0) && (u < 128))
				continue;
			else
				return false;
		}
	}
	return true;
}

/**
 * 判断该字符是否为字母
 * 
 * @param str
 * @returns {Boolean}
 */
function isAlpha(str) {
	if (isNull(str)) {
		return false;
	} else {
		for ( var i = 0; i < str.length; i++) {
			var mychar = str.charAt(i);
			if ((mychar < 'a' || mychar > 'z')
					&& (mychar < 'A' || mychar > 'Z'))
				return false;
		}
	}
	return true;
}

/**
 * 判断该字符串是否为数字
 * 
 * @param str
 * @returns {Boolean}
 */
function isDigits(str) {
	if (isNull(str)) {
		return false;
	} else {
		for ( var i = 0; i < str.length; i++) {
			var mychar = str.charAt(i);
			if (mychar < "0" || mychar > "9")
				return false;
		}
	}
	return true;
}

/**
 * 判断是否为中文
 * 
 * @param str
 * @returns {Boolean}
 */
function isChinese(str) {
	if (isNull(str)) {
		return false;
	}
	var regex = /[\u4e00-\u9fa5]/;
	for ( var i = 0; i < str.length; i++) {
		if (!regex.test(str.charAt(i))) {
			return false;
		}
	}
	return true;
}

/**
 * 判断是否为邮箱地址
 * 
 * @param str
 * @returns {Boolean}
 */
function isEmail(str){
	var emailRegex = /^(\w+)([\-+.][\w]+)*@(\w[\-\w]*\.){1,5}([A-Za-z]){2,4}$/;
    return emailRegex.test(str);
}

/**
 * 判断是否为字符和数字
 * 
 * @param str
 * @returns {Boolean} 全部是：true,否则：false
 */
function isAlphaAndDigits(str){
	if(isNull(str)) {
		return false;
	} else {
		var addressRegex = /[a-zA-Z\d]/;
	    for (var i = 0; i < str.length; i++) {
	        if (!addressRegex.test(str.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
    }
}

/**
 * 验证是否为通用名称：中文、字母、数字、-、_、空白、(、)、[、]
 * 
 * @param str
 * @returns {Boolean} true为通用字符，false为特殊字符，字符串不能为空
 */
function isGeneralName(str){
	if(isNull(str)) {
		return false;
	} else {
		var nameRegex = /[ \(\)\[\]_\-a-zA-Z\u4e00-\u9fa5\d]/;
	    for (var i = 0; i < str.length; i++) {
	        if (!nameRegex.test(str.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
    }
}

/**
 * 验证字符串是否为特殊字符
 * 
 * @param str
 * @returns {Boolean} true为特殊字符，false为通用字符，字符串能为空
 */
function isSpecialSymbol(str){
	if(isNull(str)) {
		return false;
	}
	var nameRegex = /[ \(\)\[\]_\-a-zA-Z\u4e00-\u9fa5\d]/;
    for (var i = 0; i < str.length; i++) {
        if (!nameRegex.test(str.charAt(i))) {
            return true;
        }
    }
    return false;
}

/**
 * 验证是否为通用密码：字母、数字、.、_、-
 * 
 * @param str
 * @returns {Boolean}
 */
function isGeneralPwd(str){
	if(isNull(str)) {
		return false;
	} else {
		var pwdRegex = /[\._\-a-zA-Z\d]/;
	    for (var i = 0; i < str.length; i++) {
	        if (!pwdRegex.test(str.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
    }
}

/**
 * 密码强度检查
 * 
 * @param password
 * @returns {Number} 密码强度分数
 */
function checkPassword(checkLeval,password) {
	var score = 0;
	if (password.length < 6) {
		return -6;
	}
	score += password.length * 5;
	if (password.match(/(.*[0-9].*[0-9].*[0-9])/)) {
		score += 5;
	}
	if (password
			.match(/(.*[!,@,#,$,%,^,&,*,?,_,~].*[!,@,#,$,%,^,&,*,?,_,~])/)) {
		score += 10;
	}
	if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) {
		score += 20;
	}
	if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) {
		score += 20;
	}
	if (password.match(/([!,@,#,$,%,^,&,*,?,_,~])/)
			&& password.match(/([0-9])/)) {
		score += 20;
	}
	if (password.match(/([!,@,#,$,%,^,&,*,?,_,~])/)
			&& password.match(/([a-zA-Z])/)) {
		score += 20;
	}
	if (password.match(/^\w+$/) || password.match(/^\d+$/)) {
		score -= 10;
	}
	if (score < 0) {
		score = 0;
	}
	if (score > 160) {
		score = 160;
	}
	score = score - (checkLeval - 3) * 15;
	return score;
}

/**
 * 判断字符串是否为半角
 * 
 * @param str
 *            字符串
 * @return true：半角；false：全角
 */
function isHalf(str){  
    for(var i=0;i<str.length;i++){     
    	var strCode=str.charCodeAt(i);     
       if((strCode>65248)||(strCode==12288)){     
           return false; 
       }   
    }   
    return true;
}

/**
 * 计算输入UTF-8字符串byte长度，
 * 
 * @param str
 *            输入字符串
 * @returns {Number} 返回长度
 */
function calcUTF8StrLength(str){
	var i = 0, len = 0;
	if (isNull(str))
		return 0;
	else {
		var charCode;
		for (i = 0; i < str.length; i++) {
			charCode = str.charCodeAt(i);
			if (charCode < 0x007f) {
				len = len + 1;
			} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
				len = len + 2;
			} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
				len = len + 3;
			}
		}
	}
	return len;
}

/**
 * 判断UTF-8字符串是否超过最大长度,byte长度
 * 
 * @param str
 *            输入字符
 * @param maxLen
 *            最大长度
 * @returns {Boolean} 大于返回true，小于等于返回false
 */
function isOutMaxLength(str,maxLen) {
	return calcUTF8StrLength(str) > maxLen;
}

/**
 * 判断是否为IPV4格式地址
 * 
 * @param str
 *            ip地址
 * @returns 是ip V4格式地址:true
 */
function isIpV4(str) {
	if (isNull(str)) {
		return false;
	} else {
		var regex = /^(([2]([0-4][0-9]|[5][0-5])|[0-1]?[0-9]?[0-9])[.]){3}(([2]([0-4][0-9]|[5][0-5])|[0-1]?[0-9]?[0-9]))$/;
		return regex.test(str);
	}
}
/**
 * 判断是否为MAC地址
 * 
 * @param str
 *            mac地址
 * @returns 是：true,否:false
 */
function isMac(str){
	if (isNull(str)) {
		return false;
	} else {
		var regex = /^([0-9a-fA-F]{2}[:-]{0,1}){5}[0-9a-fA-F]{2}$/;
		return regex.test(str);
	}
}

/**
 * 判断是否为日期格式
 * 
 * @param str
 *            日期字符串
 * @returns 是：true,否:false
 */
function isDate(str){
	if (isNull(str)) {
		return false;
	} else {
		var regex = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
		return regex.test(str);
	}
}

/**
 * 身份证号校验（弱）
 * 
 * @param str
 *            输入的身份证号
 * @returns 是：true,否:false
 */
function isIdCardNo(str){
	if (isNull(str)) {
		return false;
	} else {
		var regex = /^$|(^\d{15}$)|(^\d{17}([0-9]|(X|x))$)/;
		return regex.test(str);
	}
}

/**
 * 身份证号校验（强）
 * 
 * @param code
 *            身份证号
 * @returns {Boolean} 是：true,否:false
 */
function isIdCardNoStrongCheck(code){
	var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
    var tip = "";
    var pass= true;
    if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
        tip = "身份证号格式错误";
        pass = false;
    }else if(!city[code.substr(0,2)]){
        tip = "地址编码错误";
        pass = false;
    }else{
        // 18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            // 加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            // 校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++){
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                tip = "校验位错误";
                pass =false;
            }
        }
    }
    if(!pass){
    //	 alert(tip);
    }
    return pass;
}

