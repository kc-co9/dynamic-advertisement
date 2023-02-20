package com.share.co.kcl.dad.repository.model.po.entities;

import com.share.co.kcl.dad.repository.model.DadBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DadModule extends DadBase {
    /**
     * 标识
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片
     */
    private String image;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态 1-上架 0-下架
     */
    private Boolean isEnabled;

}
