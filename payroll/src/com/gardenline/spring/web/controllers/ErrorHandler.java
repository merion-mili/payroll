package com.gardenline.spring.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	private final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		return "error";
	}

	@ExceptionHandler(AccessDeniedException.class)
	public String handleDatabaseException(AccessDeniedException ex) {
		return "deneid";
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleDatabaseException(
			MissingServletRequestParameterException ex) {
		return "parameterreq";
	}

	@ExceptionHandler(Exception.class)
	public String defaultErrorHandler(HttpServletRequest req, Exception e,
			Model mav) throws Exception {

		logger.error("[URL] : {}", req.getRequestURL(), e.getMessage());

		mav.addAttribute("errorMessage", e.getMessage());
		return "error";
	}

}
