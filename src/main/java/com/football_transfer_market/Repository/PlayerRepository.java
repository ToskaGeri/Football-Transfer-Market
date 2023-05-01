package com.football_transfer_market.Repository;

import com.football_transfer_market.Models.Player;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Players p where " +
            "p.playerName LIKE CONCAT('%', :name, '%')")
    List<Player> searchPlayersByPlayerName(String name);

    @Query("SELECT p FROM Players p where " +
            "p.playerName =:n")
    List<Player> searchPlayersByPlayerSurname(@Param("n") String name);

    @Query("SELECT p FROM Players p where p.age =:n")
    List<Player> searchPlayersByAge(@Param("n") int age);

    @Query("SELECT p FROM Players p where " +
            "p.isFreeAgent =:n")
    List<Player> searchPlayersByFreeAgent(@Param("n") boolean isFreeAgent);

    @Query("SELECT p FROM Players p where " +
            "p.playerNation =:n")
    List<Player> searchPlayersByPlayerNation(@Param("n") String nation);

    @Query("SELECT p FROM Players p where " +
            "p.playerPosition =:n")
    List<Player> searchPlayersByPlayerPosition(@Param("n") String position);

    @Query("SELECT p FROM Players p where " +
            "p.playerPrice =:n")
    List<Player> searchPlayersByPlayerPrice(@Param("n") int price);

    @Query("SELECT p FROM Players p where " +
            "p.teamName =:n")
    List<Player> searchPlayersByTeamName(@Param("n") String teamName);

    @Query("SELECT p FROM Players p where " +
            "p.playerName =:n " +
            "or p.playerSurname =:s " +
            "or p.age =:a " +
            "or p.isFreeAgent =:isFree " +
            "or p.playerNation =:na " +
            "or p.playerPosition =:po " +
            "or p.teamName =:t " +
            "or p.playerPrice =:pr ")
    List<Player> searchPlayers(@Param("n") String name,
                                            @Param("s") String surname,
                                            @Param("a") int age,
                                            @Param("isFree") String isFreeAgent,
                                            @Param("na") String nation,
                                            @Param("po") String position,
                                            @Param("t") String teamName,
                                            @Param("pr") int price);

    List<Player> findAll(Specification<Player> specification);
}
