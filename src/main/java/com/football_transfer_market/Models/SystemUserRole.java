package com.football_transfer_market.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;


import java.io.Serializable;

@Entity
@Table(name = "SYSTEM_ROLE")
@Where(clause = "status = true or status is null")
@Getter
@Setter
public class SystemUserRole implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "logs_role_seq")
    @SequenceGenerator(name = "logs_role_seq", sequenceName = "logs_role_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    private String name;

    private Boolean status;

}
