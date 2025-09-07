package com.emirhansoylu.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError<E> {
	
	private Integer status; //geriye dönerken statusum bu 
	
	private Exception<E> exception; //apierrora gelen e tipini exceptiona geçeceğiz  o da messageye geçecek mesajım o tipte olacak

}
