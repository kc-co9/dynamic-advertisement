package com.share.co.kcl.dad.repository.model.domain;

import com.alibaba.fastjson2.JSON;
import com.share.co.kcl.dad.common.utils.DateUtils;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.ConfigAttributeValueSelectResultBooleanBuilder.*;
import static com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.ConfigAttributeValueSelectResultEnumBuilder.*;
import static com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.ConfigAttributeValueSelectResultIntBuilder.*;
import static com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.ConfigAttributeValueSelectResultLongBuilder.*;
import static com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.ConfigAttributeValueSelectResultTimeBuilder.*;
import static com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.ConfigAttributeValueSelectResultVarcharBuilder.*;
import static com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.ConfigAttributeValueSelectResultDatetimeBuilder.*;
import static com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.ConfigAttributeValueSelectResultDateBuilder.*;


class ConfigAttributeValueDoSerializeTest {

    @Test
    void testCommonAttribute() {
        ConfigAttributeValueSelectResult<?> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultIntBuilder.buildCommonValueResult();
        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);
        Assertions.assertEquals(TEST_COMMON_ID, configAttributeValueDo.getId(), "ID值不正确");
        Assertions.assertEquals(TEST_COMMON_KEY, configAttributeValueDo.getKey(), "KEY值不正确");
        Assertions.assertEquals(TEST_COMMON_NAME, configAttributeValueDo.getName(), "NAME值不正确");
        Assertions.assertEquals(TEST_COMMON_DESC, configAttributeValueDo.getDescription(), "DESC值不正确");
        Assertions.assertEquals(TEST_COMMON_VALUE_LIMIT, configAttributeValueDo.getValueLimit(), "限制标识不正确");
        Assertions.assertEquals(TEST_COMMON_VALUE_IS_NULL, configAttributeValueDo.getIsNull(), "空值标识不正确");
        Assertions.assertEquals(TEST_COMMON_VALUE_IS_REQUIRED, configAttributeValueDo.getIsRequired(), "必填标识不正确");
        Assertions.assertEquals(TEST_COMMON_VALUE_RANGE, configAttributeValueDo.getValueRange(), "值范围不正确");
        Assertions.assertEquals(TEST_COMMON_VALUE_FORMAT_TYPE, configAttributeValueDo.getFormatType(), "值格式类型不正确");
        Assertions.assertEquals(TEST_COMMON_VALUE_FORMAT_RULE, configAttributeValueDo.getFormatRule(), "值格式不正确");
        Assertions.assertEquals(TEST_COMMON_VALUE_VALIDATED_TYPE, configAttributeValueDo.getValidatedType(), "值业务校验类型不正确");
        Assertions.assertEquals(TEST_COMMON_VALUE_VALIDATED_RULE, configAttributeValueDo.getValidatedRule(), "值业务校验不正确");
        Assertions.assertEquals(TEST_COMMON_CREATE_TIME, configAttributeValueDo.getCreateTime(), "创建时间不正确");
    }

    @Test
    void testCommonValueSerialize() {
        Assertions.assertEquals("TEST", ConfigAttributeValueDo.serialize(MetadataValueType.VARCHAR, "TEST"));
        Assertions.assertEquals(1, ConfigAttributeValueDo.serialize(MetadataValueType.INT, 1));
        Assertions.assertEquals(1L, ConfigAttributeValueDo.serialize(MetadataValueType.LONG, 1L));
        Assertions.assertEquals(1, ConfigAttributeValueDo.serialize(MetadataValueType.BOOLEAN, true));
        Assertions.assertEquals("TEST_ENUM", ConfigAttributeValueDo.serialize(MetadataValueType.ENUM, "TEST_ENUM"));
        Assertions.assertEquals(DateUtils.valueOfSecond(LocalDateTime.parse("2022-02-02 00:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))),
                ConfigAttributeValueDo.serialize(MetadataValueType.DATETIME, "2022-02-02 00:00:00"));
        Assertions.assertEquals(DateUtils.valueOfSecond(LocalDate.parse("2022-02-02", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))),
                ConfigAttributeValueDo.serialize(MetadataValueType.DATE, "2022-02-02"));
        Assertions.assertEquals(DateUtils.valueOfSecond(LocalTime.parse("11:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))),
                ConfigAttributeValueDo.serialize(MetadataValueType.TIME, "11:00:00"));
    }

    @Test
    void testCommonValueDeserialize() {
        Assertions.assertEquals("TEST", ConfigAttributeValueDo.deserialize(MetadataValueType.VARCHAR, "TEST"));
        Assertions.assertEquals(1, ConfigAttributeValueDo.deserialize(MetadataValueType.INT, 1));
        Assertions.assertEquals(1L, ConfigAttributeValueDo.deserialize(MetadataValueType.LONG, 1L));
        Assertions.assertEquals(true, ConfigAttributeValueDo.deserialize(MetadataValueType.BOOLEAN, 1));
        Assertions.assertEquals("TEST_ENUM", ConfigAttributeValueDo.deserialize(MetadataValueType.ENUM, "TEST_ENUM"));
        Assertions.assertEquals("2022-02-02 00:00:00",
                ConfigAttributeValueDo.deserialize(MetadataValueType.DATETIME, DateUtils.valueOfSecond(LocalDateTime.parse("2022-02-02 00:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME)))));
        Assertions.assertEquals("2022-02-02",
                ConfigAttributeValueDo.deserialize(MetadataValueType.DATE, DateUtils.valueOfSecond(LocalDate.parse("2022-02-02", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE)))));
        Assertions.assertEquals("11:00:00",
                ConfigAttributeValueDo.deserialize(MetadataValueType.TIME, DateUtils.valueOfSecond(LocalTime.parse("11:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME)))));
    }

    @Test
    void testCommonValueRangeSerialize() {
        Assertions.assertEquals(JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(1, 2)))),
                ConfigAttributeValueDo.serializeValueRange(MetadataValueType.INT, new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(1, 2)))));
        Assertions.assertEquals(JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(1, 2)))),
                ConfigAttributeValueDo.serializeValueRange(MetadataValueType.LONG, new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(1, 2)))));
        Assertions.assertEquals(JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.OptionRange("testCode", "testName")))),
                ConfigAttributeValueDo.serializeValueRange(MetadataValueType.VARCHAR, new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.OptionRange("testCode", "testName")))));
        Assertions.assertEquals(JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.OptionRange(1, "testName")))),
                ConfigAttributeValueDo.serializeValueRange(MetadataValueType.BOOLEAN, new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.OptionRange(true, "testName")))));
        Assertions.assertEquals(JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.OptionRange("testCode", "testName")))),
                ConfigAttributeValueDo.serializeValueRange(MetadataValueType.ENUM, new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.OptionRange("testCode", "testName")))));
        Assertions.assertEquals(
                JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(
                        DateUtils.valueOfSecond(LocalDateTime.parse("2022-02-02 00:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))),
                        DateUtils.valueOfSecond(LocalDateTime.parse("2022-02-10 00:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))))))),
                ConfigAttributeValueDo.serializeValueRange(MetadataValueType.DATETIME,
                        new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange("2022-02-02 00:00:00", "2022-02-10 00:00:00")))));
        Assertions.assertEquals(
                JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(
                        DateUtils.valueOfSecond(LocalDate.parse("2022-02-02", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))),
                        DateUtils.valueOfSecond(LocalDate.parse("2022-02-10", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))))))),
                ConfigAttributeValueDo.serializeValueRange(MetadataValueType.DATE,
                        new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange("2022-02-02", "2022-02-10")))));
        Assertions.assertEquals(
                JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(
                        DateUtils.valueOfSecond(LocalTime.parse("12:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))),
                        DateUtils.valueOfSecond(LocalTime.parse("18:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))))))),
                ConfigAttributeValueDo.serializeValueRange(MetadataValueType.TIME,
                        new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange("12:00:00", "18:00:00")))));
    }

    @Test
    void testCommonValueRangeDeserialize() {
        String serializeInteger = JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(1, 2))));
        Assertions.assertEquals(1,
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.INT, serializeInteger)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getStart)
                        .map(String::valueOf)
                        .map(Integer::parseInt)
                        .orElse(null));
        Assertions.assertEquals(2,
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.INT, serializeInteger)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getEnd)
                        .map(String::valueOf)
                        .map(Integer::parseInt)
                        .orElse(null));

        String serializeLong = JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(1, 2))));
        Assertions.assertEquals(1,
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.LONG, serializeLong)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getStart)
                        .map(String::valueOf)
                        .map(Integer::parseInt)
                        .orElse(null));
        Assertions.assertEquals(2,
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.LONG, serializeLong)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getEnd)
                        .map(String::valueOf)
                        .map(Integer::parseInt)
                        .orElse(null));

        String serializeVarchar = JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.OptionRange("testCode", "testName"))));
        Assertions.assertEquals("testCode",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.VARCHAR, serializeVarchar)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.OptionRange.class::cast)
                        .map(MetadataValueRangeDo.OptionRange::getCode)
                        .map(String::valueOf)
                        .orElse(null));
        Assertions.assertEquals("testName",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.VARCHAR, serializeVarchar)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.OptionRange.class::cast)
                        .map(MetadataValueRangeDo.OptionRange::getName)
                        .orElse(null));

        String serializeBoolean = JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.OptionRange(1, "testName"))));
        Assertions.assertEquals(true,
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.BOOLEAN, serializeBoolean)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.OptionRange.class::cast)
                        .map(MetadataValueRangeDo.OptionRange::getCode)
                        .orElse(null));
        Assertions.assertEquals("testName",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.BOOLEAN, serializeBoolean)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.OptionRange.class::cast)
                        .map(MetadataValueRangeDo.OptionRange::getName)
                        .orElse(null));

        String serializeEnum = JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.OptionRange("testCode", "testName"))));
        Assertions.assertEquals("testCode",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.ENUM, serializeEnum)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.OptionRange.class::cast)
                        .map(MetadataValueRangeDo.OptionRange::getCode)
                        .map(String::valueOf)
                        .orElse(null));
        Assertions.assertEquals("testName",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.ENUM, serializeEnum)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.OptionRange.class::cast)
                        .map(MetadataValueRangeDo.OptionRange::getName)
                        .orElse(null));

        String serializeDatetime = JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(
                DateUtils.valueOfSecond(LocalDateTime.parse("2022-02-02 00:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))),
                DateUtils.valueOfSecond(LocalDateTime.parse("2022-02-10 00:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME)))))));
        Assertions.assertEquals("2022-02-02 00:00:00",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.DATETIME, serializeDatetime)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getStart)
                        .orElse(null));
        Assertions.assertEquals("2022-02-10 00:00:00",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.DATETIME, serializeDatetime)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getEnd)
                        .orElse(null));

        String serializeDate = JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(
                DateUtils.valueOfSecond(LocalDate.parse("2022-02-02", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))),
                DateUtils.valueOfSecond(LocalDate.parse("2022-02-10", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE)))))));
        Assertions.assertEquals("2022-02-02",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.DATE, serializeDate)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getStart)
                        .orElse(null));
        Assertions.assertEquals("2022-02-10",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.DATE, serializeDate)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getEnd)
                        .orElse(null));

        String serializeTime = JSON.toJSONString(new MetadataValueRangeDo<>(Collections.singletonList(new MetadataValueRangeDo.ValueRange(
                DateUtils.valueOfSecond(LocalTime.parse("12:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))),
                DateUtils.valueOfSecond(LocalTime.parse("18:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME)))))));
        Assertions.assertEquals("12:00:00",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.TIME, serializeTime)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getStart)
                        .orElse(null));
        Assertions.assertEquals("18:00:00",
                ConfigAttributeValueDo.deserializeValueRange(MetadataValueType.TIME, serializeTime)
                        .getRangeList()
                        .stream()
                        .findFirst()
                        .map(MetadataValueRangeDo.ValueRange.class::cast)
                        .map(MetadataValueRangeDo.ValueRange::getEnd)
                        .orElse(null));
    }

    @Test
    void testIntAttributeValueSerializeAndDeserialize() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNotNull(configAttributeValueDo.getValueType(), "数据类型为空");
        Assertions.assertEquals(TEST_INTEGER_VALUE_TYPE, configAttributeValueDo.getValueType(), "值类型不正确");

        Assertions.assertNotNull(configAttributeValueDo.getSerializeValue().orElse(null), "序列化值为空");
        Assertions.assertEquals(TEST_INTEGER_SERIALIZE_VALUE, configAttributeValueDo.getSerializeValue().orElse(null), "整形序列化值不正确");

        Assertions.assertNotNull(configAttributeValueDo.getDeserializeValue().orElse(null), "反序列化值为空");
        Assertions.assertEquals(TEST_INTEGER_DESERIALIZE_VALUE, configAttributeValueDo.getDeserializeValue().orElse(null), "整形反序列化值为空");
    }

    @Test
    void testIntAttributeNullValue() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerNullValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNull(configAttributeValueDo.getSerializeValue().orElse(null), "空值序列化异常");
        Assertions.assertNull(configAttributeValueDo.getDeserializeValue().orElse(null), "空值反序列化异常");
    }

    @Test
    void testLongAttributeValueSerializeAndDeserialize() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNotNull(configAttributeValueDo.getValueType(), "数据类型为空");
        Assertions.assertEquals(TEST_LONG_VALUE_TYPE, configAttributeValueDo.getValueType(), "值类型不正确");

        Assertions.assertNotNull(configAttributeValueDo.getSerializeValue().orElse(null), "序列化值为空");
        Assertions.assertEquals(TEST_LONG_SERIALIZE_VALUE, configAttributeValueDo.getSerializeValue().orElse(null), "长整形序列化值不正确");

        Assertions.assertNotNull(configAttributeValueDo.getDeserializeValue().orElse(null), "反序列化值为空");
        Assertions.assertEquals(TEST_LONG_DESERIALIZE_VALUE, configAttributeValueDo.getDeserializeValue().orElse(null), "长整形反序列化值为空");
    }

    @Test
    void testLongAttributeNullValue() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongNullValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNull(configAttributeValueDo.getSerializeValue().orElse(null), "空值序列化异常");
        Assertions.assertNull(configAttributeValueDo.getDeserializeValue().orElse(null), "空值反序列化异常");
    }

    @Test
    void testBooleanAttributeValueSerializeAndDeserialize() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNotNull(configAttributeValueDo.getValueType(), "数据类型为空");
        Assertions.assertEquals(TEST_BOOLEAN_VALUE_TYPE, configAttributeValueDo.getValueType(), "值类型不正确");

        Assertions.assertNotNull(configAttributeValueDo.getSerializeValue().orElse(null), "序列化值为空");
        Assertions.assertEquals(TEST_BOOLEAN_SERIALIZE_VALUE, configAttributeValueDo.getSerializeValue().orElse(null), "长整形序列化值不正确");

        Assertions.assertNotNull(configAttributeValueDo.getDeserializeValue().orElse(null), "反序列化值为空");
        Assertions.assertEquals(TEST_BOOLEAN_DESERIALIZE_VALUE, configAttributeValueDo.getDeserializeValue().orElse(null), "长整形反序列化值为空");
    }

    @Test
    void testBooleanAttributeNullValue() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanNullValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNull(configAttributeValueDo.getSerializeValue().orElse(null), "空值序列化异常");
        Assertions.assertNull(configAttributeValueDo.getDeserializeValue().orElse(null), "空值反序列化异常");
    }

    @Test
    void testEnumAttributeValueSerializeAndDeserialize() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNotNull(configAttributeValueDo.getValueType(), "数据类型为空");
        Assertions.assertEquals(TEST_ENUM_VALUE_TYPE, configAttributeValueDo.getValueType(), "值类型不正确");

        Assertions.assertNotNull(configAttributeValueDo.getSerializeValue().orElse(null), "序列化值为空");
        Assertions.assertEquals(TEST_ENUM_SERIALIZE_VALUE, configAttributeValueDo.getSerializeValue().orElse(null), "长整形序列化值不正确");

        Assertions.assertNotNull(configAttributeValueDo.getDeserializeValue().orElse(null), "反序列化值为空");
        Assertions.assertEquals(TEST_ENUM_DESERIALIZE_VALUE, configAttributeValueDo.getDeserializeValue().orElse(null), "长整形反序列化值为空");
    }

    @Test
    void testEnumAttributeNullValue() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumNullValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNull(configAttributeValueDo.getSerializeValue().orElse(null), "空值序列化异常");
        Assertions.assertNull(configAttributeValueDo.getDeserializeValue().orElse(null), "空值反序列化异常");
    }

    @Test
    void testVarcharAttributeValueSerializeAndDeserialize() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNotNull(configAttributeValueDo.getValueType(), "数据类型为空");
        Assertions.assertEquals(TEST_VARCHAR_VALUE_TYPE, configAttributeValueDo.getValueType(), "值类型不正确");

        Assertions.assertNotNull(configAttributeValueDo.getSerializeValue().orElse(null), "序列化值为空");
        Assertions.assertEquals(TEST_VARCHAR_SERIALIZE_VALUE, configAttributeValueDo.getSerializeValue().orElse(null), "长整形序列化值不正确");

        Assertions.assertNotNull(configAttributeValueDo.getDeserializeValue().orElse(null), "反序列化值为空");
        Assertions.assertEquals(TEST_VARCHAR_DESERIALIZE_VALUE, configAttributeValueDo.getDeserializeValue().orElse(null), "长整形反序列化值为空");
    }

    @Test
    void testVarcharAttributeNullValue() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharNullValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNull(configAttributeValueDo.getSerializeValue().orElse(null), "空值序列化异常");
        Assertions.assertNull(configAttributeValueDo.getDeserializeValue().orElse(null), "空值反序列化异常");
    }

    @Test
    void testDatetimeAttributeValueSerializeAndDeserialize() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNotNull(configAttributeValueDo.getValueType(), "数据类型为空");
        Assertions.assertEquals(TEST_DATETIME_VALUE_TYPE, configAttributeValueDo.getValueType(), "值类型不正确");

        Assertions.assertNotNull(configAttributeValueDo.getSerializeValue().orElse(null), "序列化值为空");
        Assertions.assertEquals(TEST_DATETIME_SERIALIZE_VALUE, configAttributeValueDo.getSerializeValue().orElse(null), "长整形序列化值不正确");

        Assertions.assertNotNull(configAttributeValueDo.getDeserializeValue().orElse(null), "反序列化值为空");
        Assertions.assertEquals(TEST_DATETIME_DESERIALIZE_VALUE, configAttributeValueDo.getDeserializeValue().orElse(null), "长整形反序列化值为空");
    }

    @Test
    void testDatetimeAttributeNullValue() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeNullValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNull(configAttributeValueDo.getSerializeValue().orElse(null), "空值序列化异常");
        Assertions.assertNull(configAttributeValueDo.getDeserializeValue().orElse(null), "空值反序列化异常");
    }

    @Test
    void testDateAttributeValueSerializeAndDeserialize() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNotNull(configAttributeValueDo.getValueType(), "数据类型为空");
        Assertions.assertEquals(TEST_DATE_VALUE_TYPE, configAttributeValueDo.getValueType(), "值类型不正确");

        Assertions.assertNotNull(configAttributeValueDo.getSerializeValue().orElse(null), "序列化值为空");
        Assertions.assertEquals(TEST_DATE_SERIALIZE_VALUE, configAttributeValueDo.getSerializeValue().orElse(null), "长整形序列化值不正确");

        Assertions.assertNotNull(configAttributeValueDo.getDeserializeValue().orElse(null), "反序列化值为空");
        Assertions.assertEquals(TEST_DATE_DESERIALIZE_VALUE, configAttributeValueDo.getDeserializeValue().orElse(null), "长整形反序列化值为空");
    }

    @Test
    void testDateAttributeNullValue() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateNullValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNull(configAttributeValueDo.getSerializeValue().orElse(null), "空值序列化异常");
        Assertions.assertNull(configAttributeValueDo.getDeserializeValue().orElse(null), "空值反序列化异常");
    }

    @Test
    void testTimeAttributeValueSerializeAndDeserialize() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNotNull(configAttributeValueDo.getValueType(), "数据类型为空");
        Assertions.assertEquals(TEST_TIME_VALUE_TYPE, configAttributeValueDo.getValueType(), "值类型不正确");

        Assertions.assertNotNull(configAttributeValueDo.getSerializeValue().orElse(null), "序列化值为空");
        Assertions.assertEquals(TEST_TIME_SERIALIZE_VALUE, configAttributeValueDo.getSerializeValue().orElse(null), "长整形序列化值不正确");

        Assertions.assertNotNull(configAttributeValueDo.getDeserializeValue().orElse(null), "反序列化值为空");
        Assertions.assertEquals(TEST_TIME_DESERIALIZE_VALUE, configAttributeValueDo.getDeserializeValue().orElse(null), "长整形反序列化值为空");
    }

    @Test
    void testTimeAttributeNullValue() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeNullValueResult();

        ConfigAttributeValueDo configAttributeValueDo = new ConfigAttributeValueDo(configAttributeValueSelectResult);

        Assertions.assertNull(configAttributeValueDo.getSerializeValue().orElse(null), "空值序列化异常");
        Assertions.assertNull(configAttributeValueDo.getDeserializeValue().orElse(null), "空值反序列化异常");
    }

}
