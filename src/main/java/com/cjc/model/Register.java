package com.cjc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Register extends RepresentationModel<Register>{

	@Id
	private int rid;
	private String name;
	private String address;
	private long mobileno;
	
}
