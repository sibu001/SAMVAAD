package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.divergent.meet.divergentmeet.exception.GenricException;
import com.divergent.meet.divergentmeet.model.StudentAnswer;
import com.divergent.meet.divergentmeet.model.StudentTestCase;
import com.divergent.meet.divergentmeet.model.TestCase;
import com.divergent.meet.divergentmeet.model.User;
import com.divergent.meet.divergentmeet.repository.StudentAnswerRepository;
import com.divergent.meet.divergentmeet.repository.StudentTestCaseRepository;
import com.divergent.meet.divergentmeet.repository.TestCaseRepository;
import com.divergent.meet.divergentmeet.repository.UserRepository;
import com.divergent.meet.divergentmeet.service.StudentAnswerService;
import com.divergent.meet.divergentmeet.utility.AuthorizationUtility;

@Service

public class StudentAnswerServiceImpl implements StudentAnswerService {
	@Autowired
	private StudentAnswerRepository studentAnswerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TestCaseRepository testCaseRepository;
	@Autowired
	private StudentTestCaseRepository studentTestCaseRepository;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Override
	public StudentAnswer create(@Valid StudentAnswer studentAnswer) {

		studentAnswer.setAttempts(1l);
		String username=AuthorizationUtility.getLoggedInUsername();
		User student=userRepository.findUserByUsername(username);
		studentAnswer.setStudent(student);
		studentAnswer = studentAnswerRepository.save(studentAnswer);
		List<TestCase> tcase = testCaseRepository.findByQuestionId(studentAnswer.getQuestion().getId());
		final String languageId = studentAnswer.getLanguage_id();
		final String sourceCode = studentAnswer.getSource_code();
		final Long id=studentAnswer.getId();
		threadPoolTaskExecutor.execute(() -> {

			if (!tcase.isEmpty())
				for (TestCase testCase : tcase) {
					{
						StudentTestCase studentTestCase = new StudentTestCase();
						Map<String, Object> m = new HashMap<>();
						m.put("source_code", sourceCode);
						m.put("language_id", languageId);
						Encoder myencoder = Base64.getMimeEncoder();
						m.put("stdin", myencoder.encodeToString(testCase.getStdin().getBytes()));
						m.put("compiler_options", testCase.getCompiler_options());
						m.put("command_line_arguments", testCase.getInput());
						m.put("redirect_stderr_to_stdout", false);

						HttpHeaders headers = new HttpHeaders();
						headers.set("contentType", "application/json");

						HttpEntity<Map> request = new HttpEntity<Map>(m, headers);
						Map output = restTemplate.postForObject(
								"http://meet.divergentsl.com:8081/submissions?base64_encoded=true&wait=false", request,
								Map.class);

						try {
							Thread.currentThread().sleep(6000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						Map finalOutput = restTemplate.getForObject("http://meet.divergentsl.com:8081/submissions/"
								+ String.valueOf(output.get("token")) + "?base64_encoded=true", Map.class);
						Decoder mydecoder = Base64.getMimeDecoder();

						String stdout = new String(mydecoder.decode(String.valueOf(finalOutput.get("stdout"))));
						stdout = stdout.replaceAll("[\\n\\t ]", "");

						String stderr = new String(mydecoder.decode(String.valueOf(finalOutput.get("stderr"))));
						stderr = stderr.replaceAll("[\\n\\t ]", "");

						String compile_output = new String(
								mydecoder.decode(String.valueOf(finalOutput.get("compile_output"))));
						compile_output = compile_output.replaceAll("[\\n\\t ]", "");

						String message = new String(mydecoder.decode(String.valueOf(finalOutput.get("message"))));
						message = message.replaceAll("[\\n\\t ]", "");

						String memory = String.valueOf(finalOutput.get("memory"));
						String time = String.valueOf(finalOutput.get("time"));

						studentTestCase.setStdout(stdout);
						studentTestCase.setStderr(stderr);
						studentTestCase.setMemory(memory);
						studentTestCase.setTime(time);
						studentTestCase.setCompile_output(compile_output);
						studentTestCase.setSandbox_message(message);
						StudentAnswer studentAnswer1=new StudentAnswer();
						studentAnswer1.setId(id);
						studentTestCase.setStudentAnswer(studentAnswer1);
						if (stdout.equals(testCase.getExpectedOutput())) {
							studentTestCase.setStatus("pass");
						} else {
							studentTestCase.setStatus("fail");

						}
						studentTestCase.setTestCase(testCase);
						studentTestCaseRepository.save(studentTestCase);

					}

				}
		});

		return studentAnswer;
	}

	public String convertWithStream(Map<String, Object> map) {
		String mapAsString = map.keySet().stream().map(key -> key + "=" + map.get(key))
				.collect(Collectors.joining(", ", "{", "}"));
		return mapAsString;
	}

	@Override
	public StudentAnswer update(@Valid StudentAnswer studentAnswer) {

		Optional<StudentAnswer> studentAnswerOldId = studentAnswerRepository.findById(studentAnswer.getId());
		if (!studentAnswerOldId.isPresent()) {
			throw new GenricException("StudentAnswer not found");
		}
		studentAnswer.setAttempts(studentAnswerOldId.get().getAttempts() + 1);

		studentAnswer = studentAnswerRepository.save(studentAnswer);

		return studentAnswer;

	}

	@Override
	public List<StudentAnswer> getAll() {
		return studentAnswerRepository.findAll();
	}

	@Override
	public List<Map<String,Object>> getAllByQuestion(Long questionId) {
		return studentAnswerRepository.getAllByQuestion(questionId);
	
		
	}

	@Override
	public StudentAnswer get(Long id) {
		Optional<StudentAnswer> studentAnswerOldId = studentAnswerRepository.findById(id);
		if (!studentAnswerOldId.isPresent()) {
			throw new GenricException("StudentAnswer not found");
		}
		return studentAnswerOldId.get();
	}

	@Override
	public void delete(@Valid Long id) {

		studentAnswerRepository.deleteById(id);

	}

	@Override
	public List<Map<String,Object>> getTestCaseById(Long id) {
		// TODO Auto-generated method stub
		
		 List<Map<String,Object>> testCaseById = studentAnswerRepository.getTestCaseById(id);
		 return testCaseById;
	}

	@Override
	public List<Map<String,Object>> getByStudentAndSession(Long studentId, Long sessionId) {
		// TODO Auto-generated method stub
		return studentAnswerRepository.getByStudentAndSession(studentId,sessionId);
	}
}
