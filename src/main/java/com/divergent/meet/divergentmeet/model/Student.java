package com.divergent.meet.divergentmeet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.divergent.meet.divergentmeet.model.audit.DateAudit;

@Entity
@Table(name = "student", uniqueConstraints = { @UniqueConstraint(columnNames = { "phone" }) })
public class Student  extends DateAudit {

	private static final long serialVersionUID = -5083581084976352921L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 40)
	private String firstname;

	@NotBlank
	@Size(max = 100)
	private String password;


	@Size(max = 50)
	private String lastname;
	
	@NotBlank
	@Size(max = 100)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "class_id")
	private MyClass myclass;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	@NaturalId
	@Size(max = 10)
	private String phone;
	
	private boolean enabled = true;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	
}
