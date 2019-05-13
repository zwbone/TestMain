package com.run;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 根据当前时间 计算2个自然月后的日期
 *
 */
public class ZiRanYueCeShi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Calendar c = Calendar.getInstance();
		System.out.println(sf.format(c.getTime()));
		c.add(Calendar.MONTH, 10);
		System.out.println(sf.format(c.getTime()));
		
		
		/*Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c4 = Calendar.getInstance();
		c1.add(Calendar.DATE, -1);
		c2.add(Calendar.DATE, -1);
		c4.add(Calendar.DATE, -1);
		
		c2.add(Calendar.MONTH, 2);
		c4.add(Calendar.MONTH, 4);
		String currentDate = sf.format(c1.getTime());
		String dateAfter2Mon = sf.format(c2.getTime());
		String dateAfter4Mon = sf.format(c4.getTime());
		System.out.println(currentDate);
		System.out.println(dateAfter2Mon);
		System.out.println(dateAfter4Mon);*/
	}

}
