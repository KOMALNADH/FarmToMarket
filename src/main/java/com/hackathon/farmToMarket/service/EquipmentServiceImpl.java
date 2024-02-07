package com.hackathon.farmToMarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.farmToMarket.entity.Equipment;
import com.hackathon.farmToMarket.repository.EquipmentRepository;
import com.hackathon.farmToMarket.repository.EquipmentUserRepository;
import com.hackathon.farmToMarket.repository.UserRepository;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService{
	
	@Autowired
	EquipmentRepository equipRepo;

	@Autowired
	EquipmentUserRepository euRepo;
	@Autowired
	UserRepository userRepo;

	@Override
	public Equipment fetchById(Long id) {
		Optional<Equipment> equipment=equipRepo.findById(id);
		if(equipment.isPresent()) {
		return equipment.get();
		}
		else {
			return null;
		}
	}



	@Override
	public List<Equipment> getAllByLocation(String location) {
		return equipRepo.findByLocation(location);
	}

	@Override
	public Equipment updateEquipment(Long equipId, Integer quantity) {
		Optional<Equipment> e=equipRepo.findById(equipId);
		if(e.isPresent()) {
		Equipment equip=e.get();
		Integer q=equip.getQuantity()-quantity;
		equip.setQuantity(q);
		equip=equipRepo.save(equip);
		return equip;
		}
		else {
			return null;
		}
	}

	@Override
	public Equipment returnEquipment(Long equipId, Integer quantity) {
		Optional<Equipment> e=equipRepo.findById(equipId);
		if(e.isPresent()) {
		Equipment equip=e.get();
		equip.setQuantity(equip.getQuantity()+quantity);
		equipRepo.save(equip);
		return equip;
		}
		else {
			return null;
		}
	}
}
