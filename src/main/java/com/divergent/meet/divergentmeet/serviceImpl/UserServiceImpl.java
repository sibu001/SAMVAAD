package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.exception.UserRequestException;
import com.divergent.meet.divergentmeet.model.Prosody;
import com.divergent.meet.divergentmeet.model.User;
import com.divergent.meet.divergentmeet.repository.ProsodyRepository;
import com.divergent.meet.divergentmeet.repository.RoleRepository;
import com.divergent.meet.divergentmeet.repository.UserRepository;
import com.divergent.meet.divergentmeet.service.UserService;
import com.divergent.meet.divergentmeet.utility.AppUtility;

@Service
public class UserServiceImpl implements UserService {

	@Value("${app.host}")
	private String host;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ProsodyRepository prosodyRepository;

//	@Autowired
//	SmtpMailSendService smtpMailSendService;

	@Override
	public User findUserById(Long id) {
		return userRepository.findUserById(id);
	}

	@Override
	public User findById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + id));
		if (user.getRoles() == null) {
			throw new InsufficientAuthenticationException("User has no roles assigned");
		}
		return user;
	}

	@Override
	public User createUser(User user) {
		User users = null;
		String password;
		if (!AppUtility.isEmail(user.getEmail())) {
			throw new UserRequestException("Email format is wrong......!");
		}
		if (user.getEmail() == null) {
			throw new UserRequestException("Email is required......!");
		}
		if (user.getPhone() == null) {
			throw new UserRequestException("Phone no is required......!");
		}
		User emailUser = userRepository.findUserByEmail(user.getEmail());
		if (emailUser != null) {
			throw new UserRequestException("Email already in used");
		}
		User phoneUser = userRepository.findByPhone(user.getPhone());
		if (phoneUser != null) {
			throw new UserRequestException("Phone already in used");
		}
		User usernameUser = userRepository.findUserByUsername(user.getUsername());
		if (usernameUser != null) {
			throw new UserRequestException("Username already in used");
		}

		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserRequestException("Email Already Use ....!");
		}
		saveProsodyUser(user, false);

		password = user.getPassword();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		users = userRepository.save(user);

		try {
			// users.setPassword(password);
			// smtpMailSendService.userCreate(users, users.getEmail(), mailBody(users));
		} catch (Exception e) {
			throw new UserRequestException("Mail is not sent.");
		}

		if (AppUtility.isEmpty(users)) {
			throw new UserRequestException("User Not Created...!");
		}

		return users;
	}

	private Prosody saveProsodyUser(User user, boolean isUpdate) {
		Prosody result = null;

		Prosody pr = prosodyRepository.findProsodyByUser(user.getUsername());
		if (pr == null && !isUpdate) {
			Prosody prosody = new Prosody();
			prosody.setUser(user.getUsername());
			prosody.setValue(user.getPassword());
			prosody.setHost("meet.divergent.com");
			prosody.setKey("password");
			prosody.setStore("accounts");
			prosody.setType("string");
			result = prosodyRepository.save(prosody);
		} else {
//			if(user.getPassword()!=null)
//			pr.setValue(user.getPassword());
//			pr.setUser(user.getUsername());

			result = prosodyRepository.save(pr);
		}
		if (result == null) {
			throw new UserRequestException("User Not Created");
		}
		return result;
	}

	private void deleteProsodyUser(User user) {
		Prosody pr = prosodyRepository.findProsodyByUser(user.getUsername());
		if (pr != null) {
			prosodyRepository.delete(pr);
		}

	}

	/**
	 * To create Mail body (for temporary user only)
	 * 
	 * @param u
	 * @return
	 */
	private StringBuilder mailBody(User u) {
		String password = u.getPassword();
		StringBuilder sBody = new StringBuilder();
		sBody.append("<html>");
		sBody.append("<head>");
		sBody.append("</head>");
		sBody.append("<body>");
		sBody.append("Hi " + u.getName() + " " + u.getLastName() + "," + "<br><br>");
		sBody.append(
				"Divergent Meet.<br>Congratulations your Divergent Meet Account Has Been Created with following credentials");
		sBody.append("<br><br>");
		sBody.append("User Name : " + u.getUsername());
		sBody.append("<br><br>Password : " + password);
		sBody.append("<br><br><br>");
		sBody.append("Thank You,");
		sBody.append("<br>");
		sBody.append("The GEO Team");
		return sBody;
	}

	/**
	 * To get user List
	 * 
	 */
	@Override
	public List<User> getUserList(String role) {

		if (role != null) {
			if (role.equals("ROLE_USER")) {
				return userRepository.findAllStudent();
			}
			if (role.equals("ROLE_TEACHER")) {
				return userRepository.findAllTeacher();
			}
			if (role.equals("ROLE_ADMIN")) {
				return userRepository.findAllAdmin();
			}
			return userRepository.findAll();

		} else {
			return userRepository.findAll();

		}

	}

	/**
	 * To get user by id
	 * 
	 */
	@Override
	public User getUserById(Long id) {

		return userRepository.findUserById(id);
	}

	/**
	 * To delete user by id
	 * 
	 */
	@Override
	public Boolean deleteUserById(Long id) {
		Optional<User> userOp = userRepository.findById(id);
		if (userOp.isPresent()) {
			User user = userOp.get();
			deleteProsodyUser(user);
			user.setEnabled(false);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public User updateUser(@Valid User user) {
		User users = null;
		String password;
		if (!AppUtility.isEmail(user.getEmail())) {
			throw new UserRequestException("Email format is wrong......!");
		}
		if (user.getEmail() == null) {
			throw new UserRequestException("Email is required......!");
		}
		if (user.getPhone() == null) {
			throw new UserRequestException("Phone no is required......!");
		}
		if (user.getId() == null) {
			throw new UserRequestException("Id not present......!");
		}
		User existinguser = userRepository.findUserById(user.getId());
		User emailUser = userRepository.findUserByEmail(user.getEmail());
		if (emailUser != null && !emailUser.getId().equals(existinguser.getId())) {
			throw new UserRequestException("Email already in used");
		}
		User phoneUser = userRepository.findByPhone(user.getPhone());
		if (phoneUser != null && !phoneUser.getId().equals(existinguser.getId())) {
			throw new UserRequestException("Phone already in used");
		}

		existinguser.setAddress(user.getAddress());
		existinguser.setAdmin(user.isAdmin());
		existinguser.setCity(user.getCity());
		existinguser.setEmail(user.getEmail());
		existinguser.setLastName(user.getLastName());
		existinguser.setName(user.getName());
		existinguser.setPhone(user.getPhone());
		existinguser.setState(user.getState());
		existinguser.setZip(user.getZip());
		existinguser.setEnabled(user.isEnabled());
		existinguser.setMyclass(user.getMyclass());
		existinguser.setSection(user.getSection());
		user = userRepository.save(user);
		try {
			// users.setPassword(password);
			// smtpMailSendService.userCreate(users, users.getEmail(), mailBody(users));
		} catch (Exception e) {
			throw new UserRequestException("Mail is not sent.");
		}
		return user;
	}

	@Override
	public List<User> getStudentByClassSection(Long classId, Long sectionId) {

		return userRepository.findAllStudentByClassAndSection(classId,sectionId);
	}

}
