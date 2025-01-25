package com.divergent.meet.divergentmeet.utility;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorizationUtility {
	
	private AuthorizationUtility() {

	}

	public static String getLoggedInUsername() {
		return getAuthentication().getName();
	}

	public static Authentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new RuntimeException("User authentication is not exist");
		}
		return authentication;
	}

	public static String getLoggedInUsernameOrElse(String elseUser) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if ((authentication != null) && !(authentication instanceof AnonymousAuthenticationToken)) {
			elseUser = authentication.getName();
		}

		return elseUser;
	}

}
