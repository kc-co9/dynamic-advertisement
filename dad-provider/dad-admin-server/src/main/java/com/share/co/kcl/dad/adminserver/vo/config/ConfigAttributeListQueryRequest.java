package com.share.co.kcl.dad.adminserver.vo.config;

import com.share.co.kcl.dad.common.model.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigAttributeListQueryRequest extends Page {

    @ApiModelProperty(value = "属性ID")
    private Long attributeId;

    @ApiModelProperty(value = "属性键")
    private String attributeKey;

    @ApiModelProperty(value = "属性名称")
    private String attributeName;
}
