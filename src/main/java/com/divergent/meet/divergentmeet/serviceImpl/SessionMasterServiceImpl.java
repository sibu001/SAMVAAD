package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.model.SessionMaster;
import com.divergent.meet.divergentmeet.repository.SessionMasterRepository;
import com.divergent.meet.divergentmeet.service.SessionMasterService;

@Service
public class SessionMasterServiceImpl implements SessionMasterService {
	@Autowired
	private SessionMasterRepository sessionRepository;

	@Override
	public SessionMaster create(@Valid SessionMaster session) {

		SessionMaster sessionOld = sessionRepository.findByName(session.getName());
		if (sessionOld != null) {
			throw new GenricException("Session name already used");
		}
		session = sessionRepository.save(session);

		return session;
	}

	@Override
	public SessionMaster update(@Valid SessionMaster session) {

		Optional<SessionMaster> sessionOldId = sessionRepository.findById(session.getId());
		if (!sessionOldId.isPresent()) {
			throw new GenricException("Session not found");
		}
		SessionMaster sessionOldName = sessionRepository.findByName(session.getName());
		if (sessionOldName != null && !sessionOldName.getId().equals(session.getId())) {
			throw new GenricException("Session name already used");
		}
		session = sessionRepository.save(session);

		return session;

	}

	@Override
	public List<SessionMaster> getAll() {
		// TODO Auto-generated method stub
		return sessionRepository.findAll();
	}
	@Override
	public List<SessionMaster> getAllBySubject(Long subjectId) {
		// TODO Auto-generated method stub
		return sessionRepository.getAllBySubject(subjectId);
	}

	@Override
	public SessionMaster get(Long id) {
		Optional<SessionMaster> sessionOldId = sessionRepository.findById(id);
		if (!sessionOldId.isPresent()) {
			throw new GenricException("Session not found");
		}
		return sessionOldId.get();
	}

	@Override
	public void delete(@Valid Long id) {
		
		sessionRepository.deleteById(id);

	}
}
