package com.divergent.meet.divergentmeet.service;

import java.util.List;

import com.divergent.meet.divergentmeet.model.Student;

public interface StudentService {
	
    public Student createStudent(Student student);
	
	public List<Student> getStudentList();
	
	public Student getStudentById(Long id);

	public Boolean deleteStudentById(Long id);
	
	public Boolean changePassword(String password,Long id);
	
	public Student studentLogin(String phone,String password);


}
