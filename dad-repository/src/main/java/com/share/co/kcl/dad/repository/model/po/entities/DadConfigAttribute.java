package com.share.co.kcl.dad.repository.model.po.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.share.co.kcl.dad.repository.model.DadBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DadConfigAttribute extends DadBase {
    /**
     * 属性键
     */
    @TableField(value = "`key`")
    private String key;
    /**
     * 属性名称
     */
    @TableField(value = "`name`")
    private String name;
    /**
     * 描述
     */
    @TableField(value = "`description`")
    private String description;
}
