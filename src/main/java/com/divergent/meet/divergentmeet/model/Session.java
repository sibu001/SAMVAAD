package com.divergent.meet.divergentmeet.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.divergent.meet.divergentmeet.model.audit.DateAudit;

@Entity
public class Session extends DateAudit {
	private static final long serialVersionUID = -5083581084976352921L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "session_master_id")
	private SessionMaster sessionMaster;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private MyClass myclass;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	private Boolean launched;
	private LocalDateTime time;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SessionMaster getSessionMaster() {
		return sessionMaster;
	}
	public void setSessionMaster(SessionMaster sessionMaster) {
		this.sessionMaster = sessionMaster;
	}
	public MyClass getMyclass() {
		return myclass;
	}
	public void setMyclass(MyClass myclass) {
		this.myclass = myclass;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public Boolean getLaunched() {
		return launched;
	}
	public void setLaunched(Boolean launched) {
		this.launched = launched;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	

}
