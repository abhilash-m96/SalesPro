package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static boolean isValidFormatDate(String date) {
		boolean value = false;

		try {
			new SimpleDateFormat("dd/MM/yyyy").parse(date);
			value = true;
		} catch (Exception e) {
			value = false;
		}

		return value;
	}

	public static String obtainCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(new Date());
	}

	public static boolean checkIfDateIsLessThanCurrentDate(String date) {

		boolean value = true;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date date1 = sdf.parse(date);
			Date date2 = sdf.parse(obtainCurrentDate());

			if (date1.compareTo(date2) > 0) {
				return false;
			}

			value = true;
		} catch (Exception e) {
			value = true;
		}

		return value;

	}

	public static void main(String s[]) {
		
		System.out.println(obtainCurrentDate());

	}

}
