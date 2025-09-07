package com.emirhansoylu.exception;



public class BaseException  extends RuntimeException{
	
	//dışarıdan baseexceptiondan nesne türetildiğinde erormessage nesnesi gelecek. bu nesnenin offstatic ve meessage typesisnin doldurulması gerekiyor.
	public BaseException( ErrorMessage errorMessage) { //constructor errormessage objesi alacak
		 //almış olduğu değeri de üst sınıfın constructoruna super diyerek veriyoruz // super burada runtimexceptionu temsil ediyor
		super(errorMessage.prepareErrorMessage()); //setlenmiş olan 2 değeri birleştirip bana  1 tane mesaj dönen fonksiyonu supere geçiyoruz.
		
	}

}
