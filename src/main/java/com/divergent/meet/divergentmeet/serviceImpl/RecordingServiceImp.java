package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.model.Recording;
import com.divergent.meet.divergentmeet.repository.RecordingRepository;
import com.divergent.meet.divergentmeet.service.RecordingService;

@Service
public class RecordingServiceImp implements RecordingService {

	@Autowired
	RecordingRepository recordingRepository;
	
	@Override
	public void saveRecording(Recording recording) {
		recordingRepository.save(recording);
	}

	@Override
	public List<Recording> getRecordingById(String id) {
		
		return recordingRepository.getRecordingByMeetingId(id);
	}

}
