package com.football_transfer_market.Controller;

import com.football_transfer_market.Models.ApiResponse;
import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Models.Team;
import com.football_transfer_market.Models.TeamPlayerDatePrice;
import com.football_transfer_market.Repository.PlayerRepository;
import com.football_transfer_market.Repository.TeamPlayersRepository;
import com.football_transfer_market.Repository.TeamRepository;
import com.football_transfer_market.Service.SearchService;
import com.football_transfer_market.spec.PlayerSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/TransferMarket/search/player")
public class SearchPlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SearchService searchService;

    @Autowired
    private TeamPlayersRepository teamPlayersRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/ByName")
    public ApiResponse<List<Player>> searchByName(@RequestParam("name") String name){
        return new ApiResponse<>(searchService.searchPlayersByName(name),"OK");
    }

    @GetMapping("/BySurname")
    public ApiResponse<List<Player>> searchBySurname(@RequestParam("surname") String surname){
        return new ApiResponse<>(playerRepository.searchPlayersByPlayerSurname(surname),"OK");
    }

    @GetMapping("/ByAge")
    public ApiResponse<List<Player>> searchByAge(@RequestParam("age") int age){
        return new ApiResponse<>(playerRepository.searchPlayersByAge(age),"OK");
    }

    @GetMapping("/ByFreeAgent")
    public ApiResponse<List<Player>> searchByFreeAgent(@RequestParam("isFreeAgent") boolean isFreeAgent){
        return new ApiResponse<>(playerRepository.searchPlayersByFreeAgent(isFreeAgent),"OK");
    }

    @GetMapping("/ByNation")
    public ApiResponse<List<Player>> searchByNation(@RequestParam("nation") String nation){
        return new ApiResponse<>(playerRepository.searchPlayersByPlayerNation(nation),"OK");
    }

    @GetMapping("/ByPosition")
    public ApiResponse<List<Player>> searchByPosition(@RequestParam("position") String position){
        return new ApiResponse<>(playerRepository.searchPlayersByPlayerPosition(position),"OK");
    }

    @GetMapping("/ByPrice")
    public ApiResponse<List<Player>> searchByPrice(@RequestParam("price") int price){
        return new ApiResponse<>(playerRepository.searchPlayersByPlayerPrice(price),"OK");
    }

    @GetMapping("/ByTeam")
    public ApiResponse<List<Player>> searchByTeamName(@RequestParam("teamName") String teamName){
        return new ApiResponse<>(playerRepository.searchPlayersByTeamName(teamName),"OK");
    }

    @GetMapping("/")
    public ApiResponse<List<Player>> searchByAll(@RequestParam(value = "name",required = false) String name,
                                                  @RequestParam(value = "surname",required = false) String surname,
                                                  @RequestParam(value = "age",required = false,defaultValue = "0") int age,
                                                  @RequestParam(value = "isFreeAgent",required = false) boolean isFreeAgent,
                                                  @RequestParam(value = "nation",required = false) String nation,
                                                  @RequestParam(value = "position",required = false) String position,
                                                  @RequestParam(value = "teamName",required = false) String teamName,
                                                  @RequestParam(value = "price",required = false,defaultValue = "0") int price) {

        Specification<Player> specification = PlayerSpec.getSpec(name,surname,age,isFreeAgent,nation,position,price,teamName);
        return new ApiResponse<>(playerRepository.findAll(specification),"OK");
    }

    @GetMapping("/getPlayerTeamsByDates")
    public ApiResponse<List<TeamPlayerDatePrice>> getPlayerTeams(@RequestParam(value = "id") Long id,
                                                                 @RequestParam(value = "startDate") Date startDate,
                                                                 @RequestParam(value = "endDate") Date endDate){
        return new ApiResponse<>(searchService.searchTeamsByPlayerContract(startDate,endDate,id),"OK");
    }
}
