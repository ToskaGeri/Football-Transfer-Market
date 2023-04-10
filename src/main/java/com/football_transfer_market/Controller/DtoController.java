package com.football_transfer_market.Controller;

import com.football_transfer_market.Dto.TeamCountryDto;
import com.football_transfer_market.Dto.TeamPlayerDto;
import com.football_transfer_market.Service.PlayerService;
import com.football_transfer_market.Service.TeamService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DtoController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public List<TeamCountryDto> getTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/players")
    public List<TeamPlayerDto> getAll(){
        return playerService.getAllPlayers();
    }


}
