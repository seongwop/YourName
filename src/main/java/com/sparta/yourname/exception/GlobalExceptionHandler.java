package com.sparta.yourname.exception;

import com.sparta.yourname.dto.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomError.class)
    public ResponseEntity<?> customRuntimeError(CustomError e){
        return new ResponseEntity<>(new CommonResponseDto<>(e.getCustomErrorMessage().getMessage()), e.getCustomErrorMessage().getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> uncheckedError(Exception e){
        return new ResponseEntity<>(new CommonResponseDto<>(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
