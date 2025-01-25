package com.divergent.meet.divergentmeet.controller;

import java.util.List;

import javax.validation.Valid;

import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.divergent.meet.divergentmeet.constants.MsgConstants;
import com.divergent.meet.divergentmeet.model.User;
import com.divergent.meet.divergentmeet.service.UserService;
import com.divergent.meet.divergentmeet.utility.ResponseMessage;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * To create user
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
		
	}

	@PutMapping()
	public ResponseMessage<User> updateUser(@Valid @RequestBody User user) {
		return new ResponseMessage<>(HttpStatus.OK.value(), MsgConstants.USER_CREATED_SUCCESSFULLY,
				userService.updateUser(user));
	}

	/**
	 * To get user list
	 * 
	 * @return
	 */
	@GetMapping("/getall")
	public ResponseMessage<List<User>> getUserList(@RequestParam(required=false,value="role") String role) {
		return new ResponseMessage<>(HttpStatus.OK.value(), userService.getUserList(role));
	}
	/**
	 * To get user list
	 * 
	 * @return
	 */
	@GetMapping("/getStudentByClassSection")
	public ResponseMessage<List<User>> getStudentByClassSection(@RequestParam("classId") Long classId,@RequestParam("sectionId") Long sectionId) {
		return new ResponseMessage<>(HttpStatus.OK.value(), userService.getStudentByClassSection(classId,sectionId));
	}

	/**
	 * To get user by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getbyid/{id}")
	public ResponseMessage<User> getById(@PathVariable("id") Long id) {
		return new ResponseMessage<>(HttpStatus.OK.value(), userService.getUserById(id));
	}

	/**
	 * To delete User
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deletebyid")
	public ResponseMessage<Boolean> deleteUserById(@Valid @RequestParam Long id) {
		return new ResponseMessage<>(HttpStatus.OK.value(), userService.deleteUserById(id));
	}

}
