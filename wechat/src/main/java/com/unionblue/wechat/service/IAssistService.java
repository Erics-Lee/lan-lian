package com.unionblue.wechat.service;

public interface IAssistService {

	String selectOfficialQuestionAndAnswer(String parentAppKey);

	String selectOfficialAnswer(String parentAppKey, Integer questionOrderNumber);

}
