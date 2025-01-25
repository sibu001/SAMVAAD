package com.divergent.meet.divergentmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divergent.meet.divergentmeet.model.Subject;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Long>  {

	Subject findByName(String name);

}
