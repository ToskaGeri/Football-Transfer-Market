package com.football_transfer_market.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "Transfer_Offer")
public class TransferOffer {

    @Id
    @Column(name = "Offer_Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long offer_Id;

    @Column(name = "Offer_Value")
    private double offerValue;

    private Long playerId;

    private String teamName;

}
