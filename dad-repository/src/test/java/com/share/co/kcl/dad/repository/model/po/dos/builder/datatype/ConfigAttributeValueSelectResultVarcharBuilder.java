package com.share.co.kcl.dad.repository.model.po.dos.builder.datatype;

import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.builder.ConfigAttributeValueSelectResultBuilder;
import org.springframework.beans.BeanUtils;

public class ConfigAttributeValueSelectResultVarcharBuilder extends ConfigAttributeValueSelectResultBuilder {

    public static final MetadataValueType TEST_VARCHAR_VALUE_TYPE = MetadataValueType.VARCHAR;
    public static final String TEST_VARCHAR_SERIALIZE_VALUE = "TEST_VALUE";
    public static final String TEST_VARCHAR_DESERIALIZE_VALUE = "TEST_VALUE";
    private static final String TEST_VARCHAR_GROOVY_RULE =
            "def check(Object value, String valueType){\n" +
                    "if(valueType=='VARCHAR'){\n" +
                    "    if(value == 'TEST_VALUE'){\n" +
                    "        return true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    return false;\n" +
                    "}\n" +
                    "check(value,valueType);";

    public static ConfigAttributeValueSelectResult<String> buildVarcharValueResult() {
        ConfigAttributeValueSelectResult<?> commonValueResult = buildCommonValueResult();
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult = new ConfigAttributeValueSelectResult<>();
        BeanUtils.copyProperties(commonValueResult, configAttributeValueSelectResult);
        configAttributeValueSelectResult.setValueType(TEST_VARCHAR_VALUE_TYPE);
        configAttributeValueSelectResult.setValue(TEST_VARCHAR_SERIALIZE_VALUE);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildVarcharNullValueResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult = buildVarcharValueResult();
        configAttributeValueSelectResult.setIsNull(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildVarcharValueRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildVarcharValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(TEST_VARCHAR_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildVarcharValueNotRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildVarcharValueResult();
        configAttributeValueRequiredResult.setIsRequired(false);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildVarcharValueRequiredFailureResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildVarcharValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildVarcharValueLimitValueResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult = buildVarcharValueResult();
        configAttributeValueSelectResult.setValueLimit(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildVarcharValueFormatSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildVarcharValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[a-z]+");
        configAttributeValueRequiredResult.setValue("aaa");
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildVarcharValueFormatFailureResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildVarcharValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[a-z]+");
        configAttributeValueRequiredResult.setValue("---");
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildVarcharValueValidatedSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildVarcharValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_VARCHAR_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(TEST_VARCHAR_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildVarcharValueValidatedFailureResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildVarcharValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_VARCHAR_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue("ERROR");
        return configAttributeValueRequiredResult;
    }

}
