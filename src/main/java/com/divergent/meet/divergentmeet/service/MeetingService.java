package com.divergent.meet.divergentmeet.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;

import com.divergent.meet.divergentmeet.model.Meeting;

public interface MeetingService {
	Meeting create(@Valid Meeting meeting);

	Meeting update(@Valid Meeting meeting);

	List<Meeting> getAll();

	Meeting get(Long id);

	void delete(@Valid Long id);
	
	public List<Meeting> findMeetingByClassAndSection(Long id);
	
	public void updateMeetingRecording();
}
