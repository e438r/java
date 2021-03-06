package com.common;



/**
 * 数组和16进制字符串转换工具类
 * 
 * @author liqiang
 *
 */
public final class HexUtil {
	private static final String HEX_CHARS = "0123456789abcdef";
	
    private HexUtil() {
    }

    /**
     * 数组转为16进制字符串
     * 
     * @param b
     * @return
     */
    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(HexUtil.HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
            sb.append(HexUtil.HEX_CHARS.charAt(b[i] & 0x0F));
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转数组
     * 
     * @param s
     * @return
     */
    public static byte[] toByteArray(String s) {
        byte[] buf = new byte[s.length() / 2];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character
                    .digit(s.charAt(j++), 16));
        }
        return buf;
    }
}
