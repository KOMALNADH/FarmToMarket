package com.hackathon.farmToMarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.farmToMarket.entity.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long>{
	List<Equipment> findByLocation(String location);
}
