package com.example.mockbus.restcontrollers;

import com.example.mockbus.exceptions.PaymentUnsuccessfulException;
import com.example.mockbus.exceptions.ResourceNotFoundException;
import com.example.mockbus.exceptions.ResponseMsg;
import com.example.mockbus.exceptions.ValidationFailedException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice("com.example.mockbus")
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

  private static Logger logger = LogManager
      .getLogger(ExceptionHandlerController.class);

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity handleAccessDenied(Exception e) {
    logger.error("Responding with unauthorized error. Message - {}",
        e.getMessage());
    return new ResponseEntity<ResponseMsg>(
        new ResponseMsg("ERROR", "ACCESS DENIED"), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(IllegalFormatException.class)
  public ResponseEntity<Object> handelIllegalFormatEx(Exception e) {
    logger.error("Illegal Format Exception");
    logger.error(e.getMessage());
    return ResponseEntity.badRequest()
        .body(new ResponseMsg("ERROR", "INVALID REQUEST"));

  }

  @ExceptionHandler(ResourceNotFoundException.class)

  public ResponseEntity<Object> handleNotFoundEx(Exception e) {
    logger.error(e.getMessage() + " Not Found");
    ResponseMsg fieldErrorResource = new ResponseMsg("ERROR",
        e.getMessage() + " not found or not avaiable");
    return new ResponseEntity<Object>(fieldErrorResource, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleInvalidConstraint(Exception exception) {
    logger.error(exception.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ResponseMsg("ERROR", "CONSTRAINT VIOLATION"));
  }

  @ExceptionHandler(ValidationFailedException.class)
  public ResponseEntity<Object> handleFailedValidation(
      ValidationFailedException e) {
    BindingResult bindingResult = e.getBindingResult();
    List<FieldError> errors = bindingResult.getFieldErrors();
    List<ResponseMsg> fieldErrorResources = new ArrayList<>();
    errors.forEach(error -> fieldErrorResources
        .add(new ResponseMsg(error.getField().toUpperCase(),
            error.getDefaultMessage())));
    return ResponseEntity.badRequest().body(fieldErrorResources);

  }

  @ExceptionHandler(PaymentUnsuccessfulException.class)
  public ResponseEntity<Object> handleFailedPayment(Exception e) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ResponseMsg("ERROR", e.getMessage()));
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<Object> handleError500(Exception e) {
    logger.error(e.getCause());
    logger.error(e.getMessage());
    logger.error(e.getCause());
    return new ResponseEntity<>(
        new ResponseMsg("ERROR", "INTERNAL ERROR, PLEASE CONTACT ADMIN"),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    logger.debug(ex.getMessage());
    return new ResponseEntity<>(new ResponseMsg("ERROR", "INVALID URL"),
        status);
  }
}
