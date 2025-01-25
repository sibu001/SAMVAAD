package com.divergent.meet.divergentmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divergent.meet.divergentmeet.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	public Teacher findByEmail(String email);
	
	public Teacher findByPhone(String phone);
}
