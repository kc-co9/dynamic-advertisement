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

public class ConfigAttributeValueSelectResultIntBuilder extends ConfigAttributeValueSelectResultBuilder {

    public static final MetadataValueType TEST_INTEGER_VALUE_TYPE = MetadataValueType.INT;
    public static final Integer TEST_INTEGER_SERIALIZE_VALUE = 100;
    public static final Integer TEST_INTEGER_DESERIALIZE_VALUE = 100;
    private static final String TEST_INTEGER_GROOVY_RULE =
            "def check(Object value, String valueType){\n" +
                    "if(valueType=='INT'){\n" +
                    "    if(value == 100){\n" +
                    "        return true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    return false;\n" +
                    "}\n" +
                    "check(value,valueType);";


    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueResult() {
        ConfigAttributeValueSelectResult<?> commonValueResult = buildCommonValueResult();
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult = new ConfigAttributeValueSelectResult<>();
        BeanUtils.copyProperties(commonValueResult, configAttributeValueSelectResult);
        configAttributeValueSelectResult.setValueType(TEST_INTEGER_VALUE_TYPE);
        configAttributeValueSelectResult.setValue(TEST_INTEGER_SERIALIZE_VALUE);
        return configAttributeValueSelectResult;
    }


    public static ConfigAttributeValueSelectResult<Integer> buildIntegerNullValueResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult = buildIntegerValueResult();
        configAttributeValueSelectResult.setIsNull(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(TEST_INTEGER_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueNotRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueResult();
        configAttributeValueRequiredResult.setIsRequired(false);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueRequiredFailureResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult = buildIntegerValueLimitValueResult();
        configAttributeValueSelectResult.setValue(100);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueLimitFailureResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult = buildIntegerValueLimitValueResult();
        configAttributeValueSelectResult.setValue(200);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueNotLimitSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult = buildIntegerValueLimitValueResult();
        configAttributeValueSelectResult.setValueLimit(false);
        configAttributeValueSelectResult.setValue(200);
        return configAttributeValueSelectResult;
    }

    private static ConfigAttributeValueSelectResult<Integer> buildIntegerValueLimitValueResult() {
        List<MetadataValueRangeDo.ValueRange> valueRangeList = new ArrayList<>();

        MetadataValueRangeDo.ValueRange valueRange1 = new MetadataValueRangeDo.ValueRange();
        valueRange1.setStart(1);
        valueRange1.setEnd(100);
        valueRangeList.add(valueRange1);

        MetadataValueRangeDo.ValueRange valueRange2 = new MetadataValueRangeDo.ValueRange();
        valueRange2.setStart(1000);
        valueRange2.setEnd(10000);
        valueRangeList.add(valueRange2);

        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult = buildIntegerValueResult();
        configAttributeValueSelectResult.setValueLimit(true);
        configAttributeValueSelectResult.setValueRange(JSON.toJSONString(new MetadataValueRangeDo<>(valueRangeList)));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueFormatSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[9]+");
        configAttributeValueRequiredResult.setValue(999);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueFormatFailureResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[1-8]+");
        configAttributeValueRequiredResult.setValue(999);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueValidatedSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_INTEGER_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(TEST_INTEGER_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildIntegerValueValidatedFailureResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_INTEGER_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(9);
        return configAttributeValueRequiredResult;
    }


}
