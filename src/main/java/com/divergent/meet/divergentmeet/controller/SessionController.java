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

import com.divergent.meet.divergentmeet.model.Session;
import com.divergent.meet.divergentmeet.service.SessionService;

@RestController
@RequestMapping("/api/session")
public class SessionController {
	@Autowired
	SessionService sessionService;

	/**
	 * To create Session
	 * 
	 * @param session
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<Session> create(@Valid @RequestBody Session session) {
		return new ResponseEntity<Session>(sessionService.create(session), HttpStatus.OK);
	}

	/**
	 * To update Session
	 * 
	 * @param session
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<Session> update(@Valid @RequestBody Session session) {
		return new ResponseEntity<Session>(sessionService.update(session), HttpStatus.OK);
	}

	/**
	 * To get Session list
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<Session>> getAll() {
		return new ResponseEntity<List<Session>>(sessionService.getAll(), HttpStatus.OK);
	}
	/**
	 * To get Session list
	 * 
	 * @return
	 */
	@GetMapping("forstudent")
	public ResponseEntity<List<Session>> getForStudentAll() {
		return new ResponseEntity<List<Session>>(sessionService.getForStudentAll(), HttpStatus.OK);
	}
	/**
	 * To get Session by subject id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("bymaster/{id}")
	public ResponseEntity<List<Session>> getAllBySession(@PathVariable("id") Long id) {
		return new ResponseEntity<List<Session>>(sessionService.getAllBySession(id), HttpStatus.OK);
	}

	/**
	 * To get Session by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Session> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<Session>(sessionService.get(id), HttpStatus.OK);
	}
	

	/**
	 * To delete class
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		sessionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
