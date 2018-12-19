package com.common;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 
 * 日期时间工具类
 * 
 * @author wangjianwei
 *
 */
@SuppressWarnings("serial")
public class DateUtil extends Date {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String LONG_DATE_PATTERN_NO_SYMBOL = "yyyyMMddHHmmss";

    public static final String DATE_PATTERN = "yyyyMMddHHmmssSSS";

    public static final String DAY_DATE_PATTERN ="yyyyMMdd";
	public DateUtil() {
		super();
	}
    
	/**
	 * 日期按照格式进行格式化
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String DateToString(Date date, String pattern) {
		String strDateTime = null;
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		strDateTime = date == null ? null : formater.format(date);
		return strDateTime;
	}
    
	/**
	 * 日期格式化成  yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String DateToString(Date date) {
		String _pattern = "yyyy-MM-dd";
		return date == null ? null : DateToString(date, _pattern);
	}

	/**
	 * 日期格式化成  yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String DateTimeToString(Date date) {
		String _pattern = "yyyy-MM-dd HH:mm:ss";
		return date == null ? null : DateToString(date, _pattern);
	}
	

	
	/**
	 * 日期格式化成  yyyy-MM-dd HH:mm:ss:SSS
	 * @param date
	 * @return
	 */
	public static String DateStampToString(Date date) {
		String _pattern = "yyyy-MM-dd HH:mm:ss:SSS";
		return date == null ? null : DateToString(date, _pattern);

	}

	/**
	 * 计算 day 天后的时间
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		if (null == date){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	
	/**
	 * 日期格式化成  yyyyMMddHHmmssSSS
	 * @param date
	 * @return
	 */
	public static String DateStampToStringNoSp(Date date) {
		String _pattern = "yyyyMMddHHmmssSSS";
		return date == null ? null : DateToString(date, _pattern);
	}

