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

public class ConfigAttributeValueSelectResultEnumBuilder extends ConfigAttributeValueSelectResultBuilder {

    public static final MetadataValueType TEST_ENUM_VALUE_TYPE = MetadataValueType.ENUM;
    public static final String TEST_ENUM_SERIALIZE_VALUE = "TEST_ENUM";
    public static final String TEST_ENUM_DESERIALIZE_VALUE = "TEST_ENUM";

    private static final String TEST_ENUM_GROOVY_RULE =
            "def check(Object value, String valueType){\n" +
                    "if(valueType=='ENUM'){\n" +
                    "    if(value == 'TEST_ENUM'){\n" +
                    "        return true;\n" +
                    "      }\n" +
                    "    }\n" +
                    "    return false;\n" +
                    "}\n" +
                    "check(value,valueType);";

    public static ConfigAttributeValueSelectResult<String> buildEnumValueResult() {
        ConfigAttributeValueSelectResult<?> commonValueResult = buildCommonValueResult();
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult = new ConfigAttributeValueSelectResult<>();
        BeanUtils.copyProperties(commonValueResult, configAttributeValueSelectResult);
        configAttributeValueSelectResult.setValueType(TEST_ENUM_VALUE_TYPE);
        configAttributeValueSelectResult.setValue(TEST_ENUM_SERIALIZE_VALUE);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumNullValueResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult = buildEnumValueResult();
        configAttributeValueSelectResult.setIsNull(true);
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildEnumValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(TEST_ENUM_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueNotRequiredSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildEnumValueResult();
        configAttributeValueRequiredResult.setIsRequired(false);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueRequiredFailureResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildEnumValueResult();
        configAttributeValueRequiredResult.setIsRequired(true);
        configAttributeValueRequiredResult.setValue(null);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueLimitSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult = buildEnumValueLimitValueResult();
        configAttributeValueSelectResult.setValue("TEST_ENUM1");
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueLimitFailureResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult = buildEnumValueLimitValueResult();
        configAttributeValueSelectResult.setValue("TEST_ENUM_NOT_EXIST");
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueNotLimitSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult = buildEnumValueLimitValueResult();
        configAttributeValueSelectResult.setValueLimit(false);
        configAttributeValueSelectResult.setValue("TEST_ENUM_NOT_EXIST");
        return configAttributeValueSelectResult;
    }

    private static ConfigAttributeValueSelectResult<String> buildEnumValueLimitValueResult() {
        List<MetadataValueRangeDo.OptionRange> optionRangeList = new ArrayList<>();

        MetadataValueRangeDo.OptionRange optionRange1 = new MetadataValueRangeDo.OptionRange();
        optionRange1.setCode("TEST_ENUM1");
        optionRange1.setName("枚举1");
        optionRangeList.add(optionRange1);

        MetadataValueRangeDo.OptionRange optionRange2 = new MetadataValueRangeDo.OptionRange();
        optionRange2.setCode("TEST_ENUM2");
        optionRange2.setName("枚举2");
        optionRangeList.add(optionRange2);

        ConfigAttributeValueSelectResult<String> configAttributeValueSelectResult = buildEnumValueResult();
        configAttributeValueSelectResult.setValueLimit(true);
        configAttributeValueSelectResult.setValueRange(JSON.toJSONString(new MetadataValueRangeDo<>(optionRangeList)));
        return configAttributeValueSelectResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueFormatSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildEnumValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[a-z]+");
        configAttributeValueRequiredResult.setValue("aaa");
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueFormatFailureResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildEnumValueResult();
        configAttributeValueRequiredResult.setFormatType(MetadataFormatType.REGULAR_EXPRESSION);
        configAttributeValueRequiredResult.setFormatRule("[a-z]+");
        configAttributeValueRequiredResult.setValue("---");
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueValidatedSuccessResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildEnumValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_ENUM_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue(TEST_ENUM_SERIALIZE_VALUE);
        return configAttributeValueRequiredResult;
    }

    public static ConfigAttributeValueSelectResult<String> buildEnumValueValidatedFailureResult() {
        ConfigAttributeValueSelectResult<String> configAttributeValueRequiredResult = buildEnumValueResult();
        configAttributeValueRequiredResult.setValidatedType(MetadataValidatedType.GROOVY);
        configAttributeValueRequiredResult.setValidatedRule(TEST_ENUM_GROOVY_RULE);
        configAttributeValueRequiredResult.setValue("ERROR");
        return configAttributeValueRequiredResult;
    }


}
