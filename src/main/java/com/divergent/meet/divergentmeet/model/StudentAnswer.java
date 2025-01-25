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

@Entity
@Table
public class StudentAnswer  extends DateAudit {

	private static final long serialVersionUID = -5083581084976352921L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String language_id;
	private String source_code;
	private Long attempts;
	@ManyToOne
	@JoinColumn(name = "question")
	private Question question;
	@OneToMany(mappedBy="studentAnswer")
	private Set<StudentTestCase> studentTestCase;
	@ManyToOne
	@JoinColumn(name = "student")
	private User student;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	
	public Long getAttempts() {
		return attempts;
	}

	public void setAttempts(Long attempts) {
		this.attempts = attempts;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
	

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public String getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}

	

	public String getSource_code() {
		return source_code;
	}

	public void setSource_code(String source_code) {
		this.source_code = source_code;
	}

	public Set<StudentTestCase> getStudentTestCase() {
		return studentTestCase;
	}

	public void setStudentTestCase(Set<StudentTestCase> studentTestCase) {
		this.studentTestCase = studentTestCase;
	}

	
	

}
