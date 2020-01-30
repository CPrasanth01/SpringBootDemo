package com.personal.demo.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class DemoAuthorizationfilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		Authentication auth = null;
		HttpServletRequest httpServletReq = (HttpServletRequest) request;
		String key = httpServletReq.getHeader("KEY");
		if (key != null && key.contains("ADMIN")) {
			SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_ADMIN");
			Set<GrantedAuthority> grantedAuth = new HashSet<GrantedAuthority>();
			grantedAuth.add(role);
			auth = new PreAuthenticatedAuthenticationToken(
					User.builder().username("ADMIN").authorities(grantedAuth).password("").build(), "", grantedAuth);
			auth.setAuthenticated(true);
			SecurityContextHolder.getContext().setAuthentication(auth);
			chain.doFilter(request, response);

		} else {
			SecurityContextHolder.getContext().setAuthentication(null);
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendError(HttpStatus.UNAUTHORIZED.value(), "UnAuthorized");
			return;
		}

	}

}
