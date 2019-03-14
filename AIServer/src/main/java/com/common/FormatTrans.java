package com.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式转换类
 * */
public class FormatTrans {
	/**
	 * 生成当前时间 日期格式为yyyy-MM-dd HH:mm:ss
	 * */
	public static String dateFormatTrans() {
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String date = formatter.format(new Date());
		return date;
	}
	/**
	 * 生成当前时间 日期格式为yyyyMMddHHmmss
	 * */
	public static String dateFormatTrans2() {
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	     String date = formatter.format(new Date());
		return date;
	}
	
	/**
	 * 生成当前时间 日期格式为yyyyMM
	 * */
	public static String dateFormatTrans3() {
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
	     String date = formatter.format(new Date());
		return date;
	}
	
	/**
	 * 生成当前时间 日期格式为yyyyMMddHHmmss
	 * */
	public static String dateFormatTrans4() {
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	     String date = formatter.format(new Date());
		return date;
	}	
	
	/**
	 * 把金额转换成保留2为小数
	 * */
	public static String MoneyTrans(String m1) {
		try{
		m1 = m1.trim();
		BigDecimal b_amount = new BigDecimal(m1);
		b_amount = b_amount.setScale(2);
		return b_amount.toString();
		}catch(Exception e){
			return "0.00"; 
		}
	}
	/**
	 * 日期转化成字符串
	 */
	
	public static String getDateStr(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	
	public static String frontCompWithZore(int sourceDate,int formatLength)  
	 {  
	  /* 
	   * 0 指前面补充零 
	   * formatLength 字符总长度为 formatLength 
	   * d 代表为正数。 
	   */  
	  String newString = String.format("%0"+formatLength+"d", sourceDate);  
	  return  newString;  
	 } 
}
