$(document).ready(function() {
	$("#footerList .footer1").bind("click", function() {
		//window.location.href = "index.html"
		window.location.href = localhost + '/index';//"index.html"
	})
	$("#footerList .footer2").bind("click", function() {
		window.location.href = localhost + '/tax';//"tax.html"
	})

	var result = 0;
	$(".invoiceInformation1 div").bind("click", function() {
		if($(this).hasClass("invoiceInformation1Div")) {
			$(this).addClass("active11");
			$(this).removeClass("invoiceInformation1Div");
			$("#invoiceInfor2Btn1Div").addClass("active12");

			var index = $(".invoiceInformation1 div").index(this);
			var st1 = $(".invoiceInformation1p .money").eq(index).html();
			var st2 = $(".invoiceInfor2Btn1 .moneyAll").html();

			result = Number(st1) + Number(st2);
			$(".moneyAll").html(result);
			var moneyAll = parseInt($(".moneyAll").html());
			var invoiceInfor3 = invoiceInfor.data.YearLimitAmt;
			if(moneyAll >= yearLimitAmt) {
				console.log(moneyAll);
				console.log(yearLimitAmt);
				$(".invoiceInfor2Btn2").addClass("active13");
			} else {
				console.log(moneyAll);
				console.log(yearLimitAmt);
				$(".invoiceInfor2Btn2").removeClass("active13");
			}

		} else {
			$(this).removeClass("active11");
			$(this).addClass("invoiceInformation1Div");
			var index = $(".invoiceInformation1 div").index(this);
			var st1 = $(".invoiceInformation1p .money").eq(index).html();
			var st2 = $(".invoiceInfor2Btn1 .moneyAll").html();
			result = Number(st2) - Number(st1);
			$(".moneyAll").html(result);
		}
		var invoiceInformation1Div = $(".invoiceInformation1 div");
		for(var i = 0; i < invoiceInformation1Div.length; i++) {
			if($(".invoiceInformation1 div").hasClass("active11")) {
				$("#invoiceInfor2Btn1Div").addClass("active12");
				$("#invoiceInfor2Btn1Div").removeClass("invoiceInfor2Btn1Div");
			} else {
				$("#invoiceInfor2Btn1Div").removeClass("active12");
				$("#invoiceInfor2Btn1Div").addClass("invoiceInfor2Btn1Div");
			}
		}
	})
	$("#invoiceInfor2Btn1Div").bind("click", function() {
		if($("#invoiceInfor2Btn1Div").hasClass("active12")) {
			$(".invoiceInformation1 div").removeClass("active11");
			$("#invoiceInfor2Btn1Div").removeClass("active12");
			$(".invoiceInformation1 div").addClass("invoiceInformation1Div");
			$("#invoiceInfor2Btn1Div").addClass("invoiceInfor2Btn1Div");
			var num = 0;
			$(".moneyAll").html(num);
		} else {
			var result = 0;
			$("#invoiceInfor2Btn1Div").addClass("active12");
			$(".invoiceInformation1 div").addClass("active11");
			$(".invoiceInformation1 div").removeClass("invoiceInformation1Div");
			$(".invoiceInformation1p .money").each(function() {
				var value = $(this).html();
				result += Number(value);
				$(".moneyAll").html(result);
			})
		}
	})

	/*顶部的切换*/
	$(".taxStepBtn").bind("click", function() {
		$(".taxStepBottom").hide();
		$(this).parent(".taxStepList").addClass("active3").siblings().removeClass("active3");

		var index = $(this).parent(".taxStepList").index();
		$(".insetComponyInfor5").eq(index).show().siblings().hide();
		$(this).next(".taxStepBottom").show();

	})

	
	var shouquan = document.getElementById("shouquan");
	var addComponyBtn = document.getElementById("addComponyBtn");
	var cantDo = document.getElementById("cantDo");
	var cantDo1 = document.getElementById("cantDo1");
	$("#shouquan").bind("click", function() {
		if(shouquan.className == 'shouquan') {
			shouquan.className = "active2";
			cantDo.disabled = true;
			cantDo1.disabled = true;
			$(".enCompony .title2 input").css("background-color", "#EBEBE4");
			if(sessionKey !== "") {
				if($(".componyInfo0")[0].value !== "" && $(".componyInfo1")[0].value !== "" && $(".componyInfo2")[0].value !== "" && $(".componyInfo4")[0].value !== "") {
					addComponyBtn.className = "active";
				} else {
					$(".componyInfo0")[0].oninput = function() {
						if($(".componyInfo1")[0].value !== "" && $(".componyInfo2")[0].value !== "" && $(".componyInfo4")[0].value !== "") {
							addComponyBtn.className = "active";
						}
					}
					$(".componyInfo1")[0].oninput = function() {
						if($(".componyInfo0")[0].value !== "" && $(".componyInfo2")[0].value !== "" && $(".componyInfo4")[0].value !== "") {
							addComponyBtn.className = "active";
						}
					}
					$(".componyInfo2")[0].oninput = function() {
						if($(".componyInfo0")[0].value !== "" && $(".componyInfo1")[0].value !== "" && $(".componyInfo4")[0].value !== "") {
							addComponyBtn.className = "active";
						}
					}
					$(".componyInfo4")[0].oninput = function() {
						if($(".componyInfo0")[0].value !== "" && $(".componyInfo1")[0].value !== "" && $(".componyInfo2")[0].value !== "") {
							addComponyBtn.className = "active";
						}
					}
				}
			} else {
				if($(".componyInfo0")[0].value !== "" && $(".componyInfo1")[0].value !== "" && $(".componyInfo2")[0].value !== "" && $(".componyInfo7")[0].value !== "" && $(".componyInfo4")[0].value !== "") {
					addComponyBtn.className = "active";
				} else {
					$(".componyInfo0")[0].oninput = function() {
						if($(".componyInfo1")[0].value !== "" && $(".componyInfo2")[0].value !== "" && $(".componyInfo7")[0].value !== "" && $(".componyInfo4")[0].value !== "") {
							addComponyBtn.className = "active";
						}
					}
					$(".componyInfo1")[0].oninput = function() {
						if($(".componyInfo0")[0].value !== "" && $(".componyInfo2")[0].value !== "" && $(".componyInfo7")[0].value !== "" && $(".componyInfo4")[0].value !== "") {
							addComponyBtn.className = "active";
						}
					}
					$(".componyInfo2")[0].oninput = function() {
						if($(".componyInfo0")[0].value !== "" && $(".componyInfo1")[0].value !== "" && $(".componyInfo7")[0].value !== "" && $(".componyInfo4")[0].value !== "") {
							addComponyBtn.className = "active";
						}
					}
					$(".componyInfo7")[0].oninput = function() {
						if($(".componyInfo0")[0].value !== "" && $(".componyInfo1")[0].value !== "" && $(".componyInfo2")[0].value !== "" && $(".componyInfo4")[0].value !== "") {
							addComponyBtn.className = "active";
						}
					}
					$(".componyInfo4")[0].oninput = function() {
						if($(".componyInfo0")[0].value !== "" && $(".componyInfo1")[0].value !== "" && $(".componyInfo2")[0].value !== "" && $(".componyInfo7")[0].value !== "") {
							addComponyBtn.className = "active";
						}
					}
				}
			}

		} else {
			shouquan.className = "shouquan";
			cantDo.disabled = false;
			cantDo1.disabled = false;
			$(".enCompony .title2 input").css("background-color", "#eef2f4");
			if($(".componyInfo5")[0].value == "" && $(".componyInfo6")[0].value == "") {
				addComponyBtn.className = "createCompony";
			}
		}
	})

	/*发票上传*/
	var $list = $("#uploadPhotoList");
	var uploader2 = WebUploader.create({
		auto: false,
		swf: 'js/Uploader.swf',
		server: '',
		pick: '#filePicker2',
		fileNumLimit: 8,
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		},
		resize: false, //尺寸不改变
		method: 'POST',
	});
	$("#filePicker2").show();
	uploader2.refresh();
	uploader2.on('fileQueued', function(file) {
		$("#maskBox").css("display", "none");
		$("#maskBox .uploadMask2").css("display", "none");
		var $li = $(
				'<div id="' + file.id + '" class="file-item thumbnail smallOne" style="z-index:2;float: left;margin: 20px 0 0 13px;width: 28%;height: 140px;border: 1px solid #d6d6d6;">' +

				'<img class="imglist" style="width:100%;height:100%;">' +

				'<img class="imgDelete" src="img/delete.png" style="width: 20px;height20px;position: absolute;top:5px;right: 5px;">' +

				'<div class="info">' + file.name + '</div>' +

				'</div>'
			),

			$img = $li.find('.imglist');
		$list.append($li);

		uploader2.makeThumb(file, function(error, src) {
			if(error) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}
			$img.attr('src', src);

		}, 1, 1);

		var Img = $("#uploadPhotoList .smallOne");
		var uploadFooter = document.getElementById("uploadFooter");
		/*var process = 0;*/
		for(var i = 0; i < Img.length; i++) {
			if(Img.length > 0) {
				uploadFooter.className = "active6";

			}
			if(Img.length >= 9) {
				$("#uploadPhotoList .uploadPhoto").css("display", "none");
				alert("单次图片最大上传量为9张")
			}
		}
		/*$(".active6").bind("click", function() {
					$(".uploadMask .uploadMask1").css("display", "block");
					var companyNo = selectCompanyLetterhead().data.companyno;
					var pushList = [];

					var img = document.getElementsByClassName("imglist");
					for(var i = 0; i < img.length; i++) {
						var imgList = {};
						imgList["companyno"] = companyNo;
						imgList["ImageBinaryCodes"] = img[i].src;
		
						pushList.push(imgList);
						console.log(pushList);
		
					}
					uploadInvoiceImageFiles(pushList, companyNo);
					
				})*/
		$(document).on("click", ".imgDelete", function(file) {
			var Img = $("#uploadPhotoList .smallOne");
			$(this).parent().remove();
			/*for(var i = 0; i < Img.length; i++) {
				if(Img.length < 9) {
					$("#uploadPhotoList .uploadPhoto").css("display", "block")
				}
			}*/

		})

	})
	var $list = $("#uploadPhotoList");
	var uploader = WebUploader.create({
		auto: false,
		swf: 'js/Uploader.swf',
		server: '',
		pick: '#filePicker',
		fileNumLimit: 9,
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		},
		resize: false, //尺寸不改变
		method: 'POST',
	});
	$("#filePicker").show();
	uploader.refresh();
	uploader.on('fileQueued', function(file) {
		var $li = $(
				'<div id="' + file.id + '" class="file-item thumbnail smallOne" style="z-index:2;float: left;margin: 20px 0 0 13px;width: 28%;height: 140px;border: 1px solid #d6d6d6;">' +

				'<img class="imglist" style="width:100%;height:100%;">' +

				'<img class="imgDelete" src="img/delete.png" style="width: 20px;height20px;position: absolute;top:5px;right: 5px;">' +

				'<div class="info">' + file.name + '</div>' +

				'</div>'
			),

			$img = $li.find('.imglist');
		$list.append($li);

		uploader.makeThumb(file, function(error, src) {
			if(error) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}
			$img.attr('src', src);

		}, 1, 1);

		$(document).on("click", ".imgDelete", function(file) {
			var Img = $("#uploadPhotoList .smallOne");
			$(this).parent().remove();
			/*for(var i = 0; i < Img.length; i++) {
				if(Img.length < 9) {
					$("#uploadPhotoList .uploadPhoto").css("display", "block");
				
					uploader.refresh();
				}
			}*/

		})
		var Img = $("#uploadPhotoList .smallOne");
		var uploadFooter = document.getElementById("uploadFooter");

		for(var i = 0; i < Img.length; i++) {
			if(Img.length > 0) {
				uploadFooter.className = "active6";

			}
			if(Img.length >= 9) {
				$("#uploadPhotoList .uploadPhoto").css("display", "none");
				alert("单次图片最大上传量为9张")
			}
		}
		var process = 0;

	})
	$("#uploadFooter").bind("click", function() {
		if($(this).hasClass("active6")) {
			console.log(2552);
			$("#maskBox").css("display", "block");
			$(".uploadMask .uploadMask1").css("display", "block");
			var companyNo = selectCompanyLetterhead().data.companyno;
			var pushList = [];

			var img = document.getElementsByClassName("imglist");
			for(var i = 0; i < img.length; i++) {
				var imgList = {};
				imgList["companyno"] = companyNo;
				imgList["ImageBinaryCodes"] = img[i].src;

				pushList.push(imgList);
				/*console.log(pushList);*/

			}
			process = setInterval(invoiceUploadProgress, 1000);
			uploadInvoiceImageFiles(pushList, companyNo);
		} else {
			alert("没有图片可以上传")
		}
	})

	
	$(".uploadMask3 .maskBtn1").bind("click", function() {
		$("#maskBox").css("display", "none");
		$(".uploadMask .uploadMask3").css("display", "none");
		window.location.reload();
	})

	/*企业审核部分的上传*/
	var $list3 = $("#uploadPhotoList3");
	var uploader3 = WebUploader.create({
		auto: false,
		swf: 'js/Uploader.swf',
		server: '',
		pick: '#filePicker3',
		fileNumLimit: 1,
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		},
		resize: false, //尺寸不改变
		method: 'POST',
	});
	$("#filePicker3").show();
	uploader3.refresh();
	uploader3.on('fileQueued', function(file) {
		/*$("#maskBox").css("display", "none");*/
		$("#maskBox #filePicker3").css("display", "none");
		var $li2 = $(
				'<div id="' + file.id + '" class="file-item thumbnail smallOne" style="z-index:2;width: 100%;height: 100%;margin:0;">' +

				'<img id="imglist" class="imglist" style="width:100%;height:100%;">' +
				
				'<div class="info">' + file.name + '</div>' +

				'</div>'
			),

			$img = $li2.find('.imglist');
		$list3.append($li2);

		uploader3.makeThumb(file, function(error, src) {
			if(error) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}
			$img.attr('src', src);

		}, 1, 1);

		var Img3 = $("#uploadPhotoList3 .smallOne");
		var photoBtn1 = document.getElementById("photoBtn1");
		/*var process = 0;*/
		for(var i = 0; i < Img3.length; i++) {
			if(Img3.length > 0) {
				if($("#creditCode").val()!==""){
					photoBtn1.className = "active15";
				}
			}
			
		}
	})
	$(".creditCode")[0].oninput=function(){
		if($("#imglist").attr("src")!==undefined){
			photoBtn1.className = "active15";
		}
	}
	
	/*退税订单部分*/
	/*三个按钮*/
	$(".orderInfor4 .orderInforBtn1").bind("click", function() {
		$("#maskBox").css("display", "block");
		$("#maskBox .phoneBox5").css("display", "block");
	})
	$(".phoneBox5 .maskBtn1").bind("click", function() {
		$("#maskBox").css("display", "block");
		$("#maskBox .phoneBox5").css("display", "none");
		$("#maskBox .phoneBox6").css("display", "block");
	})
	$(".orderInfor4 .orderInforBtn2").bind("click", function() {
		$("#maskBox").css("display", "block");
		$("#maskBox .stepBox").css("display", "block");
	})

	/*找专家*/
	$(".orderList .found").bind("click", function() {
		$("#maskBox").css("display", "block");
		$("#maskBox .phoneBox7").css("display", "block");
	})
	$(".phoneBox7 .maskBtn1").bind("click", function() {
		$("#maskBox").css("display", "block");
		$("#maskBox .phoneBox7").css("display", "none");
		$("#maskBox .phoneBox8").css("display", "block");
	})
	$(".phoneBox7 .maskBtn2").bind("click", function() {
		$("#maskBox").css("display", "none");
		$("#maskBox .phoneBox7").css("display", "none");
		/*$("#maskBox .phoneBox8").css("display","none");*/
	})
	$(".phoneBox8 .maskBtn1").bind("click", function() {
			$("#maskBox").css("display", "none");
			$("#maskBox .phoneBox7").css("display", "none");
			$("#maskBox .phoneBox8").css("display", "none");
		})
		/*三个按钮关闭*/
	$(".phoneBox5 .maskBtn2").bind("click", function() {
		$("#maskBox").css("display", "none");
		$("#maskBox .phoneBox5").css("display", "none");
	})
	$(".phoneBox6 .maskBtn1").bind("click", function() {
		$("#maskBox").css("display", "none");
		$("#maskBox .phoneBox5").css("display", "none");
		$("#maskBox .phoneBox6").css("display", "none");
	})

	$(".stepBox .stepListBtn").bind("click", function() {
		$("#maskBox").css("display", "none");
		$("#maskBox .stepBox").css("display", "none");
	})
	$(".bankBoxBtn .bankBoxBtn2").bind("click", function() {
		$("#maskBox").css("display", "none");
		$("#maskBox .bankBox").css("display", "none");
	})

	/*ajax*/
	/*发票列表*/

	var invoice = InvoicesTasksQueryE();
	console.log(invoice);
	if(invoice.code == 200) {
		/*$("#loading").css("display", "none");*/
		var secondData = invoice.data;
		var invoiceListBox = document.getElementById("invoiceListBox");
		var country;
		var srcAdress;
		var currencysymbol;
		var tax;
		for(var i = 0; i < secondData.length; i++) {

			if(!secondData[i].country) {
				country = "";
				srcAdress = "";
				currencysymbol = "";
				tax = "";
			} else if(secondData[i].country == 36) {
				country = "澳大利亚";
				srcAdress = "img/36.png";
				currencysymbol = "S/.";
				tax = 500;
			} else if(secondData[i].country == 40) {
				country = "奥地利";
				srcAdress = "img/40.png";
				currencysymbol = "S";
				tax = 50;
			} else if(secondData[i].country == 56) {
				country = "比利时";
				srcAdress = "img/56.png";
				currencysymbol = "Bi.";
				tax = 25;
			} else if(secondData[i].country == 124) {
				country = "加拿大";
				srcAdress = "img/124.png";
				currencysymbol = "$";
				tax = 200;
			} else if(secondData[i].country == 158) {
				country = "中国台湾";
				srcAdress = "img/158.png";
				currencysymbol = "NT$";
				tax = 5000;
			} else if(secondData[i].country == 208) {
				country = "丹麦";
				srcAdress = "img/208.png";
				currencysymbol = "kr";
				tax = 400;
			} else if(secondData[i].country == 246) {
				country = "芬兰";
				srcAdress = "img/246.png";
				currencysymbol = "MK.";
				tax = 40;
			} else if(secondData[i].country == 250) {
				country = "法国";
				srcAdress = "img/250.png";
				currencysymbol = "F";
				tax = 50;
			} else if(secondData[i].country == 276) {
				country = "德国";
				srcAdress = "img/276.png";
				currencysymbol = "DM";
				tax = 500;
			} else if(secondData[i].country == 352) {
				country = "冰岛";
				srcAdress = "img/352.png";
				currencysymbol = "I.";
				tax = 12700;
			} else if(secondData[i].country == 372) {
				country = "爱尔兰";
				srcAdress = "img/372.png";
				currencysymbol = "￡";
				tax = 25;
			} else if(secondData[i].country == 380) {
				country = "意大利";
				srcAdress = "img/380.png";
				currencysymbol = "₤";
				tax = 50;
			} else if(secondData[i].country == 392) {
				country = "日本";
				srcAdress = "img/392.png";
				currencysymbol = "¥";
				tax = 1000;
			} else if(secondData[i].country == 410) {
				country = "韩国";
				srcAdress = "img/410.png";
				currencysymbol = "₩";
				tax = 300000;
			} else if(secondData[i].country == 470) {
				country = "马耳他";
				srcAdress = "img/470.png";
				currencysymbol = "₤";
				tax = 23;
			} else if(secondData[i].country == 528) {
				country = "荷兰";
				srcAdress = "img/528.png";
				currencysymbol = "€";
				tax = 50;
			} else if(secondData[i].country == 554) {
				country = "新西兰";
				srcAdress = "img/554.png";
				currencysymbol = "$";
				tax = 500;
			} else if(secondData[i].country == 578) {
				country = "挪威";
				srcAdress = "img/578.png";
				currencysymbol = "kr";
				tax = 200;
			} else if(secondData[i].country == 724) {
				country = "西班牙";
				srcAdress = "img/724.png";
				currencysymbol = "€";
				tax = 50;
			} else if(secondData[i].country == 752) {
				country = "瑞典";
				srcAdress = "img/752.png";
				currencysymbol = "kr";
				tax = 500;
			} else if(secondData[i].country == 756) {
				country = "瑞士";
				srcAdress = "img/756.png";
				currencysymbol = "Fr";
				tax = 250;
			} else if(secondData[i].country == 826) {
				country = "英国";
				srcAdress = "img/826.png";
				currencysymbol = "£";
				tax = 16;
			}

			if(secondData[i].status == 0) {
				var inviceList1 = document.createElement("div");
				inviceList1.className = "inviceList1";
				inviceList1.innerHTML =
					'<p>审核中的发票</p>\
					<p class="shenHe">' + secondData[i].length + '张</p>\
					<input type="hidden" name="statusId" value="' + secondData[i].status + '"/>\
					<img src="img/rightArr2.png" />'
				invoiceListBox.appendChild(inviceList1);

			}
			if(secondData[i].status == 1) {
				var ableInviceBottom1 = 0;
				var tax2 = secondData[i].allInvoiceTaxPrice;
				var tax1 = secondData[i].yearLimitAmt;
				var percenter;
				var ableInvoice;
				percenter = (Math.round(tax2 / tax1 * 10000) / 100.00);
				if(parseInt(tax2) >= parseInt(tax1)) {
					ableInvoice = "active14";
				} else {
					ableInvoice = "ableInviceBottom2";
				}
				if(percenter >= 100) {
					percenter = 100;
				};
				var percenter2 = percenter + "%";
				console.log(percenter2);
				var inviceList2 = document.createElement("ul");
				inviceList2.className = "inviceList2";
				inviceList2.innerHTML =
					'<li class="ableInvice">\
						<div class="ableInvicetop">\
							<img class="ableInvoiceImg" src="' + srcAdress + '" />\
							<p class="ableInvoicep">' + country + '</p>\
							<p class="ableInvoicep2">可退税金：' + currencysymbol + '' + secondData[i].allInvoiceTaxPrice + '</p>\
							<input type="hidden" class="countryId1" name="countryId" value="' + secondData[i].country + '"/>\
							<input type="hidden" class="statusId1" name="statusId" value="' + secondData[i].status + '"/>\
							<input type="hidden" class="yearLimitAmt" name="yearLimitAmt" value="' + secondData[i].yearLimitAmt + '"/>\
							<img class="ableInvoiceImg2" src="img/rightArr2.png" />\
						</div>\
						<div class="ableInviceBottom">\
							<div class="ableInviceBottom1">\
								<div style="width:' + percenter2 + '"></div>\
								<p>起退税金为：' + secondData[i].yearLimitAmt + '</p>\
							</div>\
							<div class="' + ableInvoice + '">\
							<input type="hidden" name="countryId" value="' + secondData[i].country + '"/>\
							<input type="hidden" name="statusId" value="' + secondData[i].status + '"/>\
							申请退税\
							</div>\
						</div>\
					</li>'
				invoiceListBox.appendChild(inviceList2);

			}
			if(secondData[i].status == 2) {
				var inviceList3 = document.createElement("div");
				inviceList3.className = "inviceList3";
				inviceList3.innerHTML =
					'<p>不可退发票</p>\
					<p class="shenHe2">' + secondData[i].length + '张</p>\
					<input type="hidden" name="statusId" value="' + secondData[i].status + '"/>\
					<img src="img/rightArr2.png" />'
				invoiceListBox.appendChild(inviceList3);

			}
		}
		/*未审核的点击查看*/
		$(".inviceListBox .inviceList1").bind("click", function() {
			$("#loading").css("display", "block");
				$(".inviceListBox").css("display", "none");
				$(".insetComponyInfor3 .invoiceInfor").css("display", "block");
				$(".invoiceInfor .invoiceInfor1").css("display", "block");
				$(".invoiceInfor .invoiceInfor2").css("display", "none");
				$(".invoiceInfor .invoiceInfor3").css("display", "none");
				var status = $(this).find('[name="statusId"]').val();
				var country = "";
				var invoiceInfor = InvoicesTasksQueryB(status, country);
				console.log(invoiceInfor);
				var invoiceInfor11 = document.getElementById("invoiceInfor11");
				invoiceInfor11.innerHTML="";
				if(invoiceInfor.code == 200) {
					$("#loading").css("display", "none");
					if(invoiceInfor.data == "数据为空！") {
						alert("暂无数据");
					} else {
						var secondData = invoiceInfor.data.InvoicesTasksQuery;
						for(var i = 0; i < secondData.length; i++) {
							var thirdData = secondData[i].invoices;
							for(var j = 0; j < thirdData.length; j++) {
								var invoiceInforList = document.createElement("li");
								invoiceInforList.innerHTML =
									'<p>' + thirdData[j].id + '</p>\
											<img src="img/rightArr2.png" />\
											<input type="hidden" name="taskid" value="' + secondData[i].id + '"/>\
											<input type="hidden" name="invoiceid" value="' + thirdData[j].id + '"/>'

								invoiceInfor11.appendChild(invoiceInforList);
							}
						}

						$("#invoiceInfor11 li").bind("click", function() {
							var taskid = $(this).find('[name="taskid"]').val();
							var invoiceid = $(this).find('[name="invoiceid"]').val();
							//"invoiceDetail.html?taskid=" + taskid + '&invoiceid=' + invoiceid;
							window.location.href = localhost + "/invoiceDetail?taskid=" + taskid + '&invoiceid=' + invoiceid;

						})
					}

				} else {
					//alert("网络错误")
				}

			})
			/*生成的发票列表点击查看*/
		$(".inviceListBox .ableInvicetop").bind("touchend", function() {
			$("#loading").css("display", "block");
			$(".inviceListBox").css("display", "none");
			$(".insetComponyInfor3 .invoiceInfor").css("display", "block");
			$(".invoiceInfor .invoiceInfor2").css("display", "block");
			$(".invoiceInfor .invoiceInfor1").css("display", "none");
			$(".invoiceInfor .invoiceInfor3").css("display", "none");
			var status = $(this).find('[name="statusId"]').val();
			var country = $(this).find('[name="countryId"]').val();
			var yearLimitAmt= $(this).find('[name="yearLimitAmt"]').val();
			var invoiceInfor = InvoicesTasksQueryB(status, country);
			console.log(invoiceInfor);
			var invoiceInfor2 = invoiceInfor.data.InvoicesTasksQuery;
			var invoiceInfor2Top = document.getElementById("invoiceInfor2Top");
			var invoiceInformation = document.getElementById("invoiceInformation");
			invoiceInformation.innerHTML = "";
			var country;
			var currencysymbol;
			var taxMoney = 0;
			for(var i = 0; i < invoiceInfor2.length; i++) {
				$("#loading").css("display", "none");
				var invoiceInfor3 = invoiceInfor2[i].invoices;
				for(var j = 0; j < invoiceInfor3.length; j++) {
					var taxMoney1 = parseFloat(invoiceInfor3[j].invoiceTaxPrice);
					taxMoney += taxMoney1;

					if(!invoiceInfor3[j].country) {
						country = "";
						currencysymbol = "";
					} else if(invoiceInfor3[j].country == 36) {
						country = "澳大利亚";
						currencysymbol = "S/.";
					} else if(invoiceInfor3[j].country == 40) {
						country = "奥地利";
						currencysymbol = "S";
					} else if(invoiceInfor3[j].country == 56) {
						country = "比利时";
						currencysymbol = "Bi.";
					} else if(invoiceInfor3[j].country == 124) {
						country = "加拿大";
						currencysymbol = "$";
					} else if(invoiceInfor3[j].country == 158) {
						country = "中国台湾";
						currencysymbol = "NT$";
					} else if(invoiceInfor3[j].country == 208) {
						country = "丹麦";
						currencysymbol = "kr";
					} else if(invoiceInfor3[j].country == 246) {
						country = "芬兰";
						currencysymbol = "MK.";
					} else if(invoiceInfor3[j].country == 250) {
						country = "法国";
						currencysymbol = "F";
					} else if(invoiceInfor3[j].country == 276) {
						country = "德国";
						currencysymbol = "DM";
					} else if(invoiceInfor3[j].country == 352) {
						country = "冰岛";
						currencysymbol = "I.";
					} else if(invoiceInfor3[j].country == 372) {
						country = "爱尔兰";
						currencysymbol = "￡";
					} else if(invoiceInfor3[j].country == 380) {
						country = "意大利";
						currencysymbol = "₤";
					} else if(invoiceInfor3[j].country == 392) {
						country = "日本";
						currencysymbol = "¥";
					} else if(invoiceInfor3[j].country == 410) {
						country = "韩国";
						currencysymbol = "₩";
					} else if(invoiceInfor3[j].country == 470) {
						country = "马耳他";
						currencysymbol = "₤";
					} else if(invoiceInfor3[j].country == 528) {
						country = "荷兰";
						currencysymbol = "€";
					} else if(invoiceInfor3[j].country == 554) {
						country = "新西兰";
						currencysymbol = "$";
					} else if(invoiceInfor3[j].country == 578) {
						country = "挪威";
						currencysymbol = "kr";
					} else if(invoiceInfor3[j].country == 724) {
						country = "西班牙";
						currencysymbol = "€";
					} else if(invoiceInfor3[j].country == 752) {
						country = "瑞典";
						currencysymbol = "kr";
					} else if(invoiceInfor3[j].country == 756) {
						country = "瑞士";
						currencysymbol = "Fr";
					} else if(invoiceInfor3[j].country == 826) {
						country = "英国";
						currencysymbol = "£";
					}

					$("#moneyAll").html(taxMoney);

					invoiceInfor2Top.innerHTML = country;
					var invoiceInformation2 = document.createElement("li");
					invoiceInformation2.className = "invoiceInformation1";
					invoiceInformation2.innerHTML =
						'<div class="active11"></div>\
							<p class="invoiceInformation1p">税金：' + currencysymbol + '<span class="money">' + invoiceInfor3[j].invoiceTaxPrice + '</span></p>\
							<p class="invoiceInformation1p2"><input type="hidden" class="InvoiceIDs" name="InvoiceIDs" value="' + invoiceInfor3[j].id + '"/>\
							<input type="hidden" class="taskId" name="taskId" value="' + invoiceInfor2[i].id + '"/>\
							' + invoiceInfor3[j].id + '</p>\
							<img class="invoiceInformation1Img" src="img/rightArr2.png" />'

					invoiceInformation.appendChild(invoiceInformation2);
				}
				$("#invoiceInfor11 li").bind("click", function() {
					var taskid = $(this).find('[name="taskid"]').val();
					var invoiceid = $(this).find('[name="invoiceid"]').val();
					//window.location.href = "invoiceDetail.html?taskid=" + taskid + '&invoiceid=' + invoiceid;
					window.location.href = localhost + "/invoiceDetail?taskid=" + taskid + '&invoiceid=' + invoiceid;
				})
			}

			$("#invoiceInfor2Btn1Div").bind("click", function() {
				if($("#invoiceInfor2Btn1Div").hasClass("active12")) {
					var moneyAll = $(".moneyAll").html();
					var yearLimitAmt3=parseFloat(yearLimitAmt);
					if(moneyAll >= yearLimitAmt3) {
						$(".invoiceInfor2Btn2").addClass("active13");
					} else {
						$(".invoiceInfor2Btn2").removeClass("active13");
					}
				} else {
					$(".invoiceInfor2Btn2").removeClass("active13");
				}
			})
			var result = 0;
			$(".invoiceInformation1 div").bind("click", function() {
				if($(this).hasClass("invoiceInformation1Div")) {
					$(this).addClass("active11");
					$(this).removeClass("invoiceInformation1Div");
					$("#invoiceInfor2Btn1Div").addClass("active12");
					
					var index = $(".invoiceInformation1 div").index(this);
					var st1 = $(".invoiceInformation1p .money").eq(index).html();
					var st2 = $(".invoiceInfor2Btn1 .moneyAll").html();

					result = Number(st1) + Number(st2);
					$(".moneyAll").html(result);
					var moneyAll = parseFloat($(".moneyAll").html());
					var yearLimitAmt2=parseFloat(yearLimitAmt);
					if(moneyAll >= yearLimitAmt2) {
						$(".invoiceInfor2Btn2").addClass("active13");
					} else {
						$(".invoiceInfor2Btn2").removeClass("active13");
					}

				} else {
					$(this).removeClass("active11");
					$(this).addClass("invoiceInformation1Div");
					var index = $(".invoiceInformation1 div").index(this);
					var st1 = $(".invoiceInformation1p .money").eq(index).html();
					var st2 = $(".invoiceInfor2Btn1 .moneyAll").html();
					result = Number(st2) - Number(st1);
					$(".moneyAll").html(result);
					var moneyAll = parseFloat($(".moneyAll").html());
					var yearLimitAmt2=parseFloat(yearLimitAmt);
					if(moneyAll >= yearLimitAmt2) {
						$(".invoiceInfor2Btn2").addClass("active13");
					} else {
						$(".invoiceInfor2Btn2").removeClass("active13");
					}
				}
				var invoiceInformation1Div = $(".invoiceInformation1 div");
				for(var i = 0; i < invoiceInformation1Div.length; i++) {
					if($(".invoiceInformation1 div").hasClass("active11")) {
						$("#invoiceInfor2Btn1Div").addClass("active12");
						$("#invoiceInfor2Btn1Div").removeClass("invoiceInfor2Btn1Div");

					} else {
						$("#invoiceInfor2Btn1Div").removeClass("active12");
						$("#invoiceInfor2Btn1Div").addClass("invoiceInfor2Btn1Div");
					}
				}
			})
			var allMoney = parseFloat(taxMoney);
			/*var allMoney1 = parseFloat(secondData[i].yearLimitAmt);*/
			if(allMoney >= yearLimitAmt) {
				$(".invoiceInfor2Btn2").addClass("active13");
			}
		})
		/*不可退发票的点击查看*/
		$(".inviceListBox .inviceList3").bind("click", function() {
			$("#loading").css("display", "block");
			$(".inviceListBox").css("display", "none");
			$(".insetComponyInfor3 .invoiceInfor").css("display", "block");
			$(".invoiceInfor .invoiceInfor3").css("display", "block");
			$(".invoiceInfor .invoiceInfor1").css("display", "none");
			$(".invoiceInfor .invoiceInfor2").css("display", "none");
			var status = $(this).find('[name="statusId"]').val();
			var country = "";
			var invoiceInfor = InvoicesTasksQueryB(status, country);
			console.log(invoiceInfor);
			var invoiceInfor33 = document.getElementById("invoiceInfor33");
			invoiceInfor33.innerHTML="";
			if(invoiceInfor.code == 200) {
				$("#loading").css("display", "none");
				if(invoiceInfor.data == "数据为空！") {
					alert("暂无数据");
				} else {
					var secondData = invoiceInfor.data.InvoicesTasksQuery;
					console.log(secondData);
					for(var i = 0; i < secondData.length; i++) {
						var thirdData = secondData[i].invoices;
						console.log(thirdData);
						for(var j = 0; j < thirdData.length; j++) {
							var invoiceInforList = document.createElement("li");
							invoiceInforList.innerHTML =
								'<p>' + thirdData[j].id + '</p>\
											<img src="img/rightArr2.png" />\
											<input type="hidden" name="taskid" value="' + secondData[i].id + '"/>\
											<input type="hidden" name="invoiceid" value="' + thirdData[j].id + '"/>'

							invoiceInfor33.appendChild(invoiceInforList);
						}
					}

					$("#invoiceInfor33 li").bind("click", function() {
						var taskid = $(this).find('[name="taskid"]').val();
						var invoiceid = $(this).find('[name="invoiceid"]').val();
						//window.location.href = "invoiceDetail.html?taskid=" + taskid + '&invoiceid=' + invoiceid;
						window.location.href = localhost + "/invoiceDetail?taskid=" + taskid + '&invoiceid=' + invoiceid;

					})
				}

			} else {
				//alert("网络错误")
			}

		})

		/*一键生成订单*/
		$(".ableInviceBottom .ableInviceBottom2").bind("click", function() {
			alert("金额未达起退线");
		})
		$(".ableInviceBottom .active14").bind("click", function() {
			console.log("已达到起退线");
			var status = $(this).find('[name="statusId"]').val();
			var country = $(this).find('[name="countryId"]').val();
			var invoiceInfor = InvoicesTasksQueryB(status, country);
			console.log(invoiceInfor);

			var invoiceInfor2 = invoiceInfor.data.InvoicesTasksQuery;
			var invoiceInfor2Top = document.getElementById("invoiceInfor2Top");
			var invoiceInformation = document.getElementById("invoiceInformation");
			invoiceInformation.innerHTML = "";
			var country;
			var currencysymbol;
			var taxMoney = 0;
			for(var i = 0; i < invoiceInfor2.length; i++) {
				var invoiceInfor3 = invoiceInfor2[i].invoices;
				for(var j = 0; j < invoiceInfor3.length; j++) {
					var taxMoney1 = parseFloat(invoiceInfor3[j].invoiceTaxPrice);
					taxMoney += taxMoney1;

					if(!invoiceInfor3[j].country) {
						country = "";
						currencysymbol = "";
					} else if(invoiceInfor3[j].country == 36) {
						country = "澳大利亚";
						currencysymbol = "S/.";
					} else if(invoiceInfor3[j].country == 40) {
						country = "奥地利";
						currencysymbol = "S";
					} else if(invoiceInfor3[j].country == 56) {
						country = "比利时";
						currencysymbol = "Bi.";
					} else if(invoiceInfor3[j].country == 124) {
						country = "加拿大";
						currencysymbol = "$";
					} else if(invoiceInfor3[j].country == 158) {
						country = "中国台湾";
						currencysymbol = "NT$";
					} else if(invoiceInfor3[j].country == 208) {
						country = "丹麦";
						currencysymbol = "kr";
					} else if(invoiceInfor3[j].country == 246) {
						country = "芬兰";
						currencysymbol = "MK.";
					} else if(invoiceInfor3[j].country == 250) {
						country = "法国";
						currencysymbol = "F";
					} else if(invoiceInfor3[j].country == 276) {
						country = "德国";
						currencysymbol = "DM";
					} else if(invoiceInfor3[j].country == 352) {
						country = "冰岛";
						currencysymbol = "I.";
					} else if(invoiceInfor3[j].country == 372) {
						country = "爱尔兰";
						currencysymbol = "￡";
					} else if(invoiceInfor3[j].country == 380) {
						country = "意大利";
						currencysymbol = "₤";
					} else if(invoiceInfor3[j].country == 392) {
						country = "日本";
						currencysymbol = "¥";
					} else if(invoiceInfor3[j].country == 410) {
						country = "韩国";
						currencysymbol = "₩";
					} else if(invoiceInfor3[j].country == 470) {
						country = "马耳他";
						currencysymbol = "₤";
					} else if(invoiceInfor3[j].country == 528) {
						country = "荷兰";
						currencysymbol = "€";
					} else if(invoiceInfor3[j].country == 554) {
						country = "新西兰";
						currencysymbol = "$";
					} else if(invoiceInfor3[j].country == 578) {
						country = "挪威";
						currencysymbol = "kr";
					} else if(invoiceInfor3[j].country == 724) {
						country = "西班牙";
						currencysymbol = "€";
					} else if(invoiceInfor3[j].country == 752) {
						country = "瑞典";
						currencysymbol = "kr";
					} else if(invoiceInfor3[j].country == 756) {
						country = "瑞士";
						currencysymbol = "Fr";
					} else if(invoiceInfor3[j].country == 826) {
						country = "英国";
						currencysymbol = "£";
					}

					$("#moneyAll").html(taxMoney);

					invoiceInfor2Top.innerHTML = country;
					var invoiceInformation2 = document.createElement("li");
					invoiceInformation2.className = "invoiceInformation1";
					invoiceInformation2.innerHTML =
						'<div class="active11"></div>\
								<p class="invoiceInformation1p">税金：' + currencysymbol + '<span class="money">' + invoiceInfor3[j].invoiceTaxPrice + '</span></p>\
								<p class="invoiceInformation1p2">' + invoiceInfor3[j].id + '</p>\
								<img class="invoiceInformation1Img" src="img/rightArr2.png" />\
								<input type="hidden" class="InvoiceIDs" name="InvoiceIDs" value="' + invoiceInfor3[j].id + '"/>\
								<input type="hidden" class="taskId" name="taskId" value="' + invoiceInfor2[i].id + '"/>'
					invoiceInformation.appendChild(invoiceInformation2);

				}
			}

			var InvoiceTasks = [];
			var invoiceInformation1Div1 = $(".invoiceInformation1");
			for(var i = 0; i < invoiceInformation1Div1.length; i++) {
				var orderListVal = {};
				var InvoiceIDs = [];
				var TaskID = $(".taskId")[i].value;
				InvoiceIDs.push($(".InvoiceIDs")[i].value);
				console.log(TaskID);
				console.log(InvoiceIDs);
				orderListVal["TaskID"] = TaskID;
				orderListVal["InvoiceIDs"] = InvoiceIDs;
				console.log(orderListVal);
				InvoiceTasks.push(orderListVal);
			}
			var invoiceOrder2 = invoiceOrder(InvoiceTasks)
			console.log(invoiceOrder2);
			if(invoiceOrder2.code == 200) {
				alert("退税申请成功");
				window.location.reload();
			}

		})

	} else {
		$("#loading").css("display", "none");
		//alert("网络错误")
	}

	/*企业信息*/
	var compony = selectCompanyLetterhead();
	console.log(compony);
	if(compony.code == 400) {
		$("#loading").css("display", "none");
		$(".insetComponyInfor1 .taxInformation").css("display", "block");
		$(".taxStepList1 .taxStepBottom").css("display", "block");
		$(".taxStepList1 .taxStepBtn").css("color", "#12AAEB");
		$(".taxStepList2 .taxStepBtn").attr("disabled", true);
		$(".taxStepList3 .taxStepBtn").attr("disabled", true);
		$(".taxStepList4 .taxStepBtn").attr("disabled", true);
	}
	var componyData = compony.data;
	var contact;
	var email;
	if(componyData.contact){
		contact=componyData.contact;
	}else{
		contact="";
	}
	if(componyData.email){
		email=componyData.email
	}else{
		email="";
	}
	
	if(componyData.rawcompanyname) {
		$("#loading").css("display", "none");
		$(".insetComponyInfor1 .taxInformation").css("display", "none");
		$(".insetComponyInfor1 .taxInformation2").css("display", "block");
		var enComponyInfor = document.getElementById("enComponyInfor");
		enComponyInfor.innerHTML =
			'<li>\
				<div>\
					<img src="img/compony.png" />\
					<p>English name</p>\
				</div>\
				<p class="enName" id="cantDo2">' + componyData.compayname + '</p>\
			</li>\
			<li>\
				<div>\
					<img src="img/adress.png" />\
					<p>English address</p>\
				</div>\
				<p class="enName" id="cantDo3">' + componyData.englishaddress + '</p>\
			</li>'

		var chComponyInfor = document.getElementById("chComponyInfor");
		chComponyInfor.innerHTML =
			'<li class="chComponyInfor1">\
				<img src="img/compony.png" />\
				<p id="componyName1">' + componyData.rawcompanyname + '</p>\
			</li>\
			<li class="chComponyInfor2">\
				<img src="img/adress.png" />\
				<p id="componyAdrees1">' + componyData.rawaddress + '</p>\
			</li>\
			<li class="chComponyInfor3">\
				<img src="img/name.png" />\
				<p id="componyPeople1">' + contact + '</p>\
			</li>\
			<li class="chComponyInfor4">\
				<img src="img/email.png" />\
				<p id="componyEmail1">' + email + '</p>\
			</li>'

		var emailInput = document.getElementById("emailInput");
		emailInput.innerHTML =
			'<input type="text" value="' + componyData.email + '" />'
	}

	/*区别是不是管理者还是代理者*/
	if(componyData.status == -1) {
		$("#footerList .footer3").bind("click", function() {
			window.location.href = localhost + '/mine2';//"mine2.html";
		})
		$(".insetComponyInfor1 .taxInformation").css("display", "block");
		$(".taxStepList1 .taxStepBottom").css("display", "block");
		$(".taxStepList1 .taxStepBtn").css("color", "#12AAEB");
		$(".taxStepList2 .taxStepBtn").attr("disabled", true);
		$(".taxStepList3 .taxStepBtn").attr("disabled", true);
		$(".taxStepList4 .taxStepBtn").attr("disabled", true);
		
	}
	if(componyData.status == 0) {
		$("#footerList .footer3").bind("click", function() {
			window.location.href = localhost + '/mine2';//"mine2.html";
		})
		$(".insetComponyInfor1 .taxInformation").css("display", "none");
		$(".insetComponyInfor1 .taxInformation2").css("display", "block");
		$(".taxStepList1 .taxStepBtn").css("color", "#5682b2");
		$(".taxStepList1 img").attr("src", "img/12.png");
		$(".taxStepList2 .taxStepBtn").css("color", "#12AAEB");
		$(".taxStepList2 img").attr("src", "img/21.png");
		$(".taxStepList2 .taxStepBottom").css("display", "block");
		$("#taxStep .taxStepList1").removeClass("active3");
		$("#taxStep .taxStepList2").addClass("active3");
		$(".taxStepList3 .taxStepBtn").attr("disabled", true);
		$(".taxStepList4 .taxStepBtn").attr("disabled", true);
		$(".insetComponyInfor .insetComponyInfor1").css("display", "none");
		$(".insetComponyInfor .insetComponyInfor2").css("display", "block");
		if(componyData.uniformnumber=="0"){
			$(".taxInformation2 .componyRenzheng").css("display","block");
			$(".taxInformation2 .componyRenzheng1").css("display","none");
			$(".taxInformation2 .componyRenzheng2").css("display","none");
			$(".taxInformation2 .componyRenzheng3").css("display","none");
		}else{
			$(".taxInformation2 .componyRenzheng").css("display","none");
			$(".taxInformation2 .componyRenzheng1").css("display","none");
			$(".taxInformation2 .componyRenzheng2").css("display","none");
			$(".taxInformation2 .componyRenzheng3").css("display","block");
		}
		
	}
	if(componyData.status == 1) {
		$("#footerList .footer3").bind("click", function() {
			window.location.href = localhost + '/mine2';//"mine2.html";
		})
		$(".insetComponyInfor1 .taxInformation").css("display", "none");
		$(".insetComponyInfor1 .taxInformation2").css("display", "block");
		$(".taxStepList1 .taxStepBtn").css("color", "#5682b2");
		$(".taxStepList1 img").attr("src", "img/12.png");
		$(".taxStepList2 .taxStepBtn").css("color", "#12AAEB");
		$(".taxStepList2 img").attr("src", "img/21.png");
		$(".taxStepList2 .taxStepBottom").css("display", "block");
		$("#taxStep .taxStepList1").removeClass("active3");
		$("#taxStep .taxStepList2").addClass("active3");
		$(".taxStepList3 .taxStepBtn").attr("disabled", true);
		$(".taxStepList4 .taxStepBtn").attr("disabled", true);
		$(".insetComponyInfor .insetComponyInfor1").css("display", "none");
		$(".insetComponyInfor .insetComponyInfor2").css("display", "block");
		
		$(".taxInformation2 .componyRenzheng").css("display","none");
		$(".taxInformation2 .componyRenzheng2").css("display","block");
	}
	if(componyData.status == 2) {
		$("#footerList .footer3").bind("click", function() {
			window.location.href = localhost + '/mine'; 
		})
		$(".insetComponyInfor1 .taxInformation").css("display", "none");
		$(".insetComponyInfor1 .taxInformation2").css("display", "block");
		$(".taxStepList1 .taxStepBtn").css("color", "#5682b2");
		$(".taxStepList1 img").attr("src", "img/12.png");
		$(".taxStepList2 .taxStepBtn").css("color", "#12AAEB");
		$(".taxStepList2 img").attr("src", "img/21.png");
		$(".taxStepList2 .taxStepBottom").css("display", "block");
		$(".taxStepList3 .taxStepBtn").attr("disabled", true);
		$(".taxStepList4 .taxStepBtn").attr("disabled", true);
		$("#taxStep .taxStepList1").removeClass("active3");
		$("#taxStep .taxStepList2").addClass("active3");
		$(".insetComponyInfor .insetComponyInfor1").css("display", "none");
		$(".insetComponyInfor .insetComponyInfor2").css("display", "block");
		$(".taxInformation2 .componyRenzheng").css("display", "none");
		$(".taxInformation2 .componyRenzheng1").css("display", "block");
		if(invoice.code == 200) {
			$(".taxStepList3 .taxStepBtn").css("color", "#12AAEB");
			$(".taxStepList3 img").attr("src", "img/31.png");
			$(".taxStepList3 .taxStepBtn").removeAttr("disabled");
			$(".taxStepList2 .taxStepBtn").css("color", "#5682b2");
			$(".taxStepList2 img").attr("src", "img/22.png");
		}
		if(orderList.code == 200) {
			$(".taxStepList4 .taxStepBtn").css("color", "#12AAEB");
			$(".taxStepList4 img").attr("src", "img/41.png");
			$(".taxStepList4 .taxStepBtn").removeAttr("disabled");
			$(".taxStepList3 .taxStepBtn").css("color", "#5682b2");
			$(".taxStepList3 img").attr("src", "img/32.png");
			$(".taxStepList3 .taxStepBtn").removeAttr("disabled");
			$(".taxStepList2 .taxStepBtn").css("color", "#5682b2");
			$(".taxStepList2 img").attr("src", "img/22.png");
		}
		if(componyData.uniformnumber!=="0"){
			$(".taxInformation2 .componyRenzheng").css("display","none");
			$(".taxInformation2 .componyRenzheng1").css("display","block");
			var enterprisePhotoBox=document.getElementById("enterprisePhotoBox");
			enterprisePhotoBox.innerHTML=
			'<img src="'+componyData.imageBase64+'"/>\
			<img src="img/lock.png" class="lock"/>'
			
			var enterpriseNumBox=document.getElementById("enterpriseNumBox");
			enterpriseNumBox.innerHTML=
			'<input type="text" readonly="readonly" value="'+componyData.uniformnumber+'"/>'
		}
	}
	if(componyData.status == 3) {
		$("#footerList .footer3").bind("click", function() {
			window.location.href = localhost + '/mine2';//"mine2.html";
		})
		$(".insetComponyInfor .insetComponyInfor1").css("display", "none");
		$(".insetComponyInfor .insetComponyInfor2").css("display", "block");
		$(".insetComponyInfor1 .taxInformation").css("display", "none");
		$(".insetComponyInfor1 .taxInformation2").css("display", "block");
		$(".taxInformation2 .componyRenzheng").css("display", "none");
		$(".taxInformation2 .componyRenzheng2").css("display", "none");
		$("#chComponyInfor .chComponyInfor3").css("display","none");
		$("#chComponyInfor .chComponyInfor4").css("display","none");
		$(".taxStepList1 .taxStepBtn").css("color", "#5682b2");
		$(".taxStepList1 img").attr("src", "img/12.png");
		$(".taxStepList2 .taxStepBtn").css("color", "#12AAEB");
		$(".taxStepList2 img").attr("src", "img/21.png");
		$(".taxStepList2 .taxStepBottom").css("display", "block");
		$("#taxStep .taxStepList1").removeClass("active3");
		$("#taxStep .taxStepList2").addClass("active3");
		$(".taxStepList3 .taxStepBtn").attr("disabled", true);
		$(".taxStepList4 .taxStepBtn").attr("disabled", true);

	}
	/*企业认证*/
	/*上传发票1打开和关闭*/
	$(".taxInformation2 .componyRenzheng").bind("click",function(){
		$("#maskBox").css("display", "block");
		$("#maskBox .enterprise").css("display", "block");
		$(".enterprise .enterprise1").css("display", "block");
	})
	$(".enterprise1 .photoBtn2").bind("click",function(){
		$("#maskBox").css("display", "none");
		$("#maskBox .enterprise").css("display", "none");
		$(".enterprise .enterprise1").css("display", "none");
	})
	$("#photoBtn1").bind("click",function(){
		if($("#photoBtn1").hasClass("active15")) {
			$("#loading").css("display","block");
				var rawcompanyname = $("#componyName1").html();
				var rawaddress = $("#componyAdrees1").html();
				var compayname = $("#cantDo2").html();
				var englishaddress = $("#cantDo3").html();
				var contact = $("#componyPeople1").html();
				var email = $("#componyEmail1").html();
				var uniformnumber=document.getElementById("creditCode").value;
				var imageBase64=document.getElementById("imglist").src;
				var addCompony=addComponyLetterhead(rawcompanyname, rawaddress, compayname, englishaddress, contact, email,imageBase64,uniformnumber);
				console.log(addCompony);
				if(addCompony.code==200){
					$("#loading").css("display","none");
					$("#maskBox").css("display", "block");
					$("#maskBox .enterprise").css("display", "block");
					$(".enterprise .enterprise1").css("display", "none");
					$("#maskBox .phoneBox5").css("display", "none");
					$(".enterprise .enterprise3").css("display", "block");
				}else{
					alert(addCompony.msg);
					window.location.reload();
				}
		} else {
			alert("信息未填写完整不能提交认证信息")
		}
		
	})
	$(".enterprise3 .photoBtn1").bind("click",function(){
		$("#maskBox").css("display", "none");
		$("#maskBox .enterprise").css("display", "none");
		$(".enterprise .enterprise3").css("display", "none");
		window.location.reload();
	})
	
	/*上传发票2,重新上传*/
	$(".taxInformation2 .componyRenzheng2").bind("click",function(){
		$("#maskBox").css("display", "block");
		$("#maskBox .enterprise").css("display", "block");
		$(".enterprise .enterprise1").css("display", "block");
	})
	/*认证成功*/
	$(".taxInformation2 .componyRenzheng1").bind("click",function(){
		$("#maskBox").css("display", "block");
		$("#maskBox .enterprise").css("display", "block");
		$(".enterprise .enterprise2").css("display", "block");
	})
	$(".enterprise2 .photoBtn1").bind("click",function(){
		$("#maskBox").css("display", "none");
		$("#maskBox .enterprise").css("display", "none");
		$(".enterprise .enterprise2").css("display", "none");
	})
	/*顶部的切换*/
	$(".taxStepBtn1").bind("click", function() {
			if(componyData.rawcompanyname) {
				$(".insetComponyInfor1 .taxInformation2").css("display", "block");
			}
		})
		/*上传发票*/
	$(".taxStepBtn2").bind("click", function() {
			$("#uploadPhotoList .smallOne").remove();
			$("#uploadPhotoList .uploadPhoto").css("display", "block");
			uploader.refresh();
		})
		/*申请退税*/
	$(".taxStepBtn3").bind("click", function() {
		if(invoice.code == 200) {
			$(".insetComponyInfor3 .inviceListBox").css("display", "block");
			$(".insetComponyInfor3 .invoiceInfor").css("display", "none");
		}
	})

	/*企业退税的步骤*/
	$(".orderInfor4 .orderInforBtn2").bind("click", function() {
		$("#maskBox").css("display", "block");
		$("#maskBox .stepBox").css("display", "block");
	})

	/*银行账户的关联*/
	$(".orderInfor4 .orderInforBtn3").bind("click", function() {
		$("#maskBox").css("display", "block");
		$("#maskBox .bankBox").css("display", "block");
		var bankAccount = bankAccountDefault();
		console.log(bankAccount);
		var selectBankList = document.getElementById("bankName");
		var name;
		if(bankAccount.code == 200) {
			$("#loading").css("display", "none");
			var secondData = bankAccount.data;
			if(!secondData.bankId) {
				name = "";
			} else if(secondData.bankId == 1) {
				name = "中国工商银行";
			} else if(secondData.bankId == 2) {
				name = "中国农业银行";
			} else if(secondData.bankId == 3) {
				name = "中国银行";
			} else if(secondData.bankId == 4) {
				name = "中国建设银行";
			} else if(secondData.bankId == 5) {
				name = "交通银行";
			} else if(secondData.bankId == 6) {
				name = "中国邮政储蓄银行";
			} else if(secondData.bankId == 7) {
				name = "中国民生银行";
			}

			selectBankList.innerHTML =
				'<li>\
				<p>选择银行：</p>\
				<select id="selectBankList" disabled="disabled">\
					<option value="' + secondData.bankId + '">' + name + '</option>\
					<option value="7">中国民生银行</option>\
					<option value="1">中国工商银行</option>\
					<option value="2">中国农业银行</option>\
					<option value="3">中国银行</option>\
					<option value="4">中国建设银行</option>\
					<option value="5">交通银行</option>\
					<option value="6">中国邮政储蓄银行</option>\
				</select>\
			</li>\
			<li>\
				<p>开户行：</p>\
				<input placeholder="请填写银行开户行" value="' + secondData.bankName + '" id="bankCode"  readonly="readonly"/>\
			</li>\
			<li>\
				<p>企业账户：</p>\
				<input placeholder="请填写企业账户" value="' + secondData.bankAccount + '" id="bankAccount" readonly="readonly"/>\
			</li>\
			<li>\
				<p>用户名：</p>\
				<input placeholder="用户名自动获取" id="bankUserName" value="' + componyData.contact + '" readonly="readonly"/>\
			</li>'
		}
	})

	$(".bankBoxBtn .bankBoxBtn1").bind("click", function() {
		$("#loading").css("display", "block");
		var bankId = $("#selectBankList option:selected").val();
		var bankName = document.getElementById("bankCode").value;
		var bankAccount = document.getElementById("bankAccount").value;
		var bankCount = bankAccountUpdate(bankId, bankName, bankAccount);
		console.log(bankCount);
		if(bankCount.code == 200) {
			$("#loading").css("display", "none");
			$("#maskBox").css("display", "block");
			$("#maskBox .bankBox").css("display", "none");
			$("#maskBox .phoneBox9").css("display", "block");
			$(".phoneBox9 .maskBtn1").bind("click", function() {
				$("#maskBox").css("display", "none");
				$("#maskBox .phoneBox9").css("display", "none");
			})
		} else if(bankCount.code == 400) {
			$("#loading").css("display", "none");
			$("#maskBox").css("display", "block");
			$("#maskBox .bankBox").css("display", "none");
			$("#maskBox .phoneBox10").css("display", "block");
			$(".phoneBox10 .maskBtn1").bind("click", function() {
				$("#maskBox").css("display", "none");
				$("#maskBox .phoneBox10").css("display", "none");
			})
		}

	})

})

