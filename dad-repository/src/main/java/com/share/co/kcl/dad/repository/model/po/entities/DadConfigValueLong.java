package com.share.co.kcl.dad.repository.model.po.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.share.co.kcl.dad.repository.model.DadBase;

@EqualsAndHashCode(callSuper = true)
@Data
public class DadConfigValueLong extends DadBase {
    /**
     * 配置ID
     */
    private Long configId;
    /**
     * 属性ID
     */
    private Long attributeId;
    /**
     * 值
     */
    private Long value;
    /**
     * 是否为空
     */
    private Boolean isNull;
}
