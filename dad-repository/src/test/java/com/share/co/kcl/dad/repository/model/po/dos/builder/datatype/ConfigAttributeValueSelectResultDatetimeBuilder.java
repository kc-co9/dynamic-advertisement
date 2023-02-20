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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConfigAttributeValueSelectResultDatetimeBuilder extends ConfigAttributeValueSelectResultBuilder {

    public static final MetadataValueType TEST_DATETIME_VALUE_TYPE = MetadataValueType.DATETIME;
    public static final String TEST_DATETIME_DESERIALIZE_VALUE = "2023-02-01 00:00:00";
    public static final Long TEST_DATETIME_SERIALIZE_VALUE = DateUtils.valueOfSecond(
            LocalDateTime.parse(TEST_DATETIME_DESERIALIZE_VALUE, DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME)));
    private static final String TEST_DATETIME_GROOVY_RULE =
            "def check(Object value, String valueType){\n" +
                    "if(valueType=='DATETIME'){\n" +
                    "    if(value == '2023-02-01 00:00:00'){\n" +
                    "        return true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    return false;\n" +
                    "}\n" +
                    "check(value,valueType);";

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueResult() {
        ConfigAttributeValueSelectResult<?> commonValueResult = buildCommonValueResult();
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = new ConfigAttributeValueSelectResult<>();
        BeanUtils.copyProperties(commonValueResult, configAttributeValueSelectResult);
        configAttributeValueSelectResult.setValueType(TEST_DATETIME_VALUE_TYPE);
        configAttributeValueSelectResult.setValue(TEST_DATETIME_SERIALIZE_VALUE);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeNullValueResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDatetimeValueResult();
        configAttributeValueSelectResult.setIsNull(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDatetimeValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(TEST_DATETIME_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueNotRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDatetimeValueResult();
        configAttributeValueRequiredResult.setIsRequired(false);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueRequiredFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDatetimeValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }


    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDatetimeValueLimitValueResult();
        configAttributeValueSelectResult.setValue(
                DateUtils.valueOfSecond(LocalDateTime.parse("2023-02-20 12:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueLimitFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDatetimeValueLimitValueResult();
        configAttributeValueSelectResult.setValue(
                DateUtils.valueOfSecond(LocalDateTime.parse("2023-02-22 23:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueNotLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDatetimeValueLimitValueResult();
        configAttributeValueSelectResult.setValueLimit(false);
        configAttributeValueSelectResult.setValue(
                DateUtils.valueOfSecond(LocalDateTime.parse("2023-02-22 23:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))));
        return configAttributeValueSelectResult;
    }

    private static ConfigAttributeValueSelectResult<Long> buildDatetimeValueLimitValueResult() {
        List<MetadataValueRangeDo.ValueRange> valueRangeList = new ArrayList<>();

        MetadataValueRangeDo.ValueRange valueRange1 = new MetadataValueRangeDo.ValueRange();
        valueRange1.setStart(
                DateUtils.valueOfSecond(LocalDateTime.parse("2023-02-20 00:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))));
        valueRange1.setEnd(
                DateUtils.valueOfSecond(LocalDateTime.parse("2023-02-22 12:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))));
        valueRangeList.add(valueRange1);

        MetadataValueRangeDo.ValueRange valueRange2 = new MetadataValueRangeDo.ValueRange();
        valueRange2.setStart(
                DateUtils.valueOfSecond(LocalDateTime.parse("2023-02-27 00:00:00", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))));
        valueRange2.setEnd(
                DateUtils.valueOfSecond(LocalDateTime.parse("2023-02-28 23:59:59", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))));
        valueRangeList.add(valueRange2);

        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDatetimeValueResult();
        configAttributeValueSelectResult.setValueLimit(true);
        configAttributeValueSelectResult.setValueRange(JSON.toJSONString(new MetadataValueRangeDo<>(valueRangeList)));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueFormatSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDatetimeValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$");
        configAttributeValueRequiredResult.setValue(DateUtils.valueOfSecond(LocalDateTime.now()));
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueFormatFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDatetimeValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[1-8]+");
        configAttributeValueRequiredResult.setValue(999L);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueValidatedSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDatetimeValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_DATETIME_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(TEST_DATETIME_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDatetimeValueValidatedFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDatetimeValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_DATETIME_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(
                DateUtils.valueOfSecond(LocalDateTime.parse("2023-02-28 23:59:59", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME))));
        return configAttributeValueRequiredResult;
    }
}
