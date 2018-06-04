package com.stock.util;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that handles pre and post processing filter logging
 * @author anitageorge
 *
 */
@WebFilter(urlPatterns="/*", dispatcherTypes= {DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.FORWARD})
public class LoggingFilter implements Filter {
	
	private static final Logger log = LogManager.getLogger();
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)request).getSession(false); 
	    HttpServletRequest req = (HttpServletRequest) request;
		switch(req.getRequestURI()) {
		case "/StockBook/login":
			log.info("URI: " + req.getRequestURI() + ": User Login");
			break;
		case "/StockBook/register":
			log.info("URI: " + req.getRequestURI() + ": New User Registration");
			break;
		case "/StockBook/addpost":
			log.info("URI: " + req.getRequestURI() + " " + session.getAttribute("user") + " added a new post");
			break;
		}
		chain.doFilter(request, response); 
		if(req.getRequestURI().equals("/StockBook/login")) {
			log.info("URI: " + req.getRequestURI() + "\nSession: " + session.getAttribute("user"));
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
