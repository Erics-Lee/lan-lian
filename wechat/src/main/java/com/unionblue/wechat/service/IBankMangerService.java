package com.unionblue.wechat.service;

import com.unionblue.wechat.model.BankInfo;

public interface IBankMangerService {

	String bankAccountUpdate(BankInfo info, String sessionKey);

	String bankAllAccountQuery(String sessionKey);

	String bankAccountDefault(String sessionKey);

}
