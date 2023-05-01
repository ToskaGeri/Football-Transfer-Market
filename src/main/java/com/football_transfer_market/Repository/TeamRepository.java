package com.football_transfer_market.Repository;

import com.football_transfer_market.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamByTeamName(String teamName);


}
