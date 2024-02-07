package com.hackathon.farmToMarket.service;

import java.util.List;

import com.hackathon.farmToMarket.entity.Equipment;
import com.hackathon.farmToMarket.entity.EquipmentUser;
import com.hackathon.farmToMarket.entity.User;

public interface EquipmentUserService {
	
	EquipmentUser insertData(User user,Equipment equipment,Integer quantity);
	List<EquipmentUser> getAllByUserId(Long userId);
	void removeEuBy(Long userId,Long equipId,Integer quantity);
}
