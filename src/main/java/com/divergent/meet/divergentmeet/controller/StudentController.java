package com.divergent.meet.divergentmeet.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.divergent.meet.divergentmeet.constants.MsgConstants;
import com.divergent.meet.divergentmeet.model.Student;
import com.divergent.meet.divergentmeet.service.StudentService;
import com.divergent.meet.divergentmeet.utility.ResponseMessage;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	/**
	 * To create Student
	 * 
	 * @param student
	 * @return
	 */
	@PostMapping("/create")
	public ResponseMessage<Student> createStudent(@Valid @RequestBody Student student) {
		return new ResponseMessage<>(HttpStatus.OK.value(), MsgConstants.USER_CREATED_SUCCESSFULLY,
				studentService.createStudent(student));
	} 
	/**
	 * To get Student list
	 * 
	 * @return
	 */
	@GetMapping("/getall")
	public ResponseMessage<List<Student>> getStudentList() {
		return new ResponseMessage<>(HttpStatus.OK.value(), studentService.getStudentList());
	}
	
	/**
	 * To get Student by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getbyid")
	public ResponseMessage<Student> getById(@Valid @RequestParam Long id) {
		return new ResponseMessage<>(HttpStatus.OK.value(), studentService.getStudentById(id));
	}

	/**
	 * To delete User
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deletebyid")
	public ResponseMessage<Boolean> deleteStudentById(@Valid @RequestParam Long id) {
		return new ResponseMessage<>(HttpStatus.OK.value(), studentService.deleteStudentById(id));
	}

	
	/**
	 * To delete User
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/changepassword")
	public ResponseMessage<Boolean> changepassword(@Valid @RequestParam String password,@Valid @RequestParam Long id) {
		return new ResponseMessage<>(HttpStatus.OK.value(), studentService.changePassword(password, id));
	}
	
	/**
	 * To User login
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/login")
	public ResponseMessage<Student> studentLogin(@Valid @RequestBody Map<String,Object> student) {
		String phone =(String) student.get("phone");
		String password=(String) student.get("password");
		return new ResponseMessage<>(HttpStatus.OK.value(), studentService.studentLogin(phone,password));
	}
	
}
