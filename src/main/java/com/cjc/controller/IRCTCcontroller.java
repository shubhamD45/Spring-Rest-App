package com.cjc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.model.Register;
import com.cjc.model.RegisterList;
import com.cjc.repository.IRCTCrepository;

@RestController
@RequestMapping("/irctcproducer")
public class IRCTCcontroller {

	@Autowired
	private IRCTCrepository repo;

	
	@GetMapping("/")
	public ResponseEntity<String> check() {
		String msg = "IRCTC APP WORKING...!!";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@PostMapping(value = "/irctc", consumes = { "application/json", "application/xml" })
	public ResponseEntity<String> saveData(@RequestBody Register reg) {
		repo.save(reg);
		return new ResponseEntity<>("Data Saved Successfully", HttpStatus.OK);
	}

	@GetMapping(value = "/irctc", produces = { "application/json", "application/xml" })
	public ResponseEntity<RegisterList> getAllData() {
		List<Register> list = repo.findAll();
		RegisterList register = new RegisterList();
		register.setRegister(list);
		return new ResponseEntity(register, HttpStatus.OK);
	}

	@PutMapping(value = "/irctc", consumes = { "application/json", "application/xml" })
	public ResponseEntity<String> updateData(@RequestBody Register reg) {

		repo.save(reg);
		return new ResponseEntity<String>("Data updated Successfully", HttpStatus.OK);
	}

	@GetMapping(value = "/irctc/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Register> getSingleData(@PathVariable int id) {

		Register register = repo.findById(id).get();

		Link withRel = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(IRCTCcontroller.class).getAllData())
				.withRel("All Data");
		register.add(withRel);

		return new ResponseEntity<Register>(register, HttpStatus.OK);
	}

	
}
