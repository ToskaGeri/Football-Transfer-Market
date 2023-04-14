package com.football_transfer_market.Service;

import com.football_transfer_market.Dto.TeamPlayerDto;
import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Repository.PlayerRepository;
import com.football_transfer_market.utils.mapper.PlayerMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final ModelMapper modelMapper;


    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    public PlayerService(ModelMapper modelMapper, PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.modelMapper = modelMapper;
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

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        TeamPlayerDto teamPlayerDto;
        new TeamPlayerDto();
        teamPlayerDto = modelMapper.map(player, TeamPlayerDto.class);
        teamPlayerDto.setTeamId(player.getTeam().getTeamId());
        return teamPlayerDto;
    }

    private Player convertDtoToEntity(TeamPlayerDto teamPlayerDto) {

        //TODO Shembull MapStruct shiko interface PlayerMapper e cila nepermjet mapstruct, pasi i jep run projektit gjeneron klasen PlayerMapperImpl ne folderin target tek generated sources
        // aty mund te kuptosh se si e ben mapimin dhe cfare gjeje automatizon mapstruct
        // pasi ta shikosh kete meso edhe perdorimin e annotation @Mapping(target = .. source = ) tel kjo interface dhe bej nje shembull diku ku ka kuptim te perdoret
        return playerMapper.toEntity(teamPlayerDto);
    }

}
