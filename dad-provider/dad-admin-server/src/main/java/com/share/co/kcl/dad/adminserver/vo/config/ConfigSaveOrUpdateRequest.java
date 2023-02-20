package com.share.co.kcl.dad.adminserver.vo.config;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
public class ConfigSaveOrUpdateRequest {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @NotNull(message = "模块ID不能为空")
    @ApiModelProperty(value = "模块ID")
    private Long moduleId;

    @NotBlank(message = "标识不能为空")
    @ApiModelProperty(value = "标识")
    private String code;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "排序 越大优先级越高")
    private Integer sort;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态 1-上架 0-下架")
    private Boolean isEnabled;

    @ApiModelProperty(value = "额外属性列表")
    private List<ExtraAttributeItem> extraAttributeList;

    @Data
    public static class ExtraAttributeItem {

        @ApiModelProperty(value = "属性ID")
        private Long id;

        @ApiModelProperty(value = "属性值")
        private Object value;

    }

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    public Map<Long, Object> getExtraAttributeValueMap() {
        if (CollectionUtils.isEmpty(this.getExtraAttributeList())) {
            return Collections.emptyMap();
        }
        return this.getExtraAttributeList()
                .stream()
                .filter(o -> Objects.nonNull(o.getId()))
                .collect(HashMap::new, (m, v) -> m.put(v.getId(), v.getValue()), HashMap::putAll);
    }
}
