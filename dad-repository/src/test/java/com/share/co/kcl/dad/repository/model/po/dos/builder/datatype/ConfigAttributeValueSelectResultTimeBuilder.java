package com.share.co.kcl.dad.repository.model.po.dos.builder.datatype;

import com.alibaba.fastjson2.JSON;
import com.share.co.kcl.dad.common.utils.DateUtils;
import com.share.co.kcl.dad.repository.model.domain.MetadataValueRangeDo;
import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.builder.ConfigAttributeValueSelectResultBuilder;
import org.springframework.beans.BeanUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConfigAttributeValueSelectResultTimeBuilder extends ConfigAttributeValueSelectResultBuilder {
    public static final MetadataValueType TEST_TIME_VALUE_TYPE = MetadataValueType.TIME;
    public static final String TEST_TIME_DESERIALIZE_VALUE = "12:00:00";
    public static final Long TEST_TIME_SERIALIZE_VALUE = DateUtils.valueOfSecond(
            LocalTime.parse(TEST_TIME_DESERIALIZE_VALUE, DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME)));
    private static final String TEST_TIME_GROOVY_RULE =
            "def check(Object value, String valueType){\n" +
                    "if(valueType=='TIME'){\n" +
                    "    if(value == '12:00:00'){\n" +
                    "        return true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    return false;\n" +
                    "}\n" +
                    "check(value,valueType);";

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueResult() {
        ConfigAttributeValueSelectResult<?> commonValueResult = buildCommonValueResult();
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = new ConfigAttributeValueSelectResult<>();
        BeanUtils.copyProperties(commonValueResult, configAttributeValueSelectResult);
        configAttributeValueSelectResult.setValueType(TEST_TIME_VALUE_TYPE);
        configAttributeValueSelectResult.setValue(TEST_TIME_SERIALIZE_VALUE);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeNullValueResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildTimeValueResult();
        configAttributeValueSelectResult.setIsNull(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildTimeValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(TEST_TIME_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueNotRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildTimeValueResult();
        configAttributeValueRequiredResult.setIsRequired(false);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueRequiredFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildTimeValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildTimeValueLimitValueResult();
        configAttributeValueSelectResult.setValue(
                DateUtils.valueOfSecond(LocalTime.parse("11:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueLimitFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildTimeValueLimitValueResult();
        configAttributeValueSelectResult.setValue(
                DateUtils.valueOfSecond(LocalTime.parse("13:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueNotLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildTimeValueLimitValueResult();
        configAttributeValueSelectResult.setValueLimit(false);
        configAttributeValueSelectResult.setValue(
                DateUtils.valueOfSecond(LocalTime.parse("13:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))));
        return configAttributeValueSelectResult;
    }

    private static ConfigAttributeValueSelectResult<Long> buildTimeValueLimitValueResult() {
        List<MetadataValueRangeDo.ValueRange> valueRangeList = new ArrayList<>();

        MetadataValueRangeDo.ValueRange valueRange1 = new MetadataValueRangeDo.ValueRange();
        valueRange1.setStart(
                DateUtils.valueOfSecond(LocalTime.parse("01:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))));
        valueRange1.setEnd(
                DateUtils.valueOfSecond(LocalTime.parse("12:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))));
        valueRangeList.add(valueRange1);

        MetadataValueRangeDo.ValueRange valueRange2 = new MetadataValueRangeDo.ValueRange();
        valueRange2.setStart(
                DateUtils.valueOfSecond(LocalTime.parse("18:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))));
        valueRange2.setEnd(
                DateUtils.valueOfSecond(LocalTime.parse("20:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))));
        valueRangeList.add(valueRange2);

        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildTimeValueResult();
        configAttributeValueSelectResult.setValueLimit(true);
        configAttributeValueSelectResult.setValueRange(JSON.toJSONString(new MetadataValueRangeDo<>(valueRangeList)));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueFormatSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildTimeValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("^(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$");
        configAttributeValueRequiredResult.setValue(DateUtils.valueOfSecond(LocalTime.now()));
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueFormatFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildTimeValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[1-8]+");
        configAttributeValueRequiredResult.setValue(999L);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueValidatedSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildTimeValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_TIME_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(TEST_TIME_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildTimeValueValidatedFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildTimeValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_TIME_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(
                DateUtils.valueOfSecond(LocalTime.parse("20:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME))));
        return configAttributeValueRequiredResult;
    }
}
