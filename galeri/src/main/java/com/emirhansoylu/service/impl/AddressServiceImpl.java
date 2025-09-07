package com.emirhansoylu.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emirhansoylu.dto.DtoAddress;
import com.emirhansoylu.dto.DtoAddressIU;
import com.emirhansoylu.model.Address;
import com.emirhansoylu.repository.AddressRepository;
import com.emirhansoylu.service.IAddressService;

@Service 
public class AddressServiceImpl  implements IAddressService{
	
	@Autowired
	private AddressRepository addressRepository;
	
	private Address createAddress(DtoAddressIU dtoAddressIU) { //addres oluşturuyorum
		Address address = new Address();
		address.setCreateTime(new Date()); //id zaten otomatik. createtime biz verdik
		
		BeanUtils.copyProperties(dtoAddressIU, address); //dtodakileri addrese kopyaladık
		return address;
	}
	
	@Override
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		DtoAddress dtoAddress = new DtoAddress();
		
	Address savedAddress =	addressRepository.save(createAddress(dtoAddressIU)); //veritabanına kaydedilmiş adress
	BeanUtils.copyProperties(savedAddress, dtoAddress);
		
		return dtoAddress;
	}

}
