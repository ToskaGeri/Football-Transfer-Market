package com.football_transfer_market.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "Players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Player_Id")
    private Long playerId;

    @Column(name = "Player_Name")
    private String playerName;

    @Column(name = "Player_Surname")
    private String playerSurname;

    @Column(name = "Player_Age")
    private int age;

    @Column(name = "Free_Agent")
    private boolean isFreeAgent;

    @Column(name = "Player_Contract_Start_Date")
    private Date contractStartDate;

    @Column(name = "Player_Contract_End_Date")
    private Date contractEndDate;

    @Column(name = "Player_Nation")
    private String playerNation;

    @Column(name = "Player_Position")
    private String playerPosition;

    @Column(name = "Player_Price")
    private int playerPrice;

    private String teamName;

    @OneToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "Handler"})
    private List<TransferOffer> transferOffers;

    @OneToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "Handler"})
    private List<Price> priceList;


}
