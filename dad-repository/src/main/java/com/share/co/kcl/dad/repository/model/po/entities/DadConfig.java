package com.share.co.kcl.dad.repository.model.po.entities;

import com.share.co.kcl.dad.repository.model.DadBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DadConfig extends DadBase {
    /**
     * 模块ID
     */
    private Long moduleId;
    /**
     * 标识
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 图片
     */
    private String image;
    /**
     * 排序 越大优先级越高
     */
    private Integer sort;
    /**
     * 状态 1-上架 0-下架
     */
    private Boolean isEnabled;

}
