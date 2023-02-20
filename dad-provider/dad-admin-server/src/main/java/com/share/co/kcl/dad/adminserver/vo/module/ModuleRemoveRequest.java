package com.share.co.kcl.dad.adminserver.vo.module;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ModuleRemoveRequest {

    @NotNull(message = "主键id不能为空")
    @ApiModelProperty(value = "主键id")
    private Long id;
}
