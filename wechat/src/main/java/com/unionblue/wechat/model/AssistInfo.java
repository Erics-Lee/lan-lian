package com.unionblue.wechat.model;

import java.io.Serializable;

public class AssistInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int QuestionOrderNumber;
	
	private String Question;
	
	private String Answer;

	public int getQuestionOrderNumber() {
		return QuestionOrderNumber;
	}

	public void setQuestionOrderNumber(int questionOrderNumber) {
		QuestionOrderNumber = questionOrderNumber;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}
	
	

}
