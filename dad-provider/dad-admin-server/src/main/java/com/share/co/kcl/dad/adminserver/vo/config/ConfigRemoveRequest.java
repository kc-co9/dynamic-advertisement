package com.share.co.kcl.dad.adminserver.vo.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConfigRemoveRequest {

    @NotNull(message = "配置ID不能为空")
    @ApiModelProperty(value = "配置ID")
    private Long configId;
}
