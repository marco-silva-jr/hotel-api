	package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.model.Checkin;
import com.hotel.model.Hospede;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long>{
	
	@Query("SELECT h FROM Hospede h WHERE LOWER(h.nome) LIKE LOWER(CONCAT('%',:keyword,'%')) OR LOWER(h.documento) LIKE LOWER(CONCAT('%',:keyword,'%')) OR LOWER(h.telefone) LIKE LOWER(CONCAT('%',:keyword,'%'))")
	List<Hospede> findByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT c FROM Checkin c WHERE c.dataSaida > CURRENT_TIMESTAMP")
	List<Checkin> findActive();

	@Query("SELECT c FROM Checkin c WHERE c.dataSaida < CURRENT_TIMESTAMP")
	List<Checkin> findInactive();
}
