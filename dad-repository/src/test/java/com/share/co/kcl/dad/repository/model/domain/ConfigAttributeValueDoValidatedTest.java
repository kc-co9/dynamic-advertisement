package com.share.co.kcl.dad.repository.model.domain;

import com.share.co.kcl.dad.common.exception.ToastException;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ConfigAttributeValueDoValidatedTest {

    @Test
    void testIntAttributeValueValidated() {
        ConfigAttributeValueSelectResult<Integer> configValidatedValueSuccessResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueValidatedSuccessResult();
        ConfigAttributeValueDo configValidatedValueSuccessDo = new ConfigAttributeValueDo(configValidatedValueSuccessResult);
        Assertions.assertDoesNotThrow(configValidatedValueSuccessDo::validatedAttributeBusiness);

        ConfigAttributeValueSelectResult<Integer> configValidatedValueFailureResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueValidatedFailureResult();
        ConfigAttributeValueDo configValidatedValueFailureDo = new ConfigAttributeValueDo(configValidatedValueFailureResult);
        Assertions.assertThrows(ToastException.class, configValidatedValueFailureDo::validatedAttributeBusiness);
    }

    @Test
    void testLongAttributeValueValidated() {
        ConfigAttributeValueSelectResult<Long> configValidatedValueSuccessResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueValidatedSuccessResult();
        ConfigAttributeValueDo configValidatedValueSuccessDo = new ConfigAttributeValueDo(configValidatedValueSuccessResult);
        Assertions.assertDoesNotThrow(configValidatedValueSuccessDo::validatedAttributeBusiness);

        ConfigAttributeValueSelectResult<Long> configValidatedValueFailureResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueValidatedFailureResult();
        ConfigAttributeValueDo configValidatedValueFailureDo = new ConfigAttributeValueDo(configValidatedValueFailureResult);
        Assertions.assertThrows(ToastException.class, configValidatedValueFailureDo::validatedAttributeBusiness);
    }

    @Test
    void testBooleanAttributeValueValidated() {
        ConfigAttributeValueSelectResult<Integer> configValidatedValueSuccessResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanValueValidatedSuccessResult();
        ConfigAttributeValueDo configValidatedValueSuccessDo = new ConfigAttributeValueDo(configValidatedValueSuccessResult);
        Assertions.assertDoesNotThrow(configValidatedValueSuccessDo::validatedAttributeBusiness);

        ConfigAttributeValueSelectResult<Integer> configValidatedValueFailureResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanValueValidatedFailureResult();
        ConfigAttributeValueDo configValidatedValueFailureDo = new ConfigAttributeValueDo(configValidatedValueFailureResult);
        Assertions.assertThrows(ToastException.class, configValidatedValueFailureDo::validatedAttributeBusiness);
    }

    @Test
    void testVarcharAttributeValueValidated() {
        ConfigAttributeValueSelectResult<String> configValidatedValueSuccessResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharValueValidatedSuccessResult();
        ConfigAttributeValueDo configValidatedValueSuccessDo = new ConfigAttributeValueDo(configValidatedValueSuccessResult);
        Assertions.assertDoesNotThrow(configValidatedValueSuccessDo::validatedAttributeBusiness);

        ConfigAttributeValueSelectResult<String> configValidatedValueFailureResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharValueValidatedFailureResult();
        ConfigAttributeValueDo configValidatedValueFailureDo = new ConfigAttributeValueDo(configValidatedValueFailureResult);
        Assertions.assertThrows(ToastException.class, configValidatedValueFailureDo::validatedAttributeBusiness);
    }

    @Test
    void testEnumAttributeValueValidated() {
        ConfigAttributeValueSelectResult<String> configValidatedValueSuccessResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueValidatedSuccessResult();
        ConfigAttributeValueDo configValidatedValueSuccessDo = new ConfigAttributeValueDo(configValidatedValueSuccessResult);
        Assertions.assertDoesNotThrow(configValidatedValueSuccessDo::validatedAttributeBusiness);

        ConfigAttributeValueSelectResult<String> configValidatedValueFailureResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueValidatedFailureResult();
        ConfigAttributeValueDo configValidatedValueFailureDo = new ConfigAttributeValueDo(configValidatedValueFailureResult);
        Assertions.assertThrows(ToastException.class, configValidatedValueFailureDo::validatedAttributeBusiness);
    }

    @Test
    void testDatetimeAttributeValueValidated() {
        ConfigAttributeValueSelectResult<Long> configValidatedValueSuccessResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueValidatedSuccessResult();
        ConfigAttributeValueDo configValidatedValueSuccessDo = new ConfigAttributeValueDo(configValidatedValueSuccessResult);
        Assertions.assertDoesNotThrow(configValidatedValueSuccessDo::validatedAttributeBusiness);

        ConfigAttributeValueSelectResult<Long> configValidatedValueFailureResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueValidatedFailureResult();
        ConfigAttributeValueDo configValidatedValueFailureDo = new ConfigAttributeValueDo(configValidatedValueFailureResult);
        Assertions.assertThrows(ToastException.class, configValidatedValueFailureDo::validatedAttributeBusiness);
    }

    @Test
    void testDateAttributeValueValidated() {
        ConfigAttributeValueSelectResult<Long> configValidatedValueSuccessResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueValidatedSuccessResult();
        ConfigAttributeValueDo configValidatedValueSuccessDo = new ConfigAttributeValueDo(configValidatedValueSuccessResult);
        Assertions.assertDoesNotThrow(configValidatedValueSuccessDo::validatedAttributeBusiness);

        ConfigAttributeValueSelectResult<Long> configValidatedValueFailureResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueValidatedFailureResult();
        ConfigAttributeValueDo configValidatedValueFailureDo = new ConfigAttributeValueDo(configValidatedValueFailureResult);
        Assertions.assertThrows(ToastException.class, configValidatedValueFailureDo::validatedAttributeBusiness);
    }

    @Test
    void testTimeAttributeValueValidated() {
        ConfigAttributeValueSelectResult<Long> configValidatedValueSuccessResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueValidatedSuccessResult();
        ConfigAttributeValueDo configValidatedValueSuccessDo = new ConfigAttributeValueDo(configValidatedValueSuccessResult);
        Assertions.assertDoesNotThrow(configValidatedValueSuccessDo::validatedAttributeBusiness);

        ConfigAttributeValueSelectResult<Long> configValidatedValueFailureResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueValidatedFailureResult();
        ConfigAttributeValueDo configValidatedValueFailureDo = new ConfigAttributeValueDo(configValidatedValueFailureResult);
        Assertions.assertThrows(ToastException.class, configValidatedValueFailureDo::validatedAttributeBusiness);
    }

}
