package com.divergent.meet.divergentmeet.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.annotations.UpdateTimestamp;
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

import com.divergent.meet.divergentmeet.model.Meeting;
import com.divergent.meet.divergentmeet.service.MeetingService;
import com.divergent.meet.divergentmeet.utility.ResponseMessage;

@RestController
@RequestMapping("/api/meeting")
public class MeetingController {
	@Autowired
	MeetingService meetingService;

	/**
	 * To create Meeting
	 * 
	 * @param meeting
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<Meeting> create(@Valid @RequestBody Meeting meeting) {
		return new ResponseEntity<Meeting>(meetingService.create(meeting), HttpStatus.OK);
	}

	/**
	 * To update Meeting
	 * 
	 * @param meeting
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<Meeting> update(@Valid @RequestBody Meeting meeting) {
		return new ResponseEntity<Meeting>(meetingService.update(meeting), HttpStatus.OK);
	}

	/**
	 * To get Meeting list
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<Meeting>> getAll() {
		return new ResponseEntity<List<Meeting>>(meetingService.getAll(), HttpStatus.OK);
	}

	/**
	 * To get Meeting by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Meeting> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<Meeting>(meetingService.get(id), HttpStatus.OK);
	}

	/**
	 * To delete class
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		meetingService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * To get meeting by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getall")
	public ResponseMessage<List<Meeting>> getByClassAndSection(@Valid @RequestParam Long id) {
		return new ResponseMessage<>(HttpStatus.OK.value(), meetingService.findMeetingByClassAndSection(id));
	}
	
	/**
	 * To get meeting by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/updatemeetingRecording")
	public ResponseMessage<String> updateMeetingRecording() {
	   meetingService.updateMeetingRecording();
		return new ResponseMessage<>(HttpStatus.OK.value(),"Recording Udated Sucessfully");
	}
}
