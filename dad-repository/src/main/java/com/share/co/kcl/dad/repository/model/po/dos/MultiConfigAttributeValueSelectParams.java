package com.share.co.kcl.dad.repository.model.po.dos;

import lombok.Data;

import java.util.List;

@Data
public class MultiConfigAttributeValueSelectParams {

    /**
     * 配置ID列表
     */
    private List<Long> configIdList;

    /**
     * 属性ID列表
     */
    private List<Long> attributeIdList;

}
