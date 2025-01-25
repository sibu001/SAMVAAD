package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.model.Session;
import com.divergent.meet.divergentmeet.model.User;
import com.divergent.meet.divergentmeet.repository.SessionRepository;
import com.divergent.meet.divergentmeet.repository.UserRepository;
import com.divergent.meet.divergentmeet.service.SessionService;
import com.divergent.meet.divergentmeet.utility.AuthorizationUtility;

@Service
public class SessionServiceImpl implements SessionService {
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Session create(@Valid Session session) {

		session = sessionRepository.save(session);

		return session;
	}

	@Override
	public Session update(@Valid Session session) {

		Optional<Session> sessionOldId = sessionRepository.findById(session.getId());
		if (!sessionOldId.isPresent()) {
			throw new GenricException("Session not found");
		}
		
		session = sessionRepository.save(session);

		return session;

	}

	@Override
	public List<Session> getAll() {
		// TODO Auto-generated method stub
		return sessionRepository.findAll();
	}

	@Override
	public List<Session> getAllBySession(Long sessionId) {
		// TODO Auto-generated method stub
		return sessionRepository.getAllBySession(sessionId);
	}

	@Override
	public Session get(Long id) {
		Optional<Session> sessionOldId = sessionRepository.findById(id);
		if (!sessionOldId.isPresent()) {
			throw new GenricException("Session not found");
		}
		return sessionOldId.get();
	}

	@Override
	public void delete(@Valid Long id) {

		sessionRepository.deleteById(id);

	}

	@Override
	public List<Session> getForStudentAll() {
		String username=AuthorizationUtility.getLoggedInUsername();
		User student=userRepository.findUserByUsername(username);
		if(student!=null && student.getMyclass()!=null && student.getSection()!=null) {
			return sessionRepository.findByClassSectionLaunced(student.getMyclass().getId(),student.getSection().getId());
		}
		return new ArrayList<>();
	}
}
