package com.divergent.meet.divergentmeet.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.divergent.meet.divergentmeet.model.StudentAnswer;
import com.divergent.meet.divergentmeet.model.StudentTestCase;
import com.divergent.meet.divergentmeet.service.StudentAnswerService;

@RestController
@RequestMapping("/api/sudentAnswer")
public class StudentAnswerController {
	@Autowired
	StudentAnswerService studentAnswerService;



	/**
	 * To create StudentAnswer
	 * 
	 * @param studentAnswer
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<StudentAnswer> create(@Valid @RequestBody StudentAnswer studentAnswer) {
		return new ResponseEntity<StudentAnswer>(studentAnswerService.create(studentAnswer), HttpStatus.OK);
	}
	
	

	/**
	 * To update StudentAnswer
	 * 
	 * @param studentAnswer
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<StudentAnswer> update(@Valid @RequestBody StudentAnswer studentAnswer) {
		return new ResponseEntity<StudentAnswer>(studentAnswerService.update(studentAnswer), HttpStatus.OK);
	}

	/**
	 * To get StudentAnswer list
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<StudentAnswer>> getAll() {
		return new ResponseEntity<List<StudentAnswer>>(studentAnswerService.getAll(), HttpStatus.OK);
	}
	
	
	/**
	 * To get StudentAnswer list
	 * 
	 * @return
	 */
	@GetMapping("byStudentAndSession")
	public ResponseEntity<List<Map<String,Object>>> getByStudentAndSession(@RequestParam("studentId") Long studentId, @RequestParam("sessionId") Long sessionId ) {
		return new ResponseEntity<List<Map<String,Object>>>(studentAnswerService.getByStudentAndSession(studentId,sessionId), HttpStatus.OK);
	}
	
	/**
	 * To get StudentAnswer list
	 * 
	 * @return
	 */
	@GetMapping("/question/{id}")
	public ResponseEntity<List<Map<String,Object>>> getAllByQuestion(@PathVariable("id") Long id) {
		return new ResponseEntity<List<Map<String,Object>>>(studentAnswerService.getAllByQuestion(id), HttpStatus.OK);
	}
	
	


	/**
	 * To get StudentAnswer by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<StudentAnswer> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<StudentAnswer>(studentAnswerService.get(id), HttpStatus.OK);
	}
	
	/**
	 * To get StudentAnswer by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("testcase/{id}")
	public ResponseEntity<List<Map<String,Object>>> getTestCaseById(@PathVariable("id") Long id) {
		return new ResponseEntity<List<Map<String,Object>>>(studentAnswerService.getTestCaseById(id), HttpStatus.OK);
	}

	/**
	 * To delete class
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		studentAnswerService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
