package com.ktdsuniversity.edu.fp.basic.stream.advanced.utils;

import java.util.Calendar;

public abstract class ArrayUtil {
	private ArrayUtil() {
	}

	// util 유틸리티
	public static String getValue(String[] array, int index) {
		if (index >= 0 && array.length > index) {
			String value = array[index];
			if (value != null) {
				value = value.replace("\"", "");
			}
			return value;
		}
		return null;
	}
}
