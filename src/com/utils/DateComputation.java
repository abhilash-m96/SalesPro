package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateComputation {

	public static long getDifferenceDays(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public static void main(String s[]) {

		/*Date date = new Date("2017-10-15");

		Date date1 = new Date();

		long count = DateComputation.getDifferenceDays(date, date1);
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		System.out.println("COUNT =");
		System.out.println(count);*/
		
		 SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		    System.out.println(f.format(new Date()));

	}

}
