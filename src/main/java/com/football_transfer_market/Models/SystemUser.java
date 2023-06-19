package com.football_transfer_market.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SYSTEM_USER")
@Where(clause = "status = true or status is null")
@Getter
@Setter
public class SystemUser implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "logs_user_seq")
    @SequenceGenerator(name = "logs_user_seq", sequenceName = "logs_user_seq", initialValue = 1000)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    private String password;

    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SYSTEM_USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<SystemUserRole> authorities;

    private Boolean isAccountNonExpired;

    private Boolean isAccountNonLocked;

    private Boolean isCredentialsNonExpired;

    private Boolean isEnabled;

    public SystemUser() {
    }

    public SystemUser(Long id) {
        super();
    }
}
