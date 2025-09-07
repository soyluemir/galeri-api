package com.emirhansoylu.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emirhansoylu.dto.DtoCar;
import com.emirhansoylu.dto.DtoCarIU;
import com.emirhansoylu.model.Car;
import com.emirhansoylu.repository.CarRepository;
import com.emirhansoylu.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {
	
	@Autowired
	private CarRepository carRepository;
	
	private Car createCar(DtoCarIU dtoCarIU) {
		
		Car car = new Car();
		car.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoCarIU, car); //postmanden geleni car'a kopyala carı geri dön
		
		return car;
		
	}

	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
	DtoCar dtoCar = new DtoCar();
		
 	Car savedCar = carRepository.save(createCar(dtoCarIU)); // car'ı oluşturdu bana o car'ı döndü saveledim ve yakaladım ben de
 	
 	BeanUtils.copyProperties(savedCar, dtoCar);
		return dtoCar;
	}

}
