package com.common;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zishuo.wang 
 * @E-mail:zishuo.wang@qunar.com 
 * @version 创建时间：2013-6-17 下午8:23:23 
 * 基础校验
 */
public class CheckUtil {
	
	/**
	 * 判断是否是数字字符串
	 * @param value
	 * @return
	 */
	public static boolean isDigit(String value){
		for (int i = 0; i < value.length(); i++){
			if (!Character.isDigit(value.charAt(i))){
				return false;
		   }
		}
		return true;
	}

	/**
	 * 判断长度是否超限和是否是数字字符串，但不能作为是否在整型范围 内的判断
	 * @param value
     * @param nLen
	 * @return
	 */
	public static boolean isDigit(String value,int nLen){
		if(value.length() > nLen)return false;
		return isDigit(value);
	}
	
	/**
	 * 手机验证
	 * @param mobiles
	 * @return boolean 
	 */
	 public static boolean isMobileNO(String mobiles){        
		 Pattern p = Pattern.compile("^1\\d{10}$");     
		 Matcher m = p.matcher(mobiles);           
		 return m.matches();        
	 }
	 
	/**
     * 返回地址验证
     * @param value
     * @return boolean 
	 */
	 public static boolean isUrl(String value){
			String regex = "(http|ftp|https)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";   
			Pattern patternOne = Pattern.compile(regex);
			if( patternOne.matcher(value).matches()){
				return true;
			}
			String regExThree="(http|ftp|https)?://(\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b)+(/[\\w- ./?%&=]*)?";
			Pattern patternThree = Pattern.compile(regExThree);
			if( patternThree.matcher(value).matches()){
				return true;
			}
			String regExFour="(http|ftp|https)?://[A-Za-z]+(/[\\w- ./?%&=]*)?";  
			Pattern patternFour = Pattern.compile(regExFour);
			if( patternFour.matcher(value).matches()){
				return true;
			}
			String regExTwo="(http|ftp|https)?://(\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b)+[:\\d]+(/[\\w- ./?%&=]*)?";   
			Pattern patternTwo = Pattern.compile(regExTwo);
			if( patternTwo.matcher(value).matches()){
				return true;
			}
			String regExFive="(http|ftp|https)?://([\\w-]+\\.)+[\\w-]+[:\\d]+(/[\\w- ./?%&=]*)?";   
			Pattern patternFive = Pattern.compile(regExFive);
			if( patternFive.matcher(value).matches()){
				return true;
			}
			return false;
		}
	
	/**
	  * 用户IP验证
	  * @param value
	  * @return boolean 
	  */
	public static boolean isIp(String value) {
		String regex = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(value).matches();
	}

	/**
	 * 检查表达式 ， false则抛出异常
	 * @param expression
	 * @param errorMsg
	 */
	public static void verify(boolean expression, String errorMsg) {
		if(!expression) {
			throw new RuntimeException(errorMsg);
		}
	}
}
  
