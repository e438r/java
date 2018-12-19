package com.common;

import com.google.common.base.Strings;

import java.math.BigDecimal;

/**
 * Created by duwenlong on 15/3/26.
 */
public class CalcHelper {
    /**
     * decimalA 加  decimalB
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal add(BigDecimal decimalA,BigDecimal decimalB){
        if(decimalA==null || decimalB == null)
            return null;

        decimalA = decimalA.setScale(2, BigDecimal.ROUND_HALF_UP);
        decimalB = decimalB.setScale(2, BigDecimal.ROUND_HALF_UP);
        return (decimalA.add(decimalB)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * decimals元素相加
     *
     * @param decimals
     * @return total
     */
    public static BigDecimal add(BigDecimal ...decimals){
        BigDecimal total = new BigDecimal(0);
        for(BigDecimal each : decimals){
            if(each != null)
                total = add(total, each);
        }
        return total;
    }

    /**
     * decimalA 减 decimalB
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal sub(BigDecimal decimalA,BigDecimal decimalB){
        if(decimalA==null || decimalB == null)
            return null;

        decimalA = decimalA.setScale(2, BigDecimal.ROUND_HALF_UP);
        decimalB = decimalB.setScale(2, BigDecimal.ROUND_HALF_UP);
        return (decimalA.subtract(decimalB)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * decimalA 乘 decimalB
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal mul(BigDecimal decimalA,BigDecimal decimalB){
        return mul(decimalA, decimalB, 2);
    }

    /**
     * decimalA 乘 decimalB
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal mul(BigDecimal decimalA, BigDecimal decimalB, int scale){
        if(decimalA==null || decimalB == null)
            return null;
        return (decimalA.multiply(decimalB)).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * decimalA 除 decimalB
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal div(BigDecimal decimalA, BigDecimal decimalB, int scale){
        if(decimalA==null || decimalB == null)
            return null;

        if(decimalB.compareTo(BigDecimal.ZERO) ==0)
            return null;

        return decimalA.divide(decimalB, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 整数转BigDecimal
     * @return
     */
    public static BigDecimal toBigDecimal(Integer num){
        if(num == null)
            return null;
        try{
            return (new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP));
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 整数转BigDecimal
     * @return
     */
    public static BigDecimal toBigDecimal(Long num){
        if(num == null)
            return null;
        try{
            return (new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP));
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 字符串转BigDecimal
     * @param str
     * @return
     */
    public static BigDecimal toBigDecimal(String str){
        if(str == null || "".equals(str.trim()))
            return null;
        try{
            return (new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_UP));
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 字符串转BigDecimal，字符串为空时返回0
     * @param str 字符串
     * @return  BigDecimal
     */
    public static BigDecimal toBigDecimalIgnoreZero(String str){
        if(str == null || "".equals(str.trim()))
            return new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
        try{
            return (new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_UP));
        }catch(Exception e){
            return null;
        }
    }

    /**
     * Double转BigDecimal
     * @return
     */
    public static BigDecimal toBigDecimal(Double d){
        return toBigDecimal(d, 2);
    }

    public static BigDecimal toBigDecimal(Double d, int scale){
        if(d == null)
            return null;
        try{
            return (new BigDecimal(d).setScale(scale, BigDecimal.ROUND_HALF_UP));
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 将以分表示的金额转为以元为单位
     * @param centvalue
     * @return
     */
    public static BigDecimal toYuan(String centvalue) {
        try {
            return new BigDecimal(centvalue).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 元转为分
     * @param amount
     * @return
     */
    public static String toCent(BigDecimal amount) {
        try {
            return amount.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 元转为分
     * @param yuan
     * @return
     */
    public static String toCent(String yuan) {
        try {
            if(Strings.isNullOrEmpty(yuan)) {
                return null;
            }
            BigDecimal amount = toBigDecimal(yuan);
            return toCent(amount);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Object 转为BigDecimal
     * @param obj
     * @return
     */
    public static BigDecimal toBigDecimal(Object obj) {
        try {
            if(obj == null) {
                return null;
            }
            return toBigDecimal(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串转BigDecimal, 带默认值
     * @param str
     * @return
     */
    public static BigDecimal toBigDecimal(String str, BigDecimal defaultValue){
        if(str == null || "".equals(str.trim()))
            return defaultValue;
        try{
            return (new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_UP));
        }catch(Exception e){
            return defaultValue;
        }
    }


    public static BigDecimal add(Object o1, Object o2) {
        if(o1 == null && o2 == null) {
            return null;
        }
        BigDecimal val1 = o1==null?BigDecimal.ZERO: new BigDecimal(o1.toString());
        BigDecimal val2 = o2==null?BigDecimal.ZERO: new BigDecimal(o2.toString());
        return val1.add(val2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static String decimalToString(BigDecimal decimal, int scale) {
        if(decimal == null) {
            return null;
        } else {
            return decimal.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
        }
    }

    /**
     * decimals元素相加
     *
     * @param decimals
     * @return total
     */
    public static BigDecimal addIgnoreNull(BigDecimal ...decimals){
        BigDecimal total = new BigDecimal(0);
        for(BigDecimal each : decimals){
            if(each != null)
                total = add(total, each);
        }
        return total;
    }

    /**
     * decimals元素相加
     *
     * @return total
     */
    public static BigDecimal addObjIgnoreNull(Object ...objects){
        BigDecimal total = new BigDecimal(0);
        for(Object each : objects){
            if(each != null)
                total = add(total, each);
        }
        return total;
    }

    public static BigDecimal nullToZero(BigDecimal val) {
        return val == null ? BigDecimal.ZERO : val;
    }
}
