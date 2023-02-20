package com.share.co.kcl.dad.repository.model.po.dos;

import lombok.Data;

import java.util.List;

@Data
public class ConfigAttributeSelectParams {

    /**
     * 属性ID
     */
    private Long attributeId;

    /**
     * 属性ID列表
     */
    private List<Long> attributeIdList;

    /**
     * 属性名称
     */
    private String attributeKey;

    /**
     * 属性名称
     */
    private String attributeName;
}
