package com.pigplace.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	public static String getCurrentTime(String format) {
		// 현재 날짜 구하기        
		LocalDateTime now = LocalDateTime.now();         
		// 포맷 정의        
		System.out.println(now);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);         
		// 포맷 적용        
		String formatedNow = now.format(formatter);
		
		return formatedNow;
		
	}
}
