package br.com.smartclinmed.web.resources.exception;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.smartclinmed.web.services.exceptions.AuthorizationException;
import br.com.smartclinmed.web.services.exceptions.DataIntegrityException;
import br.com.smartclinmed.web.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> NoSuchElementException(NoSuchElementException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Validation error", "Check the inconsistencies noted below", request.getRequestURI());
		err.addError("Data integrity", e.getMessage());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> contraintIntegrity(ConstraintViolationException e,
			HttpServletRequest request) {
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Validation error", "Check the inconsistencies noted below", request.getRequestURI());
		err.addError("Data integrity", "Change not allowed, linked items");
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Validation error", "Check the inconsistencies noted below", request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Access denied",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<StandardError> accesDenied(AccessDeniedException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
				"Function not enabled for your user", "Access denied", request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	// @ExceptionHandler(FileException.class)
	// public ResponseEntity<StandardError> file(FileException e, HttpServletRequest
	// request) {
	//
	// StandardError err = new StandardError(System.currentTimeMillis(),
	// HttpStatus.BAD_REQUEST.value(), "Erro de arquivo", e.getMessage(),
	// request.getRequestURI());
	// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	// }
	//
	// @ExceptionHandler(AmazonServiceException.class)
	// public ResponseEntity<StandardError> amazonService(AmazonServiceException e,
	// HttpServletRequest request) {
	//
	// HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
	// StandardError err = new StandardError(System.currentTimeMillis(),
	// code.value(), "Erro Amazon Service", e.getMessage(),
	// request.getRequestURI());
	// return ResponseEntity.status(code).body(err);
	// }
	//
	// @ExceptionHandler(AmazonClientException.class)
	// public ResponseEntity<StandardError> amazonClient(AmazonClientException e,
	// HttpServletRequest request) {
	//
	// StandardError err = new StandardError(System.currentTimeMillis(),
	// HttpStatus.BAD_REQUEST.value(), "Erro Amazon Client", e.getMessage(),
	// request.getRequestURI());
	// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	// }
	//
	// @ExceptionHandler(AmazonS3Exception.class)
	// public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e,
	// HttpServletRequest request) {
	//
	// StandardError err = new StandardError(System.currentTimeMillis(),
	// HttpStatus.BAD_REQUEST.value(), "Erro S3", e.getMessage(),
	// request.getRequestURI());
	// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	// }

}
