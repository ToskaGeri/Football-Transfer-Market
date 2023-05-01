package com.football_transfer_market.Controller;

import com.football_transfer_market.Dto.TeamPlayerDto;
import com.football_transfer_market.Models.*;
import com.football_transfer_market.Repository.*;
import com.football_transfer_market.Service.*;
import com.football_transfer_market.errors.CustomError;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

import static com.football_transfer_market.utils.ErrorConstants.*;

@RestController
@RequestMapping("/TransferMarket")
public class MainController {

    private final CountryRepository countryRepository;

    private final TeamRepository teamRepository;

    private final PlayerRepository playerRepository;

    private final TransferOfferRepository transferOfferRepository;

    private final TransferOfferService transferOfferService;

    private final TeamPlayersRepository teamPlayersRepository;

    private final PlayerService playerService;

    public MainController(CountryRepository countryRepository, TeamRepository teamRepository, PlayerRepository playerRepository, TransferOfferRepository transferOfferRepository, TransferOfferService transferOfferService, TeamPlayersRepository teamPlayersRepository, PlayerService playerService) {
        this.countryRepository = countryRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.transferOfferRepository = transferOfferRepository;
        this.transferOfferService = transferOfferService;
        this.teamPlayersRepository = teamPlayersRepository;
        this.playerService = playerService;
    }


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
