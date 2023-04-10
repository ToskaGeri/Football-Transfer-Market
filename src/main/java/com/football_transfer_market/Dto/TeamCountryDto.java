package com.football_transfer_market.Dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamCountryDto {
    private String teamName;
    private Long countryId;
    private List<TeamPlayerDto> players;

}
