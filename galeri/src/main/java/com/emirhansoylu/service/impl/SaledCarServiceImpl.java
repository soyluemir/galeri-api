package com.emirhansoylu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import com.emirhansoylu.dto.CurrenyRatesResponse;
import com.emirhansoylu.dto.DtoCar;
import com.emirhansoylu.dto.DtoCustomer;
import com.emirhansoylu.dto.DtoGallerist;
import com.emirhansoylu.dto.DtoSaledCar;
import com.emirhansoylu.dto.DtoSaledCarIU;
import com.emirhansoylu.enums.CarStatusType;
import com.emirhansoylu.exception.BaseException;
import com.emirhansoylu.exception.ErrorMessage;
import com.emirhansoylu.exception.MessageType;
import com.emirhansoylu.model.Car;
import com.emirhansoylu.model.Customer;
import com.emirhansoylu.model.SaledCar;
import com.emirhansoylu.repository.CarRepository;
import com.emirhansoylu.repository.CustomerRepository;
import com.emirhansoylu.repository.GalleristRepository;
import com.emirhansoylu.repository.SaledCarRepository;
import com.emirhansoylu.service.ICurrencyRatesService;
import com.emirhansoylu.service.ISaledCarService;
import com.emirhansoylu.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService{
	
	  @Autowired
      private SaledCarRepository saledCarRepository;

    private final SecurityFilterChain filterChain;
	
		@Autowired
		private CustomerRepository customerRepository;
		
		@Autowired
		private GalleristRepository galleristRepository;
		
		@Autowired
		private CarRepository carRepository;
		
		@Autowired
		private ICurrencyRatesService currencyRatesService;
		
		
		
		
		private BigDecimal convertCustomerAmountToUSD(Customer customer) {
			
		CurrenyRatesResponse currenyRatesResponse =
		currencyRatesService.getCurrenyRates(DateUtils.getCurrentDate(new java.sql.Date(0)), DateUtils.getCurrentDate(new java.sql.Date(0))); //günün tarihi
		BigDecimal usd = new BigDecimal(	currenyRatesResponse.getItems().get(0).getUsd()); // usd değerini aldık güncel olarak
		
		BigDecimal customerUSDAmount =	customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP); //yukarıya yuvarla //noktadan sonra 2 karakter gözüksün
		
		return customerUSDAmount;
			
		
			
		}


    SaledCarServiceImpl(SecurityFilterChain filterChain) {
        this.filterChain = filterChain;
    }
    public boolean checkCarStatus(Long carId) {
    Optional<Car> optCar =	carRepository.findById(carId);
    if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
    	return false;
    	

	}
    return true;
    
    }
    public BigDecimal remaningCustomerAmount(Customer customer, Car car) { //kalan müşteri parası
    BigDecimal customerUSDAmount =	convertCustomerAmountToUSD(customer);
    BigDecimal remaningCustomerUSDAmount =  customerUSDAmount.subtract(car.getPrice());
 
    CurrenyRatesResponse currenyRatesResponse =  
    currencyRatesService.getCurrenyRates(DateUtils.getCurrentDate(new java.sql.Date(0)), DateUtils.getCurrentDate(new java.sql.Date(0)));
    BigDecimal usd = new BigDecimal(currenyRatesResponse.getItems().get(0).getUsd());
   return remaningCustomerUSDAmount.multiply(usd);  
    }
	
	      public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) { // parası yetiyor mu kontrolü
	  Optional<Customer> optCustomer = 	  customerRepository.findById(dtoSaledCarIU.getCustomerId());
	  if (optCustomer.isEmpty()) {
		  throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		
	}
	  Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
	  if (optCar.isEmpty()) {
		  throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		
	}
	  // 37000 cüzdan  35000 araba  =0 1 -1 eşitse 0  büyükse örnekteki gibi 1 döner cüzdan küçükse 0 döner
	BigDecimal customerUSDAmount =  convertCustomerAmountToUSD(optCustomer.get());
	if (customerUSDAmount.compareTo(optCar.get().getPrice())==0 ||customerUSDAmount.compareTo(optCar.get().getPrice())>0 ) { 
		return true;		
	}	  	 	  
	  return false;	    	  	    	  
	      }
			
	      
	      public SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
	    	  SaledCar saledCar = new SaledCar();
	    	  saledCar.setCreateTime(new Date());	
	    	 // saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()));
	    	//  saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()));
	    	//  saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()));
	    	  
	    	  return saledCar;	    	  
	      }
	      
	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		if (checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
		}
		if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
			
		}
	SaledCar savedSaledCar =	saledCarRepository.save(createSaledCar(dtoSaledCarIU));
	Car car =	savedSaledCar.getCar();
	car.setCarStatusType(CarStatusType.SALED);
	carRepository.save(car);	
	Customer customer = savedSaledCar.getCustomer();
	customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
	customerRepository.save(customer);
		return toDTO(savedSaledCar);
	}	
	public DtoSaledCar toDTO(SaledCar saledCar) {
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);		
		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		dtoSaledCar.setCar(dtoCar);
		return dtoSaledCar;
		
		
	}

}
