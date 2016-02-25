/**
 * 删除字符前后的空格 方法
 * @param str
 * @returns
 */
function trim(str)
{ 
	if(str == null)
	{
		return str;
	}
	if(typeof(str) != "undefined")
	{
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	return str;
}

/**
 * 判断数组中是否包含某个值
 */
var contains = function(array,obj){
    var i = array.length;
    while (i--) {
        if (array[i] == obj+"") {
            return true;
        }
    }
    return false;
};

/**
 * 判断数组中是否包含指定的元素element
 * @param element
 * @returns {Boolean}
 */
Array.prototype.contain = function(element) {
	for (var index = 0; index < this.length; index++)
	{
		if (this[index] == element)
		{
			return true;
		}
	}
	return false;
};

/**
 * 元素所在位置
 * @param val
 * @returns {Number}
 */
Array.prototype.indexOf = function (val) {   
    for (var i = 0; i < this.length; i++) {   
        if (this[i] == val) {   
            return i;   
        }   
    }   
    return -1;   
}; 

/**
 * 删除数组中指定的元素element
 * @param element
 * @returns {Boolean}
 */
Array.prototype.remove = function(element){
	 for (var index = 0; index < this.length; index++) 
	 {   
	        if (this[index] == val) 
	        {   
	        	 this.splice(index, 1,"00000000");   
	        	 break;
	        }   
	   }   
};

/**
 * 自定义 map对象
 * @returns {Map}
 */
function Map(){
	 this.keys = new Array();
	 this.data = new Array();
	 
	 /**
	  * put方法
	  */
	 this.put = function(key,value){
	  if(this.data[key] == null){
	   this.keys.push(value);
	  }
	  this.data[key] = value;
	 };

	 /**
	  * get方法
	  */
	 this.get = function(key){
	  return this.data[key];
	 };

	 /**
	  * remove方法
	  */
	 this.remove = function(key){
	  this.keys.remove(key);
	  this.data[key] = null;
	 };

	 
	 /**
	  * isEmpty方法
	  */
	 this.isEmpty = function(){
	  return this.keys.length == 0;
	 };

	 /**
	  * 大小
	  */
	 this.size = function(){
		 return this.keys.length;
	 };
	 
	 this.clear = function(){
		 this.keys.length = 0;
		 this.data.length = 0;
	 };
	}

/**
 * 设置cookie
 * @param name  cookie名称
 * @param value  cookie值
 */
function setCookie(name, value)
{
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + 1 * 24 * 60 * 60 * 1000); //1天
    // 这里一定要加上path。不然到index.jsp页面去清除cookie的时候会出现找不到cookie的问题。
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString()+";path=/"; 
}

/**
 * 读取cookies函数
 * @param name
 * @returns
 */
function getCookie(name)        
{
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]); return null;

}

/**
 * 删除cookie
 * @param name cookie名称
 */
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null) document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString()+";path=/";
}

/**
 * CheckBox 文本点击事件：点击文本的时候Checkbox状态也改变
 * @param checkId
 */
function selectChkBox(checkId){
	var chkBox = document.getElementById(checkId);
	chkBox.checked =! chkBox.checked;
}

/**
 * 设备特殊工作时间设置的设备可用状态
 * 
 * @param devExWorkingTimeStatus
 * @returns {String}
 */
function getDevExWorkingTimeStatus(devExWorkingTimeStatus){
	if(devExWorkingTimeStatus == 0){
		return "设备可用";
	}else if(devExWorkingTimeStatus == 1){
		return "设备不可用";
	}else{
		return "";
	}
}


/**
 * 设备状态
 * 
 * 11：未启用在库，12：未启用在途，21：启用在库、31：停用在库、41：停用待上缴在库，42：停用待上缴在途，43：调拨停用 ，60：报废 
 * @param devStatus
 * @returns {String}
 */
function getSealDevStatus(devStatus){
	if(devStatus == 11){ return "未启用在库"; }
	if(devStatus == 12){ return "未启用在途"; }
	if(devStatus == 21){ return "启用在库"; }
	if(devStatus == 31){ return "停用在库"; }
	if(devStatus == 41){ return "停用待上缴在库"; }
	if(devStatus == 42){ return "停用待上缴在途"; }
	if(devStatus == 43){ return "调拨停用"; }
	if(devStatus == 44){ return "调拨停用在途"; }
	if(devStatus == 60){ return "报废"; }
	return "   ";
}

