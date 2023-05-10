package com.sparta.yourname.exception;


import com.sparta.yourname.util.CustomErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomError extends RuntimeException{
    private final CustomErrorMessage customErrorMessage;
}
