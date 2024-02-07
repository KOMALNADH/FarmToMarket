package com.hackathon.farmToMarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.farmToMarket.entity.User;
import com.hackathon.farmToMarket.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;

	@Override
	public User fetchById(long id) {
		Optional<User> user=userRepo.findById(id);
		if(user.isPresent()) {
		return user.get();
		}
		else {
			return null;
		}
	}

}
