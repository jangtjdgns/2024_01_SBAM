package com.KoreaIT.demo.util;

public class Util {
	
	// null, 공백 검사
	public static boolean empty(String str) {
		
		if (str == null) {
			return true;
		}
		
		return str.trim().length() == 0;
	}
	
	// 가변인자활용
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}
}
