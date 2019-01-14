$(document).ready(
		function() {
			console.log(selectOfficialQuestionAndAnswer());
			console.log(selectCompanyInfoByName('蓝连财务'));
			console.log(login());
			console.log(addCompanyLetterhead('上海蓝连财务', '杨树浦路10号', '', '', '韦总', 'cblig@163.com', '13291832235', '0'));
			console.log(selectCompanyLetterheadUser());
});

//查询帮助中心
function selectOfficialQuestionAndAnswer() {
	var result = "";
	var data = {};
	var url = localhost+"/assist/selectOfficialQuestionAndAnswer";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//通过问题的序号查询答案
//questionOrderNumber:问题序号
function selectOfficialAnswer(questionOrderNumber) {
	var result = "";
	var data = {questionOrderNumber: questionOrderNumber};
	var url = localhost+"/assist/selectOfficialAnswer";
	getDataAJAX(url, "GET", data, false, function(resultData) {
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

//登陆,供测试使用,部分接口登陆后才能使用
function login() {
	var result = "";
	var data = {};
	var url = localhost+"/login";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//添加企业信息
function addCompanyLetterhead(rawcompanyname, rawaddress, compayname, englishaddress, contact, email, phone1) {
	var result = "";
	var data = {
			rawcompanyname: rawcompanyname,    //原始公司名称
			rawaddress: rawaddress,            //原始公司地址
			compayname: compayname,            //英文公司名称
			englishaddress: englishaddress,    //英文公司地址
			contact: contact,                  //联絡人
			email: email,                      //默认电子邮件
			phone1: phone1                    //默认电话
	};
	var url = localhost+"/company/addCompanyLetterhead";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}

//查詢企业信息
function selectCompanyLetterheadUser() {
	var result = "";
	var data = {};
	var url = localhost+"/company/selectCompanyLetterheadUser";
	getDataAJAX(url, "GET", data, false, function(resultData) {
		result = resultData;
	});
	return JSON.parse(result);
}