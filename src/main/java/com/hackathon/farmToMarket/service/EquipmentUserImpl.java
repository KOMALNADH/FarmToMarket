package com.hackathon.farmToMarket.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.farmToMarket.entity.Equipment;
import com.hackathon.farmToMarket.entity.EquipmentUser;
import com.hackathon.farmToMarket.entity.User;
import com.hackathon.farmToMarket.repository.EquipmentRepository;
import com.hackathon.farmToMarket.repository.EquipmentUserRepository;
import com.hackathon.farmToMarket.repository.UserRepository;

@Service
@Transactional
public class EquipmentUserImpl implements EquipmentUserService{
	
	@Autowired
	EquipmentUserRepository euRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	EquipmentRepository equipRepo;
	@Override
	public EquipmentUser insertData(User user, Equipment equipment, Integer quantity) {
		EquipmentUser equipmentUser=euRepo.findByEquipmentAndUser(equipment,user);
		if(equipmentUser !=null) {
			equipmentUser.setAmount(equipmentUser.getAmount()+quantity);
			euRepo.save(equipmentUser);
			return equipmentUser;
		}
		else {
		EquipmentUser eu=new EquipmentUser();
		eu.setUser(user);
		eu.setEquipment(equipment);
		eu.setAmount(quantity);
		euRepo.save(eu);
		return eu;
		}
	}

	@Override
	public List<EquipmentUser> getAllByUserId(Long userId) {
		Optional<User> user=userRepo.findById(userId);
		if(user.isPresent()) {
		return euRepo.findAllByUser(user.get());
		}
		else {
			return Collections.emptyList();
		}
	}

	@Override
	public void removeEuBy(Long userId, Long equipId, Integer quantity) {
		Optional<User> user=userRepo.findById(userId);
		if(user.isPresent()) {
		Optional<Equipment> equipment=equipRepo.findById(equipId);
		if(equipment.isPresent()) {
		EquipmentUser eu=euRepo.findByEquipmentAndUser(equipment.get(),user.get());
		if(eu.getAmount().equals(quantity)) {
			euRepo.delete(eu);
		}
		else {
			eu.setAmount(eu.getAmount()-quantity);
			euRepo.save(eu);
		}
		}
		}
		
		
	}

}
