package com.football_transfer_market.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "Team_Players_Prices")
public class TeamPlayerDatePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "player_contract_start_date")
    private Date playerContractStartDate;

    @Column(name = "player_contract_end_date")
    private Date playerContractEndDate;

    @Column(name = "player_price")
    private int playerPrice;

//    @OneToOne
//    @JoinColumn(name = "Team_Id",referencedColumnName = "Team_Id")
//    private Team team;
//
//    @OneToOne
//    @JoinColumns({@JoinColumn(name = "Player_Id",referencedColumnName = "Player_Id"),
//                    @JoinColumn(name = "Player_Contract_Start_Date",referencedColumnName = "Player_Contract_Start_Date"),
//                    @JoinColumn(name = "Player_Contract_End_Date",referencedColumnName = "Player_Contract_End_Date"),
//                    @JoinColumn(name = "Player_Price", referencedColumnName = "Player_Price")}
//            )
//    private Player player;

}
