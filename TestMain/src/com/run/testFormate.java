package com.run;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
/**
 * 生成指定日期的DATE对象
 * @author zhangwenbao
 *
 */
public class testFormate {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		String birthday = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String birthdaytemp = "20180425".replace("-", "").trim();
		if(StringUtils.isNotBlank(birthdaytemp)){
			try {
				birthday = sdf2.format(sdf.parse(birthdaytemp));
				
			} catch (Exception e) {
				birthday = null;
			}
		}
		System.out.println("birthday====="+birthday);
	}

}
