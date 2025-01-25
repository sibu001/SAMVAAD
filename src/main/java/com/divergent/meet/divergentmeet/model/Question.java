package com.divergent.meet.divergentmeet.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.divergent.meet.divergentmeet.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Question extends DateAudit {
	private static final long serialVersionUID = -5083581084976352921L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String question;
	private String sourceCode;
	private String sampleInput;
	private String sampleOutput;
	@ManyToOne
	@JoinColumn(name = "session")
	private Session session;
	@OneToMany(mappedBy="question")
	private Set<TestCase> testCases;
	
	@OneToMany(mappedBy="question")
	@JsonIgnore
	private Set<StudentAnswer> studentAnswer;
	
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
	public Set<StudentAnswer> getStudentAnswer() {
		return studentAnswer;
	}
	public void setStudentAnswer(Set<StudentAnswer> studentAnswer) {
		this.studentAnswer = studentAnswer;
	}
	
	

}
