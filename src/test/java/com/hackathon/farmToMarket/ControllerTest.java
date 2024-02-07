package com.hackathon.farmToMarket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.farmToMarket.entity.Equipment;
import com.hackathon.farmToMarket.entity.EquipmentUser;
import com.hackathon.farmToMarket.entity.User;
import com.hackathon.farmToMarket.repository.EquipmentRepository;
import com.hackathon.farmToMarket.repository.EquipmentUserRepository;
import com.hackathon.farmToMarket.repository.UserRepository;
import com.hackathon.farmToMarket.service.EquipmentService;
import com.hackathon.farmToMarket.service.EquipmentUserService;
import com.hackathon.farmToMarket.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest
 class ControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	EquipmentRepository equipRepo;
	@MockBean
	UserRepository userRepo;
	@MockBean
	EquipmentUserRepository euRepo;
	@MockBean
	EquipmentService equipmentService;
	@MockBean
	EquipmentUserService euService;
	@MockBean 
	UserService userService;
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	

	@Test
	void testGetEquipmentByLocation() throws Exception {
		Equipment equip=new Equipment(1l,"tractor","Chennai",10,null);
		Equipment equip1=new Equipment(2l,"tractor","Chennai",10,null);
		List<Equipment> list=new ArrayList<>();
		list.add(equip);
		list.add(equip1);
		Mockito.when(equipmentService.getAllByLocation("Chennai")).thenReturn(list);
		MvcResult result=mockMvc.perform(get("/farmToMarket/getEquipment/Chennai")).andReturn();
		String res=result.getResponse().getContentAsString();
		List<Object> l=Arrays.asList(mapper.readValue(res, Equipment[].class));
		assertEquals(list.size(),l.size());
	}
	@Test
	void testbookEquipment() throws Exception {
		Equipment equip=new Equipment(1l,"tractor","Chennai",10,null);
		User user=new User(1l,"komalnadh","komalnadh.13@gmail.com","user",null);
		EquipmentUser eu=new EquipmentUser(1,user,equip,1);
		Mockito.when(equipmentService.fetchById(1l)).thenReturn(equip);
		Mockito.when(userService.fetchById(1l)).thenReturn(user);
		Mockito.when(euService.insertData(user,equip,1)).thenReturn(eu);
		equip.setQuantity(equip.getQuantity()-1);
		Mockito.when(equipmentService.updateEquipment(1l, 1)).thenReturn(equip);
		MvcResult result=mockMvc.perform(post("/farmToMarket/bookEquioment/user/1/Equip/1/quantity/1")).andReturn();
		String res=result.getResponse().getContentAsString();
		EquipmentUser eu1=mapper.readValue(res, EquipmentUser.class);
		assertEquals(eu.getUser(),eu1.getUser());
	}
	@Test
	void testBookEquipmentIfQuantityZero() throws Exception {
		Equipment equip=new Equipment(1l,"tractor","Chennai",0,null);
		Mockito.when(equipmentService.fetchById(1l)).thenReturn(equip);
		MvcResult result=mockMvc.perform(post("/farmToMarket/bookEquioment/user/1/Equip/1/quantity/1"))
								.andExpect(status().isNoContent())
								.andReturn();
		MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

	}
	@Test
	void testGetByUserId() throws Exception {
		Equipment equip=new Equipment(1l,"tractor","Chennai",10,null);
		User user=new User(1l,"komalnadh","komalnadh.13@gmail.com","user",null);
		EquipmentUser eu=new EquipmentUser(1,user,equip,1);
		List<EquipmentUser> list=new ArrayList<>();
		list.add(eu);
		Mockito.when(euService.getAllByUserId(1l)).thenReturn(list);
		MvcResult result=mockMvc.perform(get("/farmToMarket/getEquipById/1")).andReturn();
		String res=result.getResponse().getContentAsString();
		List<Object> l=Arrays.asList(mapper.readValue(res, EquipmentUser[].class));
		assertEquals(list.size(),l.size());
	}
	@Test
	void testReturnEquipment() throws Exception {
		Equipment equip=new Equipment(1l,"tractor","Chennai",10,null);
		Mockito.when(equipRepo.save(equip)).thenReturn(equip);
		Mockito.when(equipmentService.returnEquipment(1l, 1)).thenReturn(equip);
		MvcResult result=mockMvc.perform(put("/farmToMarket/returnEquipment/user/1/equipId/1/quantity/1")).andReturn();
		String res=result.getResponse().getContentAsString();
		Equipment e=mapper.readValue(res, Equipment.class);
		assertEquals(e.getQuantity(),equip.getQuantity());
	}
}
