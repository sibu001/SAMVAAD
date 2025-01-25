package com.divergent.meet.divergentmeet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.divergent.meet.divergentmeet.model.RoleName;
import com.divergent.meet.divergentmeet.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find {@link User} by Email1
	 * 
	 * @param email1
	 * @return {@link User}
	 */
	public User findUserByEmail(String email);
	
	public User  findByPhone(String phone);

	
	/**
	 * Find {@link User} by username
	 * 
	 * @param userma,e
	 * @return {@link User}
	 */
	public User findUserByUsername(String username);
		
	public Boolean existsByUsername(String username);
	
	public Optional<User> findByUsernameOrEmail(String username, String email);
	
	public User findUserById(Long id);
	
	/**
	 * Soft delete {@link User} by Id
	 * 
	 * @param id
	 * @return number of deleted User
	 */
	@Transactional
	@Modifying
	public int deleteUserById(Long id);
	
	@Query("select u from User u left join u.roles rl where rl.name=:name")
	List<User> findByRoles(@Param("name") RoleName name);

	@Query("select u from User u left join u.roles rl where rl.name='ROLE_USER'")
	public List<User> findAllStudent();
	
	@Query("select u from User u left join u.roles rl where rl.name='ROLE_TEACHER'")
	public List<User> findAllTeacher();
	
	@Query("select u from User u left join u.roles rl where rl.name='ROLE_ADMIN'")
	public List<User> findAllAdmin();

	@Query("select u from User u left join u.roles rl where rl.name='ROLE_USER' and u.myclass.id=:classId and u.section.id=:sectionId")
	public List<User> findAllStudentByClassAndSection(@Param("classId")Long classId, @Param("sectionId")Long sectionId);


}
