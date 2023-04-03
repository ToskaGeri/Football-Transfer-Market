package com.football_transfer_market.Service;

import com.football_transfer_market.Models.Country;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

public interface TransferOfferService {


    public boolean isTransferMarketOpen(ZonedDateTime d1, ZonedDateTime d2);

}
