package com.share.co.kcl.dad.adminserver.vo.module;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.share.co.kcl.dad.repository.model.po.entities.DadModule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ModuleListQueryResponse {

    private IPage<PageItem> result;

    public ModuleListQueryResponse(IPage<DadModule> result) {
        this.result = result.convert(dadModule -> {
            PageItem pageItem = new PageItem();
            BeanUtils.copyProperties(dadModule, pageItem);
            return pageItem;
        });
    }

    @Data
    public static class PageItem {

        @ApiModelProperty(value = "主键id")
        private Long id;

        @ApiModelProperty(value = "标识")
        private String code;

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "图片")
        private String image;

        @ApiModelProperty(value = "状态")
        private Boolean isEnabled;

        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;

        @ApiModelProperty(value = "更新时间")
        private LocalDateTime updateTime;

    }
}
