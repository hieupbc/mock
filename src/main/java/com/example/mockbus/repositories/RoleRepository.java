package com.example.mockbus.repositories;

import com.example.mockbus.entities.RoleDomain;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleDomain, Integer> {

  Optional<RoleDomain> findRoleDomainByName(String name);
}
