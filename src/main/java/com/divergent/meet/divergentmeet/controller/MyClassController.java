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

import com.divergent.meet.divergentmeet.model.MyClass;
import com.divergent.meet.divergentmeet.service.MyClassService;

@RestController
@RequestMapping("/api/class")
public class MyClassController {
	@Autowired
	MyClassService myClassService;

	/**
	 * To create MyClass
	 * 
	 * @param myClass
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<MyClass> create(@Valid @RequestBody MyClass myClass) {
		return new ResponseEntity<MyClass>(myClassService.create(myClass), HttpStatus.OK);
	}

	/**
	 * To update MyClass
	 * 
	 * @param myClass
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<MyClass> update(@Valid @RequestBody MyClass myClass) {
		return new ResponseEntity<MyClass>(myClassService.update(myClass), HttpStatus.OK);
	}

	/**
	 * To get MyClass list
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<MyClass>> getAll() {
		return new ResponseEntity<List<MyClass>>(myClassService.getAll(), HttpStatus.OK);
	}

	/**
	 * To get MyClass by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<MyClass> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<MyClass>(myClassService.get(id), HttpStatus.OK);
	}

	/**
	 * To delete class
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		myClassService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
