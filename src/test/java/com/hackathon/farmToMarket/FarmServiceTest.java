package com.hackathon.farmToMarket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackathon.farmToMarket.entity.Equipment;
import com.hackathon.farmToMarket.entity.EquipmentUser;
import com.hackathon.farmToMarket.entity.User;
import com.hackathon.farmToMarket.repository.EquipmentUserRepository;
import com.hackathon.farmToMarket.service.EquipmentService;
import com.hackathon.farmToMarket.service.EquipmentUserService;
import com.hackathon.farmToMarket.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class FarmServiceTest {
	@Autowired
	EquipmentService equipService;
	@Autowired
	UserService userService;
	@Autowired
	EquipmentUserService euService;
	@Autowired
	EquipmentUserRepository euRepo;
	
	@Test
	void testFetchByEquipmentId() {
		Equipment equpi=equipService.fetchById(1l);
		assertEquals(1l,equpi.getEquipmentId());
	}
	@Test
	void testFetchByEquipmentIdIfNull() {
		Equipment equip=equipService.fetchById(7l);
		assertNull(equip);
	}

	@Test
	void testUserById() {
		User u=userService.fetchById(1l);
		assertEquals(1l,u.getUserId());
	}
	@Test
	void testUserByIdIfNull(){
		User u=userService.fetchById(7l);
		assertNull(u);
	}
	@Test
	void testGetAllByLocation() {
		List<Equipment> e=equipService.getAllByLocation("chennai");
		assertEquals("Chennai",e.get(0).getLocation());
	}
	@Test
//	@Disabled
	void testupdateEquipment() {
		Equipment e=equipService.fetchById(1l);
		Equipment eq=equipService.updateEquipment(1l, 2);
		assertEquals(e.getQuantity()-2,eq.getQuantity());
	}
	@Test
	void testUpdateEquipmentIfNull() {
		Equipment eq=equipService.updateEquipment(7l, 2);
		assertNull(eq);
	}
	@Test
	void testreturnEquipment() {
		Equipment e=equipService.fetchById(1l);
		Equipment eq=equipService.returnEquipment(1l, 2);
		assertEquals(e.getQuantity(),eq.getQuantity()-2);
	}
	@Test
	void testReturnEquipmentIfNull() {
		Equipment eq=equipService.returnEquipment(7l, 2);
		assertNull(eq);
	}
	@Test
//	@Disabled
	void testInsertEquipmentUser() {
		User user=userService.fetchById(3l);
		Equipment equipment=equipService.fetchById(1l);
		EquipmentUser e=euRepo.findByEquipmentAndUser(equipment, user);
		if(e ==null) {
		EquipmentUser eu=euService.insertData(user, equipment, 1);
		assertEquals(3l,eu.getUser().getUserId());
		}
	}
	@Test
//	@Disabled
	void testInsertEquipmentUserIfNotNull() {
		User user=userService.fetchById(1l);
		Equipment equipment=equipService.fetchById(1l);
		EquipmentUser e1=new EquipmentUser(1,user,equipment,1);
		euRepo.save(e1);
		EquipmentUser e=euRepo.findByEquipmentAndUser(equipment, user);
		
		if(e!=null) {
		EquipmentUser eu=euService.insertData(user, equipment, 1);
		assertEquals(e.getAmount()+1,eu.getAmount());
		}
		
	}
	@Test
	void testGetAllByUserId() {
		List<EquipmentUser> list=euService.getAllByUserId(1l);
		List<EquipmentUser> eu=new ArrayList<>();
		list.forEach(eu :: add);
		assertEquals(list.size(),eu.size());
	}
	@Test
	void testGetAllUserIdIfNull() {
		List<EquipmentUser> list=euService.getAllByUserId(7l);
		assertEquals(Collections.emptyList(),list);
	}
	@Test
//	@Disabled
	void testremoveEuBy() {
		User user=userService.fetchById(2l);
		Equipment equipment=equipService.fetchById(4l);
		EquipmentUser eu=new EquipmentUser(1,user,equipment,1);
		euRepo.save(eu);
		EquipmentUser e=euRepo.findByEquipmentAndUser(equipment, user);
		if(e.getAmount().equals(1)) {
		euService.removeEuBy(2l, 4l, 1);
		List<EquipmentUser> list=euRepo.findByUserAndEquipment(user, equipment);
		assertEquals(0,list.size());
		}
		
	}
	@Test
//	@Disabled
	void testremoveEuByIfQuantityNotMatches() {
		User user=userService.fetchById(3l);
		Equipment equipment=equipService.fetchById(4l);
		EquipmentUser eu=new EquipmentUser(1,user,equipment,5);
		euRepo.save(eu);
		EquipmentUser e=euRepo.findByEquipmentAndUser(equipment, user);
		if(e.getAmount()!=1) {
		euService.removeEuBy(3l, 4l, 1);
		EquipmentUser e1=euRepo.findByEquipmentAndUser(equipment, user);
		assertEquals(eu.getAmount()-1,e1.getAmount());
		}
		
	}
}
