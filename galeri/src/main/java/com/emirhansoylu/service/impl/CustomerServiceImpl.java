package com.emirhansoylu.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheOperationInvoker.ThrowableWrapper;
import org.springframework.stereotype.Service;

import com.emirhansoylu.dto.DtoAccount;
import com.emirhansoylu.dto.DtoAddress;
import com.emirhansoylu.dto.DtoCustomer;
import com.emirhansoylu.dto.DtoCustomerIU;
import com.emirhansoylu.exception.BaseException;
import com.emirhansoylu.exception.ErrorMessage;
import com.emirhansoylu.exception.MessageType;
import com.emirhansoylu.model.Account;
import com.emirhansoylu.model.Address;
import com.emirhansoylu.model.Customer;
import com.emirhansoylu.repository.AccountRepository;
import com.emirhansoylu.repository.AddressRepository;
import com.emirhansoylu.repository.CustomerRepository;
import com.emirhansoylu.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	 
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
	Optional<Address> optAddress =	 addressRepository.findById(dtoCustomerIU.getAddressId()); //böyle bir address var mı
	if (optAddress.isEmpty()) {
		throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));	
	}	
	Optional<Account> optAccount =	accountRepository.findById(dtoCustomerIU.getAccountId());
	if (optAccount.isEmpty()) {	
		throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));	
	}		
		Customer customer = new Customer();
		customer.setCreateTime(new Date());		
		BeanUtils.copyProperties(dtoCustomerIU, customer);		
		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());				
		return customer;
	}

	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		DtoCustomer dtoCustomer = new  DtoCustomer();
		DtoAddress dtoAddress = new DtoAddress();
		DtoAccount dtoAccount = new DtoAccount();
	Customer savedCustomer =	customerRepository.save(createCustomer(dtoCustomerIU));	
	BeanUtils.copyProperties(savedCustomer, dtoCustomer); //iki değer boşta dtoaddress ve dto account onu da elle kopyalıyoruz
	BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
	BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);	
	dtoCustomer.setAddress(dtoAddress);
	dtoCustomer.setAccount(dtoAccount);	
		return dtoCustomer;
	}

}
