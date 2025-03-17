package com.lloop.cloudpicturebackend.model.enums;

import lombok.Getter;
import org.springframework.util.ObjectUtils;

/**
 * @Author lloop
 * @Create 2025/3/17 15:15
 */
@Getter
public enum UserRoleEnum {

    USER("用户", "user"),
    ADMIN("管理员", "admin");

    private final String text;

    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static UserRoleEnum getEnumByValue(String value) {
        if(ObjectUtils.isEmpty(value)) return null;
        for(UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if(userRoleEnum.value.equals(value)) {
                return userRoleEnum;
            }
        }
        return null;
    }

}
