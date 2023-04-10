package com.football_transfer_market.Service;

import com.football_transfer_market.Dto.TeamCountryDto;
import com.football_transfer_market.Dto.TeamPlayerDto;
import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Models.Team;
import com.football_transfer_market.Repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlayerRepository playerRepository;

    public List<TeamPlayerDto> getAllPlayers(){
        return playerRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private TeamPlayerDto convertEntityToDto(Player player) {

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        TeamPlayerDto teamPlayerDto;
        new TeamPlayerDto();
        teamPlayerDto = modelMapper.map(player, TeamPlayerDto.class);
        teamPlayerDto.setTeamId(player.getTeam().getTeamId());
        return teamPlayerDto;
    }

    private Player convertDtoToEntity(TeamPlayerDto teamPlayerDto) {

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        Player player;
        new Player();
        player = modelMapper.map(teamPlayerDto,Player.class);
        return player;
    }

}
