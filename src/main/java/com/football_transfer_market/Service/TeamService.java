package com.football_transfer_market.Service;

import com.football_transfer_market.Dto.TeamCountryDto;
import com.football_transfer_market.Dto.TeamPlayerDto;
import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Models.Team;
import com.football_transfer_market.Repository.TeamRepository;
import com.football_transfer_market.utils.mapper.PlayerMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    //TODO menyra e sygjeruar per Dependency injection eshte me konstruktor sic e bera une ketu me poshte dhe jo me @Autowired
    private final TeamRepository teamRepository;

    private final ModelMapper modelMapper;

    public TeamService(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    public List<TeamCountryDto> getAllTeams(){
        return teamRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    private TeamCountryDto convertEntityToDto(Team team) {

        //TODO kte pse e ke perdorur? Nuk ke pse te kesh variabla "ambiguous" pra me shume se sa nje variabel qe te perkoje me emrin
        // te sygjeroj te perdoresh mapstruct, eshte me e sakte dhe me e shpejte sepse gjeneron kod per te mappuar nga entitet ne dto dhe nuk e ben kete gje
        // me vone gjate runtime te aplikacionit sic e ben model mapper
        // E kam konfiguruar une per projektin dhe mund te shikosh nje shembull tek Player Sevice
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        TeamCountryDto teamCountryDto;
        new TeamCountryDto();
        teamCountryDto = modelMapper.map(team, TeamCountryDto.class);
        teamCountryDto.setCountryId(team.getCountry().getCountryId());
        return teamCountryDto;
    }

    private Team convertDtoToEntity(TeamCountryDto teamCountryDto) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        Team team;
        new Team();
        team = modelMapper.map(teamCountryDto,Team.class);
        return team;
    }

}