	/**
	 * 字符串按照一定格式格式化日期 Date
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date StringToDate(String str, String pattern) {
		Date dateTime = null;
		try {
			if (str != null && !str.equals("")) {
				SimpleDateFormat formater = new SimpleDateFormat(pattern);
				dateTime = formater.parse(str);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return dateTime;
	}

	/**
	 * 字符串按照一定格式格式化日期  yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static Date StringToDate(String str) {
		String _pattern = "yyyy-MM-dd";
		return StringToDate(str, _pattern);
	}
    
	/**
	 * 字符串按照一定格式格式化日期  yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static Date StringToDateTime(String str) {
		String _pattern = "yyyy-MM-dd HH:mm:ss";
		return StringToDate(str, _pattern);
	}
   
	/**
	 * 字符串按照一定格式格式化日期  yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static Timestamp StringToDateHMS(String str) {
		Timestamp time = null;
		time = Timestamp.valueOf(str);
		return time;
	}

	/**
	 * 根据年、月、日格式成Date
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date YmdToDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}

	/**
	 * 日期格式化成 MM/dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String communityDateToString(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd HH:mm:ss");
		String strDateTime = date == null ? null : formater.format(date);
		return strDateTime;
	}

	public static Date getMaxDateOfDay(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(11, calendar.getActualMaximum(11));
			calendar.set(12, calendar.getActualMaximum(12));
			calendar.set(13, calendar.getActualMaximum(13));
			calendar.set(14, calendar.getActualMaximum(14));
			return calendar.getTime();
		}
	}
    
	public static Date getMinDateOfDay(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(11, calendar.getActualMinimum(11));
			calendar.set(12, calendar.getActualMinimum(12));
			calendar.set(13, calendar.getActualMinimum(13));
			calendar.set(14, calendar.getActualMinimum(14));
			return calendar.getTime();
		}
	}

	/**
	 * 取指定日后n天，n允许为负数
	 * 
	 * @param date
	 * @param afterDays
	 * @return java.util.Date
	 */
	public static Date getAfterDay(Date date, int afterDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, afterDays);
		return cal.getTime();
	}
	
	/**
	 * 获取指定几个月后的日期
	 * @param date
	 * @param afterMonth
	 * @return
	 */
	public static Date getAfterMonth( Date date, int afterMonth){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH,afterMonth);
		return cal.getTime();
	}

	/**
	 * 计算两个时间中之间的 天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int DateDiff(Date date1, Date date2) {
		int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000);
		return i;
	}

	/**
	 * 计算两个时间中之间的 分钟数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int MinDiff(Date date1, Date date2) {
		int i = (int) ((date1.getTime() - date2.getTime()) / 1000 / 60);
		return i;
	}

	/**
	 * 计算两个时间中之间的 秒数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int TimeDiff(Date date1, Date date2) {
		int i = (int) ((date1.getTime() - date2.getTime()));
		return i;
	}

	/**
	 * 根据日期获取星期数
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date){
		Calendar c = Calendar.getInstance();
		String week = null;
		c.setTime(date);
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
			week = "[周日]";
		}else if(c.get(Calendar.DAY_OF_WEEK) == 2){
			week = "[周一]";
		}else if(c.get(Calendar.DAY_OF_WEEK) == 3){
			week = "[周二]";
		} else if(c.get(Calendar.DAY_OF_WEEK) == 4){
			week = "[周三]";
		} else if(c.get(Calendar.DAY_OF_WEEK) == 5){
			week = "[周四]";
		} else if(c.get(Calendar.DAY_OF_WEEK) == 6){
			week = "[周五]";
		} else if(c.get(Calendar.DAY_OF_WEEK) == 7){
			week = "[周六]";
		} else {
			week = "";
		}
		return week;
	}

	/**
	 * 获取当前月的第一天
	 * @param date
	 * @return	返回当前月第一天的日期
	 */
	public static String getFirstDayStrOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return formatDate2Str(calendar.getTime());
	}

	/**
	 * 日期格式化成默认格式的字符串，yyyy-MM-dd
	 * @param date 待格式化日期
	 * @return	格式化字符串
	 */
	public static String formatDate2Str(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return sdf.format(date);
	}

    /**
     * 返回：当前系统年份
     * @return String
     */
    public static String getNowYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split("-")[0];
    }

    /**
     * 返回：当前系统月份
     * @return 09
     */
    public static String getNowMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split("-")[1];
    }

    /**
     * 返回：当前系统日
     * @return 09
     */
    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(new Date()).split("-")[2].split(" ")[0];
    }

	public enum  Format {
		DATE("yyyyMMdd"),
		DATE_WITH_SYMBOL("yyyy-MM-dd"),
		TIME("yyyyMMddHHmmss"),
		TIME_WITH_SYMBOL("yyyy-MM-dd HH:mm:ss");

		private String desc;
		Format(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	/**
	 * @param src fucking data
	 * @param format 格式
	 * @return 格式化后的字符串
	 */
	public static String format(String src , Format format) {
		Preconditions.checkNotNull(format, "format 必传");
		if(Strings.isNullOrEmpty(src)) {
			return src;
		}

		return DateToString(StringToDate(stdFormat(src), LONG_DATE_PATTERN_NO_SYMBOL), format.getDesc());
	}

	private static String stdFormat(String src) {
		//去符号
		src = src.replaceAll(":","").replaceAll("-","").replaceAll(" ", "");
		return Strings.padEnd(src, LONG_DATE_PATTERN_NO_SYMBOL.length(), '0');
	}

	/**
	 * @param src fucking data
	 * @return 格式化后的字符串
	 */
	public static Date compatibleParse(String src) {
		if(Strings.isNullOrEmpty(src)) {
			return null;
		}

		return StringToDate(stdFormat(src), LONG_DATE_PATTERN_NO_SYMBOL);
	}

	/**
	 * 获取当日时间得0点
	 * @return
	 */
	public static Date getZeroByToDays(){
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * late-early .
	 *
	 * @param early
	 * @param late
	 * @return
	 */
	public static Integer daysBetween(Date early, Date late) {
		if (null == early || null == late) {
			return null;
		}

		Calendar calEarly = Calendar.getInstance();
		Calendar calLate = Calendar.getInstance();
		calEarly.setTime(early);
		calLate.setTime(late);

        /* 设置时间为0时 */
		calEarly.set(Calendar.HOUR_OF_DAY, 0);
		calEarly.set(Calendar.MINUTE, 0);

		calEarly.set(Calendar.SECOND, 0);
		calLate.set(Calendar.HOUR_OF_DAY, 0);

		calLate.set(Calendar.MINUTE, 0);
		calLate.set(Calendar.SECOND, 0);

        /* 得到俩个日期相差天数 */
		return ((int) (calLate.getTime().getTime() / 1000) - (int) (calEarly.getTime().getTime() / 1000)) / 3600 / 24;
	}
	/**
	 * 获取2个日期差的月份
	 *
	 * @param start 开始日期
	 * @param end   结束日期
	 */
	public static Integer getMonthBetween(Date start, Date end) {
		if (null == start || null == end) {
			return null;
		}
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.setTime(start);
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(end);

		int years = calendarEnd.get(Calendar.YEAR) - calendarStart.get(Calendar.YEAR);
		int months = calendarEnd.get(Calendar.MONTH) - calendarStart.get(Calendar.MONTH);
		return years * 12 + months;
	}
}
