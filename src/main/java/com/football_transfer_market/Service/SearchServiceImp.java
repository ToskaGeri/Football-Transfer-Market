package com.football_transfer_market.Service;

import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Models.TeamPlayerDatePrice;
import com.football_transfer_market.Repository.PlayerRepository;
import com.football_transfer_market.Repository.TeamPlayersRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImp implements SearchService{

    private final PlayerRepository playerRepository;

    private final TeamPlayersRepository teamPlayersRepository;

    public SearchServiceImp(PlayerRepository playerRepository, TeamPlayersRepository teamPlayersRepository) {
        this.playerRepository = playerRepository;
        this.teamPlayersRepository = teamPlayersRepository;
    }

    public List<Player> searchPlayersByName(String name){
        return playerRepository.searchPlayersByPlayerName(name);
    }

    @Override
    public List<TeamPlayerDatePrice> searchTeamsByPlayerContract(Date contractStartDate, Date contractEndDate, Long id) {
        return teamPlayersRepository.searchTeamsByPlayerStartDates(contractStartDate,contractEndDate,id);
    }
}
