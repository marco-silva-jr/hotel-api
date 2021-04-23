package com.hotel.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
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

import com.hotel.model.Checkin;
import com.hotel.model.Hospede;
import com.hotel.services.CheckinService;

@RestController
@RequestMapping("/checkin")
public class CheckinController {
	
	@Autowired
	private CheckinService checkinService;
	
	@GetMapping
	public ResponseEntity<List<Checkin>> findAll() {
		List<Checkin> list = checkinService.findAll();		
		return ResponseEntity.ok().body(list);
	}
		
	@GetMapping(value= "hospedes/{keyword}")
	public ResponseEntity<List<Hospede>> pesquisarHospedes(@PathVariable String keyword) {
		List<Hospede> obj = checkinService.findkey(keyword);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value= "pesquisa/{ativo}")
	public ResponseEntity<List<Checkin>> buscarHospedes(@PathVariable boolean ativo) {
		List<Checkin> listCheckin = new ArrayList<Checkin>();
		List<Checkin> list = new ArrayList<Checkin>();
		DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
		if (ativo) {			
			list = checkinService.findActive();
		} else {
			list = checkinService.findInactive();
		}	
		
		for (Checkin chk : list) {
			Checkin chkNovo = new Checkin();
			BigDecimal valorTotal = BigDecimal.ZERO;
			if (chk.getDataSaida() ==null) {
				chk.setDataSaida(DateTime.now());
			}
			
			DateTime datEntAux = chk.getDataEntrada();
			
			while (datEntAux.isBefore(chk.getDataSaida()) || dateTimeComparator.compare(datEntAux, chk.getDataSaida()) == 0) {
				LocalDate dataEntrada = new LocalDate(datEntAux);
				int day = dataEntrada.dayOfWeek().get(); 
				BigDecimal valorDiaria = new BigDecimal(120.00);
				BigDecimal valorAdicional = BigDecimal.ZERO;
				if (chk.isAdicionalVeiculo()) {
					valorAdicional = new BigDecimal(20.00);
				}				
				
				if (DateTimeConstants.SUNDAY == day || DateTimeConstants.SATURDAY == day) {
					valorDiaria = new BigDecimal(150.00);
					if (chk.isAdicionalVeiculo()) {
						valorAdicional = new BigDecimal(20.00);
					}
				}
				if (dateTimeComparator.compare(datEntAux, chk.getDataSaida()) == 0) {
					if ((chk.getDataSaida().getHourOfDay() == 16 && chk.getDataSaida().getMinuteOfHour() > 30) || chk.getDataSaida().getHourOfDay() >16) {
						valorDiaria = valorDiaria.multiply(new BigDecimal(2)); 						
					}
				}
				
				valorTotal = valorTotal.add(valorDiaria.add(valorAdicional));
	            datEntAux = datEntAux.plusDays(1);
	        }		
			
			chkNovo.setValorGasto(valorTotal.setScale(2));
			chkNovo.setId(chk.getId());
			chkNovo.setDataEntrada(chk.getDataSaida());
			chkNovo.setDataSaida(chk.getDataSaida());
			chkNovo.setHospede(chk.getHospede());
			chkNovo.setAdicionalVeiculo(chk.isAdicionalVeiculo());
			listCheckin.add(chkNovo);
		} 
		
		return ResponseEntity.ok().body(listCheckin);
	}
	
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Checkin> buscarPorID(@PathVariable Long id) {
		Checkin obj = checkinService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Checkin> adicionar(@RequestBody Checkin checkin) {
		Checkin obj = checkinService.save(checkin);		
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value= "/{id}")
	public ResponseEntity<Checkin> atualizar(@RequestBody Checkin checkin, @PathVariable Long id) {
		Checkin obj = checkinService.findById(id);
		checkinService.save(checkin);
		return ResponseEntity.ok().body(obj);
	}
	

}
