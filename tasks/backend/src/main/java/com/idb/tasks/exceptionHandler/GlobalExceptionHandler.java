package com.idb.tasks.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.*;

import static com.idb.tasks.common.Constants.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put(TIMESTAMP, new Date().getTime());
        responseBody.put(STATUS, status.value());

        Map<String, List<String>> errors = new HashMap<>();
        for(FieldError fieldError: ex.getFieldErrors()){
            String fieldName = fieldError.getField();
            List<String> errorList = errors.getOrDefault(fieldName, new LinkedList<>());
            errorList.add(fieldError.getDefaultMessage());
            errors.put(fieldName, errorList);
        }

        responseBody.put(ERROR, errors);

        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
