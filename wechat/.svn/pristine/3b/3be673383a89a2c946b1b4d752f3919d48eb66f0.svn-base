package com.unionblue.wechat.controller;

import com.unionblue.wechat.service.IAssistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/assist")
public class AssistController {

	@Autowired
	private IAssistService service;
	
	/**
     * 帮助中心
     * @return
     */
	@RequestMapping("/selectOfficialQuestionAndAnswer")
    @ResponseBody
	public String selectOfficialQuestionAndAnswer() {
		String result = service.selectOfficialQuestionAndAnswer();
		return result;
	}
	
	/**
     * 帮助中心
     * @param companyName
     * @return
     */
	@RequestMapping("/selectOfficialAnswer")
    @ResponseBody
	public String selectOfficialAnswer(Integer questionOrderNumber) {
		String result = service.selectOfficialAnswer(questionOrderNumber);
		return result;
	}

	
	
}
