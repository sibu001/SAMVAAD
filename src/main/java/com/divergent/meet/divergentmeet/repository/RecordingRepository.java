package com.divergent.meet.divergentmeet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divergent.meet.divergentmeet.model.Recording;

public interface RecordingRepository extends JpaRepository<Recording, Long> {
 
	
	public List<Recording> getRecordingByMeetingId(String id);
	
}
