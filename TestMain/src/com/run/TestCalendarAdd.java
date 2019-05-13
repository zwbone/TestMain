package com.run;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期加减
 * 根据当前时间 计算2个自然月后的日期
 */
public class TestCalendarAdd {

	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
//		cal.add(Calendar.DATE, -1);
		cal.add(Calendar.HOUR_OF_DAY, -24);
		System.out.println(cal.getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm00");
		sdf.format(cal.getTime());
		System.out.println(sdf.format(cal.getTime()));
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Calendar c = Calendar.getInstance();
		System.out.println(f.format(c.getTime()));
		//根据当前时间 计算2个自然月后的日期
		c.add(Calendar.MONTH, 2);
		System.out.println(f.format(c.getTime()));
		
		
		Date now = new Date();
		Calendar calw = Calendar.getInstance();
		calw.getTime();
		SimpleDateFormat sdfw = new SimpleDateFormat("yyyy-MM-dd");
		sdfw.format(now);
		System.out.println("now="+sdfw.format(now));
		System.out.println("cal.getTime()="+sdfw.format(calw.getTime()));
	}
}
