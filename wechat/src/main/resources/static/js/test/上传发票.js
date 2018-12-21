var process = 0;
		
$(document).ready(function() {
	/* if(automaticLogin($("#openId").html(), $("#sessionKey").html())){
				alert("可以继续下一步");
			} */
	/*<div id="ParentAppKey" style="display:none;" th:text="${parentAppKey}"></div>*/
	console.log(login());	
	var companyNo = selectCompanyLetterhead().data.companyno;
	var data1 = {companyno: companyNo, ImageBinaryCodes:'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACIAAAAcCAYAAAAEN20fAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAL/SURBVEhLzZdbUuswDIa7uKyGZClN0m2kadkAw9NpcylsAIYmac8DA8O99MZNR7+cGDsDb9Aez6iyZUn/lzgPbof+k6FBLi8v6fT0lI6Pj+no6OhXDRrQgmYzBOT8/JzyPKerqyt6f3+XDYyPj496psZPraFxfX0tmtDG6IAqyzJ6e3uTxF0aNKENhg5eEd4ENkC6aw9tMHRwXi8vLxIE4a796+urfDMdfDwI7NPAICCg2qdpEBwNAvvyGmS73UpQ+TkNPY8O//I665HjOIbVcck7oZ43pLnk9+hE19v+JHSoF3If75DmX+zDWyC2KZjhgBuEEx2fhBybK+HJdqJAsDcfkucgZvYwa3iOhzJ6maZBNpvN15ZycZDTLPbIi2eUBx4NZjMaMEi+yRlkQDMjtxcPGMh8g21DvdG/Ng2yXq8lIH6GZiEL8ToNBaTqt0Bc7OcUugrEqm95PESYfr8Pb4HAVquVmgOARcrEAOlXlPkAqRTIGiAxVcjF0yK/qTf6oRYgVn9jH16DINBYkyBWg5QCUmqQmEGyVaZAJF/NS3jrKEIaACT5pn+91iDL5VKCnz7lhiGl44AcP6MiUs1SBomrkkECAQlEvM7nedHUcx3ApR8/jBcVrf62t0AsK2Py/FQaOn7MwgxQLhVIWQhIyuIAgThAZM518ZjXAGFxq5fZv2Ua5Pn52bJp5JIbTdV6xDDymgNKrLyExfs01fOAzaV+oWp0Pfbq2qTrUDBq6j9NgywWCwk0PumqhouRrwAQL/rkOr40VHkJ+QcKZLGAmEP+qO4jdeo7ARAAlK/7tvQ0yNPTkwSVv6DogAXR7CCiCx1n/4dj3T7v1x9jd6ziFxG53YTzxuQLAAtOjbom3u5Xewtkn6ZBHh8f92oCgkvJ3d2dBB4eHnbu7+/v1cUI17SyLHVw176qKnVVxMU1SRK6vb2VjV0aNKEtl2dc5c/OziRQFIVs4qgaQ8FPr29ubkQLmtCWvxPyywNUeEU4L3w8v2nQgBY0m/Gf/OUk+gdIlHBhv75NoQAAAABJRU5ErkJggg=='};
	var data3 = {companyno: companyNo, ImageBinaryCodes:'data:image/png;base64,'};
	var dataList = [];
	dataList.push(data1);
	dataList.push(data1);
	dataList.push(data1);
	dataList.push(data1);
	dataList.push(data3);
	process = setInterval(invoiceUploadProgress,1000);
	console.log(uploadInvoiceImageFiles(dataList));				
	
});

//上传发票
//base64Codes:发票图檔Base64Code
function uploadInvoiceImageFiles(base64Codes) {
	var result = "";
	var data = {base64Codes: JSON.stringify(base64Codes)};
	var url = localhost+"/invoice/uploadInvoiceImageFiles";
	getDataAJAX(url, "POST", data, true, function(resultData) {
		result = resultData;
		console.log(result);
		//{"data":[{"companyno":"3127","errMessage":"储存发票错误、取消上传","imageBinaryCodes":"data:image/png;base64,"}],"code":"400"}
		//{"data":"作业成功" ,"code":"200"}
		console.log(JSON.parse(result));
		clearInterval(process);
	});
}
		
//获取上传发票进度
function invoiceUploadProgress() {
	var result = "";
	var data = {};
	var url = localhost+"/invoice/invoiceUploadProgress";
	getDataAJAX(url, "GET", data, true, function(resultData) {
		//  resultData = 1/7   代表共7张图片，当前处理到第1张
		console.log(resultData);
	});
}
		