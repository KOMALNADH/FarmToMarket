package com.hackathon.farmToMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.farmToMarket.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
