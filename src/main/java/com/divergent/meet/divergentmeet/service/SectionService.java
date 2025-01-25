package com.divergent.meet.divergentmeet.service;

import java.util.List;

import javax.validation.Valid;

import com.divergent.meet.divergentmeet.model.Section;

public interface SectionService {
	Section create(@Valid Section section);

	Section update(@Valid Section section);

	List<Section> getAll();

	Section get(Long id);

	void delete(@Valid Long id);

}
