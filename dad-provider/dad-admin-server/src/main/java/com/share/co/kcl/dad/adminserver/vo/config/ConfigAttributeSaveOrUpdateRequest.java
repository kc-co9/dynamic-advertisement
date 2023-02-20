package com.share.co.kcl.dad.adminserver.vo.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.share.co.kcl.dad.repository.model.domain.ConfigAttributeValueDo;
import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigAttribute;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigMetadata;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ConfigAttributeSaveOrUpdateRequest {

    @ApiModelProperty(value = "ID")
    private Long id;

    @NotBlank(message = "键不能为空")
    @ApiModelProperty(value = "键")
    private String key;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @NotNull(message = "值类型不能为空")
    @ApiModelProperty(value = "值类型")
    private MetadataValueType valueType;

    @NotNull(message = "值限制不能为空")
    @ApiModelProperty(value = "值限制")
    private Boolean valueLimit;

    @ApiModelProperty(value = "取值范围")
    private Object valueRange;

    @NotNull(message = "内容格式校验类型不能为空")
    @ApiModelProperty(value = "内容格式校验类型")
    private MetadataFormatType formatType;

    @ApiModelProperty(value = "内容格式校验规则")
    private String formatRule;

    @NotNull(message = "是否必须填写不能为空")
    @ApiModelProperty(value = "是否必须填写")
    private Boolean isRequired;

    @NotNull(message = "业务校验类型不能为空")
    @ApiModelProperty(value = "业务校验类型")
    private MetadataValidatedType validatedType;

    @ApiModelProperty(value = "业务校验规则")
    private String validatedRule;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    public DadConfigAttribute extractConfigAttribute() {
        DadConfigAttribute dadConfigAttribute = new DadConfigAttribute();
        BeanUtils.copyProperties(this, dadConfigAttribute);
        return dadConfigAttribute;
    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    public DadConfigMetadata extractConfigMetadata() {
        DadConfigMetadata dadConfigMetadata = new DadConfigMetadata();
        dadConfigMetadata.setAttributeId(this.getId());
        dadConfigMetadata.setValueType(this.getValueType());
        dadConfigMetadata.setValueLimit(this.getValueLimit());
        dadConfigMetadata.setValueRange(
                ConfigAttributeValueDo.serializeValueRange(this.getValueType(),
                        ConfigAttributeValueDo.deserializeValueRange(this.getValueType(), JSON.toJSONString(this.getValueRange()))));
        dadConfigMetadata.setFormatType(this.getFormatType());
        dadConfigMetadata.setFormatRule(this.getFormatRule());
        dadConfigMetadata.setIsRequired(this.getIsRequired());
        dadConfigMetadata.setValidatedType(this.getValidatedType());
        dadConfigMetadata.setValidatedRule(this.getValidatedRule());
        return dadConfigMetadata;
    }

}
