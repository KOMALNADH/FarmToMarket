package com.hackathon.farmToMarket.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="equipment_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Data
public class EquipmentUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="equipment_id",nullable=false)
	private Equipment equipment;
	
	private Integer amount;


	
}
