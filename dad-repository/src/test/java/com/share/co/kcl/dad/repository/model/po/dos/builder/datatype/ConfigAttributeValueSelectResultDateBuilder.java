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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConfigAttributeValueSelectResultDateBuilder extends ConfigAttributeValueSelectResultBuilder {

    public static final MetadataValueType TEST_DATE_VALUE_TYPE = MetadataValueType.DATE;
    public static final String TEST_DATE_DESERIALIZE_VALUE = "2023-02-01";
    public static final Long TEST_DATE_SERIALIZE_VALUE = DateUtils.valueOfSecond(
            LocalDate.parse(TEST_DATE_DESERIALIZE_VALUE, DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE)));
    private static final String TEST_DATE_GROOVY_RULE =
            "def check(Object value, String valueType){\n" +
                    "if(valueType=='DATE'){\n" +
                    "    if(value == '2023-02-01'){\n" +
                    "        return true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    return false;\n" +
                    "}\n" +
                    "check(value,valueType);";

    public static ConfigAttributeValueSelectResult<Long> buildDateValueResult() {
        ConfigAttributeValueSelectResult<?> commonValueResult = buildCommonValueResult();
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = new ConfigAttributeValueSelectResult<>();
        BeanUtils.copyProperties(commonValueResult, configAttributeValueSelectResult);
        configAttributeValueSelectResult.setValueType(TEST_DATE_VALUE_TYPE);
        configAttributeValueSelectResult.setValue(TEST_DATE_SERIALIZE_VALUE);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateNullValueResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDateValueResult();
        configAttributeValueSelectResult.setIsNull(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDateValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(TEST_DATE_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueNotRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDateValueResult();
        configAttributeValueRequiredResult.setIsRequired(false);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueRequiredFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDateValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDateValueLimitValueResult();
        configAttributeValueSelectResult.setValue(
                DateUtils.valueOfSecond(LocalDate.parse("2023-02-20", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueLimitFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDateValueLimitValueResult();
        configAttributeValueSelectResult.setValue(
                DateUtils.valueOfSecond(LocalDate.parse("2023-02-24", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueNotLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDateValueLimitValueResult();
        configAttributeValueSelectResult.setValueLimit(false);
        configAttributeValueSelectResult.setValue(
                DateUtils.valueOfSecond(LocalDate.parse("2023-02-24", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))));
        return configAttributeValueSelectResult;
    }

    private static ConfigAttributeValueSelectResult<Long> buildDateValueLimitValueResult() {
        List<MetadataValueRangeDo.ValueRange> valueRangeList = new ArrayList<>();

        MetadataValueRangeDo.ValueRange valueRange1 = new MetadataValueRangeDo.ValueRange();
        valueRange1.setStart(
                DateUtils.valueOfSecond(LocalDate.parse("2023-02-20", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))));
        valueRange1.setEnd(
                DateUtils.valueOfSecond(LocalDate.parse("2023-02-22", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))));
        valueRangeList.add(valueRange1);

        MetadataValueRangeDo.ValueRange valueRange2 = new MetadataValueRangeDo.ValueRange();
        valueRange2.setStart(
                DateUtils.valueOfSecond(LocalDate.parse("2023-02-27", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))));
        valueRange2.setEnd(
                DateUtils.valueOfSecond(LocalDate.parse("2023-02-28", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))));
        valueRangeList.add(valueRange2);

        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildDateValueResult();
        configAttributeValueSelectResult.setValueLimit(true);
        configAttributeValueSelectResult.setValueRange(JSON.toJSONString(new MetadataValueRangeDo<>(valueRangeList)));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueFormatSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDateValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$");
        configAttributeValueRequiredResult.setValue(DateUtils.valueOfSecond(LocalDate.now()));
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueFormatFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDateValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[1-8]+");
        configAttributeValueRequiredResult.setValue(System.currentTimeMillis() / 1000);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueValidatedSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDateValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_DATE_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(TEST_DATE_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildDateValueValidatedFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildDateValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_DATE_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(
                DateUtils.valueOfSecond(LocalDate.parse("2023-02-28", DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE))));
        return configAttributeValueRequiredResult;
    }
}
