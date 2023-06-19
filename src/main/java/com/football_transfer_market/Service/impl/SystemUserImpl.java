package com.football_transfer_market.Service.impl;


import com.football_transfer_market.Models.SystemUser;
import com.football_transfer_market.Models.SystemUserRole;
import com.football_transfer_market.Repository.SystemRoleRepository;
import com.football_transfer_market.Repository.SystemUserRepository;
import com.football_transfer_market.Service.SystemUserService;
import com.football_transfer_market.errors.CustomError;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SystemUserImpl implements SystemUserService {

    private final SystemUserRepository systemUserRepository;
    private final SystemRoleRepository systemRoleRepository;

    private final BCryptPasswordEncoder passwordEncoder;


    public SystemUserImpl(
            SystemUserRepository systemUserRepository,
            SystemRoleRepository systemRoleRepository,
            BCryptPasswordEncoder passwordEncoder
                         ) {
        this.systemUserRepository = systemUserRepository;
        this.systemRoleRepository = systemRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SystemUser saveUser(SystemUser systemUser) {

        Optional<SystemUser> logsUserUsername = systemUserRepository.findSystemUserByUsername(systemUser.getUsername());
        if (logsUserUsername.isPresent()) {
            throw CustomError.builder()
                    .errorCode(400)
                    .errorMessage("Ky username eshte i zene.")
                    .build();
        }
        setUserRoles(systemUser);
        systemUser.setStatus(true);
        systemUser.setIsAccountNonExpired(true);
        systemUser.setIsAccountNonLocked(true);
        systemUser.setIsCredentialsNonExpired(true);
        systemUser.setIsEnabled(true);
        systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        return systemUserRepository.save(systemUser);
    }

    private void setUserRoles(SystemUser systemUser) {
        if (systemUser.getAuthorities() == null || systemUser.getAuthorities().isEmpty()) {
            throw CustomError.builder()
                    .errorCode(400)
                    .errorMessage("Ju lutem specifikoni nje rol per perdoruesin")
                    .build();
        } else {
            List<SystemUserRole> systemUserRoles = new ArrayList<>();
            systemUser.getAuthorities().forEach(role -> {
                Optional<SystemUserRole> systemUserRole = systemRoleRepository.findSystemUserRoleByName(role.getName());
                if(systemUserRole.isEmpty()) {
                    throw CustomError.builder()
                            .errorCode(400)
                            .errorMessage("Roli nuk u gjet ne sistem: " + role.getName())
                            .build();
                }
                systemUserRoles.add(systemUserRole.get());
            });
            systemUser.setAuthorities(systemUserRoles);
        }
    }
}
