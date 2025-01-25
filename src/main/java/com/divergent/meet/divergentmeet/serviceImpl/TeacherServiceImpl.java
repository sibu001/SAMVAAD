package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.model.Teacher;
import com.divergent.meet.divergentmeet.repository.TeacherRepository;
import com.divergent.meet.divergentmeet.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Teacher create(@Valid Teacher teacher) {

		if (teacher.getEmail() != null) {
			Teacher teacherOld = teacherRepository.findByEmail(teacher.getEmail());
			if (teacherOld != null) {
				throw new GenricException("Email already used");
			}
		}
		if (teacher.getPhone() != null) {
			Teacher teacherOld = teacherRepository.findByPhone(teacher.getPhone());
			if (teacherOld != null) {
				throw new GenricException("Phone already used");
			}
		}
		

		teacher = teacherRepository.save(teacher);

		return teacher;
	}

	@Override
	public Teacher update(@Valid Teacher teacher) { 

		if (teacher.getEmail() != null) {
			Teacher teacherOld = teacherRepository.findByEmail(teacher.getEmail());
			if (teacherOld != null && !teacher.getId().equals(teacherOld.getId())) {
				throw new GenricException("Email already used");
			}
		}
		if (teacher.getPhone() != null) {
			Teacher teacherOld = teacherRepository.findByPhone(teacher.getPhone());
			if (teacherOld != null && !teacher.getId().equals(teacherOld.getId())) {
				throw new GenricException("Phone already used");
			}
		}
		teacher = teacherRepository.save(teacher);

		return teacher;

	}

	@Override
	public List<Teacher> getAll() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher get(Long id) {
		Optional<Teacher> teacherOldId = teacherRepository.findById(id);
		if (!teacherOldId.isPresent()) {
			throw new GenricException("Teacher not found");
		}
		return teacherOldId.get();
	}

	@Override
	public void delete(@Valid Long id) {
		teacherRepository.deleteById(id);

	}
}
