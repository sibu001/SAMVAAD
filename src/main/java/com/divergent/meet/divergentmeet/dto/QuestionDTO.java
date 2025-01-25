package com.divergent.meet.divergentmeet.dto;

import java.util.HashSet;
import java.util.Set;

import com.divergent.meet.divergentmeet.model.Question;
import com.divergent.meet.divergentmeet.model.Session;
import com.divergent.meet.divergentmeet.model.StudentAnswer;
import com.divergent.meet.divergentmeet.model.TestCase;

public class QuestionDTO {
	private Long id;
	private String question;
	private String sourceCode;
	private String sampleInput;
	private String sampleOutput;
	private Session session;
	private Set<TestCase> testCases;
	private Set<StudentAnswer> studentAnswerList;

	public QuestionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionDTO(Question question) {
		super();
		this.id = question.getId();
		this.question = question.getQuestion();
		this.sourceCode = question.getSourceCode();
		this.sampleInput = question.getSampleInput();
		this.sampleOutput = question.getSampleOutput();
		this.session = question.getSession();
		Set<StudentAnswer> studentAnswerList = new HashSet<StudentAnswer>();
		if (question.getStudentAnswer() != null) {
			for (StudentAnswer studentAnswer2 : question.getStudentAnswer()) {
				StudentAnswer studentAnswer = new StudentAnswer();
				studentAnswer.setId(studentAnswer2.getId());
				studentAnswerList.add(studentAnswer);
			}
		}

		this.studentAnswerList = studentAnswerList;
		Set<TestCase> testCaseList = new HashSet<TestCase>();
		if (question.getTestCases() != null)
			for (TestCase testCase : question.getTestCases()) {
				TestCase testCaseMap = new TestCase();
				testCaseMap.setExpectedOutput(testCase.getExpectedOutput());
				testCaseMap.setInput(testCase.getInput());
				testCaseMap.setId(testCase.getId());
				testCaseList.add(testCaseMap);
			}

		this.testCases = testCaseList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSampleInput() {
		return sampleInput;
	}

	public void setSampleInput(String sampleInput) {
		this.sampleInput = sampleInput;
	}

	public String getSampleOutput() {
		return sampleOutput;
	}

	public void setSampleOutput(String sampleOutput) {
		this.sampleOutput = sampleOutput;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Set<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(Set<TestCase> testCases) {
		this.testCases = testCases;
	}

	public Set<StudentAnswer> getStudentAnswerList() {
		return studentAnswerList;
	}

	public void setStudentAnswerList(Set<StudentAnswer> studentAnswerList) {
		this.studentAnswerList = studentAnswerList;
	}

}
