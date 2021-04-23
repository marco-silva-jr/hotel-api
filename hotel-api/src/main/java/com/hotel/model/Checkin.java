package com.hotel.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
@Entity
@Table(name = "Checkins")
public class Checkin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Hospede hospede;
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataEntrada;
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataSaida;
	private boolean adicionalVeiculo;
	@Transient
	private BigDecimal valorGasto;
	
			
	public Checkin(Long id, Hospede hospede, DateTime dataEntrada, DateTime dataSaida, boolean adicionalVeiculo) {
		super();
		this.id = id;
		this.hospede = hospede;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.adicionalVeiculo = adicionalVeiculo;
	}


	public Checkin() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Hospede getHospede() {
		return hospede;
	}


	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public DateTime getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(DateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public DateTime getDataSaida() {
		return dataSaida;
	}


	public void setDataSaida(DateTime dataSaida) {
		this.dataSaida = dataSaida;
	}


	public boolean isAdicionalVeiculo() {
		return adicionalVeiculo;
	}


	public void setAdicionalVeiculo(boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}


	public BigDecimal getValorGasto() {
		return valorGasto;
	}


	public void setValorGasto(BigDecimal valorGasto) {
		this.valorGasto = valorGasto;
	}

	
}
