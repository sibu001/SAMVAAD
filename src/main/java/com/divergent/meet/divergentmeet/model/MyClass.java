package com.divergent.meet.divergentmeet.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.divergent.meet.divergentmeet.model.audit.DateAudit;

@Entity
@Table(name = "class")
public class MyClass extends DateAudit  {
	private static final long serialVersionUID = -5083581084976352921L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String name;
	
	private LocalDateTime classTime;
	

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getClassTime() {
		return classTime;
	}
	public void setClassTime(LocalDateTime classTime) {
		this.classTime = classTime;
	}
	
	
	
	
}
