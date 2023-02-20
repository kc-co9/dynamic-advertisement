package com.share.co.kcl.dad.repository.model.po.dos.builder;

import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;

import java.time.LocalDateTime;

public class ConfigAttributeValueSelectResultBuilder {

    public static final Long TEST_COMMON_ID = 1L;
    public static final String TEST_COMMON_KEY = "testKey";
    public static final String TEST_COMMON_NAME = "testName";
    public static final String TEST_COMMON_DESC = "testDesc";

    public static final Boolean TEST_COMMON_VALUE_IS_NULL = false;
    public static final Boolean TEST_COMMON_VALUE_LIMIT = false;
    public static final String TEST_COMMON_VALUE_RANGE = "";
    public static final MetadataFormatType TEST_COMMON_VALUE_FORMAT_TYPE = MetadataFormatType.NONE;
    public static final String TEST_COMMON_VALUE_FORMAT_RULE = "";
    public static final Boolean TEST_COMMON_VALUE_IS_REQUIRED = true;
    public static final MetadataValidatedType TEST_COMMON_VALUE_VALIDATED_TYPE = MetadataValidatedType.NONE;
    public static final String TEST_COMMON_VALUE_VALIDATED_RULE = "";
    public static final LocalDateTime TEST_COMMON_CREATE_TIME = LocalDateTime.now();
    public static final LocalDateTime TEST_COMMON_UPDATE_TIME = LocalDateTime.now();

    public static ConfigAttributeValueSelectResult<?> buildCommonValueResult() {
        ConfigAttributeValueSelectResult<?> configAttributeValueSelectResult = new ConfigAttributeValueSelectResult<>();
        configAttributeValueSelectResult.setId(TEST_COMMON_ID);
        configAttributeValueSelectResult.setKey(TEST_COMMON_KEY);
        configAttributeValueSelectResult.setName(TEST_COMMON_NAME);
        configAttributeValueSelectResult.setDescription(TEST_COMMON_DESC);
        configAttributeValueSelectResult.setIsNull(TEST_COMMON_VALUE_IS_NULL);
        configAttributeValueSelectResult.setValueLimit(TEST_COMMON_VALUE_LIMIT);
        configAttributeValueSelectResult.setValueRange(TEST_COMMON_VALUE_RANGE);
        configAttributeValueSelectResult.setFormatType(TEST_COMMON_VALUE_FORMAT_TYPE);
        configAttributeValueSelectResult.setFormatRule(TEST_COMMON_VALUE_FORMAT_RULE);
        configAttributeValueSelectResult.setIsRequired(TEST_COMMON_VALUE_IS_REQUIRED);
        configAttributeValueSelectResult.setValidatedType(TEST_COMMON_VALUE_VALIDATED_TYPE);
        configAttributeValueSelectResult.setValidatedRule(TEST_COMMON_VALUE_VALIDATED_RULE);
        configAttributeValueSelectResult.setCreateTime(TEST_COMMON_CREATE_TIME);
        configAttributeValueSelectResult.setUpdateTime(TEST_COMMON_UPDATE_TIME);
        return configAttributeValueSelectResult;
    }
}
