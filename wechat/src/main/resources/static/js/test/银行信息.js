        $(document).ready(function() {
			console.log(bankAccountUpdate('', '中国建设银行', '621226.......'));
		});
        
      //提交银行默认转账账户
		function bankAccountUpdate(bankId, bankName, bankAccount) {
			var result = "";
			var data = {
					isDelete: '0',                                                             //1为删除；0为更新或新增,默认为0
					bankCountry: '156',                                                        //国家id； 默认156中国
					bankName: bankName,                                                        //开户行
					bankId: bankId,                                                            //银行id
					bankAccount: bankAccount,                                                  //银行帐户编码
					isDefault: '1'                                                             //常规为0，设置为1的时候为默认账户，默认为1
			};
			var url = localhost+"/bank/bankAccountUpdate";
			getDataAJAX(url, "POST", data, false, function(resultData) {
				result = resultData;
			});
			return JSON.parse(result);
		}
		
		//查询默认银行
		function bankAccountDefault() {
			var result = "";
			var data = {};
			var url = localhost+"/bank/bankAccountDefault";
			getDataAJAX(url, "GET", data, false, function(resultData) {
				result = resultData;
			});
			return JSON.parse(result);
		}
		
		//查询银行账户下拉列表
		function selectBank() {
			var result = "";
			var data = {};
			var url = localhost+"/bank/selectBank";
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
