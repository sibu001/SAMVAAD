package com.divergent.meet.divergentmeet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.divergent.meet.divergentmeet.model.Student;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {

	public Student findStudentByPhone(String phone);
	
	public Student findStudentById(Long id);
	
	public Boolean existsByPhone(String phone);
	
	@Query("select s from Student s where s.myclass.id=:id")
	public List<Student> findByMyclassId(@Param("id") Long id);
	
	@Query("select s from Student s where s.myclass.id=?1 and s.section.id=?2")
	public List<Student> findByMyclassIdAndSectionId(Long id,Long sectionid);
	
	@Query("select s from Student s where s.phone=?1 and s.password=?2")
	public Student checkPhoneAndPassword(String phone,String password);

	
	
	/**
	 * Soft delete {@link Student} by Id
	 * 
	 * @param id
	 * @return number of deleted Student
	 */
	@Transactional
	@Modifying
	public int deleteStudentById(Long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Student SET password=?1 WHERE id=?2")
	public int updatePassword(String password, Long id);

	
}
