package com.football_transfer_market.Dto;


import lombok.Data;

import java.util.Date;

@Data
public class TeamPlayerDto {

    private Long teamId;
    private String playerName;
    private String playerSurname;
    private Date contractStartDate;
    private Date contractEndDate;
    private int playerPrice;
}
