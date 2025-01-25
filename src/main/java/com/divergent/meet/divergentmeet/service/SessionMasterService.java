package com.divergent.meet.divergentmeet.service;

import java.util.List;

import javax.validation.Valid;

import com.divergent.meet.divergentmeet.model.SessionMaster;

public interface SessionMasterService {
	SessionMaster create(@Valid SessionMaster session);

	SessionMaster update(@Valid SessionMaster session);

	List<SessionMaster> getAll();

	SessionMaster get(Long id);

	void delete(@Valid Long id);

	List<SessionMaster> getAllBySubject(Long subjectId);
}
