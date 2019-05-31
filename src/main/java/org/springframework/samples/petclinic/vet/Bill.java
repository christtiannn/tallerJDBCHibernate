package org.springframework.samples.petclinic.vet;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.visit.Visit;

@Entity
@Table(name = "facturas")
public class Bill extends BaseEntity{
	
	@ManyToOne 
	@JoinColumn(name = "owner")
	private Owner owner;
	
	@Digits(integer=10, fraction=0)
	private long idNumber;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy="bill", cascade = CascadeType.ALL)
	private Visit visit;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date paymentDate;
	
	@Digits(integer=5,fraction=2)
	@DecimalMin("0.0")
	private double money;

	public Bill() {}
}

