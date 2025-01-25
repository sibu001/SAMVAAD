package com.divergent.meet.divergentmeet.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.divergent.meet.divergentmeet.DivergentmeetApplication;
import com.divergent.meet.divergentmeet.exception.UserRequestException;
import com.divergent.meet.divergentmeet.model.Meeting;
import com.divergent.meet.divergentmeet.model.Student;
import com.divergent.meet.divergentmeet.model.User;
import com.divergent.meet.divergentmeet.repository.StudentRepository;
import com.divergent.meet.divergentmeet.repository.UserRepository;
import com.divergent.meet.divergentmeet.service.SmtpMailSendService;

@Service
public class SmtpMailSendServiceImpl implements SmtpMailSendService {
//	String fromEmail;
//
//	String password;
//
//	String host;
//
//	String port;
//
//	boolean auth;
//
//	boolean startTls;
//
//	String protocol;
//
//	boolean debug;
//
//	String provider;

	// = "51f9768dfcffc94f5b105dd440b10278-16ffd509-52f92ac4"
//	String apiKey;

	// = "sandbox5ae531958eb547f4bce4b98ee3a4246c.mailgun.org"
//	String domain;

//	private final String PROTOCOL_KEY = "mail.transport.protocol";
//	private final String HOST = "mail.host";
//	private final String PORT_KEY = "mail.smtp.port";
//	private final String AUTH_KEY = "mail.smtp.auth";

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StudentRepository studentRepository;

	public boolean sendMail(String email, StringBuilder sb,String sub) {

		Properties props = System.getProperties();
			

		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.port", 587); // TLS Port
		props.put("mail.smtp.auth",true); // enable authentication
		props.put("mail.smtp.starttls.enable",true); // enable STARTTLS
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug",false);

		Authenticator auth1 = new Authenticator() {
			// override the getPasswordAuthentication method
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("aakash.divergent@gmail.com", "@@kash5241");
			}
		};
		Session session = Session.getInstance(props, auth1);

		return sendEmail(session, email, sub, sb);

	}

	public boolean sendEmail(Session session, String toEmail, String subject, StringBuilder body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("aakash.divergent@gmail.com", "Divergent"));

			msg.setReplyTo(InternetAddress.parse(toEmail, false));

			msg.setSubject(subject, "UTF-8");
			Multipart mimeMultipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setContent(body.toString(), "text/html; charset=utf-8");
			mimeMultipart.addBodyPart(textBodyPart);
			msg.setContent(mimeMultipart);
			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			Transport.send(msg);
			DivergentmeetApplication.LOGGER.info("Email Sent through SMTP To :" + toEmail);
			return true;
		} catch (Exception e) {
			DivergentmeetApplication.LOGGER.error(e.getMessage());
			return false;
		}

	}

//    @Override
//	public Boolean userCreate(User u, String email, StringBuilder sb) {
////		boolean b = false;
//		b = sendMail(u, sb);
//		if (b) {
//			return true;
//		} else {
//			DivergentmeetApplication.LOGGER.error("Error ocurred while sending Mail to " + fromEmail);
//			throw new UserRequestException("Mail Not send");
//		}
	   
	//}

	@Override
	public void sendMailOfMeetingToStudent(Meeting meeting) {
		if (meeting.getMyclass() != null) {
			List<Student> students = studentRepository.findByMyclassId(meeting.getMyclass().getId());
			for (Student student : students) {
				
			}
		}

	}

	@Override
	public Boolean meetingCreate(String email,StringBuilder sb) {
		boolean b = false;
		b = sendMail(email, sb,"Please Join the Meeting");
		if (b) {
			return true;
		} else {
			DivergentmeetApplication.LOGGER.error("Error ocurred while sending Mail to " + email);
//			throw new UserRequestException("Mail Not send");
			return false;
		}
	}

}