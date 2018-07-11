package com.example.mockbus.services.Impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mockbus.entities.RoleDomain;
import com.example.mockbus.repositories.RoleRepository;
import com.example.mockbus.services.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDomain, Integer> implements RoleService {
	private RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository jpaRepository) {
		super(jpaRepository);
		this.roleRepository = jpaRepository;
	}

	@Override
	public Optional<RoleDomain> findByName(String name) {
		return roleRepository.findRoleDomainByName(name);
	}
}
