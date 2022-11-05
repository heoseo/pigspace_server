package com.pigspace.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.pigspace.common.support.LogObject;

public class DateUtil extends LogObject{
	private static final long serialVersionUID = -4189947457981214874L;

	public static String getCurrentTime(String format) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		String formatedNow = now.format(formatter);

		return formatedNow;

	}

	public static String getCurrentTimePlusDays(String format, int days) {
		LocalDateTime now = LocalDateTime.now();
		if(days < 0)
			now = now.minusDays(days);
		else
			now = now.plusDays(days);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		String formatedNow = now.format(formatter);

		return formatedNow;

	}
}
