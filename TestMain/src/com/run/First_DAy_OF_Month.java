package com.run;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 获取当前时间的第一个天和当前天和最后一天
 * @author zhangwenbao
 *
 */
public class First_DAy_OF_Month {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String firstDay = sdf.format(cal.getTime());
		Date now = new Date();
		String nowDay = sdf.format(now);
		
		Calendar last = Calendar.getInstance();
		//last.set(Calendar.DAY_OF_MONTH, last.getMaximum(Calendar.DAY_OF_MONTH));
		last.set(Calendar.DAY_OF_MONTH, last.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDay = sdf.format(last.getTime());
		
		System.out.println("firstDay="+firstDay);
		System.out.println("nowDay="+nowDay);
		System.out.println("lastDay="+lastDay);
	}
}
