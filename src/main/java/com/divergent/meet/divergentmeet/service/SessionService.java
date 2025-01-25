package com.divergent.meet.divergentmeet.service;

import java.util.List;

import javax.validation.Valid;

import com.divergent.meet.divergentmeet.model.Session;

public interface SessionService {
	Session create(@Valid Session session);

	Session update(@Valid Session session);

	List<Session> getAll();

	Session get(Long id);

	void delete(@Valid Long id);

	List<Session> getAllBySession(Long subjectId);

	List<Session> getForStudentAll();
}
