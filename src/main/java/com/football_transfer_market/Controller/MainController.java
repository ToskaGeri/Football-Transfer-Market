package com.football_transfer_market.Controller;

import com.football_transfer_market.Dto.TeamPlayerDto;
import com.football_transfer_market.Models.*;
import com.football_transfer_market.Repository.*;
import com.football_transfer_market.Service.*;
import com.football_transfer_market.errors.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static com.football_transfer_market.utils.ErrorConstants.*;

@RestController
@RequestMapping("/TransferMarket")
public class MainController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TransferOfferRepository transferOfferRepository;

    @Autowired
    private TransferOfferService transferOfferService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private TeamPlayersRepository teamPlayersRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;


    @GetMapping("/countries")
    public ApiResponse<List<Country>> allCountries() {
        return new ApiResponse<>(countryRepository.findAll(Sort.by(Sort.Direction.ASC,"countryId")), "U kthye me sukses");
    }

    @GetMapping("/teams")
    public List<Team> allTeams() {
        return teamRepository.findAll(Sort.by(Sort.Direction.ASC,"teamId"));
    }

    @GetMapping("/players")
    public List<TeamPlayerDto> allPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping("/offer")
    public ApiResponse<TransferOffer> makeOffer(@RequestBody TransferOffer transferOffer){
        ZonedDateTime date = ZonedDateTime.now();

        Player player = playerRepository.getReferenceById(transferOffer.getPlayerId());
        Team team = teamRepository.findTeamByTeamName(transferOffer.getTeamName());
        Country country = countryRepository.getReferenceById(team.getCountry().getCountryId());

        double offeredPrice = transferOffer.getOfferValue();
        int limitPrice = (player.getPlayerPrice() * 60) / 100;

        if (!transferOfferService.isTransferMarketOpen(date, country.getCountryCloseDate())) {
            throw new CustomError(CLOSED_MARKET_ERROR_MESSAGE, CLOSED_MARKET_ERROR_CODE);
        }

        else if(offeredPrice < limitPrice){
            throw new CustomError(LOW_PRICE_OFFER_ERROR_MESSAGE,LOW_PRICE_OFFER_ERROR_CODE);
        }

        else if(team.getPlayers().contains(player)){
            throw new CustomError(OFFER_PLAYER_SAME_TEAM_ERROR_MESSAGE,OFFER_PLAYER_SAME_TEAM_ERROR_CODE);
        }

        return new ApiResponse<>(transferOfferRepository.saveAndFlush(transferOffer),"Transfer Succesfully Created");
    }

    @GetMapping("/playerHistory")
    public ApiResponse<List<TeamPlayerDatePrice>> history(@RequestParam("id") Long id){
        return new ApiResponse<>(teamPlayersRepository.findAllByPlayerId(id),"OK");
    }

}