/**
 * 设备调拨状态
 * 
 * 10：发起调拨，11：拨入机构确认通过 12：拨入机构确认不通过   13：上级确认调拨通过，14：上级确认调拨不通过
 * @param devStatus
 * @returns {String}
 */
function getSealDevTransfStatus(devTransfStatus){
	if(devTransfStatus == 10){ return "发起调拨"; }
	if(devTransfStatus == 11){ return "拨入机构确认通过"; }
	if(devTransfStatus == 12){ return "拨入机构确认不通过"; }
	if(devTransfStatus == 13){ return "上级确认调拨通过"; }
	if(devTransfStatus == 14){ return "上级确认调拨不通过"; }
	return "";
}

/**
 * 
 * @param value
 * @returns {String}
 *  0:正在用印 1.用印完成 2.挂起未用印 3.取消用印 4.用印失败故障 5:异常(无用印图像)
 */
function  getSealStatusMsg(value){
	if(value == "0") { return "正在用印"; } 
	if(value == "1") {return "用印完成";}
	if(value == "2") {return "挂起未用印";}
	if(value == "3") { return"取消用印"; }
	if(value == "4") { return  "用印故障"; } 
	if(value == "5") { return "异常"; }  
	return  "未知状态";
}

/**
 * 图片放大缩小
 * @param imgId img标签ID
 * @param zoomType 是放大还是缩小 小于0 为缩小，大于0为放大
 */
function imgZoom(imgId,zoomType){
	var img = document.getElementById(imgId);
	var imgsrc = img.src;
	img.onload =null;//注销onload事件，防止超过100%的时候又缩小的问题。
	var width = parseInt(img.style.width.replace("%", ""));
	//如果是放大
	if(zoomType > 0 ) { 
		var w = parseInt((width  + width * 0.2));
		img.style.width = (w == width ? w + 1 : w)+ "%";
		//var marginLeft = $(img).width() / 2;
		//if(marginLeft > $(window).width() / 2) {
		//	marginLeft = $(window).width() / 2;
		//}
		
		//$(img).css("margin-left", -marginLeft);
//		if(width){
//			img.style.width =  width -0 +100;
//			img.style.height =height -0 +75;
//		}else{
//			alert("图像不允许在放大.");
//		}
	}else{
		var w = parseInt((width - width / 6));
		img.style.width = (w == 0 ? 1 : w) + "%";
		
		//var marginLeft = $(img).width() / 2;
		///if(marginLeft > $(window).width() / 2) {
		//	marginLeft = $(window).width() / 2;
		//}
		
		//$(img).css("margin-left", -marginLeft);
//		if(width > 100 ){
//			img.style.width = width - 100;
//			img.style.height = height -75; //offsetTop
//		}else{
//			alert("图像不允许在缩小.");
//		}
	}
	img.src =imgsrc;
}



var total_myangle=0;

/**
 * 图片旋转
 * @param pic_id 图片ID
 * @param angle 旋转角度 90 180 -90
 * @returns {rotationPicture}
 */
