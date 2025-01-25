package com.divergent.meet.divergentmeet.service;

import java.util.List;

import javax.validation.Valid;

import com.divergent.meet.divergentmeet.model.Subject;

public interface SubjectService {
	Subject create(@Valid Subject subject);

	Subject update(@Valid Subject subject);

	List<Subject> getAll();

	Subject get(Long id);

	void delete(@Valid Long id);
}
