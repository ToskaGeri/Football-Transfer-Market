package com.football_transfer_market.Repository;

import com.football_transfer_market.Models.SystemUser;
import com.football_transfer_market.Models.SystemUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemRoleRepository extends JpaRepository<SystemUserRole,Long> {

    Optional<SystemUserRole> findSystemUserRoleByName(String name);
}
