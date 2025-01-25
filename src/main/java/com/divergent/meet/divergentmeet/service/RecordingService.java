package com.divergent.meet.divergentmeet.service;

import java.util.List;

import com.divergent.meet.divergentmeet.model.Recording;

public interface RecordingService {
	public void saveRecording(Recording recording);
	
	public List<Recording> getRecordingById(String id);
}
