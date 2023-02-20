package com.share.co.kcl.dad.adminserver.vo.module;

import com.share.co.kcl.dad.common.model.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ModuleListQueryRequest extends Page {

    @ApiModelProperty(value = "模块ID")
    private Long id;

    @ApiModelProperty(value = "模块CODE")
    private String code;

    @ApiModelProperty(value = "模块NAME")
    private String name;

    @ApiModelProperty(value = "模块是否上架")
    private Boolean isEnabled;
}
