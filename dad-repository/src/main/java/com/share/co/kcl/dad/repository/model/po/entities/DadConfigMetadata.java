package com.share.co.kcl.dad.repository.model.po.entities;

import com.share.co.kcl.dad.repository.model.DadBase;
import com.share.co.kcl.dad.repository.model.domain.MetadataValueRangeDo;
import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DadConfigMetadata extends DadBase {
    /**
     * 属性ID
     */
    private Long attributeId;
    /**
     * 值类型
     */
    private MetadataValueType valueType;
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
}
