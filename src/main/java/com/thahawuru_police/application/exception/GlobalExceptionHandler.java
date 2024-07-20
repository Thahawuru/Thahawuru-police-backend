package com.thahawuru_police.application.exception;

import com.thahawuru_police.application.dto.response.ApiError;
import com.thahawuru_police.application.dto.response.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError<List<ErrorDTO>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorDTO> errorMessages = ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> {
                    if (objectError instanceof FieldError) {
//                        return ((FieldError) objectError).getDefaultMessage();
                        return new ErrorDTO("VALIDATION_FAILED!",((FieldError) objectError).getDefaultMessage());
                    }
                    return new ErrorDTO("VALIDATION_FAILED!",objectError.getDefaultMessage());
                })
                .collect(Collectors.toList());


        ApiError<List<ErrorDTO>> apiError = new ApiError<>(HttpStatus.BAD_REQUEST.value(), errorMessages);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError<ErrorDTO>> handleUserNotFoundException(Exception ex, WebRequest req){
        ApiError<ErrorDTO> apiError = new ApiError<>(HttpStatus.NOT_FOUND.value(),new ErrorDTO("NOT_FOUND",ex.getMessage()));
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError<ErrorDTO>> handleGlobalException(Exception ex, WebRequest req){
        ApiError<ErrorDTO> apiError = new ApiError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),new ErrorDTO("INTERNAL_SERVER_ERROR",ex.getMessage()));
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    };


}
