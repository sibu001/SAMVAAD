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

import com.divergent.meet.divergentmeet.model.Section;
import com.divergent.meet.divergentmeet.service.SectionService;

@RestController
@RequestMapping("/api/section")
public class SectionController {
	@Autowired
	SectionService sectionService;

	/**
	 * To create Section
	 * 
	 * @param section
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<Section> create(@Valid @RequestBody Section section) {
		return new ResponseEntity<Section>(sectionService.create(section), HttpStatus.OK);
	}

	/**
	 * To update Section
	 * 
	 * @param section
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<Section> update(@Valid @RequestBody Section section) {
		return new ResponseEntity<Section>(sectionService.update(section), HttpStatus.OK);
	}

	/**
	 * To get Section list
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<Section>> getAll() {
		return new ResponseEntity<List<Section>>(sectionService.getAll(), HttpStatus.OK);
	}

	/**
	 * To get Section by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Section> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<Section>(sectionService.get(id), HttpStatus.OK);
	}

	/**
	 * To delete class
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		sectionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
