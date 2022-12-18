package com.cjc.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@XmlRootElement

public class RegisterList {

	
	private List<Register> register;
}
