package com.app.mapp.error;

import com.app.mapp.exception.EmailSendException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class EmailExceptionHandler {

  @ResponseBody
  @ExceptionHandler(EmailSendException.class)
  ResponseEntity<CustomErrorResponse> userNotFoundHandler(EmailSendException ex) {
    CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now()
        , HttpStatus.INTERNAL_SERVER_ERROR.value()
        , ex.getMessage(),false);
    return new ResponseEntity(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }


}