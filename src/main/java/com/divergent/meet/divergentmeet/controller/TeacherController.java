package com.divergent.meet.divergentmeet.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.divergent.meet.divergentmeet.model.Teacher;
import com.divergent.meet.divergentmeet.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	/**
	 * To create Teacher
	 * 
	 * @param teacher
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<Teacher> create(@Valid @RequestBody Teacher teacher) {
		return new ResponseEntity<Teacher>(teacherService.create(teacher), HttpStatus.OK);
	}

	/**
	 * To update Teacher
	 * 
	 * @param teacher
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<Teacher> update(@Valid @RequestBody Teacher teacher) {
		return new ResponseEntity<Teacher>(teacherService.update(teacher), HttpStatus.OK);
	}

	/**
	 * To get Teacher list
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<Teacher>> getAll() {
		return new ResponseEntity<List<Teacher>>(teacherService.getAll(), HttpStatus.OK);
	}

	/**
	 * To get Teacher by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Teacher> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<Teacher>(teacherService.get(id), HttpStatus.OK);
	}

	/**
	 * To delete class
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		teacherService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
