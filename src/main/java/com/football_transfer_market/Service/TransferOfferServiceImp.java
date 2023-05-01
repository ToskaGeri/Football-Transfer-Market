package com.football_transfer_market.Service;

import com.football_transfer_market.Repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class TransferOfferServiceImp implements TransferOfferService {

    @Override
    public boolean isTransferMarketOpen(ZonedDateTime d1, ZonedDateTime d2) {
        return d1.compareTo(d2) < 0;
    }

}
