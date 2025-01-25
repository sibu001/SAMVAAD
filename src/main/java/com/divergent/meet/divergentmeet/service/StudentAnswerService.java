package com.divergent.meet.divergentmeet.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.divergent.meet.divergentmeet.model.StudentAnswer;
import com.divergent.meet.divergentmeet.model.StudentTestCase;

public interface StudentAnswerService {
	StudentAnswer create(@Valid StudentAnswer studentAnswer);

	StudentAnswer update(@Valid StudentAnswer studentAnswer);

	List<StudentAnswer> getAll();

	StudentAnswer get(Long id);

	void delete(@Valid Long id);
	public List<Map<String,Object>> getAllByQuestion(Long questionId);

	List<Map<String,Object>> getTestCaseById(Long id);

	List<Map<String,Object>> getByStudentAndSession(Long studentId, Long sessionId);
}
