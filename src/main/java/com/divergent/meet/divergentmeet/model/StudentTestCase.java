package com.divergent.meet.divergentmeet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.divergent.meet.divergentmeet.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class StudentTestCase extends DateAudit {
	private static final long serialVersionUID = -5083581084976352921L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private String stdout;
	private String stderr;
	private String memory;
	private String compile_output;
	private String time;
	private String sandbox_message;
	@ManyToOne
	@JoinColumn(name = "test_case")
	private TestCase testCase;

	
	
	@ManyToOne
	@JoinColumn(name = "question")
	@JsonIgnore
	private Question question;
	@ManyToOne
	@JoinColumn(name = "studentAnswer")
	private StudentAnswer studentAnswer;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public String getStdout() {
		return stdout;
	}
	public void setStdout(String stdout) {
		this.stdout = stdout;
	}
	
	public String getStderr() {
		return stderr;
	}
	public void setStderr(String stderr) {
		this.stderr = stderr;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	
	public String getCompile_output() {
		return compile_output;
	}
	public void setCompile_output(String compile_output) {
		this.compile_output = compile_output;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public StudentAnswer getStudentAnswer() {
		return studentAnswer;
	}
	public void setStudentAnswer(StudentAnswer studentAnswer) {
		this.studentAnswer = studentAnswer;
	}
	public String getSandbox_message() {
		return sandbox_message;
	}
	public void setSandbox_message(String sandbox_message) {
		this.sandbox_message = sandbox_message;
	}
	public TestCase getTestCase() {
		return testCase;
	}
	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}
	
	
}
