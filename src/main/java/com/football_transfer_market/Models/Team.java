package com.football_transfer_market.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity(name = "Teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Team_Id")
    private Long teamId;

    @Column(name = "Team_Name")
    private String teamName;

    @Column(name = "Team_Budget")
    private Double teamBudget;

    @ManyToOne
    @JoinColumn(name = "Country_Id")
    private Country country;

    @OneToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "Handler"})
    @JoinTable(
            name = "team_players",
            joinColumns = @JoinColumn(name = "Team_Id"),
            inverseJoinColumns = @JoinColumn(name = "Player_Id")
    )
    private List<Player> players;

    @OneToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "Handler"})
    private List<TransferOffer> transferOffers;
}
