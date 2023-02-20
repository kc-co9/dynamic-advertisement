package com.share.co.kcl.dad.adminserver.vo.config;

import com.share.co.kcl.dad.repository.model.domain.MetadataValueRangeDo;
import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigAttribute;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigMetadata;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

import static com.share.co.kcl.dad.repository.model.domain.ConfigAttributeValueDo.deserializeValueRange;

@Data
public class ConfigAttributeDetailGetResponse {

    @ApiModelProperty(value = "属性ID")
    private Long id;

    @ApiModelProperty(value = "属性键")
    private String key;

    @ApiModelProperty(value = "属性名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "值类型")
    private MetadataValueType valueType;

    @ApiModelProperty(value = "值限制")
    private Boolean valueLimit;

    @ApiModelProperty(value = "取值范围")
    private MetadataValueRangeDo<? extends MetadataValueRangeDo.IRange> valueRange;

    @ApiModelProperty(value = "内容格式校验类型")
    private MetadataFormatType formatType;

    @ApiModelProperty(value = "内容格式校验规则")
    private String formatRule;

    @ApiModelProperty(value = "是否必须填写")
    private Boolean isRequired;

    @ApiModelProperty(value = "业务校验类型")
    private MetadataValidatedType validatedType;

    @ApiModelProperty(value = "业务校验规则")
    private String validatedRule;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    public ConfigAttributeDetailGetResponse() {
    }

    public ConfigAttributeDetailGetResponse(DadConfigMetadata metadata, DadConfigAttribute attribute) {
        BeanUtils.copyProperties(attribute, this);
        BeanUtils.copyProperties(metadata, this);
        this.setValueRange(deserializeValueRange(metadata.getValueType(), metadata.getValueRange()));
        this.setId(attribute.getId());
        this.setKey(attribute.getKey());
        this.setName(attribute.getName());
        this.setUpdateTime(metadata.getUpdateTime());
    }
}
