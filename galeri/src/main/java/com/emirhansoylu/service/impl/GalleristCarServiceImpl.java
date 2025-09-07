package com.emirhansoylu.service.impl;


import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.emirhansoylu.dto.DtoAddress;
import com.emirhansoylu.dto.DtoCar;
import com.emirhansoylu.dto.DtoGallerist;
import com.emirhansoylu.dto.DtoGalleristCar;
import com.emirhansoylu.dto.DtoGalleristCarIU;
import com.emirhansoylu.exception.BaseException;
import com.emirhansoylu.exception.ErrorMessage;
import com.emirhansoylu.model.Car;
import com.emirhansoylu.model.Gallerist;
import com.emirhansoylu.model.GalleristCar;
import com.emirhansoylu.repository.CarRepository;
import com.emirhansoylu.repository.GalleristCarRepository;
import com.emirhansoylu.repository.GalleristRepository;
import com.emirhansoylu.service.IGalleristCarService;

public class GalleristCarServiceImpl implements IGalleristCarService {
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
    private GalleristCarRepository galleristCarRepository;
	
	@Autowired
	 private CarRepository carRepository;
	
	
	
	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		
	Optional<Gallerist>	 optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
	if (optGallerist.isEmpty()) {
		throw new BaseException(new ErrorMessage(com.emirhansoylu.exception.MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
	}	
		 
	Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
	if (optCar.isEmpty()) { 
		throw new BaseException(new ErrorMessage(com.emirhansoylu.exception.MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
		
	}	
	GalleristCar galleristCar = new GalleristCar();
	galleristCar.setCreateTime(new Date());
	galleristCar.setGallerist(optGallerist.get());
	galleristCar.setCar(optCar.get());
	
	return galleristCar;
		
	}
		
	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();	
		
		DtoAddress dtoAddress = new DtoAddress();
		
	    GalleristCar savedGalleristCar =	galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
	    
	    BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
	    BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist); //veritabanındakileri dtolularak opyala
	    BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
	    
	  
	    BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
	    
	    dtoGallerist.setAddress(dtoAddress);
	    
	    dtoGalleristCar.setGallerist(dtoGallerist); //dolu olan dtogalleristi verdim
	    dtoGalleristCar.setCar(dtoCar);
		return dtoGalleristCar;
	}

}
