package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Hospede;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long>{	
	
}
