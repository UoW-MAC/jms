package com.jms.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jms.domain.entities.User;
import com.jms.domain.valuetypes.ResponseStatus;
import com.jms.presentation.model.Response;
import com.jms.service.IUserService;

/**
 * @author william zhang
 */
@Controller
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	private static final String LOGIN_PAGE = "login";
	private static final String USER_CENTER_PAGE = "user-center";
	private static final String HOME_PAGE = "home";

	@Resource
	private IUserService userService;
	
	@Autowired
    protected AuthenticationManager authenticationManager;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView show() {
		String resultPage = HOME_PAGE;
		
		try {
			if (userService.isLogged()) {
				resultPage = USER_CENTER_PAGE;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new ModelAndView(resultPage);
	}

	@RequestMapping(value = "makeRegister", method = RequestMethod.POST)
	public @ResponseBody Response handleRegister(@ModelAttribute("registerForm") User user, HttpServletRequest request) {
		Map<String, Object> models = null;

		try {
			String noEncryptedPassword = user.getPassword();
			userService.register(user);
			
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), noEncryptedPassword);
			request.getSession();
	        token.setDetails(new WebAuthenticationDetails(request));
	        Authentication authenticatedUser = authenticationManager.authenticate(token);
	        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			
			models = new HashMap<String, Object>();
			models.put("redirect", USER_CENTER_PAGE);

		} catch (Exception ex) {
			return new Response(ResponseStatus.FAILURE.getStatusCode(), ResponseStatus.FAILURE.getStatusDescription(),
					null);
		}

		return new Response(ResponseStatus.SUCCESS.getStatusCode(), ResponseStatus.SUCCESS.getStatusDescription(),
				models);
	}

	@RequestMapping(value = "userNameValidation", method = RequestMethod.POST)
	public @ResponseBody boolean userNameValidation(HttpServletRequest request) {
		Boolean userNameValidation = true;

		try {
			 if (userService.isUserNameExisted(request.getParameter("userName"))) {
				 userNameValidation = false;
			 }
		} catch (Exception e) {
			// TODO: handle exception
		}

		return userNameValidation;
	}

	@RequestMapping(value = "emailValidation", method = RequestMethod.POST)
	public @ResponseBody boolean emailValidation(HttpServletRequest request) {
		Boolean emailValidation = true;

		try {
			if (userService.isEmailExisted(request.getParameter("userName"))) {
				emailValidation = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return emailValidation;
	}

	@RequestMapping(value = "user-center", method = RequestMethod.GET)
	public ModelAndView showUserCenter(HttpServletRequest request) {
		String menu = request.getParameter("menu");
		Map<String, Object> models = null;
		models = new HashMap<String, Object>();
		models.put("menu", menu);
		
		return new ModelAndView(USER_CENTER_PAGE, models);
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(HttpServletRequest request) {
		String errorCode = request.getParameter("error");
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("error", errorCode);

		return new ModelAndView(LOGIN_PAGE, models);
	}

	@RequestMapping(value = "access-denied", method = RequestMethod.GET)
	public ModelAndView handleAccessDenied() {
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("access-denied", "access-denied");
		return new ModelAndView(LOGIN_PAGE, models);
	}

	@RequestMapping(value = "login-success", method = RequestMethod.POST)
	public @ResponseBody Response handleLoginSuccessOnPost(HttpServletRequest request) {
		return handleLoginSuccess(request);
	}

	@RequestMapping(value = "login-success", method = RequestMethod.GET)
	public @ResponseBody Response handleLoginSuccess(HttpServletRequest request) {
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("redirect", USER_CENTER_PAGE);
		return new Response(ResponseStatus.LOGIN_SUCCESS.getStatusCode(),
				ResponseStatus.LOGIN_SUCCESS.getStatusDescription(), models);
	}

	@RequestMapping(value = "login-failure", method = RequestMethod.POST)
	public @ResponseBody Response handleLoginFailureOnPost(HttpServletRequest request) {
		return handleLoginFailure(request);
	}

	@RequestMapping(value = "login-failure", method = RequestMethod.GET)
	public @ResponseBody Response handleLoginFailure(HttpServletRequest request) {
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("redirect", LOGIN_PAGE);
		models.put("error", 1);

		return new Response(ResponseStatus.LOGIN_FAILURE.getStatusCode(),
				ResponseStatus.LOGIN_FAILURE.getStatusDescription(), models);
	}

}
