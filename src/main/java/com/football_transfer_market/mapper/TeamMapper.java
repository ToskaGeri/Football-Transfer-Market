package com.football_transfer_market.mapper;

import com.football_transfer_market.Dto.TeamCountryDto;
import com.football_transfer_market.Models.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper extends BaseEntityMapper<TeamCountryDto, Team>{
}
