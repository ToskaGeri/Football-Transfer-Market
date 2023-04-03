package com.football_transfer_market.errors;

import com.football_transfer_market.Models.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CustomError.class })
    public ResponseEntity<Object> handleCustomBadRequestException(CustomError ex) {
        return new ResponseEntity<>(
                new ApiResponse<>(ex.getErrorMessage(), ex.getErrorCode()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
