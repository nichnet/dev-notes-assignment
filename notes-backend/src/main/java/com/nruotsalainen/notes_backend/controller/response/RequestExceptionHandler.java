package com.nruotsalainen.notes_backend.controller.response;

import com.nruotsalainen.notes_backend.controller.response.exception.BadRequestException;
import com.nruotsalainen.notes_backend.controller.response.exception.InvalidQueryParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestExceptionHandler {
    @ExceptionHandler(InvalidQueryParameterException.class)
    public ResponseEntity<Object> handleInvalidQueryParameterException(InvalidQueryParameterException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid Query Parameter", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestParameterException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Bad Request", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
