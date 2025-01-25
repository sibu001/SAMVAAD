package com.divergent.meet.divergentmeet.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.DivergentmeetApplication;
import com.divergent.meet.divergentmeet.exception.UserRequestException;
import com.divergent.meet.divergentmeet.model.Student;
import com.divergent.meet.divergentmeet.repository.StudentRepository;
import com.divergent.meet.divergentmeet.service.StudentService;
import com.divergent.meet.divergentmeet.utility.AppUtility;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Student createStudent(Student student) {
		Student students = null;
		String password;
		Student s = studentRepository.findStudentByPhone(student.getPhone());
		if (student.getId() != null) {
			if (s == null || s.getId().equals(student.getId())) {
				Student student2 = studentRepository.findStudentById(student.getId());
				student.setPassword(student2.getPassword());
				students = studentRepository.save(student);
			} else {
				DivergentmeetApplication.LOGGER.error("Phone Number Already Used.... !");
				throw new UserRequestException("Phone Number Already Use.... !");
			}
		} else {
			if (AppUtility.isEmpty(s)) {
				if (studentRepository.existsByPhone(student.getPhone())) {
					throw new UserRequestException("Phone Number Already Use ....!");
				}
				password = student.getPassword();
				// student.setPassword(passwordEncoder.encode(student.getPassword()));
				students = studentRepository.save(student);
			} else {
				throw new UserRequestException("Phone Number Already Use ....!");
			}
			try {
				// students.setPassword(password);
				// smtpMailSendService.userCreate(students, students.getEmail(),
				// mailBody(students));
			} catch (Exception e) {
				throw new UserRequestException("Mail is not sent.");
			}
		}
		if (AppUtility.isEmpty(students)) {
			throw new UserRequestException("student Not Created...!");
		}

		return students;
	}

	@Override
	public List<Student> getStudentList() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findStudentById(id);
	}

	@Override
	public Boolean deleteStudentById(Long id) {
		Optional<Student> studentOp = studentRepository.findById(id);
		if (studentOp.isPresent()) {
			Student student = studentOp.get();
			student.setEnabled(false);
			studentRepository.save(student);
			return true;
		}
		return false;

	}

	@Override
	public Boolean changePassword(String password, Long id) {
		int i = studentRepository.updatePassword(password, id);
		if (i == 0) {
			return false;
		}
		return true;
	}

	@Override
	public Student studentLogin(String phone, String password) {
		Student s = studentRepository.checkPhoneAndPassword(phone, password);
		if (AppUtility.isEmpty(s)) {
			throw new UserRequestException("Phone and password is incorrect!");
		}
		if(!s.isEnabled()) {
			throw new UserRequestException("Your account has been disabled");
		}

		return s;
	}

}
