package com.emirhansoylu.model;

import java.math.BigDecimal;

import com.emirhansoylu.enums.CarStatusType;
import com.emirhansoylu.enums.CurrencyType;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gallerist_car"                           //1 	idleri		//3
,uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id","car_id"},name = "uq_gallerist_car")})
@Getter                          // tekrar tekrar aynı kayıt yazılmasın diye ekledik uniqConstraints ile
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GalleristCar extends BaseEntity {
	
	@ManyToOne   //1 numaranın 5 6 7 numaralı arabaları var
	private Gallerist gallerist; 
	
	@ManyToOne
	private Car car;

}
