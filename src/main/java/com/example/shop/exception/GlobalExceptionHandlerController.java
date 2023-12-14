package com.example.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.toString(),exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorDetails.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.toString(),exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorDetails.toString(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleArticleAlreadyExistsException(ArticleAlreadyExistsException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.toString(),exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorDetails.toString(), HttpStatus.BAD_REQUEST);
    }
}