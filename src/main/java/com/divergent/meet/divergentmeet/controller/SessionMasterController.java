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

import com.divergent.meet.divergentmeet.model.SessionMaster;
import com.divergent.meet.divergentmeet.service.SessionMasterService;

@RestController
@RequestMapping("/api/session/master")
public class SessionMasterController {
	@Autowired
	SessionMasterService sessionService;

	/**
	 * To create Session
	 * 
	 * @param session
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<SessionMaster> create(@Valid @RequestBody SessionMaster session) {
		return new ResponseEntity<SessionMaster>(sessionService.create(session), HttpStatus.OK);
	}

	/**
	 * To update Session
	 * 
	 * @param session
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<SessionMaster> update(@Valid @RequestBody SessionMaster session) {
		return new ResponseEntity<SessionMaster>(sessionService.update(session), HttpStatus.OK);
	}

	/**
	 * To get Session list
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<SessionMaster>> getAll() {
		return new ResponseEntity<List<SessionMaster>>(sessionService.getAll(), HttpStatus.OK);
	}
	/**
	 * To get Session by subject id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("subject/{id}")
	public ResponseEntity<List<SessionMaster>> getBySubjectId(@PathVariable("id") Long id) {
		return new ResponseEntity<List<SessionMaster>>(sessionService.getAllBySubject(id), HttpStatus.OK);
	}

	/**
	 * To get Session by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<SessionMaster> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<SessionMaster>(sessionService.get(id), HttpStatus.OK);
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
