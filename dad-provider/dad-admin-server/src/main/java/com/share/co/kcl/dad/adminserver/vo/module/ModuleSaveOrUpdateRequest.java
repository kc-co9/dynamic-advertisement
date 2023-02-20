package com.share.co.kcl.dad.adminserver.vo.module;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ModuleSaveOrUpdateRequest {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "标识")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "状态")
    private Boolean isEnabled;

}
