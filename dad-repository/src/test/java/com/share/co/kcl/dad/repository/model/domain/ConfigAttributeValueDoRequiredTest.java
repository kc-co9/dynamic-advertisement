package com.share.co.kcl.dad.repository.model.domain;

import com.share.co.kcl.dad.common.exception.ToastException;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ConfigAttributeValueDoRequiredTest {

    @Test
    void testIntAttributeValueRequired() {
        ConfigAttributeValueSelectResult<Integer> configRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueRequiredSuccessResult();
        ConfigAttributeValueDo configRequiredValueSuccessDo = new ConfigAttributeValueDo(configRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Integer> configNotRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueNotRequiredSuccessResult();
        ConfigAttributeValueDo configNotRequiredValueSuccessDo = new ConfigAttributeValueDo(configNotRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configNotRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Integer> configRequiredValueFailureResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueRequiredFailureResult();
        ConfigAttributeValueDo configRequiredValueFailureDo = new ConfigAttributeValueDo(configRequiredValueFailureResult);
        Assertions.assertThrows(ToastException.class, configRequiredValueFailureDo::validatedAttributeRequired);
    }

    @Test
    void testLongAttributeValueRequired() {
        ConfigAttributeValueSelectResult<Long> configRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueRequiredSuccessResult();
        ConfigAttributeValueDo configRequiredValueSuccessDo = new ConfigAttributeValueDo(configRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Long> configNotRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueNotRequiredSuccessResult();
        ConfigAttributeValueDo configNotRequiredValueSuccessDo = new ConfigAttributeValueDo(configNotRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configNotRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Long> configRequiredValueFailureResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueRequiredFailureResult();
        ConfigAttributeValueDo configRequiredValueFailureDo = new ConfigAttributeValueDo(configRequiredValueFailureResult);
        Assertions.assertThrows(ToastException.class, configRequiredValueFailureDo::validatedAttributeRequired);
    }

    @Test
    void testVarcharAttributeValueRequired() {
        ConfigAttributeValueSelectResult<String> configRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharValueRequiredSuccessResult();
        ConfigAttributeValueDo configRequiredValueSuccessDo = new ConfigAttributeValueDo(configRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<String> configNotRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharValueNotRequiredSuccessResult();
        ConfigAttributeValueDo configNotRequiredValueSuccessDo = new ConfigAttributeValueDo(configNotRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configNotRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<String> configRequiredValueFailureResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharValueRequiredFailureResult();
        ConfigAttributeValueDo configRequiredValueFailureDo = new ConfigAttributeValueDo(configRequiredValueFailureResult);
        Assertions.assertThrows(ToastException.class, configRequiredValueFailureDo::validatedAttributeRequired);
    }

    @Test
    void testBooleanAttributeValueRequired() {
        ConfigAttributeValueSelectResult<Integer> configRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanValueRequiredSuccessResult();
        ConfigAttributeValueDo configRequiredValueSuccessDo = new ConfigAttributeValueDo(configRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Integer> configNotRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanValueNotRequiredSuccessResult();
        ConfigAttributeValueDo configNotRequiredValueSuccessDo = new ConfigAttributeValueDo(configNotRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configNotRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Integer> configRequiredValueFailureResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanValueRequiredFailureResult();
        ConfigAttributeValueDo configRequiredValueFailureDo = new ConfigAttributeValueDo(configRequiredValueFailureResult);
        Assertions.assertThrows(ToastException.class, configRequiredValueFailureDo::validatedAttributeRequired);
    }

    @Test
    void testEnumAttributeValueRequired() {
        ConfigAttributeValueSelectResult<String> configRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueRequiredSuccessResult();
        ConfigAttributeValueDo configRequiredValueSuccessDo = new ConfigAttributeValueDo(configRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<String> configNotRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueNotRequiredSuccessResult();
        ConfigAttributeValueDo configNotRequiredValueSuccessDo = new ConfigAttributeValueDo(configNotRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configNotRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<String> configRequiredValueFailureResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueRequiredFailureResult();
        ConfigAttributeValueDo configRequiredValueFailureDo = new ConfigAttributeValueDo(configRequiredValueFailureResult);
        Assertions.assertThrows(ToastException.class, configRequiredValueFailureDo::validatedAttributeRequired);
    }

    @Test
    void testDatetimeAttributeValueRequired() {
        ConfigAttributeValueSelectResult<Long> configRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueRequiredSuccessResult();
        ConfigAttributeValueDo configRequiredValueSuccessDo = new ConfigAttributeValueDo(configRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Long> configNotRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueNotRequiredSuccessResult();
        ConfigAttributeValueDo configNotRequiredValueSuccessDo = new ConfigAttributeValueDo(configNotRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configNotRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Long> configRequiredValueFailureResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueRequiredFailureResult();
        ConfigAttributeValueDo configRequiredValueFailureDo = new ConfigAttributeValueDo(configRequiredValueFailureResult);
        Assertions.assertThrows(ToastException.class, configRequiredValueFailureDo::validatedAttributeRequired);
    }

    @Test
    void testDateAttributeValueRequired() {
        ConfigAttributeValueSelectResult<Long> configRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueRequiredSuccessResult();
        ConfigAttributeValueDo configRequiredValueSuccessDo = new ConfigAttributeValueDo(configRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Long> configNotRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueNotRequiredSuccessResult();
        ConfigAttributeValueDo configNotRequiredValueSuccessDo = new ConfigAttributeValueDo(configNotRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configNotRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Long> configRequiredValueFailureResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueRequiredFailureResult();
        ConfigAttributeValueDo configRequiredValueFailureDo = new ConfigAttributeValueDo(configRequiredValueFailureResult);
        Assertions.assertThrows(ToastException.class, configRequiredValueFailureDo::validatedAttributeRequired);
    }

    @Test
    void testTimeAttributeValueRequired() {
        ConfigAttributeValueSelectResult<Long> configRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueRequiredSuccessResult();
        ConfigAttributeValueDo configRequiredValueSuccessDo = new ConfigAttributeValueDo(configRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Long> configNotRequiredValueSuccessResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueNotRequiredSuccessResult();
        ConfigAttributeValueDo configNotRequiredValueSuccessDo = new ConfigAttributeValueDo(configNotRequiredValueSuccessResult);
        Assertions.assertDoesNotThrow(configNotRequiredValueSuccessDo::validatedAttributeRequired);

        ConfigAttributeValueSelectResult<Long> configRequiredValueFailureResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueRequiredFailureResult();
        ConfigAttributeValueDo configRequiredValueFailureDo = new ConfigAttributeValueDo(configRequiredValueFailureResult);
        Assertions.assertThrows(ToastException.class, configRequiredValueFailureDo::validatedAttributeRequired);
    }

}
