// 测试地址
var localhost = "http://www.lan-lian.com/wechat";

//首页上将此值改为true，其它页面不操作此值
var isHome = false;

//openId:微信进入时自带参数
//sessionKey:用户登陆成功时自带的参数
//用户自登陆
function automaticLogin(openId, sessionKey){
	if(openId == ''){
		alert("请从微信端进入！！！");
		return false;
	}else{
		var account = '';   //用户邮箱
		//未登录
		if(sessionKey == ''){
			//获取用户邮箱
			var data = societyQueryAccount();
			if(data.ReturnCode == '0178'){//未注册,需要登陆后才能操作则跳转，不需要的页面不操作
				if(!isHome){
					window.location.href = '注册页面';					
				}
			    return false;
			}else if(data.ReturnCode == '0000'){ //绑定了邮箱
				//获取到的邮箱
				account = JSON.parse(data.ReturnJson).account;
				var loginResult = userLogin(account);
				if(loginResult.code == 600){  //邮箱未用邮箱验证,需要登陆后才能操作则跳转，不需要的页面不操作
					if(!isHome){
						window.location.href = '注册页面?account='+account;						
					}
				    return false;
				}else if(loginResult.code == '400'){ //其它异常，理论上应该不会出现
					alert(data.msg);
					return false;
				}else if(loginResult.code == '200'){  //总算能登陆成功了
				}
			}else{   //其它异常，理论上应该不会出现
				alert(data.ReturnMessage);
				return false;					
			}
		}else{
			var companyInfo = selectCompanyLetterhead();
			//用户注册成功，但是没有公司
			if(!isHome && companyInfo.code == '200' && companyInfo.data.status == -1){
				window.location.href = '注册页面?account='+account;
				return false;
			}
		}
		return true;
	}
}


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
function login() {
	var result = "";
	var data = {};
	var url = localhost+"/login1";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//获取可操作的公司
//返回值中status说明  -1:旗下没任何公司  0:上传公司信息待审核   1:上传公司信息审核失败   2:上传公司信息审核成功   3:为该审核成功的公司的代理上传者
function selectCompanyLetterhead() {
	var result = "";
	var data = {};
	var url = localhost+"/company/selectCompanyLetterhead";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//获取openId,仅仅提供测试使用
function getOpenId() {
	var result = "";
	var data = {};
	var url = localhost+"/getOpenId";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return result;
}

//获取SessionKey,仅仅提供测试使用
function getSessionKey() {
	var result = "";
	var data = {};
	var url = localhost+"/getSessionKey";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return result;
}

//设置openId,仅仅提供测试使用
function openId(openId) {
	var result = "";
	var data = {openId: openId};
	var url = localhost+"/openId";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		var code = JSON.parse(resultData).code;
		alert(code);
		if(code == 400){
			alert("openId添加成功");		
		}else{
			alert("openId已存在");		
		}
	});
}

//获取用户邮箱
function societyQueryAccount() {
	var result = "";
	var data = {};
	var url = localhost + "/user/societyQueryAccount";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(JSON.parse(result).data);
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
		xhrFields:{withCredentials:true},
		success : function(res) {
			callback(res);
		},
		error : function(e) {
		}
	});
};