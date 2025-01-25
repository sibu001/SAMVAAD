package com.divergent.meet.divergentmeet.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.divergent.meet.divergentmeet.model.audit.UserDateAudit;

@Entity
@Table(name = "meeting")
public class Meeting extends UserDateAudit {
	private static final long serialVersionUID = -5083581084976352921L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean scheduleMeeting;
	@NotBlank
	@Size(max = 200)
	private String meetingId;
	private Boolean passwordRequired;
	private String password;
	private String videoHost;
	private String videoParticipant;
	private String audio;
	private Boolean enableJoinbeforehost;
	private Boolean muteParticipantsUponEntry;
	private Boolean enableWaitingRoom;
	private Boolean recordMeetingAutomaticallyOnTheLocalComputer;
	private String meetingRecordingUrl;
	
	@ManyToOne
	@JoinColumn(name = "class_id")
	private MyClass myclass;
	
	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;
	
	private String topic;
	private String discription;
	private LocalDate date;
	private LocalTime time;
	private Integer durationInHour;
	private Integer durationInMinuit;
	private String timeZone;
	private Boolean recurringMeeting;
	private String recurrenceType;
	private Integer repeatEvery;
	private String endType;
	private LocalDateTime endDate;
	private Integer endOccurrence;
	private String occureBy;
	private Integer dayOfMonth;
	private String occurenceOfDay;
	private String dayOfOccurence;
	private Boolean sunOccurence;
	private Boolean monOccurence;
	private Boolean tueOccurence;
	private Boolean wedOccurence;
	private Boolean thuOccurence;
	private Boolean friOccurence;
	private Boolean satOccurence;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getScheduleMeeting() {
		return scheduleMeeting;
	}
	public void setScheduleMeeting(Boolean scheduleMeeting) {
		this.scheduleMeeting = scheduleMeeting;
	}
	public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	public Boolean getPasswordRequired() {
		return passwordRequired;
	}
	public void setPasswordRequired(Boolean passwordRequired) {
		this.passwordRequired = passwordRequired;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVideoHost() {
		return videoHost;
	}
	public void setVideoHost(String videoHost) {
		this.videoHost = videoHost;
	}
	public String getVideoParticipant() {
		return videoParticipant;
	}
	public void setVideoParticipant(String videoParticipant) {
		this.videoParticipant = videoParticipant;
	}
	public String getAudio() {
		return audio;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public Boolean getEnableJoinbeforehost() {
		return enableJoinbeforehost;
	}
	public void setEnableJoinbeforehost(Boolean enableJoinbeforehost) {
		this.enableJoinbeforehost = enableJoinbeforehost;
	}
	public Boolean getMuteParticipantsUponEntry() {
		return muteParticipantsUponEntry;
	}
	public void setMuteParticipantsUponEntry(Boolean muteParticipantsUponEntry) {
		this.muteParticipantsUponEntry = muteParticipantsUponEntry;
	}
	public Boolean getEnableWaitingRoom() {
		return enableWaitingRoom;
	}
	public void setEnableWaitingRoom(Boolean enableWaitingRoom) {
		this.enableWaitingRoom = enableWaitingRoom;
	}
	public Boolean getRecordMeetingAutomaticallyOnTheLocalComputer() {
		return recordMeetingAutomaticallyOnTheLocalComputer;
	}
	public void setRecordMeetingAutomaticallyOnTheLocalComputer(Boolean recordMeetingAutomaticallyOnTheLocalComputer) {
		this.recordMeetingAutomaticallyOnTheLocalComputer = recordMeetingAutomaticallyOnTheLocalComputer;
	}
	public MyClass getMyclass() {
		return myclass;
	}
	public void setMyclass(MyClass myclass) {
		this.myclass = myclass;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public Integer getDurationInHour() {
		return durationInHour;
	}
	public void setDurationInHour(Integer durationInHour) {
		this.durationInHour = durationInHour;
	}
	public Integer getDurationInMinuit() {
		return durationInMinuit;
	}
	public void setDurationInMinuit(Integer durationInMinuit) {
		this.durationInMinuit = durationInMinuit;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public Boolean getRecurringMeeting() {
		return recurringMeeting;
	}
	public void setRecurringMeeting(Boolean recurringMeeting) {
		this.recurringMeeting = recurringMeeting;
	}
	public String getRecurrenceType() {
		return recurrenceType;
	}
	public void setRecurrenceType(String recurrenceType) {
		this.recurrenceType = recurrenceType;
	}
	public Integer getRepeatEvery() {
		return repeatEvery;
	}
	public void setRepeatEvery(Integer repeatEvery) {
		this.repeatEvery = repeatEvery;
	}
	public String getEndType() {
		return endType;
	}
	public void setEndType(String endType) {
		this.endType = endType;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public Integer getEndOccurrence() {
		return endOccurrence;
	}
	public void setEndOccurrence(Integer endOccurrence) {
		this.endOccurrence = endOccurrence;
	}
	public String getOccureBy() {
		return occureBy;
	}
	public void setOccureBy(String occureBy) {
		this.occureBy = occureBy;
	}
	public Integer getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	
	public String getOccurenceOfDay() {
		return occurenceOfDay;
	}
	public void setOccurenceOfDay(String occurenceOfDay) {
		this.occurenceOfDay = occurenceOfDay;
	}
	public String getDayOfOccurence() {
		return dayOfOccurence;
	}
	public void setDayOfOccurence(String dayOfOccurence) {
		this.dayOfOccurence = dayOfOccurence;
	}
	public Boolean getSunOccurence() {
		return sunOccurence;
	}
	public void setSunOccurence(Boolean sunOccurence) {
		this.sunOccurence = sunOccurence;
	}
	public Boolean getMonOccurence() {
		return monOccurence;
	}
	public void setMonOccurence(Boolean monOccurence) {
		this.monOccurence = monOccurence;
	}
	public Boolean getTueOccurence() {
		return tueOccurence;
	}
	public void setTueOccurence(Boolean tueOccurence) {
		this.tueOccurence = tueOccurence;
	}
	public Boolean getWedOccurence() {
		return wedOccurence;
	}
	public void setWedOccurence(Boolean wedOccurence) {
		this.wedOccurence = wedOccurence;
	}
	public Boolean getThuOccurence() {
		return thuOccurence;
	}
	public void setThuOccurence(Boolean thuOccurence) {
		this.thuOccurence = thuOccurence;
	}
	public Boolean getFriOccurence() {
		return friOccurence;
	}
	public void setFriOccurence(Boolean friOccurence) {
		this.friOccurence = friOccurence;
	}
	public Boolean getSatOccurence() {
		return satOccurence;
	}
	public void setSatOccurence(Boolean satOccurence) {
		this.satOccurence = satOccurence;
	}
	public String getMeetingRecordingUrl() {
		return meetingRecordingUrl;
	}
	public void setMeetingRecordingUrl(String meetingRecordingUrl) {
		this.meetingRecordingUrl = meetingRecordingUrl;
	}
}
