package com.football_transfer_market.Service;

import com.football_transfer_market.Dto.TeamPlayerDto;
import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Repository.PlayerRepository;
import com.football_transfer_market.mapper.PlayerMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerService( PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public List<TeamPlayerDto> getAllPlayers(){
        return playerRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    private TeamPlayerDto convertEntityToDto(Player player) {

//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//
//        TeamPlayerDto teamPlayerDto;
//        new TeamPlayerDto();
//        teamPlayerDto = modelMapper.map(player, TeamPlayerDto.class);
//        teamPlayerDto.setTeamId(player.getTeam().getTeamId());
//        return teamPlayerDto;
        return playerMapper.toDto(player);
    }

    private Player convertDtoToEntity(TeamPlayerDto teamPlayerDto) {
        return playerMapper.toEntity(teamPlayerDto);
    }

}
