package com.divergent.meet.divergentmeet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.divergent.meet.divergentmeet.model.SessionMaster;

@Repository
public interface SessionMasterRepository  extends JpaRepository<SessionMaster, Long> {

	SessionMaster findByName(String name);

	@Query("select s from SessionMaster s where s.subject.id=:subjectId")
	List<SessionMaster> getAllBySubject(@Param("subjectId") Long subjectId);
	
	

}
