package com.divergent.meet.divergentmeet.serviceImpl;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.model.Meeting;
import com.divergent.meet.divergentmeet.model.Recording;
import com.divergent.meet.divergentmeet.model.Student;
import com.divergent.meet.divergentmeet.repository.MeetingRepository;
import com.divergent.meet.divergentmeet.repository.RecordingRepository;
import com.divergent.meet.divergentmeet.repository.StudentRepository;
import com.divergent.meet.divergentmeet.service.MeetingService;
import com.divergent.meet.divergentmeet.service.SmtpMailSendService;

@Service
public class MeetingServiceImpl implements MeetingService {
	@Autowired
	private MeetingRepository meetingRepository;
	
	@Autowired
	SmtpMailSendService smtpMailSendService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	RecordingRepository recordingRepository;
	

	@Override
	public Meeting create(@Valid Meeting meeting) {

		if (meeting.getMeetingId() == null) {
			throw new GenricException("Meeting Id can not be null");
		}
		if (meeting.getMeetingId() != null) {
			Meeting meetingOldId = meetingRepository.findByMeetingId(meeting.getMeetingId());
			if (meetingOldId != null) {
				throw new GenricException("Meeting Id already used");
			}
		} else {

		}
		if(meeting.getDate()==null) {
			meeting.setDate(LocalDate.now());
		}
		if(meeting.getTime()==null) {
			meeting.setTime(LocalTime.now());
		}
		meeting = meetingRepository.save(meeting);
		List<Student> lst=studentRepository.findByMyclassIdAndSectionId(meeting.getMyclass().getId(),meeting.getSection().getId());
		StringBuilder sb= mailBody(meeting);
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < lst.size(); i++) {
					Student studetns=lst.get(i);
					try {
						smtpMailSendService.meetingCreate(studetns.getEmail(),sb);
					} catch (Exception e) {
						//throw new UserRequestException("Mail is not sent.");
					}	
				}		
			}
		});
		t1.start();
		
		
		
		
		return meeting;
	}
	
	/**
	 * To create Mail body (for temporary user only)
	 * 
	 * @param u
	 * @return
	 */
	private StringBuilder mailBody(Meeting m) {
//		String password = u.getPassword();
		StringBuilder sBody = new StringBuilder();
		sBody.append("<html>");
		sBody.append("<head>");
		sBody.append("</head>");
		sBody.append("<body>");
		sBody.append("Hi , Student" + "<br><br>");
		sBody.append("Your .<br>Online classes Details Here");
		sBody.append("<br><br>");
		sBody.append("Topic Name : " + m.getTopic());
		sBody.append("<br><br>Meeting Id : " + m.getMeetingId());
		sBody.append("<br><br>Please Attend the class");
		sBody.append("<br><br><br>");
		sBody.append("Thank You,");
		return sBody;
	}


	@Override
	public Meeting update(@Valid Meeting meeting) {

		Optional<Meeting> meetingOldId = meetingRepository.findById(meeting.getId());
		if (!meetingOldId.isPresent()) {
			throw new GenricException("Meeting not found");
		}

		meeting = meetingRepository.save(meeting);

		return meeting;

	}

	@Override
	public List<Meeting> getAll() {
		// TODO Auto-generated method stub
		return meetingRepository.findAll();
	}

	@Override
	public Meeting get(Long id) {
		Optional<Meeting> meetingOldId = meetingRepository.findById(id);
		if (!meetingOldId.isPresent()) {
			throw new GenricException("Class not found");
		}
		return meetingOldId.get();
	}

	@Override
	public void delete(@Valid Long id) {
		meetingRepository.deleteById(id);

	}

	@Override
	public List<Meeting> findMeetingByClassAndSection(Long id) {
		Student st=studentRepository.findStudentById(id);
		return meetingRepository.findMeetingByClassAndSection(st.getMyclass().getName(), st.getSection().getName());
	}

	@Override
	public void updateMeetingRecording(){
		getVideoRecordingFile();
	}
	
	@Scheduled(cron = "0 0 0/2 1/1 * ? *",zone = "IST")
	public void getVideoRecordingFile(){
		
		List<Meeting> meetingList=meetingRepository.findAll();
		List<Map<String,Object>> meetingRecordingFilePath=new ArrayList<>();
		File folder = new File("/tmp/recordings/");

		File[] files = folder.listFiles();
		Map<String,Object> meetingPath=new HashMap<String,Object>();
		
		for (File file : files) {
			if (file.isDirectory()) {
				File folder2 = new File(file.getAbsolutePath());
				File[] files2 = folder2.listFiles();
				for (File file2 : files2) {
					if (file2.isFile()&&file2.getName().toLowerCase().endsWith(".mp4")) {
						String[] arrOfStr=file2.getName().split("_", 2);
						meetingPath.put("id", arrOfStr[0]);
						meetingPath.put("path", file2.getAbsolutePath().substring(4));
						meetingRecordingFilePath.add(meetingPath);
						for(Meeting m:meetingList) {
							if(m.getMeetingId().equals(arrOfStr[0])) {
								//meetingRepository.updateMeetingRecordingUrl(file2.getAbsolutePath().substring(4),m.getId());
								Recording recording=new Recording();
								recording.setMeetingId(m.getMeetingId());
								recording.setRecordingUrl(file2.getAbsolutePath().substring(4));
								try {
									recordingRepository.save(recording);
								}catch(Exception ex) {
									
								}
							}
						}
					}
				}
			}
		}
	}
	
}
