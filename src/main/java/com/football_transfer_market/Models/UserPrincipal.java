package com.football_transfer_market.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final SystemUser user;
    private final Long id;


    public UserPrincipal(SystemUser user, Long id) {
        this.user = user;
        this.id = id;
    }

    public UserPrincipal(SystemUser user){
        this.user = user;
        this.id = user.getId();
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
       this.user.getAuthorities().forEach(r -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + r.getName());
            authorities.add(grantedAuthority);
        });

        return authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getIsCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.getIsEnabled();
    }

    public static SystemUser getCurrentUserEntity(){
        UserPrincipal loginUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new SystemUser(loginUser.getId());
    }

}