package com.example.mockbus.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mockbus.DTO.UserRegisterForm;
import com.example.mockbus.entities.RoleDomain;
import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.exceptions.EntityAlreadyExistException;
import com.example.mockbus.exceptions.PaymentUnsuccessfulException;
import com.example.mockbus.repositories.RoleRepository;
import com.example.mockbus.repositories.UserRepository;
import com.example.mockbus.services.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDomain, Long> implements UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private Converter<UserRegisterForm, UserDomain> userRegisterFormToUser;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			Converter<UserRegisterForm, UserDomain> userRegisterFormToUser) {
		super(userRepository);
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRegisterFormToUser = userRegisterFormToUser;
	}

	public String convertSearchName(String name) {
		return "%".concat(name).concat("%");
	}

	@Override
	public boolean isExist(String email) {
		Optional<UserDomain> userDomainByEmail = userRepository.findUserDomainByEmail(email);
		return userDomainByEmail.isPresent();
	}

	@Override
	public List<UserDomain> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserDomain> findByEmail(String email) {
		return userRepository.findUserDomainByEmail(email);
	}

	@Override
	public Page<UserDomain> findByNameIsLike(String name, int page, int size) {
		return this.findByNameIsLike(name, PageRequest.of(page, size));
	}

	@Override
	public List<UserDomain> findByNameIsLike(String name) {
		return userRepository.findUserDomainByNameIsLike(this.convertSearchName(name));
	}

	public Page<UserDomain> findByNameIsLike(String name, Pageable pageable) {
		return userRepository.findUserDomainByNameIsLike(convertSearchName(name), pageable);
	}

	@Override
	@Transactional
	public UserDomain registerNewUser(UserRegisterForm userRegisterForm) {
		if (isExist(userRegisterForm.getEmail())) {
			throw new EntityAlreadyExistException(
					"There is an account with that email adress: " + userRegisterForm.getEmail());
		}
		UserDomain convert = userRegisterFormToUser.convert(userRegisterForm);
		Set<RoleDomain> roles = convert.getRoles();
		roles.add(roleRepository.findRoleDomainByName("ROLE_USER").get());
		convert.setRoles(roles);
		UserDomain userDomain = this.create(convert);
		return userDomain;
	}

	@Override
	@Transactional
	public boolean makePayment(UserDomain userDomain, int value) {
		int accountBalance = userDomain.getAccountBalance();
		if (accountBalance < value) {
			throw new PaymentUnsuccessfulException("Your Account doesnt have enough money");
		} else {
			accountBalance -= value;
			userDomain.setAccountBalance(accountBalance);
			return true;
		}
	}

}
