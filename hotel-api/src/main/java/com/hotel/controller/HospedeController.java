package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.Hospede;
import com.hotel.services.HospedeService;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {
	
	@Autowired
	private HospedeService hospedeService;
	
	@GetMapping
	public ResponseEntity<List<Hospede>> findAll() {
		List<Hospede> list = hospedeService.findAll();		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Hospede> findById(@PathVariable Long id) {
		Hospede obj = hospedeService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Hospede> Adicionar(@RequestBody Hospede hospede) {
		Hospede obj = hospedeService.save(hospede);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value= "/{id}")
	public ResponseEntity<Hospede> update(@RequestBody Hospede hospede, @PathVariable Long id) {
		Hospede obj = hospedeService.findById(id);
		hospedeService.save(hospede);
		return ResponseEntity.ok().body(obj);
	}

}
