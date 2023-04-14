package com.football_transfer_market.Service;

import com.football_transfer_market.Dto.TeamCountryDto;
import com.football_transfer_market.Dto.TeamPlayerDto;
import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Models.Team;
import com.football_transfer_market.Repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TeamCountryDto> getAllTeams(){
        return teamRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private TeamCountryDto convertEntityToDto(Team team) {

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
