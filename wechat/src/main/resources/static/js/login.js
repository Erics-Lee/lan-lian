// 测试地址
/*var localhost2 = "https://www.lan-lian.com/wechat";*/ //getURL();
//var localhost2 = getURL();

//openId:微信进入时自带参数
//sessionKey:用户登陆成功时自带的参数
//isHome: 首页上将此值改为true，其它页面false
//用户自登陆
function automaticLogin(openId, sessionKey, isHome){
	if(openId == ''){
		alert("请从微信端进入！！！");
		return false;
	}else{
		//未登录
		if(sessionKey == ''){
			//获取用户邮箱
			var data = JSON.parse(societyQueryAccount());
			if(data.ReturnCode == '0178'){//未注册,需要登陆后才能操作则跳转，不需要的页面不操作
				if(!isHome){
					window.location.href = localhost + '/tax';					
				}
			    return false;
			}else if(data.ReturnCode == '0000'){ //绑定了邮箱
				//获取到的邮箱
				var account = data.account;
				var loginResult = userLogin(account);
				if(loginResult.code == 600){  //邮箱未用邮箱验证,需要登陆后才能操作则跳转，不需要的页面不操作
					if(!isHome){
						window.location.href = localhost + '/tax?account='+account;//'注册页面?account='+account;						
					}
				    return false;
				}else if(loginResult.code == 400){ //其它异常，理论上应该不会出现
					alert(loginResult.msg);
					return false;
				}else if(loginResult.code == 200){  //总算能登陆成功了
				}
			}else{   //其它异常，理论上应该不会出现
				alert(data.ReturnMessage);
				return false;					
			}
		}
		return true;
	}
}

var localhost = getURL();//"https://www.lan-lian.com/wechat";

function getURL(){
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： wechat/test.html
	var pathName = window.document.location.pathname;
	// 获取主机地址，如： http://localhost:8080
	var localhostPaht = curWwwPath.substring(0, curWwwPath.indexOf(pathName));
	// 获取带"/"的项目名，如：/wechat
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	// 得到了 http://localhost:8080/wechat
	var localhostHref = localhostPaht + projectName;
	return localhostHref;
}

//登陆,供测试使用,部分接口登陆后才能使用
/*function login2() {
	var result = "";
	var data = {};
	var url = localhost2+"/login";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}*/

//获取openId,仅仅提供测试使用
/*function getOpenId() {
	var result = "";
	var data = {};
	var url = localhost2+"/getOpenId";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	console.log(result);
	return result;
}*/

//获取SessionKey,仅仅提供测试使用
/*function getSessionKey() {
	var result = "";
	var data = {};
	var url = localhost2+"/getSessionKey";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return result;
}*/

//设置openId,仅仅提供测试使用
/*function openId(openId) {
	var result = "";
	var data = {openId: openId};
	var url = localhost2+"/openId";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		var code = JSON.parse(resultData).code;
		alert(code);
		if(code == 400){
			alert("openId添加成功");		
		}else{
			alert("openId已存在");		
		}
	});
}*/

//获取用户邮箱
function societyQueryAccount() {
	var result = "";
	var data = {};
	var url = localhost + "/user/societyQueryAccount";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		console.log(resultData);
		result = resultData;
	});
	return JSON.parse(result).data;
}

//正式登陆
function userLogin(account) {
	var result = "";
	var data = {account: account};
	var url = localhost+"/user/login";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//url:链接地址; 
//type:get或post请求; 
//data:请求数据参数(格式:{data1: 1, data2: 2, ... , datan: n});
//async:true为异步,false为同步;
//callback:返回结果
function getDataAJAX(url, type, data, async, callback) {
	var obj = data;
	$.ajax({
		type : type,
		url : url + "?_=" + Date.parse(new Date()) / 1000,
		async : async,
		data : obj,
		datatype : "text",
		success : function(res) {
			callback(res);
		},
		error : function(e) {
		}
	});
};

