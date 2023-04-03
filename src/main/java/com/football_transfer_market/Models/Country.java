package com.football_transfer_market.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter

@Entity(name = "Countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Country_Id")
    private Long countryId;

    @Column(name = "Country_Name")
    private String countryName;

    @Column(name = "Country_Transfer_Close_Date")
    private ZonedDateTime countryCloseDate;

    @OneToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "Handler"})
    @JoinTable(
            name = "country_teams",
            joinColumns = @JoinColumn(name = "Country_Id"),
            inverseJoinColumns = @JoinColumn(name = "Team_Id")
    )
    private List<Team> teams;

    public Country() {
    }

}
