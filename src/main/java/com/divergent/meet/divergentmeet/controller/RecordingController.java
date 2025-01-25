package com.divergent.meet.divergentmeet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divergent.meet.divergentmeet.model.Meeting;
import com.divergent.meet.divergentmeet.model.Recording;
import com.divergent.meet.divergentmeet.service.RecordingService;

@RestController
@RequestMapping("/api/recording")
public class RecordingController {

	@Autowired
	RecordingService recordingService;
	
	/**
	 * To get Recording list
	 * 
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<List<Recording>> getByMeetingId(@PathVariable("id") String id) {
		return new ResponseEntity<List<Recording>>(recordingService.getRecordingById(id), HttpStatus.OK);
	}
}
