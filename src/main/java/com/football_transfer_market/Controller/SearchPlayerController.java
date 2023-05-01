package com.football_transfer_market.Controller;

import com.football_transfer_market.Models.ApiResponse;
import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Models.TeamPlayerDatePrice;
import com.football_transfer_market.Repository.PlayerRepository;
import com.football_transfer_market.Service.SearchService;
import com.football_transfer_market.spec.PlayerSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/TransferMarket/search/player")
public class SearchPlayerController {


    private final PlayerRepository playerRepository;

    private final SearchService searchService;


    public SearchPlayerController(PlayerRepository playerRepository, SearchService searchService) {
        this.playerRepository = playerRepository;
        this.searchService = searchService;
    }

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

    @PostMapping("/")
    public ApiResponse<List<Player>> searchByAll(@RequestBody Player player) {

        Specification<Player> specification = PlayerSpec.getSpec(player.getPlayerName(), player.getPlayerSurname(),
                player.getAge(), player.isFreeAgent(), player.getPlayerNation(), player.getPlayerPosition(), player.getPlayerPrice(),
                player.getTeamName());
        return new ApiResponse<>(playerRepository.findAll(specification),"OK");
    }

    @GetMapping("/getPlayerTeamsByDates")
    public ApiResponse<List<TeamPlayerDatePrice>> getPlayerTeams(@RequestParam(value = "id") Long id,
                                                                 @RequestParam(value = "startDate") Date startDate,
                                                                 @RequestParam(value = "endDate") Date endDate){
        return new ApiResponse<>(searchService.searchTeamsByPlayerContract(startDate,endDate,id),"OK");
    }
}
