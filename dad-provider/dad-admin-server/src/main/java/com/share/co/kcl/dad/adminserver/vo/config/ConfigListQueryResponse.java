package com.share.co.kcl.dad.adminserver.vo.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfig;
import com.share.co.kcl.dad.repository.model.po.entities.DadModule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Data
public class ConfigListQueryResponse {

    private IPage<PageItem> result;

    public ConfigListQueryResponse() {
    }

    public ConfigListQueryResponse(IPage<DadConfig> result, Map<Long, DadModule> dadModuleMap) {
        this.result = result.convert(dadConfig -> {
            PageItem pageItem = new PageItem();
            BeanUtils.copyProperties(dadConfig, pageItem);
            pageItem.setModuleId(
                    Optional.ofNullable(dadModuleMap.get(dadConfig.getModuleId()))
                            .map(DadModule::getId)
                            .orElse(null));
            pageItem.setModuleCode(
                    Optional.ofNullable(dadModuleMap.get(dadConfig.getModuleId()))
                            .map(DadModule::getCode)
                            .orElse(""));
            pageItem.setModuleName(
                    Optional.ofNullable(dadModuleMap.get(dadConfig.getModuleId()))
                            .map(DadModule::getName)
                            .orElse(""));
            return pageItem;
        });
    }

    @Data
    public static class PageItem {

        @ApiModelProperty(value = "模块ID")
        private Long moduleId;

        @ApiModelProperty(value = "模块CODE")
        private String moduleCode;

        @ApiModelProperty(value = "模块名称")
        private String moduleName;

        @ApiModelProperty(value = "主键ID")
        private Long id;

        @ApiModelProperty(value = "标识")
        private String code;

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "描述")
        private String description;

        @ApiModelProperty(value = "图片")
        private String image;

        @ApiModelProperty(value = "排序 越大优先级越高")
        private Integer sort;

        @ApiModelProperty(value = "状态")
        private Boolean isEnabled;

        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;

        @ApiModelProperty(value = "更新时间")
        private LocalDateTime updateTime;

    }
}
