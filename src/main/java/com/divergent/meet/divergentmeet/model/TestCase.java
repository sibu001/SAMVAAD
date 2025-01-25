package com.divergent.meet.divergentmeet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.divergent.meet.divergentmeet.model.audit.DateAudit;

@Entity
@Table
public class TestCase extends DateAudit {
	private static final long serialVersionUID = -5083581084976352921L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String input;
	private String expectedOutput;
	private String stdin;
	private String redirect_stderr_to_stdout;
	private String compiler_options;

	@ManyToOne
	@JoinColumn(name = "question")
	private Question question;
	@ManyToOne
	@JoinColumn(name = "studentAnswer")
	private StudentAnswer student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getExpectedOutput() {
		return expectedOutput;
	}

	public void setExpectedOutput(String expectedOutput) {
		this.expectedOutput = expectedOutput;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getStdin() {
		return stdin;
	}

	public void setStdin(String stdin) {
		this.stdin = stdin;
	}

	public String getRedirect_stderr_to_stdout() {
		return redirect_stderr_to_stdout;
	}

	public void setRedirect_stderr_to_stdout(String redirect_stderr_to_stdout) {
		this.redirect_stderr_to_stdout = redirect_stderr_to_stdout;
	}

	

	public String getCompiler_options() {
		return compiler_options;
	}

	public void setCompiler_options(String compiler_options) {
		this.compiler_options = compiler_options;
	}

	public StudentAnswer getStudent() {
		return student;
	}

	public void setStudent(StudentAnswer student) {
		this.student = student;
	}

}
