package com.divergent.meet.divergentmeet.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.dto.QuestionDTO;
import com.divergent.meet.divergentmeet.model.Question;

@Service
public class QuestionMapper {
	
	public List<QuestionDTO> questionsToQuestionDTOs(List<Question> questions) {
		return questions.stream().filter(Objects::nonNull).map(this::questionToQuestionDTO).collect(Collectors.toList());
	}

	public QuestionDTO questionToQuestionDTO(Question question) {
		return new QuestionDTO(question);
	}

	public List<Question> questionDTOsToQuestions(List<QuestionDTO> questionDTOs) {
		return questionDTOs.stream().filter(Objects::nonNull).map(this::questionDTOToQuestion).collect(Collectors.toList());
	}

	public Question questionDTOToQuestion(QuestionDTO questionDTO) {
		if (questionDTO == null) {
			return null;
		} else {
			Question question = new Question();
			question.setId(questionDTO.getId());
			question.setQuestion(questionDTO.getQuestion());
			question.setSampleInput(questionDTO.getSampleInput());
			question.setSampleOutput(questionDTO.getSampleOutput());
			question.setSession(questionDTO.getSession());
			question.setSourceCode(questionDTO.getSourceCode());
			question.setTestCases(questionDTO.getTestCases());
			return question;
		}
	}

	public Question questionFromQuestionId(Long questionId) {
		if (questionId == null) {
			return null;
		}
		Question question = new Question();
		question.setId(questionId);
		return question;
	}

}
