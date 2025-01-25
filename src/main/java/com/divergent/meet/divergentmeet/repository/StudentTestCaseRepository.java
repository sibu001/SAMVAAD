package com.divergent.meet.divergentmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divergent.meet.divergentmeet.model.StudentTestCase;

@Repository
public interface StudentTestCaseRepository extends JpaRepository<StudentTestCase, Long> {

}
