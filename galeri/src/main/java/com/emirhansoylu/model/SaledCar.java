package com.emirhansoylu.model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "saled_car"
,uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id","car_id","customer_id"},name = "uq_gallerist_car_customer")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaledCar extends BaseEntity {
	
	@ManyToOne
	private Gallerist gallerist; // 1 numaralı galerinin 1 numaralı arabasını 1 numaralı müşteri alabilir
								// 1 numaralı galerinin 2 numaralı arabasını 1 numaralı müşteri alabilir
								// 1 numaralı galerinin 2 numaralı arabasını 1 numaralı müşteri alamaz tekrar bu yüzden uniqConstraints tanımlayacağız yine
	@ManyToOne
	private Car car; 
	// şu galerinin şu arabasını şu müşteri almıştır
	@ManyToOne
	private Customer customer;

	}



