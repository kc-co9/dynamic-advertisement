package com.share.co.kcl.dad.repository.model.domain;

import com.share.co.kcl.dad.common.exception.ToastException;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.builder.datatype.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ConfigAttributeValueDoFormatTest {

    @Test
    void testIntAttributeValueFormat() {
        ConfigAttributeValueSelectResult<Integer> configFormatValueSuccessResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueFormatSuccessResult();
        ConfigAttributeValueDo configFormatValueSuccessDo = new ConfigAttributeValueDo(configFormatValueSuccessResult);
        Assertions.assertDoesNotThrow(configFormatValueSuccessDo::validatedAttributeFormat);

        ConfigAttributeValueSelectResult<Integer> configFormatValueFailureResult =
                ConfigAttributeValueSelectResultIntBuilder.buildIntegerValueFormatFailureResult();
        ConfigAttributeValueDo configFormatValueFailureDo = new ConfigAttributeValueDo(configFormatValueFailureResult);
        Assertions.assertThrows(ToastException.class, configFormatValueFailureDo::validatedAttributeFormat);
    }

    @Test
    void testLongAttributeValueFormat() {
        ConfigAttributeValueSelectResult<Long> configFormatValueSuccessResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueFormatSuccessResult();
        ConfigAttributeValueDo configFormatValueSuccessDo = new ConfigAttributeValueDo(configFormatValueSuccessResult);
        Assertions.assertDoesNotThrow(configFormatValueSuccessDo::validatedAttributeFormat);

        ConfigAttributeValueSelectResult<Long> configFormatValueFailureResult =
                ConfigAttributeValueSelectResultLongBuilder.buildLongValueFormatFailureResult();
        ConfigAttributeValueDo configFormatValueFailureDo = new ConfigAttributeValueDo(configFormatValueFailureResult);
        Assertions.assertThrows(ToastException.class, configFormatValueFailureDo::validatedAttributeFormat);
    }

    @Test
    void testBooleanAttributeValueFormat() {
        ConfigAttributeValueSelectResult<Integer> configFormatValueSuccessResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanValueFormatSuccessResult();
        ConfigAttributeValueDo configFormatValueSuccessDo = new ConfigAttributeValueDo(configFormatValueSuccessResult);
        Assertions.assertDoesNotThrow(configFormatValueSuccessDo::validatedAttributeFormat);

        ConfigAttributeValueSelectResult<Integer> configFormatValueFailureResult =
                ConfigAttributeValueSelectResultBooleanBuilder.buildBooleanValueFormatFailureResult();
        ConfigAttributeValueDo configFormatValueFailureDo = new ConfigAttributeValueDo(configFormatValueFailureResult);
        Assertions.assertThrows(ToastException.class, configFormatValueFailureDo::validatedAttributeFormat);
    }

    @Test
    void testDatetimeAttributeValueFormat() {
        ConfigAttributeValueSelectResult<Long> configFormatValueSuccessResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueFormatSuccessResult();
        ConfigAttributeValueDo configFormatValueSuccessDo = new ConfigAttributeValueDo(configFormatValueSuccessResult);
        Assertions.assertDoesNotThrow(configFormatValueSuccessDo::validatedAttributeFormat);

        ConfigAttributeValueSelectResult<Long> configFormatValueFailureResult =
                ConfigAttributeValueSelectResultDatetimeBuilder.buildDatetimeValueFormatFailureResult();
        ConfigAttributeValueDo configFormatValueFailureDo = new ConfigAttributeValueDo(configFormatValueFailureResult);
        Assertions.assertThrows(ToastException.class, configFormatValueFailureDo::validatedAttributeFormat);
    }

    @Test
    void testDateAttributeValueFormat() {
        ConfigAttributeValueSelectResult<Long> configFormatValueSuccessResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueFormatSuccessResult();
        ConfigAttributeValueDo configFormatValueSuccessDo = new ConfigAttributeValueDo(configFormatValueSuccessResult);
        Assertions.assertDoesNotThrow(configFormatValueSuccessDo::validatedAttributeFormat);

        ConfigAttributeValueSelectResult<Long> configFormatValueFailureResult =
                ConfigAttributeValueSelectResultDateBuilder.buildDateValueFormatFailureResult();
        ConfigAttributeValueDo configFormatValueFailureDo = new ConfigAttributeValueDo(configFormatValueFailureResult);
        Assertions.assertThrows(ToastException.class, configFormatValueFailureDo::validatedAttributeFormat);
    }

    @Test
    void testTimeAttributeValueFormat() {
        ConfigAttributeValueSelectResult<Long> configFormatValueSuccessResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueFormatSuccessResult();
        ConfigAttributeValueDo configFormatValueSuccessDo = new ConfigAttributeValueDo(configFormatValueSuccessResult);
        Assertions.assertDoesNotThrow(configFormatValueSuccessDo::validatedAttributeFormat);

        ConfigAttributeValueSelectResult<Long> configFormatValueFailureResult =
                ConfigAttributeValueSelectResultTimeBuilder.buildTimeValueFormatFailureResult();
        ConfigAttributeValueDo configFormatValueFailureDo = new ConfigAttributeValueDo(configFormatValueFailureResult);
        Assertions.assertThrows(ToastException.class, configFormatValueFailureDo::validatedAttributeFormat);
    }

    @Test
    void testVarcharAttributeValueFormat() {
        ConfigAttributeValueSelectResult<String> configFormatValueSuccessResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharValueFormatSuccessResult();
        ConfigAttributeValueDo configFormatValueSuccessDo = new ConfigAttributeValueDo(configFormatValueSuccessResult);
        Assertions.assertDoesNotThrow(configFormatValueSuccessDo::validatedAttributeFormat);

        ConfigAttributeValueSelectResult<String> configFormatValueFailureResult =
                ConfigAttributeValueSelectResultVarcharBuilder.buildVarcharValueFormatFailureResult();
        ConfigAttributeValueDo configFormatValueFailureDo = new ConfigAttributeValueDo(configFormatValueFailureResult);
        Assertions.assertThrows(ToastException.class, configFormatValueFailureDo::validatedAttributeFormat);
    }

    @Test
    void testEnumAttributeValueFormat() {
        ConfigAttributeValueSelectResult<String> configFormatValueSuccessResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueFormatSuccessResult();
        ConfigAttributeValueDo configFormatValueSuccessDo = new ConfigAttributeValueDo(configFormatValueSuccessResult);
        Assertions.assertDoesNotThrow(configFormatValueSuccessDo::validatedAttributeFormat);

        ConfigAttributeValueSelectResult<String> configFormatValueFailureResult =
                ConfigAttributeValueSelectResultEnumBuilder.buildEnumValueFormatFailureResult();
        ConfigAttributeValueDo configFormatValueFailureDo = new ConfigAttributeValueDo(configFormatValueFailureResult);
        Assertions.assertThrows(ToastException.class, configFormatValueFailureDo::validatedAttributeFormat);
    }

}
