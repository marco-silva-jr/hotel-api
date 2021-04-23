package com.hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.exceptions.EntityNotFoundException;
import com.hotel.model.Hospede;
import com.hotel.repository.HospedeRepository;

@Service
public class HospedeService {
	
	@Autowired
	private HospedeRepository hospedeRepository; 

	public Hospede findById(Long id) {
		return hospedeRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Hóspede Não encontrado "+ id));		
	}
	
	public List<Hospede> findAll() {
		return hospedeRepository.findAll();		
	}
	
	public Hospede save(Hospede hospede) {
		if (hospede.getNome().isEmpty()) {
			throw new EntityNotFoundException("Preencha o campo nome!");		
		} else if (hospede.getDocumento().isEmpty()) {
			throw new EntityNotFoundException("Preencha o campo documento!");	
		} else {		
			return hospedeRepository.save(hospede);
		}
	}
		
	public String delete(Long id) {
		hospedeRepository.deleteById(id);
		return "Hóspede removido! "+id;
	}
}
