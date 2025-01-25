package com.divergent.meet.divergentmeet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divergent.meet.divergentmeet.model.Prosody;

public interface ProsodyRepository extends JpaRepository<Prosody, Long> {
	
	/**
	 * Find {@link Prosody} by User
	 * 
	 * @param user
	 * @return {@link Prosody}
	 */
	public Prosody findProsodyByUser(String user);
	
}
