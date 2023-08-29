package com.VirtualCard.maplerads.advice;

import com.VirtualCard.maplerads.Exception.EmailExistsException;
import com.VirtualCard.maplerads.Exception.EmailNotFound;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler extends Throwable {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmailExistsException.class)
    public Map<String, String> handleEmailExistsAlreadyException(EmailExistsException exception) {
        Map<String, String> map =  new HashMap<>();
        map.put("errorMessage", exception.getMessage());
        return map;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, IllegalStateException.class})
    public Object handlePathVariableException(Exception exception, HttpServletRequest request){
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", exception.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EmailNotFound.class, BadCredentialsException.class, ExpiredJwtException.class})
    public Object invalidEmail(Exception exception){
        Map<String, Object> response = new HashMap<>();
        response.put("errorMessage", exception.getMessage());
        return response;
    }


}