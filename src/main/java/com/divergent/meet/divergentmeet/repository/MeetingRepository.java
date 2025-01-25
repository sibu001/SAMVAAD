package com.divergent.meet.divergentmeet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.divergent.meet.divergentmeet.model.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
	Meeting findByMeetingId(String meetingId);
	
	@Query("select m from Meeting m where m.myclass.name=?1 and m.section.name=?2")
	public List<Meeting> findMeetingByClassAndSection(String className,String sectionName);

	
	@Query("select m from Meeting m where m.meetingRecordingUrl IS NULL")
	public List<Meeting> findMeetingByNullRecordingUrl();
	
	@Transactional
	@Modifying
	@Query("UPDATE Meeting SET meetingRecordingUrl=?1 WHERE id=?2")
	public int updateMeetingRecordingUrl(String meetingRecordingUrl, Long id);
}
