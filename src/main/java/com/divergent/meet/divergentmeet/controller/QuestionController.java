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

import com.divergent.meet.divergentmeet.dto.QuestionDTO;
import com.divergent.meet.divergentmeet.model.Question;
import com.divergent.meet.divergentmeet.service.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
	@Autowired
	QuestionService questionService;

	/**
	 * To create Question
	 * 
	 * @param question
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<QuestionDTO> create(@Valid @RequestBody Question question) {
		return new ResponseEntity<QuestionDTO>(questionService.create(question), HttpStatus.OK);
	}

	/**
	 * To update Question
	 * 
	 * @param question
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<QuestionDTO> update(@Valid @RequestBody Question question) {
		return new ResponseEntity<QuestionDTO>(questionService.update(question), HttpStatus.OK);
	}

	/**
	 * To get Question list
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<QuestionDTO>> getAll() {
		return new ResponseEntity<List<QuestionDTO>>(questionService.getAll(), HttpStatus.OK);
	}

	/**
	 * To get Question list
	 * 
	 * @return
	 */
	@GetMapping("/session/{id}")
	public ResponseEntity<List<QuestionDTO>> getAllBySessionId(@PathVariable("id") Long id) {
		return new ResponseEntity<List<QuestionDTO>>(questionService.getAllBySession(id), HttpStatus.OK);
	}

	/**
	 * To get Question by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<QuestionDTO> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<QuestionDTO>(questionService.get(id), HttpStatus.OK);
	}

	/**
	 * To delete class
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		questionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
