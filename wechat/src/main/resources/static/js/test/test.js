
// 测试地址
var localhost = "http://192.168.3.15:8080"; //getURL();

$(document).ready(function() {
	console.log(selectTaxRefundCountrys());
	console.log(selectTaxRefundPolicy(36));
});

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

//查询可退税的国家
function selectTaxRefundCountrys() {
	var result = "";
	var data = {};
	var url = localhost+"/country/selectTaxRefundCountrys";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//查询退税国家政策
//countryNumber: 国家编码ID
function selectTaxRefundPolicy(countryNumber) {
	var result = "";
	var data = {countryNumber: countryNumber};
	var url = localhost+"/country/selectTaxRefundPolicy";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}