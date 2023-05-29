package com.football_transfer_market;

import com.football_transfer_market.Configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class FootballTransferMarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(FootballTransferMarketApplication.class, args);
    }

}
