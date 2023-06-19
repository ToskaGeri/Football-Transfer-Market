package com.football_transfer_market.errors;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomError extends RuntimeException{

    private final String errorMessage;
    private final int errorCode;

    public CustomError(String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
