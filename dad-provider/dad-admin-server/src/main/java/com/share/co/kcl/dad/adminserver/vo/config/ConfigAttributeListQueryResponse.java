package com.share.co.kcl.dad.adminserver.vo.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeSelectResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ConfigAttributeListQueryResponse {

    private IPage<PageItem> result;

    public ConfigAttributeListQueryResponse(IPage<ConfigAttributeSelectResult> result) {
        this.result = result.convert(configAttributeSelectResult -> {
            PageItem pageItem = new PageItem();
            BeanUtils.copyProperties(configAttributeSelectResult, pageItem);
            return pageItem;
        });
    }

    @Data
    public static class PageItem {

        @ApiModelProperty(value = "ID")
        private Long id;

        @ApiModelProperty(value = "键")
        private String key;

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "描述")
        private String description;

        @ApiModelProperty(value = "值类型")
        private MetadataValueType valueType;

        @ApiModelProperty(value = "值限制")
        private Boolean valueLimit;

        @ApiModelProperty(value = "内容格式校验类型")
        private MetadataFormatType formatType;

        @ApiModelProperty(value = "是否必须填写")
        private Boolean isRequired;

        @ApiModelProperty(value = "业务校验类型")
        private MetadataValidatedType validatedType;

        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;

        @ApiModelProperty(value = "更新时间")
        private LocalDateTime updateTime;
    }
}
