package com.share.co.kcl.dad.repository.model.po.dos;

import com.share.co.kcl.dad.repository.model.domain.MetadataValueRangeDo;
import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MultiConfigAttributeValueSelectResult {

    /**
     * 配置ID
     */
    private Long configId;
    /**
     * 属性ID
     */
    private Long id;
    /**
     * 属性键
     */
    private String key;
    /**
     * 属性名称
     */
    private String name;
    /**
     * 名称
     */
    private String description;
    /**
     * 值类型
     */
    private MetadataValueType valueType;
    /**
     * 属性值
     */
    private Object value;
    /**
     * 是否为空
     */
    private Boolean isNull;
    /**
     * 值限制
     */
    private Boolean valueLimit;
    /**
     * 取值范围JSON
     *
     * @see MetadataValueRangeDo
     */
    private String valueRange;
    /**
     * 内容格式校验类型
     */
    private MetadataFormatType formatType;
    /**
     * 内容格式校验规则
     */
    private String formatRule;
    /**
     * 是否必须填写
     */
    private Boolean isRequired;
    /**
     * 业务校验类型
     */
    private MetadataValidatedType validatedType;
    /**
     * 业务校验规则
     */
    private String validatedRule;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
