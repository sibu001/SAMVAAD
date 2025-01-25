package com.divergent.meet.divergentmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divergent.meet.divergentmeet.model.Section;

@Repository
public interface SectionRepository  extends JpaRepository<Section, Long>{
	public Section findByName(String name);

	

}
