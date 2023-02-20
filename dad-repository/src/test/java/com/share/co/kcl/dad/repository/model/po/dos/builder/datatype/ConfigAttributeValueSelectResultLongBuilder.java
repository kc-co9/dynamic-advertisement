package com.share.co.kcl.dad.repository.model.po.dos.builder.datatype;

import com.alibaba.fastjson2.JSON;
import com.share.co.kcl.dad.repository.model.domain.MetadataValueRangeDo;
import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.builder.ConfigAttributeValueSelectResultBuilder;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigAttributeValueSelectResultLongBuilder extends ConfigAttributeValueSelectResultBuilder {

    public static final MetadataValueType TEST_LONG_VALUE_TYPE = MetadataValueType.LONG;
    public static final Long TEST_LONG_SERIALIZE_VALUE = 10000L;
    public static final Long TEST_LONG_DESERIALIZE_VALUE = 10000L;
    private static final String TEST_LONG_GROOVY_RULE =
            "def check(Object value, String valueType){\n" +
                    "if(valueType=='LONG'){\n" +
                    "    if(value == 10000){\n" +
                    "        return true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    return false;\n" +
                    "}\n" +
                    "check(value,valueType);";

    public static ConfigAttributeValueSelectResult<Long> buildLongValueResult() {
        ConfigAttributeValueSelectResult<?> commonValueResult = buildCommonValueResult();
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = new ConfigAttributeValueSelectResult<>();
        BeanUtils.copyProperties(commonValueResult, configAttributeValueSelectResult);
        configAttributeValueSelectResult.setValueType(TEST_LONG_VALUE_TYPE);
        configAttributeValueSelectResult.setValue(TEST_LONG_SERIALIZE_VALUE);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongNullValueResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildLongValueResult();
        configAttributeValueSelectResult.setIsNull(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildLongValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(TEST_LONG_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueNotRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildLongValueResult();
        configAttributeValueRequiredResult.setIsRequired(false);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueRequiredFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildLongValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildLongValueLimitValueResult();
        configAttributeValueSelectResult.setValue(100L);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueLimitFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildLongValueLimitValueResult();
        configAttributeValueSelectResult.setValue(200L);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueNotLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildLongValueLimitValueResult();
        configAttributeValueSelectResult.setValueLimit(false);
        configAttributeValueSelectResult.setValue(200L);
        return configAttributeValueSelectResult;
    }

    private static ConfigAttributeValueSelectResult<Long> buildLongValueLimitValueResult() {
        List<MetadataValueRangeDo.ValueRange> valueRangeList = new ArrayList<>();

        MetadataValueRangeDo.ValueRange valueRange1 = new MetadataValueRangeDo.ValueRange();
        valueRange1.setStart(1);
        valueRange1.setEnd(100);
        valueRangeList.add(valueRange1);

        MetadataValueRangeDo.ValueRange valueRange2 = new MetadataValueRangeDo.ValueRange();
        valueRange2.setStart(1000);
        valueRange2.setEnd(10000);
        valueRangeList.add(valueRange2);

        ConfigAttributeValueSelectResult<Long> configAttributeValueSelectResult = buildLongValueResult();
        configAttributeValueSelectResult.setValueLimit(true);
        configAttributeValueSelectResult.setValueRange(JSON.toJSONString(new MetadataValueRangeDo<>(valueRangeList)));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueFormatSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildLongValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[9]+");
        configAttributeValueRequiredResult.setValue(999L);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueFormatFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildLongValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[1-8]+");
        configAttributeValueRequiredResult.setValue(999L);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueValidatedSuccessResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildLongValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_LONG_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(TEST_LONG_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Long> buildLongValueValidatedFailureResult() {
        ConfigAttributeValueSelectResult<Long> configAttributeValueRequiredResult = buildLongValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_LONG_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(9L);
        return configAttributeValueRequiredResult;
    }
}
