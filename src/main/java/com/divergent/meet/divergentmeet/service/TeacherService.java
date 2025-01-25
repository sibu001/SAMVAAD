package com.divergent.meet.divergentmeet.service;

import java.util.List;

import javax.validation.Valid;

import com.divergent.meet.divergentmeet.model.Teacher;

public interface TeacherService {
	Teacher create(@Valid Teacher teacher);

	Teacher update(@Valid Teacher teacher);

	List<Teacher> getAll();

	Teacher get(Long id);

	void delete(@Valid Long id);
}
