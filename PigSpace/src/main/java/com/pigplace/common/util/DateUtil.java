package com.pigplace.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	public static String getCurrentTime(String format) {
		// ���� ��¥ ���ϱ�        
		LocalDateTime now = LocalDateTime.now();         
		// ���� ����        
		System.out.println(now);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);         
		// ���� ����        
		String formatedNow = now.format(formatter);
		
		return formatedNow;
		
	}
}
