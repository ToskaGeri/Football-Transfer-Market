package com.football_transfer_market.Repository;

import com.football_transfer_market.Models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser,Long> {

    Optional<SystemUser> findSystemUserByUsername(String username);
}
