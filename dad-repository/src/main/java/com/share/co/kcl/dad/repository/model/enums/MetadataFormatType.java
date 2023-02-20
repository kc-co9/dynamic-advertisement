package com.share.co.kcl.dad.repository.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetadataFormatType {

    NONE(0, "无"),
    REGULAR_EXPRESSION(1, "正则表达式"),;

    @EnumValue
    private final Integer code;
    private final String msg;
}
