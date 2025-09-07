package com.emirhansoylu.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtils {
	
	public static String getCurrentDate(Date date) { //tarihi string olarak dönmek //static erişebiliriz
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	return	simpleDateFormat.format(date);
	}

}
