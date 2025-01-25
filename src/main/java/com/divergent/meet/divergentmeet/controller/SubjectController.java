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

import com.divergent.meet.divergentmeet.model.Subject;
import com.divergent.meet.divergentmeet.service.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
	@Autowired
	SubjectService subjectService;

	/**
	 * To create Subject
	 * 
	 * @param subject
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<Subject> create(@Valid @RequestBody Subject subject) {
		return new ResponseEntity<Subject>(subjectService.create(subject), HttpStatus.OK);
	}

	/**
	 * To update Subject
	 * 
	 * @param subject
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<Subject> update(@Valid @RequestBody Subject subject) {
		return new ResponseEntity<Subject>(subjectService.update(subject), HttpStatus.OK);
	}

	/**
	 * To get Subject list
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<Subject>> getAll() {
		return new ResponseEntity<List<Subject>>(subjectService.getAll(), HttpStatus.OK);
	}

	/**
	 * To get Subject by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Subject> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<Subject>(subjectService.get(id), HttpStatus.OK);
	}

	/**
	 * To delete class
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		subjectService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
