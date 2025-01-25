package com.divergent.meet.divergentmeet.service;

import java.util.List;

import javax.validation.Valid;

import com.divergent.meet.divergentmeet.dto.QuestionDTO;
import com.divergent.meet.divergentmeet.model.Question;

public interface QuestionService {
	QuestionDTO create(@Valid Question question);

	QuestionDTO update(@Valid Question question);

	List<QuestionDTO> getAll();

	QuestionDTO get(Long id);

	void delete(@Valid Long id);
	
	public List<QuestionDTO> getAllBySession(Long sessionId);
}
