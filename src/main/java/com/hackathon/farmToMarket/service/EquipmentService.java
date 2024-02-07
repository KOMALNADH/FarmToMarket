package com.hackathon.farmToMarket.service;

import java.util.List;

import com.hackathon.farmToMarket.entity.Equipment;

public interface EquipmentService {
	Equipment fetchById(Long id);
	List<Equipment> getAllByLocation(String location);
	Equipment updateEquipment(Long equipId,Integer quantity);
	Equipment returnEquipment(Long equipId,Integer quantity);
}
