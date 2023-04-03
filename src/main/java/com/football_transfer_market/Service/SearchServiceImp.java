package com.football_transfer_market.Service;

import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Models.TeamPlayerDatePrice;
import com.football_transfer_market.Repository.PlayerRepository;
import com.football_transfer_market.Repository.TeamPlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImp implements SearchService{

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamPlayersRepository teamPlayersRepository;

    public List<Player> searchPlayersByName(String name){
        return playerRepository.searchPlayersByPlayerName(name);
    }

    @Override
    public List<TeamPlayerDatePrice> searchTeamsByPlayerContract(Date contractStartDate, Date contractEndDate, Long id) {
        return teamPlayersRepository.searchTeamsByPlayerStartDates(contractStartDate,contractEndDate,id);
    }
}
