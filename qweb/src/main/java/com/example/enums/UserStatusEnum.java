package com.example.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户状态枚举
 *
 * @author shining.cui
 */
public enum UserStatusEnum {
    /**
     * 申请中
     */
    INITIAL(0, "申请中"),

    /**
     * 授信成功
     */
    REGISTER(1, "授信成功"),

    /**
     * 激活成功
     */
    SIGN(2, "激活成功"),

    /**
     * 已失效
     */
    INVALID(3, "已失效"),

    /**
     * 授信失败
     */
    REGISTER_FAILED(4, "授信失败"),

    /**
     * 激活失败
     */
    SIGN_FAILED(5, "激活失败");

    private final Integer code;

    private final String message;

    private static final Map<Integer, UserStatusEnum> MAP = new HashMap<Integer, UserStatusEnum>();

    static {
        for (UserStatusEnum userStatusEnum : values()) {
            MAP.put(userStatusEnum.getCode(), userStatusEnum);
        }
    }

    public static UserStatusEnum codeOf(Integer code) {
        return MAP.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    UserStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
