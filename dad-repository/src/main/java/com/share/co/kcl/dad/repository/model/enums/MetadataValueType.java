package com.share.co.kcl.dad.repository.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.share.co.kcl.dad.repository.model.domain.ConfigAttributeValueDo;
import com.share.co.kcl.dad.repository.model.domain.MetadataValueRangeDo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum MetadataValueType {

    VARCHAR(1, "字符串类型"),
    INT(2, "整形类型"),
    LONG(3, "长整形类型"),
    BOOLEAN(4, "布尔类型"),
    ENUM(5, "枚举类型"),
    DATETIME(6, "日期时间类型"),
    DATE(7, "日期类型"),
    TIME(8, "时间类型"),
    ;

    @EnumValue
    private final Integer code;
    private final String msg;

    public static class DatabaseType {

        private DatabaseType() {

        }

        public static List<MetadataValueType> getIntTypeValue() {
            return Arrays.asList(INT, BOOLEAN);
        }

        public static List<MetadataValueType> getLongTypeValue() {
            return Arrays.asList(LONG, DATETIME, DATE, TIME);
        }

        public static List<MetadataValueType> getStringTypeValue() {
            return Arrays.asList(ENUM, VARCHAR);
        }
    }

    public static class MetadataRangeType {

        private MetadataRangeType() {
        }

        public static List<MetadataValueType> getValueRangeType() {
            return Arrays.asList(INT, LONG, DATETIME, DATE, TIME);
        }

        public static List<MetadataValueType> getOptionRangeType() {
            return Arrays.asList(ENUM, VARCHAR, BOOLEAN);
        }
    }
}
