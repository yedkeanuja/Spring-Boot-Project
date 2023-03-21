package com.psms.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class GlobalException {
	public ResponseEntity<?> resourceNotFundHandling(ResourceNotFoundException exception, WebRequest request)
	{
		ErrorDetails errordetails = new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);
	}

}
