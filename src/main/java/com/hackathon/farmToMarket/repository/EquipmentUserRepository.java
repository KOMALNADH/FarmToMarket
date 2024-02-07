package com.hackathon.farmToMarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.farmToMarket.entity.Equipment;
import com.hackathon.farmToMarket.entity.EquipmentUser;
import com.hackathon.farmToMarket.entity.User;

@Repository
public interface EquipmentUserRepository extends JpaRepository<EquipmentUser,Integer>{
	List<EquipmentUser> findAllByUser(User user);
	List<EquipmentUser> findByUserAndEquipment(User user,Equipment equipment);
	EquipmentUser findByEquipmentAndUser(Equipment equipment,User user);

}
