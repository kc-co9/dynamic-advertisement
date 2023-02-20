package com.share.co.kcl.dad.repository.model.po.dos.builder.datatype;

import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.builder.ConfigAttributeValueSelectResultBuilder;
import org.springframework.beans.BeanUtils;

public class ConfigAttributeValueSelectResultBooleanBuilder extends ConfigAttributeValueSelectResultBuilder {

    public static final MetadataValueType TEST_BOOLEAN_VALUE_TYPE = MetadataValueType.BOOLEAN;
    public static final Integer TEST_BOOLEAN_SERIALIZE_VALUE = 1;
    public static final Boolean TEST_BOOLEAN_DESERIALIZE_VALUE = Boolean.TRUE;
    private static final String TEST_BOOLEAN_GROOVY_RULE =
            "def check(Object value, String valueType){\n" +
                    "if(valueType=='BOOLEAN'){\n" +
                    "    if(value == true){\n" +
                    "        return true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    return false;\n" +
                    "}\n" +
                    "check(value,valueType);";

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanValueResult() {
        ConfigAttributeValueSelectResult<?> commonValueResult = buildCommonValueResult();
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult = new ConfigAttributeValueSelectResult<>();
        BeanUtils.copyProperties(commonValueResult, configAttributeValueSelectResult);
        configAttributeValueSelectResult.setValueType(TEST_BOOLEAN_VALUE_TYPE);
        configAttributeValueSelectResult.setValue(TEST_BOOLEAN_SERIALIZE_VALUE);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanNullValueResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult = buildBooleanValueResult();
        configAttributeValueSelectResult.setIsNull(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanValueRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult = buildBooleanValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(TEST_BOOLEAN_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanValueNotRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult = buildBooleanValueResult();
        configAttributeValueRequiredResult.setIsRequired(false);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanValueRequiredFailureResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult = buildBooleanValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanValueLimitValueResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueSelectResult = buildBooleanValueResult();
        configAttributeValueSelectResult.setValueLimit(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanValueFormatSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult = buildBooleanValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("true|false");
        configAttributeValueRequiredResult.setValue(1);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanValueFormatFailureResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult = buildBooleanValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[1-2]+");
        configAttributeValueRequiredResult.setValue(1);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanValueValidatedSuccessResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult = buildBooleanValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_BOOLEAN_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(TEST_BOOLEAN_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<Integer> buildBooleanValueValidatedFailureResult() {
        ConfigAttributeValueSelectResult<Integer> configAttributeValueRequiredResult = buildBooleanValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_BOOLEAN_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(0);
        return configAttributeValueRequiredResult;
    }

}
