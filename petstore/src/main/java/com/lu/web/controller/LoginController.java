package com.lu.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lu.model.AppError;
import com.lu.model.login.LoginRequest;
import com.lu.model.login.LoginResponse;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping(value = "login", method = { RequestMethod.POST, RequestMethod.GET })
	public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest req) {
		LoginResponse response = new LoginResponse();

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getLoginId(),
				request.getPassword());
		token.setDetails(new WebAuthenticationDetails(req));
		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(auth);

			if (auth.isAuthenticated()) {
				HttpSession session = req.getSession(true);
				response.setRoles(new ArrayList<String>());
				for (GrantedAuthority ga:auth.getAuthorities() ){
					response.getRoles().add(ga.getAuthority());
				}
				session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
			} else {
				SecurityContextHolder.getContext().setAuthentication(null);

				AppError e = new AppError("notLogin", "invalid ID or Password", null);
				response.setErrors(new ArrayList<AppError>());
				response.getErrors().add(e);
			}
		} catch (BadCredentialsException e) {
			// todo
			System.out.println("bad password");
			AppError er = new AppError("notLogin", "invalid ID or Password", null);
			response.setErrors(new ArrayList<AppError>());
			response.getErrors().add(er);
		}

		return response;
	}

}
