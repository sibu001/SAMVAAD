package com.divergent.meet.divergentmeet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.divergent.meet.divergentmeet.model.TestCase;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

	@Query("select tc from TestCase tc where tc.question.id=:id")
	List<TestCase> findByQuestionId(@Param("id")Long id);

}
