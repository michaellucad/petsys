package com.lu.web.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class DefaultRequestMatcher implements RequestMatcher{

	@Override
	public boolean matches(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
