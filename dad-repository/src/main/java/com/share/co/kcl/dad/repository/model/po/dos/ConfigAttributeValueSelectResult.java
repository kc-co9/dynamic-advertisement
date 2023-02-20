package com.share.co.kcl.dad.repository.model.po.dos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigAttributeValueSelectResult<T> extends ConfigAttributeSelectResult {

    /**
     * 属性值
     */
    private transient T value;

    /**
     * 是否为空
     */
    private transient Boolean isNull;
}
