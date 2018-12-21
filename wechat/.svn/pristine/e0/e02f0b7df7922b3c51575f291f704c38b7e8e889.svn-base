//测试地址
/*var localhost = 'http://192.168.43.80:8080';*/ //getURL();
/*var localhost = "https://www.lan-lian.com/wechat";*/
/*$(document).ready(function(){
	var InvoiceTasks = [];
    var data1 = {TaskID:'827',InvoiceIDs:['828','829']};
    var data2 = {TaskID:'835',InvoiceIDs:['836']};
    var data3 = {TaskID:'837',InvoiceIDs:['838']};
    InvoiceTasks.push(data1);
    InvoiceTasks.push(data2);
    InvoiceTasks.push(data3);
    console.log(invoiceOrder(InvoiceTasks));//生成订单
    console.log(invoiceOrderQuery());

})*/



//登陆,供测试使用,部分接口登陆后才能使用
/*function login() {
	var result = "";
	var data = {};
	var url = localhost2 + "/login";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}*/

/*function getURL() {
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： wechat/test.html
	var pathName = window.document.location.pathname;
	// 获取主机地址，如： http://localhost:8080
	var localhostPaht = curWwwPath.substring(0, curWwwPath
		.indexOf(pathName));
	// 获取带"/"的项目名，如：/wechat
	var projectName = pathName.substring(0, pathName.substr(1).indexOf(
		'/') + 1);
	// 得到了 http://localhost:8080/wechat
	var localhostHref = localhostPaht + projectName;
	return localhostHref;
}*/

function getPar(par) {
    //获取当前URL
    var local_url = document.location.href;
    //	console.log(local_url);
    //获取要取得的get参数位置
    var get = local_url.indexOf(par + "=");
    if(get == -1) {
        return false;
    }
    //截取字符串
    var get_par = local_url.slice(par.length + get + 1);
    //判断截取后的字符串是否还有其他get参数
    var nextPar = get_par.indexOf("&");
    if(nextPar != -1) {
        get_par = get_par.slice(0, nextPar);
    }

    return get_par;
}

//url:链接地址;
//type:get或post请求;
//data:请求数据参数(格式:{data1: 1, data2: 2, ... , datan: n});
//async:true为异步,false为同步;
//callback:返回结果
/*function getDataAJAX(url, type, data, async, callback) {
	var obj = data;
	$.ajax({
		type: type,
		url: url + "?_=" + Date.parse(new Date()) / 1000,
		async: async,
		data: obj,
		datatype: "text",
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
		},
		success: function(res) {
			callback(res);
		},
		error: function(e) {}
	});
};*/

//个人信息查询（个人中心查询手机号）
function accountExtendProfileQuery() {
	var result = "";
	var data = {};
	var url = localhost + "/user/accountExtendProfileQuery";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//个人信息修改（修改个人手机号）
function accountExtendProfileUpdate(mobilePhoneNumber,emailValue) {
	var result = "";
	var data = {
		mobilephonenumber: mobilePhoneNumber, //手机号码
		secndemailaddress: emailValue
	};
	var url = localhost + "/user/accountExtendProfileUpdate";
	getDataAJAX(url, "POST", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//个人统计（发票数量、订单数量等）
function userDetail() {
	var result = "";
	var data = {};
	var url = localhost + "/user/userDetail";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//发票状态及数量查询
function InvoicesTasksQueryE() {
	var result = "";
	var data = {};
	var url = localhost + "/invoice/InvoicesTasksQueryE";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//发票列表
function InvoicesTasksQueryB(status,country) {
    var result = "";
    var data = {
        status:status,
        country:country
	};
    var url = localhost+"/invoice/InvoicesTasksQueryB";
    getDataAJAX(url, "GET", data, false, function(resultData) {
        result = resultData;
    });
    return JSON.parse(result);
}

//发票详情
function invoiceQuery(taskid,invoiceid) {
    var result = "";
    var data = {
        taskid:taskid,
        invoiceid:invoiceid
    };
    var url = localhost+"/invoice/invoiceQuery";
    getDataAJAX(url, "GET", data, false, function(resultData) {
        result = resultData;
    });
    return JSON.parse(result);
}
//生成订单
function invoiceOrder(InvoiceTasks) {
    var result = "";
    var data = {
        InvoiceTasks:JSON.stringify(InvoiceTasks)
    };
    var url = localhost+"/order/invoiceOrder";
    getDataAJAX(url, "POST", data, false, function(resultData) {
        result = resultData;
    });
    return JSON.parse(result);
}

//订单列表
function invoiceOrderQuery() {
    var result = "";
    var data = {};
    var url = localhost+"/order/invoiceOrderQuery";
    getDataAJAX(url, "GET", data, false, function(resultData) {
        result = resultData;
    });
    return JSON.parse(result);
}