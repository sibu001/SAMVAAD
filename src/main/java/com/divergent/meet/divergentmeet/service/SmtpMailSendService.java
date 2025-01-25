package com.divergent.meet.divergentmeet.service;


import com.divergent.meet.divergentmeet.model.Meeting;
import com.divergent.meet.divergentmeet.model.User;

public interface SmtpMailSendService {

	//public Boolean userCreate(String u, String smail, StringBuilder sb);
	
	public Boolean meetingCreate(String email,  StringBuilder sb);
	
	public void sendMailOfMeetingToStudent(Meeting meeting);

}