/*生成订单*/
$(".invoiceInfor2Btn .invoiceInfor2Btn2").bind("click", function() {
	var InvoiceTasks = [];
	if($(this).hasClass("active13")) {
		var invoiceInformation1Div = $(".active11");
		for(var i = 0; i < invoiceInformation1Div.length; i++) {
			var orderListVal = {};
			var InvoiceIDs = [];
			var TaskID = $(".taskId")[i].value;
			InvoiceIDs.push($(".InvoiceIDs")[i].value);
			orderListVal["TaskID"] = TaskID;
			orderListVal["InvoiceIDs"] = InvoiceIDs;

			InvoiceTasks.push(orderListVal);

		}
		var invoiceOrder = invoiceOrder(InvoiceTasks)
		console.log(invoiceOrder);
		if(invoiceOrder.code == 200) {
			alert("退税申请成功");
			window.location.reload();
		}
	} else {
		alert("金额未达起退线")
	}

})

/*订单的列表*/
var orderList = invoiceOrderQuery();
console.log(orderList);
var orderListBox = document.getElementById("orderListBox");
if(orderList.code == 200) {
	/*$("#loading").css("display", "none");*/
	var secondOrder = orderList.data;
	var country;
	var currencysymbol;
	var srcAdress;
	var tax;
	var step;
	for(var i = 0; i < secondOrder.length; i++) {
		if(!secondOrder[i].country) {
			country = "";
			srcAdress = "";
			currencysymbol = "";
			tax = "";
		} else if(secondOrder[i].country == 36) {
			country = "澳大利亚";
			srcAdress = "img/36.png";
			currencysymbol = "S/.";
			tax = 500;
		} else if(secondOrder[i].country == 40) {
			country = "奥地利";
			srcAdress = "img/40.png";
			currencysymbol = "S";
			tax = 50;
		} else if(secondOrder[i].country == 56) {
			country = "比利时";
			srcAdress = "img/56.png";
			currencysymbol = "Bi.";
			tax = 25;
		} else if(secondOrder[i].country == 124) {
			country = "加拿大";
			srcAdress = "img/124.png";
			currencysymbol = "$";
			tax = 200;
		} else if(secondOrder[i].country == 158) {
			country = "中国台湾";
			srcAdress = "img/158.png";
			currencysymbol = "NT$";
			tax = 5000;
		} else if(secondOrder[i].country == 208) {
			country = "丹麦";
			srcAdress = "img/208.png";
			currencysymbol = "kr";
			tax = 400;
		} else if(secondOrder[i].country == 246) {
			country = "芬兰";
			srcAdress = "img/246.png";
			currencysymbol = "MK.";
			tax = 40;
		} else if(secondOrder[i].country == 250) {
			country = "法国";
			srcAdress = "img/250.png";
			currencysymbol = "F";
			tax = 50;
		} else if(secondOrder[i].country == 276) {
			country = "德国";
			srcAdress = "img/276.png";
			currencysymbol = "DM";
			tax = 500;
		} else if(secondOrder[i].country == 352) {
			country = "冰岛";
			srcAdress = "img/352.png";
			currencysymbol = "I.";
			tax = 12700;
		} else if(secondOrder[i].country == 372) {
			country = "爱尔兰";
			srcAdress = "img/372.png";
			currencysymbol = "￡";
			tax = 25;
		} else if(secondOrder[i].country == 380) {
			country = "意大利";
			srcAdress = "img/380.png";
			currencysymbol = "₤";
			tax = 50;
		} else if(secondOrder[i].country == 392) {
			country = "日本";
			srcAdress = "img/392.png";
			currencysymbol = "¥";
			tax = 1000;
		} else if(secondOrder[i].country == 410) {
			country = "韩国";
			srcAdress = "img/410.png";
			currencysymbol = "₩";
			tax = 300000;
		} else if(secondOrder[i].country == 470) {
			country = "马耳他";
			srcAdress = "img/470.png";
			currencysymbol = "₤";
			tax = 23;
		} else if(secondOrder[i].country == 528) {
			country = "荷兰";
			srcAdress = "img/528.png";
			currencysymbol = "€";
			tax = 50;
		} else if(secondOrder[i].country == 554) {
			country = "新西兰";
			srcAdress = "img/554.png";
			currencysymbol = "$";
			tax = 500;
		} else if(secondOrder[i].country == 578) {
			country = "挪威";
			srcAdress = "img/578.png";
			currencysymbol = "kr";
			tax = 200;
		} else if(secondOrder[i].country == 724) {
			country = "西班牙";
			srcAdress = "img/724.png";
			currencysymbol = "€";
			tax = 50;
		} else if(secondOrder[i].country == 752) {
			country = "瑞典";
			srcAdress = "img/752.png";
			currencysymbol = "kr";
			tax = 500;
		} else if(secondOrder[i].country == 756) {
			country = "瑞士";
			srcAdress = "img/756.png";
			currencysymbol = "Fr";
			tax = 250;
		} else if(secondOrder[i].country == 826) {
			country = "英国";
			srcAdress = "img/826.png";
			currencysymbol = "£";
			tax = 16;
		}
		var stepList = document.getElementById("stepList");
		stepList.innerHTML = '';
		if(secondOrder[i].status == 0) {
			step = "订单申请";
			stepList.innerHTML =
				'<li>\
							<div class="active7"></div>\
							<p class="active8">订单申请</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书寄发</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书回传</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书确认</p>\
						</li>\
						<li>\
							<div></div>\
							<p>订单寄发国外</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税务局已送件</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税金返还</p>\
						</li>\
						<li>\
							<div></div>\
							<p>客户入账</p>\
						</li>\
						<li>\
							<div></div>\
							<p>确认结案</p>\
						</li>'
		} else if(secondOrder[i].status == 1) {
			step = "授权书寄发";
			stepList.innerHTML =
				'<li>\
							<div></div>\
							<p>订单申请</p>\
						</li>\
						<li>\
							<div class="active7"></div>\
							<p class="active8">授权书寄发</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书回传</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书确认</p>\
						</li>\
						<li>\
							<div></div>\
							<p>订单寄发国外</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税务局已送件</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税金返还</p>\
						</li>\
						<li>\
							<div></div>\
							<p>客户入账</p>\
						</li>\
						<li>\
							<div></div>\
							<p>确认结案</p>\
						</li>'
		} else if(secondOrder[i].status == 2) {
			step = "授权书回传";
			stepList.innerHTML =
				'<li>\
							<div></div>\
							<p>订单申请</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书寄发</p>\
						</li>\
						<li>\
							<div class="active7"></div>\
							<p class="active8">授权书回传</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书确认</p>\
						</li>\
						<li>\
							<div></div>\
							<p>订单寄发国外</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税务局已送件</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税金返还</p>\
						</li>\
						<li>\
							<div></div>\
							<p>客户入账</p>\
						</li>\
						<li>\
							<div></div>\
							<p>确认结案</p>\
						</li>'
		} else if(secondOrder[i].status == 3) {
			step = "授权书确认";
			stepList.innerHTML =
				'<li>\
							<div></div>\
							<p>订单申请</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书寄发</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书回传</p>\
						</li>\
						<li>\
							<div class="active7"></div>\
							<p class="active8">授权书确认</p>\
						</li>\
						<li>\
							<div></div>\
							<p>订单寄发国外</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税务局已送件</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税金返还</p>\
						</li>\
						<li>\
							<div></div>\
							<p>客户入账</p>\
						</li>\
						<li>\
							<div></div>\
							<p>确认结案</p>\
						</li>'
		} else if(secondOrder[i].status == 4) {
			step = "订单寄发国外";
			stepList.innerHTML =
				'<li>\
							<div></div>\
							<p>订单申请</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书寄发</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书回传</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书确认</p>\
						</li>\
						<li>\
							<div class="active7"></div>\
							<p class="active8">订单寄发国外</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税务局已送件</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税金返还</p>\
						</li>\
						<li>\
							<div></div>\
							<p>客户入账</p>\
						</li>\
						<li>\
							<div></div>\
							<p>确认结案</p>\
						</li>'
		} else if(secondOrder[i].status == 5) {
			step = "税务局已送件";
			stepList.innerHTML =
				'<li>\
							<div></div>\
							<p>订单申请</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书寄发</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书回传</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书确认</p>\
						</li>\
						<li>\
							<div></div>\
							<p>订单寄发国外</p>\
						</li>\
						<li>\
							<div class="active7"></div>\
							<p class="active8">税务局已送件</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税金返还</p>\
						</li>\
						<li>\
							<div></div>\
							<p>客户入账</p>\
						</li>\
						<li>\
							<div></div>\
							<p>确认结案</p>\
						</li>'
		} else if(secondOrder[i].status == 6) {
			step = "税金返还";
			stepList.innerHTML =
				'<li>\
							<div></div>\
							<p>订单申请</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书寄发</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书回传</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书确认</p>\
						</li>\
						<li>\
							<div></div>\
							<p>订单寄发国外</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税务局已送件</p>\
						</li>\
						<li>\
							<div class="active7"></div>\
							<p class="active8">税金返还</p>\
						</li>\
						<li>\
							<div></div>\
							<p>客户入账</p>\
						</li>\
						<li>\
							<div></div>\
							<p>确认结案</p>\
						</li>'
		} else if(secondOrder[i].status == 7) {
			step = "客户入账";
			stepList.innerHTML =
				'<li>\
							<div></div>\
							<p>订单申请</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书寄发</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书回传</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书确认</p>\
						</li>\
						<li>\
							<div></div>\
							<p>订单寄发国外</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税务局已送件</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税金返还</p>\
						</li>\
						<li>\
							<div class="active7"></div>\
							<p class="active8">客户入账</p>\
						</li>\
						<li>\
							<div></div>\
							<p>确认结案</p>\
						</li>'
		} else if(secondOrder[i].status == 8) {
			step = "确认结案";
			stepList.innerHTML =
				'<li>\
							<div></div>\
							<p>订单申请</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书寄发</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书回传</p>\
						</li>\
						<li>\
							<div></div>\
							<p>授权书确认</p>\
						</li>\
						<li>\
							<div></div>\
							<p>订单寄发国外</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税务局已送件</p>\
						</li>\
						<li>\
							<div></div>\
							<p>税金返还</p>\
						</li>\
						<li>\
							<div></div>\
							<p>客户入账</p>\
						</li>\
						<li>\
							<div class="active7"></div>\
							<p class="active8">确认结案</p>\
						</li>'
		}

		var orderList1 = document.createElement("div");
		orderList1.className = "orderList";
		orderList1.innerHTML =
			'<ul class="orderInfor">\
			<li class="orderInfor1">\
				<img src="' + srcAdress + '" />\
				<p>' + country + '</p>\
				<p>可退税金：' + currencysymbol + '' + secondOrder[i].totalInvoiceTaxPrice + '</p>\
			</li>\
			<li class="orderInfor2">订单号：' + secondOrder[i].fbillno + '</li>\
			<li class="orderInfor3">' + secondOrder[i].createtime + '</li>\
			<li class="orderInfor4">\
				<div class="orderInforBtn1">退税材料</div>\
				<div class="orderInforBtn2">' + step + '</div>\
				<div class="orderInforBtn3">付款信息</div>\
			</li>\
		</ul>\
		<div class="found">找专家</div>'

		orderListBox.appendChild(orderList1);
	}
} else {
	$("#loading").css("display", "none");
	//alert("网络错误");
}

