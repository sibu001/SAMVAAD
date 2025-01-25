package com.divergent.meet.divergentmeet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.divergent.meet.divergentmeet.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

	@Query("select s from Session s where s.sessionMaster.id=:sessionId")
	List<Session> getAllBySession(@Param("sessionId") Long sessionId);

	@Query("select s from Session s where s.myclass.id=:classId and s.section.id=:sectionId and (launched is null or launched=true) ")
	List<Session> findByClassSectionLaunced(@Param("classId") Long classId,@Param("sectionId") Long sectionId);

}
