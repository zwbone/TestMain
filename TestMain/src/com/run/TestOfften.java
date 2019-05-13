package com.run;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Create with IntelliJ IDEA
 * Create By zhangwenbao
 * Date:{$Date}
 * Time:{TIME}
 */
public class TestOfften {
    public static void main(String[] args) throws Exception {
        try {
            String chinessLange= "中午是一个企业";
            System.out.println("chinessLange=="+chinessLange);
            String tempEcoder = java.net.URLEncoder.encode(chinessLange,"UTF-8");
            System.out.println("tempEcoder=="+tempEcoder);
            String tempDcoder = URLDecoder.decode(tempEcoder,"UTF-8");
            System.out.println("tempDcoder=="+tempDcoder);
            System.out.println("-----------------------");
            String chinessLange2= "中午是一个企业";
            System.out.println("chinessLange2=="+chinessLange2);
            String tempEcoder2 = java.net.URLEncoder.encode(java.net.URLEncoder.encode(chinessLange2,"UTF-8"),"UTF-8");
            System.out.println("tempEcoder2=="+tempEcoder2);
            String tempDcoder2 = java.net.URLDecoder.decode(tempEcoder2,"UTF-8");
            System.out.println("tempDcoder2=="+tempDcoder2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            /*chinessLange==中午是一个企业
            tempEcoder==%E4%B8%AD%E5%8D%88%E6%98%AF%E4%B8%80%E4%B8%AA%E4%BC%81%E4%B8%9A
            tempDcoder==中午是一个企业
                    -----------------------
                    chinessLange2==中午是一个企业
            tempEcoder2==%25E4%25B8%25AD%25E5%258D%2588%25E6%2598%25AF%25E4%25B8%2580%25E4%25B8%25AA%25E4%25BC%2581%25E4%25B8%259A
            tempDcoder2==%E4%B8%AD%E5%8D%88%E6%98%AF%E4%B8%80%E4%B8%AA%E4%BC%81%E4%B8%9A*/
        }
    }
}
