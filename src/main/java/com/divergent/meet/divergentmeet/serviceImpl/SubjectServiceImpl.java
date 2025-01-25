package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.model.Subject;
import com.divergent.meet.divergentmeet.repository.SubjectRepository;
import com.divergent.meet.divergentmeet.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public Subject create(@Valid Subject subject) {

		Subject subjectOld = subjectRepository.findByName(subject.getName());
		if (subjectOld != null) {
			throw new GenricException("Class name already used");
		}
		subject = subjectRepository.save(subject);

		return subject;
	}

	@Override
	public Subject update(@Valid Subject subject) {

		Optional<Subject> subjectOldId = subjectRepository.findById(subject.getId());
		if (!subjectOldId.isPresent()) {
			throw new GenricException("Class not found");
		}
		Subject subjectOldName = subjectRepository.findByName(subject.getName());
		if (subjectOldName != null && !subjectOldName.getId().equals(subject.getId())) {
			throw new GenricException("Class name already used");
		}
		subject = subjectRepository.save(subject);

		return subject;

	}

	@Override
	public List<Subject> getAll() {
		return subjectRepository.findAll();
	}

	@Override
	public Subject get(Long id) {
		Optional<Subject> subjectOldId = subjectRepository.findById(id);
		if (!subjectOldId.isPresent()) {
			throw new GenricException("Subject not found");
		}
		return subjectOldId.get();
	}

	@Override
	public void delete(@Valid Long id) {
		
		subjectRepository.deleteById(id);

	}
}
