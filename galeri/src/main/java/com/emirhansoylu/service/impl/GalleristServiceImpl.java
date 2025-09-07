package com.emirhansoylu.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import com.emirhansoylu.dto.DtoAddress;
import com.emirhansoylu.dto.DtoGallerist;
import com.emirhansoylu.dto.DtoGalleristIU;
import com.emirhansoylu.exception.BaseException;
import com.emirhansoylu.exception.ErrorMessage;
import com.emirhansoylu.exception.MessageType;
import com.emirhansoylu.model.Address;
import com.emirhansoylu.model.Gallerist;
import com.emirhansoylu.repository.AddressRepository;
import com.emirhansoylu.repository.GalleristRepository;
import com.emirhansoylu.service.IGalleristService;

@Service
public class GalleristServiceImpl  implements IGalleristService{

    private final SecurityFilterChain filterChain;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private AddressRepository addressRepository;


    GalleristServiceImpl(SecurityFilterChain filterChain) {
        this.filterChain = filterChain;
    }		
	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
	Optional<Address> optAddress =	addressRepository.findById(dtoGalleristIU.getAddressId());
	if (optAddress.isEmpty()) {
		throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
		
	}			
		Gallerist gallerist = new Gallerist();
		gallerist.setCreateTime(new Date());	
		BeanUtils.copyProperties(dtoGalleristIU, gallerist); //ama idyi kopyalayamaz burası
		gallerist.setAddress(optAddress.get());					
		return gallerist;
	}

	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoAddress dtoAddress = new DtoAddress();
	 Gallerist savedGallerist =	galleristRepository.save(createGallerist(dtoGalleristIU));
	 BeanUtils.copyProperties(savedGallerist, dtoGallerist); //addresi kopyalayamaz
	 BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress); // veritabanındaki galleriste adress objesi var onu dtoaddrese kopyala
	 dtoGallerist.setAddress(dtoAddress);
		return dtoGallerist;
	}

}
