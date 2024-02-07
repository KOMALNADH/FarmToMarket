package com.hackathon.farmToMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.farmToMarket.entity.Equipment;
import com.hackathon.farmToMarket.entity.EquipmentUser;
import com.hackathon.farmToMarket.entity.User;
import com.hackathon.farmToMarket.service.EquipmentService;
import com.hackathon.farmToMarket.service.EquipmentUserService;
import com.hackathon.farmToMarket.service.UserService;

@RestController
@RequestMapping(value="/farmToMarket")
public class FarmToMarketController {
	
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	UserService userService;
	@Autowired
	EquipmentUserService euService;

	
	@GetMapping(value="/getEquipment/{location}")
	public ResponseEntity<List<Equipment>> getByLocation(@PathVariable String location){
		List<Equipment> list=equipmentService.getAllByLocation(location);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping(value="/bookEquioment/user/{userId}/Equip/{equipId}/quantity/{quantity}")
	public ResponseEntity<EquipmentUser> bookEquipment(@PathVariable Long userId,@PathVariable Long equipId,@PathVariable Integer quantity){
		Equipment e=equipmentService.fetchById(equipId);
		if(e.getQuantity() !=0) {
		User user=userService.fetchById(userId);
		Equipment equipment=equipmentService.updateEquipment(equipId,quantity);
		EquipmentUser eu=euService.insertData(user,equipment,quantity);
		return new ResponseEntity<>(eu,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping(value="/getEquipById/{userId}")
	public ResponseEntity<List<EquipmentUser>> getByUserId(@PathVariable Long userId){
		List<EquipmentUser> list=euService.getAllByUserId(userId);
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	@PutMapping(value="/returnEquipment/user/{userId}/equipId/{equipId}/quantity/{quantity}")
	public ResponseEntity<Equipment> returnEquipment(@PathVariable Long userId,@PathVariable Long equipId,@PathVariable Integer quantity){
		Equipment equip=equipmentService.returnEquipment(equipId,quantity);
		euService.removeEuBy(userId,equipId,quantity);
		return new ResponseEntity<>(equip,HttpStatus.OK);
	}

}
