package com.hackathon.farmToMarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hackathon.farmToMarket.repository.EquipmentRepository;
import com.hackathon.farmToMarket.repository.UserRepository;

@SpringBootApplication
public class FarmToMarketApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FarmToMarketApplication.class, args);
	}
	@Autowired
	UserRepository userRepo;
	@Autowired
	EquipmentRepository equipmentRepo;
	@Override
	public void run(String... args) throws Exception {
		
	}

	
}
