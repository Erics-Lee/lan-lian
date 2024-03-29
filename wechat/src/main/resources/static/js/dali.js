// 测试地址
/*var localhost2 = "http://192.168.43.137:8088";*/ //getURL();
/*var localhost2 = "https://www.lan-lian.com/wechat";*/

//登陆,供测试使用,部分接口登陆后才能使用
/*function login2() { 不写死，微信获取
	var result = "";
	var data = {};
	var url = localhost2 + "/login";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}*/

//登陆,供测试使用,部分接口登陆后才能使用
/*function login3() { 写死
	var result = "";
	var data = {};
	var url = localhost2 + "/login1";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
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

/*function getURL() {
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
}*/

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
		xhrFields:{withCredentials:true},
		success: function(res) {
			callback(res);
		},
		error: function(e) {}
	});
};*/

//查询可退税的国家
function selectTaxRefundCountrys() {
	var result = "";
	var data = {};
	var url = localhost + "/country/selectTaxRefundCountrys";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});

	return JSON.parse(result);
}

//查询退税国家政策
//countryNumber: 国家编码ID
function selectTaxRefundPolicy(countryNumber) {
	var result = "";
	var data = {
		countryNumber: countryNumber
	};
	var url = localhost + "/country/selectTaxRefundPolicy";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//查询帮助中心
function selectOfficialQuestionAndAnswer() {
	var result = "";
	var data = {};
	var url = localhost + "/assist/selectOfficialQuestionAndAnswer";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}
//通过问题的序号查询答案
//questionOrderNumber:问题序号
function selectOfficialAnswer(questionOrderNumber) {
	var result = "";
	var data = {
		questionOrderNumber: questionOrderNumber
	};
	var url = localhost + "/assist/selectOfficialAnswer";
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
	var url = localhost + "/company/selectCompanyLetterhead";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

/*$(document).ready(function() {
	console.log(bankAccountUpdate('', '中国建设银行', '621226.......'));
});*/

//提交银行默认转账账户
function bankAccountUpdate(bankId, bankName, bankAccount) {
	var result = "";
	var data = {
		isDelete: '0', //1为删除；0为更新或新增,默认为0
		bankCountry: '156', //国家id； 默认156中国
		bankName: bankName, //开户行
		bankId: bankId, //银行id
		bankAccount: bankAccount, //银行帐户编码
		isDefault: '1' //常规为0，设置为1的时候为默认账户，默认为1
	};
	var url = localhost + "/bank/bankAccountUpdate";
	getDataAJAX(url, "POST", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//查询默认银行
function bankAccountDefault() {
	var result = "";
	var data = {};
	var url = localhost + "/bank/bankAccountDefault";
	getDataAJAX(url, "GET", data, false , function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//启信宝接口获取公司信息
//name:公司名称关键字
function selectCompanyInfoByName(name) {
	var length = (name.toString()).length;
	if(length >= 4){
	    var result = "";
	    var data = {name: name};
	    var url = localhost+"/company/selectCompanyInfoByName";
	    getDataAJAX(url, "GET", data, false, function(resultData) {
		   result = resultData;
	    });
	    return JSON.parse(result);			
	}else{
		return '';
	}
}

/*公司获取地址和统一编号*/
function getContactInfoByName(name){
	var result = "";
    var data = {name: name};
    var url = localhost+"/company/getContactInfoByName";
    getDataAJAX(url, "GET", data, false, function(resultData) {
	   result = resultData;
    });
    return JSON.parse(result);			
}

//查询银行账户下拉列表
function bankList() {
	var result = "";
	var data = {};
	var url = localhost + "/bank/selectBank";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
		/*result = JSON.parse(resultData).data;
		for(var item in result){   //item:银行id   result[item]:银行名称
			console.log(item+"   "+result[item]);
		}*/
		/*map.put("1", "中国工商银行");
		map.put("2", "中国农业银行");
		map.put("3", "中国银行");
		map.put("4", "中国建设银行");
		map.put("5", "交通银行");
		map.put("6", "中国邮政储蓄银行");
		map.put("7", "中国民生银行");*/
	});
	return JSON.parse(result);
}

//上传发票
/*var process = 0;
$(document).ready(function() {
	console.log(login3());	
	var companyNo = selectCompanyLetterhead().data.companyno;
	var data1 = {companyno: companyNo, ImageBinaryCodes:'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACIAAAAcCAYAAAAEN20fAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAL/SURBVEhLzZdbUuswDIa7uKyGZClN0m2kadkAw9NpcylsAIYmac8DA8O99MZNR7+cGDsDb9Aez6iyZUn/lzgPbof+k6FBLi8v6fT0lI6Pj+no6OhXDRrQgmYzBOT8/JzyPKerqyt6f3+XDYyPj496psZPraFxfX0tmtDG6IAqyzJ6e3uTxF0aNKENhg5eEd4ENkC6aw9tMHRwXi8vLxIE4a796+urfDMdfDwI7NPAICCg2qdpEBwNAvvyGmS73UpQ+TkNPY8O//I665HjOIbVcck7oZ43pLnk9+hE19v+JHSoF3If75DmX+zDWyC2KZjhgBuEEx2fhBybK+HJdqJAsDcfkucgZvYwa3iOhzJ6maZBNpvN15ZycZDTLPbIi2eUBx4NZjMaMEi+yRlkQDMjtxcPGMh8g21DvdG/Ng2yXq8lIH6GZiEL8ToNBaTqt0Bc7OcUugrEqm95PESYfr8Pb4HAVquVmgOARcrEAOlXlPkAqRTIGiAxVcjF0yK/qTf6oRYgVn9jH16DINBYkyBWg5QCUmqQmEGyVaZAJF/NS3jrKEIaACT5pn+91iDL5VKCnz7lhiGl44AcP6MiUs1SBomrkkECAQlEvM7nedHUcx3ApR8/jBcVrf62t0AsK2Py/FQaOn7MwgxQLhVIWQhIyuIAgThAZM518ZjXAGFxq5fZv2Ua5Pn52bJp5JIbTdV6xDDymgNKrLyExfs01fOAzaV+oWp0Pfbq2qTrUDBq6j9NgywWCwk0PumqhouRrwAQL/rkOr40VHkJ+QcKZLGAmEP+qO4jdeo7ARAAlK/7tvQ0yNPTkwSVv6DogAXR7CCiCx1n/4dj3T7v1x9jd6ziFxG53YTzxuQLAAtOjbom3u5Xewtkn6ZBHh8f92oCgkvJ3d2dBB4eHnbu7+/v1cUI17SyLHVw176qKnVVxMU1SRK6vb2VjV0aNKEtl2dc5c/OziRQFIVs4qgaQ8FPr29ubkQLmtCWvxPyywNUeEU4L3w8v2nQgBY0m/Gf/OUk+gdIlHBhv75NoQAAAABJRU5ErkJggg=='};
	var dataList = [];
	dataList.push(data1);
	dataList.push(data1);
	dataList.push(data1);
	dataList.push(data1);
	process = setInterval(invoiceUploadProgress,1000);
	uploadInvoiceImageFiles(dataList);
		
	
});*/


//base64Codes:发票图檔Base64Code
function uploadInvoiceImageFiles(base64Codes) {
	var result = "";
	var data = {base64Codes: JSON.stringify(base64Codes)};
	var url = localhost+"/invoice/uploadInvoiceImageFiles";
	getDataAJAX2(url, "POST", data, true, function(resultData) {
	/*	result = resultData;*/
		//{"data":[{"companyno":"3127","errMessage":"储存发票错误、取消上传","imageBinaryCodes":"data:image/png;base64,"}],"code":"400"}
		//{"data":"作业成功" ,"code":"200"}
		result=(JSON.parse(resultData));
		clearInterval(process);
		if(result.code==200){
			$(".uploadMask .uploadMask1").css("display", "none");
			$(".uploadMask .uploadMask3").css("display", "none");
			$(".uploadMask .uploadMask2").css("display", "block");
			$("#uploadPhotoList .uploadPhoto").css("display", "blcok")
			$("#uploadPhotoList").children(".smallOne").remove();
			$(".uploadMask2 .maskBtn1").bind("click",function(){
				$("maskBox").css("display","none");
				$(".uploadMask .uploadMask2").css("display", "none");
				window.location.reload();
			})
			
		}else if(result.code==400){
			$(".uploadMask .uploadMask1").css("display", "none");
			$(".uploadMask .uploadMask2").css("display", "none");
			$(".uploadMask .uploadMask3").css("display", "block");
			var imgFalse=document.getElementById("imgFalse");
			console.log(result);
			var resultFalse=result.data;
			for(var i=0;i<resultFalse.length;i++){
				var ImgUpload=document.createElement("span");
				ImgUpload.innerHTML=
				'<img class="imgList2" src="'+resultFalse[i].imageBinaryCodes+'">'
				imgFalse.appendChild(ImgUpload);
			}
		}
	});
	/*return JSON.parse(result); */
}
		
//获取上传发票进度
function invoiceUploadProgress() {
	var result = "";
	var data = {};
	var url = localhost+"/invoice/invoiceUploadProgress";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		//  resultData = 1/7   代表共7张图片，当前处理到第1张
		/*console.log(resultData);*/
		var uploadSpeed=document.getElementById("uploadSpeed");
		uploadSpeed.innerHTML=resultData;
		$("#waitUpload").html("正在上传中...");
	});
}


/*倒计时*/
var countdown = 60;

//通过email发送验证码
function registerEMail(sessionKey, email) {
	console.log(1111+"   "+sessionKey);
	var obj = $("#btn");
	settime(obj);
	if(sessionKey != '') { //已经登陆成功，不需要注册了
		alert("账号已认证通过,无需输入验证码验证,请直接进行下一步");
		$("#loading").css("display", "none");
		return false;
	}
	var url = localhost + "/user/registerEMail";
	var data = {
		account: email
	};
	getDataAJAX1(url, "GET", data, true, function(resultData) {
		var code = JSON.parse(resultData).code;
		if(code == '0039') {
			$("#loading").css("display", "none");
			//用户已经存在，待通过验证码验证,验证码重新发送
			sendVerificateCode(email);
		} else if(code == '200') {
			$("#loading").css("display", "none");
			alert("验证码已发送至您的邮箱,请注意查收");
			//用户注册成功，待通过验证码验证,验证码以发送
		} else { //未知错误
			$("#loading").css("display", "none");
			alert(JSON.parse(resultData).msg);
			clearTimeout(settime);
			obj.attr('disabled', false);
			$("#btn").val("点击发送")
		}
	});
}

//重新获取验证码
function sendVerificateCode(email) {
	var url = localhost + "/user/sendVerificateCode";
	var data = {
		account: email
	};
	getDataAJAX1(url, "GET", data, true, function(resultData) {
		var code = JSON.parse(resultData).code;
		if(code == '200') {
			console.log(9);
			alert("验证码已发送至您的邮箱,请注意查收");
			//验证码发送成功
		} else { //未知错误
			/*console.log(10);*/
			alert(JSON.parse(resultData).msg);
		}
	});
}

/*添加企业的认证图片和信息*/
function addComponyLetterhead(rawcompanyname, rawaddress, compayname, englishaddress, contact, email,imageBase64,uniformnumber,representativename){
	var addCompony="";
	var data={
		rawcompanyname:rawcompanyname,
		rawaddress:rawaddress,
		compayname:compayname,
		englishaddress:englishaddress,
		contact:contact,
		email:email,
		imageBase64:imageBase64,
		uniformnumber:uniformnumber,
		representativename:representativename
	};
	var url= localhost + "/company/addCompanyLetterhead";
	getDataAJAX1(url,"POST",data,true,function(resultData){
		 addCompony = JSON.parse(resultData);
		 console.log(addCompony);
		if(addCompony.code==200){
			$("#loading").css("display","none");
			$("#maskBox").css("display", "block");
			$("#maskBox .enterprise").css("display", "block");
			$(".enterprise .enterprise1").css("display", "none");
			$("#maskBox .phoneBox5").css("display", "none");
			$(".enterprise .enterprise3").css("display", "block");
			$(".enterprise3 .photoBtn1").bind("click",function(){
				$("#maskBox").css("display", "none");
				$("#maskBox .enterprise").css("display", "none");
				$(".enterprise .enterprise3").css("display", "none");
				window.location.reload();
			})
		}else{
			$("#loading").css("display","none");
			alert(addCompony.msg);
			window.location.reload();
		}
	});
	/*return JSON.parse(result);*/
}

//加入该公司
function assignRelatedCompany(companyNo) {
	var url = localhost + "/company/assignRelatedCompany";
	var data = {
		companyno: companyNo, //公司编号
		isDelete: '0' //0:加入公司， 1:退出公司
	};
	getDataAJAX(url, "GET", data, false, function(resultData) {
		var code = JSON.parse(resultData).code;
		if(code == '200') {
			$("#loading").css("display", "none");
			$("#maskBox").css("display", "block");
			$("#maskBox .addComponyBox").css("display", "none");
			$("#maskBox .addComponyName").css("display", "block");
			$(".addComponyName .maskBtn").bind("click", function() {
					window.location.reload();
				})
				//加入公司成功
		} else { ///未知错误，公司添加失败
			$("#loading").css("display", "none");
			alert(JSON.parse(resultData).msg);
		}
	});
}
//退出该公司
function assignRelatedCompany2(companyNo) {
	var url = localhost + "/company/assignRelatedCompany";
	var data = {
		companyno: companyNo, //公司编号
		isDelete: '1' //0:加入公司， 1:退出公司
	};
	getDataAJAX(url, "GET", data, false, function(resultData) {
		var code = JSON.parse(resultData).code;
		if(code == '200') {
			$("#loading").css("display", "none");
				window.location.reload();
				//退出公司成功
		} else { ///未知错误，公司退出失败
			$("#loading").css("display", "none");
			alert(JSON.parse(resultData).msg);
		}
	});
}

function settime(obj) { //发送验证码倒计时
	if(countdown == 0) {

		obj.attr('disabled', false);
		//obj.removeattr("disabled"); 
		$("#btn").val("点击发送")
		countdown = 60;
		return;
	} else {
		obj.attr('disabled', true);
		$("#btn").val("重新发送(" + countdown + ")");
		/*$("#btn").css("background-color", "#EBEBE4")*/
		countdown--;
	}
	setTimeout(function() {
		settime(obj)
	}, 1000)
}

/*ajax用于loading的2*/
function getDataAJAX2(url, type, data, async, callback) {
	var obj = data;
	$.ajax({
		type : type,
		url : url + "?_=" + Date.parse(new Date()) / 1000,
		async : async,
		data : obj,
		datatype : "text",
		beforeSend:function(){
			$("#maskBox").css("display", "block");
			$(".uploadMask .uploadMask1").css("display", "block");
		},
		success : function(res) {
			callback(res);
		},
		complete:function(){
			$(".uploadMask .uploadMask1").css("display", "none");
		},
		error : function(e) {
		}
	});
};

