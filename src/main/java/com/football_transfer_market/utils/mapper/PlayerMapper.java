package com.football_transfer_market.utils.mapper;

import com.football_transfer_market.Dto.TeamPlayerDto;
import com.football_transfer_market.Models.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper extends BaseEntityMapper<TeamPlayerDto, Player> {


}
