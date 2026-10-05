package com.akshat.spring_ai.exception;

import com.akshat.spring_ai.exception.custom.EmptyModelResponseException;
import com.akshat.spring_ai.exception.custom.InvalidImageException;
import com.akshat.spring_ai.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex,
                                                                        HttpServletRequest request) {
        log.warn(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(InvalidImageException.class)
    public ResponseEntity<ErrorResponse> handleInvalidImageException(InvalidImageException ex,
                                                                     HttpServletRequest request) {
        log.warn(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex,
                                                                              HttpServletRequest request) {
        log.warn(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.CONTENT_TOO_LARGE;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleResourceAccessException(ResourceAccessException ex,
                                                                       HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), "Something went " +
                "wrong", request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(EmptyModelResponseException.class)
    public ResponseEntity<ErrorResponse> handleEmptyModelResponseException(EmptyModelResponseException ex,
                                                                           HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.BAD_GATEWAY;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), "Something went " +
                "wrong", request.getRequestURI());
        return new ResponseEntity<>(errorResponse, status);
    }


}
