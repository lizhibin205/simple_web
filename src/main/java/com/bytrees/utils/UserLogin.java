package com.bytrees.utils;

import javax.servlet.http.HttpSession;

import com.bytrees.entity.User;

public class UserLogin {
	private static final String LOGIN_SESSION_NAME = "user_login";

	public static void setLoginSession(HttpSession session, User user) { 
		session.setAttribute(LOGIN_SESSION_NAME, user.getId().toString() + "|" + user.getUsername());
	}

	public static String getLoginUserName(HttpSession session) {
		String loginSession = (String)session.getAttribute(LOGIN_SESSION_NAME);
		if (loginSession == null) {
			return null;
		}
		String[] loginSessionPart = loginSession.split("\\|");
		if (loginSessionPart.length != 2) {
			return null;
		}
		return loginSessionPart[1];
	}

}
