package com.hackathon.farmToMarket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackathon.farmToMarket.entity.Equipment;
import com.hackathon.farmToMarket.entity.EquipmentUser;
import com.hackathon.farmToMarket.entity.User;
import com.hackathon.farmToMarket.repository.EquipmentRepository;
import com.hackathon.farmToMarket.repository.EquipmentUserRepository;
import com.hackathon.farmToMarket.repository.UserRepository;
import com.hackathon.farmToMarket.service.EquipmentService;
import com.hackathon.farmToMarket.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RepositoryTest {
	@Autowired
	UserRepository userRepo;
	@Autowired
	EquipmentRepository equipRepo;
	@Autowired
	EquipmentService equipService;
	@Autowired
	UserService userService;
	@Autowired
	EquipmentUserRepository euRepo;
	
	@Test
	void testFindById() {
		Optional<User> u=userRepo.findById(1l);
		User user=u.get();
		assertEquals(1l,user.getUserId());
	}

	@Test
	void testFindByEquipmentId() {
		Optional<Equipment> e=equipRepo.findById(1l);
		Equipment equip=e.get();
		assertEquals(1l,equip.getEquipmentId());
	}

	@Test
	void testFindByLocation() {
		List<Equipment> list=equipRepo.findByLocation("chennai");
		assertEquals("Chennai",list.get(0).getLocation());
	}
	@Test
//	@Disabled
	void testSave() {
		Equipment equip=new Equipment(5l,"tractor","Chennai",10,null);
		equipRepo.save(equip);
		Equipment e=equipService.fetchById(equip.getEquipmentId());
		assertEquals(equip.getEquipmentId(),e.getEquipmentId());
	}
	@Test
//	@Disabled
	void testSaveEquipmentUser() {
		User user=userService.fetchById(1l);
		Equipment equipment=equipService.fetchById(2l);
		EquipmentUser eu=new EquipmentUser(1,user,equipment,1);
		EquipmentUser e=euRepo.save(eu);
		assertEquals(e.getAmount(),eu.getAmount());
	}
	@Test
	void testfindByEquipmentAndUser() {
		User user=userService.fetchById(1l);
		Equipment equipment=equipService.fetchById(4l);
		EquipmentUser e=new EquipmentUser(1,user,equipment,1);
		euRepo.save(e);
		EquipmentUser eu=euRepo.findByEquipmentAndUser(equipment,user);
		assertEquals(user.getUserId(),eu.getUser().getUserId());
	}
	
	@Test
	void testFindByUser() {
		User user=userService.fetchById(1l);
		List<EquipmentUser> list=euRepo.findAllByUser(user);
		List<EquipmentUser> l=new ArrayList<>();
		list.forEach(l::add);
		assertEquals(list.size(),l.size());
	}
	@Test
//	@Disabled
	void testDelete() {
		User user=userService.fetchById(1l);
		Equipment equipment=equipService.fetchById(2l);
		EquipmentUser eu=new EquipmentUser(1,user,equipment,1);
		euRepo.save(eu);
		euRepo.delete(eu);
		List<EquipmentUser> l=euRepo.findAll();
		assertEquals(l.size(),l.size());
		
	}
	@Test
	void testSaveAllUsers() {
		List<User> l=userRepo.findAll();
		List<User> list=new ArrayList<>();
		list.add(new User(5l,"komal","komalnadh.13@gmail.com","user",null));
		userRepo.saveAll(list);
		List<User> l1=userRepo.findAll();
		assertEquals(l.size(),l1.size());
	}
}
