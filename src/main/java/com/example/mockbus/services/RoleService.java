package com.example.mockbus.services;

import com.example.mockbus.entities.RoleDomain;

import java.util.Optional;

public interface RoleService extends BaseService<RoleDomain,Integer> {

    Optional<RoleDomain> findByName(String name);
}
