package com.football_transfer_market.Service;

import com.football_transfer_market.Dto.TeamCountryDto;
import com.football_transfer_market.Models.Team;
import com.football_transfer_market.Repository.TeamRepository;
import com.football_transfer_market.mapper.TeamMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public List<TeamCountryDto> getAllTeams(){
        return teamRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private TeamCountryDto convertEntityToDto(Team team) {
        return teamMapper.toDto(team);
    }

    private Team convertDtoToEntity(TeamCountryDto teamCountryDto) {
        return teamMapper.toEntity(teamCountryDto);
    }
}
