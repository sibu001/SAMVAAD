package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.dto.QuestionDTO;
import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.mapper.QuestionMapper;
import com.divergent.meet.divergentmeet.model.Question;
import com.divergent.meet.divergentmeet.model.TestCase;
import com.divergent.meet.divergentmeet.repository.QuestionRepository;
import com.divergent.meet.divergentmeet.repository.TestCaseRepository;
import com.divergent.meet.divergentmeet.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private TestCaseRepository testCaseRepository;
	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public QuestionDTO create(@Valid Question question) {

		
		Set<TestCase> testCases=question.getTestCases();
		question.setTestCases(null);
		question = questionRepository.save(question);
		for (TestCase testCase :testCases ) {
			testCase.setQuestion(question);
			testCaseRepository.save(testCase);	
		}
		

		return questionMapper.questionToQuestionDTO(question);
	}

	@Override
	public QuestionDTO update(@Valid Question question) {

		Optional<Question> questionOldId = questionRepository.findById(question.getId());
		if (!questionOldId.isPresent()) {
			throw new GenricException("Question not found");
		}
		
		question = questionRepository.save(question);

		return questionMapper.questionToQuestionDTO(question);

	}

	@Override
	public List<QuestionDTO> getAll() {
		return questionMapper.questionsToQuestionDTOs(questionRepository.findAll());
	}
	@Override
	public List<QuestionDTO> getAllBySession(Long sessionId) {
		return questionMapper.questionsToQuestionDTOs(questionRepository.findBySession(sessionId));
	}

	@Override
	public QuestionDTO get(Long id) {
		Optional<Question> questionOldId = questionRepository.findById(id);
		if (!questionOldId.isPresent()) {
			throw new GenricException("Question not found");
		}
		return questionMapper.questionToQuestionDTO(questionOldId.get());
	}

	@Override
	public void delete(@Valid Long id) {
		
		questionRepository.deleteById(id);

	}
}
