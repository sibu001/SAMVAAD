package com.divergent.meet.divergentmeet.service;

import java.util.List;

import javax.validation.Valid;

import com.divergent.meet.divergentmeet.model.User;

public interface UserService {

	User findUserById(Long id);
	
	User findById(Long id);
	
	public User createUser(User user);
	
	public List<User> getUserList(String role);
	
	public User getUserById(Long id);

	public Boolean deleteUserById(Long id);

	public User updateUser(@Valid User user);

	List<User> getStudentByClassSection(Long classId, Long sectionId);

}
