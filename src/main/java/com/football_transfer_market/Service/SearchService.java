package com.football_transfer_market.Service;

import com.football_transfer_market.Models.Player;
import com.football_transfer_market.Models.TeamPlayerDatePrice;

import java.util.Date;
import java.util.List;

public interface SearchService {

    List<Player> searchPlayersByName(String name);

    List<TeamPlayerDatePrice> searchTeamsByPlayerContract(Date contractStartDate, Date contractEndDate, Long id);
}
