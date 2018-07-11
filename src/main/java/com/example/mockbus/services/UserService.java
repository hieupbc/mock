package com.example.mockbus.services;

import java.util.List;
import java.util.Optional;

import com.example.mockbus.DTO.UserRegisterForm;
import com.example.mockbus.entities.UserDomain;
import org.springframework.data.domain.Page;

public interface UserService extends BaseService<UserDomain, Long>{
	boolean isExist(String email);
	List<UserDomain> findAll();

	Optional<UserDomain> findByEmail(String email);

	Page<UserDomain> findByNameIsLike(String name, int page, int size);

	List<UserDomain> findByNameIsLike(String name);

	UserDomain registerNewUser(UserRegisterForm userRegisterForm);
	
	boolean makePayment(UserDomain userDomain, int value);
}
