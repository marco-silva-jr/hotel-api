package com.hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.exceptions.EntityNotFoundException;
import com.hotel.model.Checkin;
import com.hotel.model.Hospede;
import com.hotel.repository.CheckinRepository;

@Service
public class CheckinService {
	
	@Autowired
	private CheckinRepository checkinRepository; 

	public List<Hospede> findkey(String keyword) {
		return checkinRepository.findByKeyword(keyword);
	}
	
	public Checkin findById(Long id) {
		return checkinRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Checkin Não encontrado "+ id));		
	}
	
	public List<Checkin> findAll() {
		return checkinRepository.findAll();		
	}
	
	public List<Checkin> findActive() {
		return checkinRepository.findActive();		
	}
	
	public List<Checkin> findInactive() {
		return checkinRepository.findInactive();		
	}
	
	public Checkin save(Checkin checkin) {	
		return checkinRepository.save(checkin);		
	}
		
}
