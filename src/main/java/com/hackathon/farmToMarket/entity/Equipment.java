package com.hackathon.farmToMarket.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="equipment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Equipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long equipmentId;
	private String equipmentName;
	private String location;
	private Integer quantity;
	
//	@ManyToMany(cascade=CascadeType.ALL)
//	private List<User> users;
	@JsonIgnore
	@OneToMany(mappedBy="equipment",cascade=CascadeType.ALL)
	private Set<EquipmentUser> equipmentUser;

	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", location=" + location
				+ ", quantity=" + quantity + ", equipmentUser=" + equipmentUser + "]";
	}


	
	
	
	
}
