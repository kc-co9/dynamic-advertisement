package com.share.co.kcl.dad.repository.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetadataValidatedType {

    NONE(0, "无"),
    GROOVY(1, "groovy脚本");

    @EnumValue
    private final Integer code;
    private final String msg;
}
