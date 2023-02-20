package com.share.co.kcl.dad.adminserver.vo.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConfigAttributeRemoveRequest {

    @NotNull(message = "属性ID不能为空")
    @ApiModelProperty(value = "属性ID")
    private Long attributeId;
}
