package com.divergent.meet.divergentmeet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.divergent.meet.divergentmeet.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	
	@Query("select q from Question q where q.session.id=:sessionId ")
	List<Question> findBySession(@Param("sessionId") Long sessionId);
	
}
