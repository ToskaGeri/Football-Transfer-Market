package com.football_transfer_market.Repository;


import com.football_transfer_market.Models.TeamPlayerDatePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TeamPlayersRepository extends JpaRepository<TeamPlayerDatePrice,Long> {

    @Query("SELECT p FROM Team_Players_Prices p where p.playerId =:id" +
            " AND p.playerContractStartDate >=:n AND p.playerContractStartDate <=:m" )
    List<TeamPlayerDatePrice> searchTeamsByPlayerStartDates(@Param("n") Date contractStartDate, @Param("m") Date contractEndDate, @Param("id") Long id);

    @Query("SELECT p FROM Team_Players_Prices p where p.playerId =:id")
    List<TeamPlayerDatePrice> findAllByPlayerId(@Param("id")Long id);

}
