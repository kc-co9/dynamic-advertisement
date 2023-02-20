package com.share.co.kcl.dad.repository.model.domain;

import com.share.co.kcl.dad.common.exception.ToastException;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ConfigAttributeValueDoLimitTest {

    @Test
    void testIntAttributeValueLimit() {
        ConfigAttributeValueSelectResult<Integer> configLimitValueSuccessResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueLimitSuccessResult();
        ConfigAttributeValueDo configLimitValueSuccessDo = new ConfigAttributeValueDo(configLimitValueSuccessResult);
        Assertions.assertDoesNotThrow(configLimitValueSuccessDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Integer> configLimitValueFailureResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueLimitFailureResult();
        ConfigAttributeValueDo configLimitValueFailureResultDo = new ConfigAttributeValueDo(configLimitValueFailureResult);
        Assertions.assertThrows(ToastException.class, configLimitValueFailureResultDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Integer> configNotLimitValueResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueNotLimitSuccessResult();
        ConfigAttributeValueDo configNotLimitValueDo = new ConfigAttributeValueDo(configNotLimitValueResult);
        Assertions.assertDoesNotThrow(configNotLimitValueDo::validatedAttributeRange);
    }

    @Test
    void testLongAttributeValueLimit() {
        ConfigAttributeValueSelectResult<Long> configLimitValueSuccessResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueLimitSuccessResult();
        ConfigAttributeValueDo configLimitValueSuccessDo = new ConfigAttributeValueDo(configLimitValueSuccessResult);
        Assertions.assertDoesNotThrow(configLimitValueSuccessDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Long> configLimitValueFailureResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueLimitFailureResult();
        ConfigAttributeValueDo configLimitValueFailureResultDo = new ConfigAttributeValueDo(configLimitValueFailureResult);
        Assertions.assertThrows(ToastException.class, configLimitValueFailureResultDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Long> configNotLimitValueResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueNotLimitSuccessResult();
        ConfigAttributeValueDo configNotLimitValueDo = new ConfigAttributeValueDo(configNotLimitValueResult);
        Assertions.assertDoesNotThrow(configNotLimitValueDo::validatedAttributeRange);
    }

    @Test
    void testBooleanAttributeValueLimit() {
        ConfigAttributeValueSelectResult<Integer> configLimitValueSuccessResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanValueLimitValueResult();
        ConfigAttributeValueDo configLimitValueSuccessDo = new ConfigAttributeValueDo(configLimitValueSuccessResult);
        Assertions.assertDoesNotThrow(configLimitValueSuccessDo::validatedAttributeRange);
    }

    @Test
    void testVarcharAttributeValueLimit() {
        ConfigAttributeValueSelectResult<String> configLimitValueSuccessResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharValueLimitValueResult();
        ConfigAttributeValueDo configLimitValueSuccessDo = new ConfigAttributeValueDo(configLimitValueSuccessResult);
        Assertions.assertDoesNotThrow(configLimitValueSuccessDo::validatedAttributeRange);
    }

    @Test
    void testDatetimeAttributeValueLimit() {
        ConfigAttributeValueSelectResult<Long> configLimitValueSuccessResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueLimitSuccessResult();
        ConfigAttributeValueDo configLimitValueSuccessDo = new ConfigAttributeValueDo(configLimitValueSuccessResult);
        Assertions.assertDoesNotThrow(configLimitValueSuccessDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Long> configLimitValueFailureResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueLimitFailureResult();
        ConfigAttributeValueDo configLimitValueFailureResultDo = new ConfigAttributeValueDo(configLimitValueFailureResult);
        Assertions.assertThrows(ToastException.class, configLimitValueFailureResultDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Long> configNotLimitValueResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueNotLimitSuccessResult();
        ConfigAttributeValueDo configNotLimitValueDo = new ConfigAttributeValueDo(configNotLimitValueResult);
        Assertions.assertDoesNotThrow(configNotLimitValueDo::validatedAttributeRange);
    }

    @Test
    void testDateAttributeValueLimit() {
        ConfigAttributeValueSelectResult<Long> configLimitValueSuccessResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueLimitSuccessResult();
        ConfigAttributeValueDo configLimitValueSuccessDo = new ConfigAttributeValueDo(configLimitValueSuccessResult);
        Assertions.assertDoesNotThrow(configLimitValueSuccessDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Long> configLimitValueFailureResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueLimitFailureResult();
        ConfigAttributeValueDo configLimitValueFailureResultDo = new ConfigAttributeValueDo(configLimitValueFailureResult);
        Assertions.assertThrows(ToastException.class, configLimitValueFailureResultDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Long> configNotLimitValueResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueNotLimitSuccessResult();
        ConfigAttributeValueDo configNotLimitValueDo = new ConfigAttributeValueDo(configNotLimitValueResult);
        Assertions.assertDoesNotThrow(configNotLimitValueDo::validatedAttributeRange);
    }

    @Test
    void testTimeAttributeValueLimit() {
        ConfigAttributeValueSelectResult<Long> configLimitValueSuccessResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueLimitSuccessResult();
        ConfigAttributeValueDo configLimitValueSuccessDo = new ConfigAttributeValueDo(configLimitValueSuccessResult);
        Assertions.assertDoesNotThrow(configLimitValueSuccessDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Long> configLimitValueFailureResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueLimitFailureResult();
        ConfigAttributeValueDo configLimitValueFailureResultDo = new ConfigAttributeValueDo(configLimitValueFailureResult);
        Assertions.assertThrows(ToastException.class, configLimitValueFailureResultDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<Long> configNotLimitValueResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueNotLimitSuccessResult();
        ConfigAttributeValueDo configNotLimitValueDo = new ConfigAttributeValueDo(configNotLimitValueResult);
        Assertions.assertDoesNotThrow(configNotLimitValueDo::validatedAttributeRange);
    }

    @Test
    void testEnumAttributeValueLimit() {
        ConfigAttributeValueSelectResult<String> configLimitValueSuccessResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueLimitSuccessResult();
        ConfigAttributeValueDo configLimitValueSuccessDo = new ConfigAttributeValueDo(configLimitValueSuccessResult);
        Assertions.assertDoesNotThrow(configLimitValueSuccessDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<String> configLimitValueFailureResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueLimitFailureResult();
        ConfigAttributeValueDo configLimitValueFailureResultDo = new ConfigAttributeValueDo(configLimitValueFailureResult);
        Assertions.assertThrows(ToastException.class, configLimitValueFailureResultDo::validatedAttributeRange);

        ConfigAttributeValueSelectResult<String> configNotLimitValueResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueNotLimitSuccessResult();
        ConfigAttributeValueDo configNotLimitValueDo = new ConfigAttributeValueDo(configNotLimitValueResult);
        Assertions.assertDoesNotThrow(configNotLimitValueDo::validatedAttributeRange);
    }

}
