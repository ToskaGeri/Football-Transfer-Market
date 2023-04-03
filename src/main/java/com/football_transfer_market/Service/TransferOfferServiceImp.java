package com.football_transfer_market.Service;

import com.football_transfer_market.Models.Country;
import com.football_transfer_market.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class TransferOfferServiceImp implements TransferOfferService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public boolean isTransferMarketOpen(ZonedDateTime d1, ZonedDateTime d2) {
        return d1.compareTo(d2) < 0;
    }

}
