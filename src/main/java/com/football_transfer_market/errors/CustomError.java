package com.football_transfer_market.errors;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomError extends RuntimeException{

    private String errorMessage;
    private int errorCode;

    public CustomError(String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
