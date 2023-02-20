package com.share.co.kcl.dad.adminserver.vo.config;

import com.share.co.kcl.dad.repository.model.domain.ConfigAttributeValueDo;
import com.share.co.kcl.dad.repository.model.domain.MetadataValueRangeDo;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigIncludingExtraAttributeResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadModule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class ConfigDetailGetResponse {

    @ApiModelProperty(value = "模块ID")
    private Long moduleId;

    @ApiModelProperty(value = "模块CODE")
    private String moduleCode;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "主键id")
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

    @ApiModelProperty(value = "状态 1-上架 0-下架")
    private Boolean isEnabled;

    @ApiModelProperty(value = "额外属性列表")
    private List<ExtraAttributeItem> extraAttributeList;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    public ConfigDetailGetResponse() {
    }

    public ConfigDetailGetResponse(DadModule dadModule, ConfigIncludingExtraAttributeResult configIncludingExtraAttributeResult) {
        BeanUtils.copyProperties(configIncludingExtraAttributeResult, this);
        this.setModuleId(Optional.ofNullable(dadModule).map(DadModule::getId).orElse(null));
        this.setModuleCode(Optional.ofNullable(dadModule).map(DadModule::getCode).orElse(""));
        this.setModuleName(Optional.ofNullable(dadModule).map(DadModule::getName).orElse(""));
        this.setExtraAttributeList(
                Optional.ofNullable(configIncludingExtraAttributeResult.getExtraAttributeList()).orElse(Collections.emptyList())
                        .stream()
                        .map(ConfigAttributeValueDo::new)
                        .map(attributeValueDo -> {
                            ExtraAttributeItem extraAttributeItem = new ExtraAttributeItem();
                            extraAttributeItem.setId(attributeValueDo.getId());
                            extraAttributeItem.setKey(attributeValueDo.getKey());
                            extraAttributeItem.setName(attributeValueDo.getName());
                            extraAttributeItem.setValueType(attributeValueDo.getValueType());
                            extraAttributeItem.setValueLimit(attributeValueDo.getValueLimit());
                            extraAttributeItem.setValueRange(attributeValueDo.getDeserializeRangeValue());
                            extraAttributeItem.setIsRequired(attributeValueDo.getIsRequired());
                            extraAttributeItem.setValue(attributeValueDo.getDeserializeValue().orElse(null));
                            extraAttributeItem.setIsNull(attributeValueDo.getIsNull());
                            return extraAttributeItem;
                        })
                        .collect(Collectors.toList()));
    }

    @Data
    public static class ExtraAttributeItem {

        @ApiModelProperty(value = "属性ID")
        private Long id;

        @ApiModelProperty(value = "属性键")
        private String key;

        @ApiModelProperty(value = "属性名称")
        private String name;

        @ApiModelProperty(value = "值类型")
        private MetadataValueType valueType;

        @ApiModelProperty(value = "值限制")
        private Boolean valueLimit;

        @ApiModelProperty(value = "取值范围")
        private MetadataValueRangeDo<? extends MetadataValueRangeDo.IRange> valueRange;

        @ApiModelProperty(value = "是否必须填写")
        private Boolean isRequired;

        @ApiModelProperty(value = "属性值")
        private Object value;

        @ApiModelProperty(value = "属性值是否为空")
        private Boolean isNull;

    }

}