function rotationPicture(pic_id, angle){
	if (angle != 361){
		total_myangle += angle;
	}else{
		total_myangle = 0;
	}
	total_myangle = total_myangle % 360;
	this.angle = total_myangle;
	if (this.angle >= 0){
		var rotation = Math.PI * this.angle / 180;
	}
	else{
		var rotation = Math.PI * (360 + this.angle) / 180;
	}
	var costheta = Math.cos(rotation);
	var sintheta = Math.sin(rotation);
	if (document.all && !window.opera) {
		var canvas = document.createElement('img');
		canvas.style.position = "absolute";
		canvas.style.border= 0;
		canvas.style.padding= 0;
		canvas.style.margin= 0;
		canvas.style.left= 0;
		canvas.style.top= 0;
		canvas.src = pic_id.src;
		canvas.style.height = pic_id.style.height;
		canvas.style.width = pic_id.style.width;
		canvas.height = pic_id.style.height;
		canvas.width = pic_id.style.width;
		canvas.style.filter = "progid:DXImageTransform.Microsoft.Matrix(M11=" + costheta + ",M12=" + (-sintheta) + ",M21=" + sintheta + ",M22=" + costheta + ",SizingMethod='auto expand')";
	}else{
		var canvas = document.createElement('canvas');
		if (!pic_id.oImage){
			canvas.oImage = new Image();
			canvas.oImage.src = pic_id.src;
		}else {
			canvas.oImage = pic_id.oImage;
		}
		canvas.style.width = canvas.width = Math.abs(costheta * canvas.oImage.width) + Math.abs(sintheta * canvas.oImage.height);
		canvas.style.height = canvas.height = Math.abs(costheta * canvas.oImage.height) + Math.abs(sintheta * canvas.oImage.width);
		var context = canvas.getContext('2d');
		context.save();
		if (rotation <= Math.PI / 2) {
			context.translate(sintheta * canvas.oImage.height, 0);
		} else if (rotation <= Math.PI) {
			context.translate(canvas.width, -costheta * canvas.oImage.height);
		}else if (rotation <= 1.5 * Math.PI){
			context.translate(-costheta * canvas.oImage.width, canvas.height);
		} else{
			context.translate(0, -sintheta * canvas.oImage.width);
		}
		context.rotate(rotation);
		context.drawImage(canvas.oImage, 0, 0, canvas.oImage.width, canvas.oImage.height);
		context.restore();
	}
	canvas.id = pic_id.id;
	canvas.angle = pic_id.angle;
	pic_id.parentNode.replaceChild(canvas, pic_id);
}

function Serialize(obj,objName){
    switch(typeof(obj)){
        case "object":
            var str = "{";
            for(var o in obj){
                str +="\""+objName+"."+o + "\":" + Serialize(obj[o],null) +",";
            }
            if(str.substr(str.length-1) == ",")
                str = str.substr(0,str.length -1);
            return str + "}";
            break;
        case "array":            
            var str = "[";
            for(var o in obj){
                str += Serialize(obj[o],null) +",";
            }
            if(str.substr(str.length-1) == ",")
                str = str.substr(0,str.length -1);
            return str + "]";
            break;
        case "boolean":
            return "\"" + obj.toString() + "\"";
            break;
        case "date":
            return "\"" + obj.toString() + "\"";
            break;
        case "function":
            break;
        case "number":
            return "\"" + obj.toString() + "\"";
            break; 
        case "string":
            return "\"" + obj.toString() + "\"";
            break;    
    }
}

/**    
 * 对Date的扩展，将 Date 转化为指定格式的String    
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符    
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)    
 * eg:    
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423    
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04    
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04    
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04    
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18    
 */     
Date.prototype.pattern=function(fmt) {      
    var o = {      
    "M+" : this.getMonth()+1, //月份      
    "d+" : this.getDate(), //日      
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时      
    "H+" : this.getHours(), //小时      
    "m+" : this.getMinutes(), //分      
    "s+" : this.getSeconds(), //秒      
    "q+" : Math.floor((this.getMonth()+3)/3), //季度      
    "S" : this.getMilliseconds() //毫秒      
    };      
    var week = {      
    "0" : "/u65e5",      
    "1" : "/u4e00",      
    "2" : "/u4e8c",      
    "3" : "/u4e09",      
    "4" : "/u56db",      
    "5" : "/u4e94",      
    "6" : "/u516d"     
    };      
    if(/(y+)/.test(fmt)){      
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));      
    }      
    if(/(E+)/.test(fmt)){      
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);      
    }      
    for(var k in o){      
        if(new RegExp("("+ k +")").test(fmt)){      
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));      
        }      
    }      
    return fmt;      
}    


function getEod(yt,date){  
    if(date){  
        var a_date=date.split("-");  
        date=new Date(a_date[0],a_date[1]-1,a_date[2]);  
    }else{  
        date=new Date();  
    }  
    var i_milliseconds=date.getTime();  
    if(yt=="y"){  
        i_milliseconds-=1000*60*60*24;  
    }else{  
        i_milliseconds+=1000*60*60*24;  
    }  
    var t_date = new Date();  
    t_date.setTime(i_milliseconds);  
    var i_year = t_date.getFullYear();  
    var i_month = ("0"+(t_date.getMonth()+1)).slice(-2);  
    var i_day = ("0"+t_date.getDate()).slice(-2);  
    return i_year+"-"+i_month+"-"+i_day;  
} 