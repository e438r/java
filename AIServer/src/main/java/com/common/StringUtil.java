/**
 *
 */
package com.common;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 字符串工具类
 *
 * @author wangjianwei
 */
public final class StringUtil {

    private StringUtil() {
    }

    /**
     * 字符串数据填充
     *
     * @param src    源数据
     * @param val    填充数据
     * @param length 规定数据长度
     * @return
     */
    public static String stringFilling(String source, String filler, int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length - source.length(); i++) {
            sb.append(filler);
        }
        sb.append(source);
        return sb.toString().substring(0, length);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0))
            return true;

        for (int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 字符串截取
     *
     * @param src    字符串源
     * @param length 截取长度
     * @return 截取后的字符串
     */
    public static String subStringValue(String src, int length) {
        String temp = "";
        if (isNotBlank(src)) {
            if (src.length() >= length) {
                temp = substring(src, 0, length);
            } else {
                temp = src;
            }
        }
        return temp;
    }

    /**
     * 从开头到spliter字符,截取字符串数组中的每一项
     *
     * @param arr     源字符串数组
     * @param spliter 切割符
     * @return 切割后的字符串数组
     */
    public static String[] subStrBefore(String[] arr, String spliter) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].substring(0, arr[i].indexOf(spliter));
        }
        return arr;
    }

    /**
     * 将字串转成日期，字串格式: yyyy-MM-dd
     *
     * @param string String
     * @return Date
     */

    public static Date parseDate(String string) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return (Date) formatter.parse(string);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 字符串数组中是否包含指定的字符串。
     *
     * @param strings       字符串数组
     * @param string        字符串
     * @param caseSensitive 是否大小写敏感
     * @return 包含时返回true，否则返回false
     */

    public static boolean contains(String[] strings, String string,
                                   boolean caseSensitive) {
        for (int i = 0; i < strings.length; i++) {
            if (caseSensitive == true) {
                if (strings[i].equals(string)) {
                    return true;
                }
            } else {
                if (strings[i].equalsIgnoreCase(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 字符串数组中是否包含指定的字符串。大小写敏感。
     *
     * @param strings 字符串数组
     * @param string  字符串
     * @return 包含时返回true，否则返回false
     */

    public static boolean contains(String[] strings, String string) {
        return contains(strings, string, true);
    }

    /**
     * 不区分大小写判定字符串数组中是否包含指定的字符串。
     *
     * @param strings 字符串数组
     * @param string  字符串
     * @return 包含时返回true，否则返回false
     */

    public static boolean containsIgnoreCase(String[] strings, String string) {
        return contains(strings, string, false);
    }

    /**
     * 返回一个整数数组
     *
     * @param s String[]
     * @return int[]
     */
    public static int[] parseInt(String[] s) {
        if (s == null) {
            return (new int[0]);
        }
        int[] result = new int[s.length];
        try {
            for (int i = 0; i < s.length; i++) {
                result[i] = Integer.parseInt(s[i]);
            }
        } catch (NumberFormatException ex) {
            return result;
        }
        return result;
    }

    /**
     * 返回一个整数数组
     *
     * @param s       String
     * @param spliter 分隔符如逗号
     * @return int[]
     */
    public static int[] split(String s, String spliter) {
        if (s == null || s.indexOf(spliter) == -1) {
            return (new int[0]);
        }
        String[] ary = s.split(spliter);
        int[] result = new int[ary.length];
        try {
            for (int i = 0; i < ary.length; i++) {
                result[i] = Integer.parseInt(ary[i]);
            }
        } catch (NumberFormatException ex) {
            return result;
        }
        return result;
    }

    /**
     * 将整型数组合并为用字符分割的字符串
     *
     * @param arr
     * @return String
     */
    public static String join(int[] arr) {
        if (arr == null || arr.length == 0)
            return "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0, len = arr.length; i < len; i++) {
            sb.append(",").append(arr[i]);
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }

    /**
     * 将字符串的第一个字母大写
     *
     * @param s String
     * @return String
     */
    public static String firstCharToUpperCase(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        char[] arrC = s.toCharArray();
        arrC[0] = Character.toUpperCase(arrC[0]);
        return String.copyValueOf(arrC);
    }

    /**
     * 根据当前字节长度取出加上当前字节长度不超过最大字节长度的子串
     *
     * @param str
     * @param currentLen
     * @param MAX_LEN
     * @return
     */
    public static String getSubStr(String str, int currentLen, int MAX_LEN) {
        int i;
        for (i = 0; i < str.length(); i++) {
            if (str.substring(0, i + 1).getBytes().length + currentLen > MAX_LEN) {
                break;
            }
        }
        if (i == 0) {
            return "";
        } else {
            return str.substring(0, i);
        }
    }

    /**
     * 字符串拼接函数
     *
     * @param splitStr 字符拼接分隔符
     * @param strAry   待拼接的变长字符串
     * @return 返回拼接后的字符串
     */
    public static String concatStr(String splitStr, String... strAry) {
        StringBuffer sb = new StringBuffer();
        int len = strAry.length;
        for (int i = 0; i < len; i++) {

            sb.append(strAry[i]);
            if (i < len - 1)
                sb.append(splitStr);
        }
        return sb.toString();
    }

    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return "";
        }

        return str.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param length
     * @return
     */
    public static String truncateString(String str, int length) {
        String result = StringUtils.trimToEmpty(str);
        if (result.length() < length) {
            return result;
        }
        return result.substring(0, length);

    }

    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return "";
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * map 转换成字符串
     *
     * @param map
     * @return
     */
    public static String mapToString(Map<String, String> map) {
        String msg = "";
        try {

            Document document = DocumentHelper.createDocument();
            document.setXMLEncoding("GBK");
            Element rootElement = document.addElement("data");
            Iterator<Entry<String, String>> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                Element nameElement = rootElement.addElement(entry.getKey());
                nameElement.setText(entry.getValue());
            }
            msg = document.asXML();
        } catch (Exception e) {
            //log.error("parserXmlData 解析xml 报错："+e.getMessage());
        }
        return msg;
    }
}
