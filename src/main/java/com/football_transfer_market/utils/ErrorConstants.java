package com.football_transfer_market.utils;

public class ErrorConstants {

    private ErrorConstants() {
        throw new IllegalStateException("Must not instantiate utility class");
    }

    public static final String CLOSED_MARKET_ERROR_MESSAGE = "The market is closed";
    public static final int CLOSED_MARKET_ERROR_CODE = 1;

    public static final String LOW_PRICE_OFFER_ERROR_MESSAGE = "Offer's value is not acceptable";

    public static final int LOW_PRICE_OFFER_ERROR_CODE = 2;

    public static final String OFFER_PLAYER_SAME_TEAM_ERROR_MESSAGE = "Offered Player is in the same team";

    public static final int OFFER_PLAYER_SAME_TEAM_ERROR_CODE = 3;
}
