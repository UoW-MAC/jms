package com.jms.presentation.filter;

import com.jms.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author williamzhang
 *
 */

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger logger = Logger.getLogger(LoginFilter.class);

	@Autowired
	IUserService userService;

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String usernameParamName = getUsernameParameter();
		String usernameParamValue = request.getParameter(usernameParamName);
		RequestWrapper requestWrapper = new RequestWrapper(request);
		
		try {
			if (StringUtils.isNotBlank(usernameParamValue)) {
				requestWrapper.addParameter(usernameParamName, usernameParamValue);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		Authentication authentication;

		if (requestWrapper.getParamMap().isEmpty()) {
			authentication = super.attemptAuthentication(request, response);
		} else {
			authentication = super.attemptAuthentication(requestWrapper, response);
		}

		return authentication;
	}

	public void setRememberMeServices(RememberMeServices rememberMeServices) {

		super.setRememberMeServices(rememberMeServices);
	}

	public RememberMeServices getRememberMeServices() {
		return super.getRememberMeServices();
	}
}
