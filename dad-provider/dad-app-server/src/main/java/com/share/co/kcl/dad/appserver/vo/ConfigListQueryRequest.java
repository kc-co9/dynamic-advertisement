package com.share.co.kcl.dad.appserver.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ConfigListQueryRequest {

    @NotBlank(message = "模块CODE不能为空")
    @ApiModelProperty(value = "模块CODE")
    private String moduleCode;
}