//base64Codes:发票图檔Base64Code
function uploadInvoiceImageFiles1(base64Codes) {
	var result = "";
	var data = {base64Codes: JSON.stringify(base64Codes)};
	var url = localhost+"/invoice/uploadInvoiceImageFiles";
	getDataAJAX(url, "POST", data, true, function(resultData) {
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
			imgFalse.innerHTML="";
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
$(".uploadMask3 .maskBtn2").bind("click", function() {
	console.log(552);
	$("#maskBox").css("display", "block");
	$(".uploadMask .uploadMask3").css("display", "none");
	$(".uploadMask .uploadMask1").css("display", "block");
	var companyNo2 = selectCompanyLetterhead().data.companyno;
	var pushList2 = [];

	var img2 = $("#imgFalse span img")
	console.log(img2.length);
	for(var i = 0; i < img2.length; i++) {
		var imgList2 = {};
		imgList2["companyno"] = companyNo2;
		imgList2["ImageBinaryCodes"] = img2[i].src;
		/*console.log(companyNo2);
		console.log(img2[i].src);*/
		pushList2.push(imgList2);
		/*console.log(pushList2);*/

	}
	process = setInterval(invoiceUploadProgress, 1000);
	uploadInvoiceImageFiles1(pushList2, companyNo2);

})