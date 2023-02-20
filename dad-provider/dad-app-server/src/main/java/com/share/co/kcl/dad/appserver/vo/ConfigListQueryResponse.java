package com.share.co.kcl.dad.appserver.vo;

import com.share.co.kcl.dad.repository.model.domain.ConfigAttributeValueDo;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class ConfigListQueryResponse {

    private List<ListItem> result;

    public ConfigListQueryResponse() {
    }

    public ConfigListQueryResponse(List<DadConfig> dadConfigList, List<MultiConfigAttributeValueSelectResult> extraAttributeList) {
        Map<Long, List<MultiConfigAttributeValueSelectResult>> extraAttributeMap =
                Optional.ofNullable(extraAttributeList).orElse(Collections.emptyList())
                        .stream()
                        .collect(Collectors.groupingBy(MultiConfigAttributeValueSelectResult::getConfigId));
        this.result = Optional.ofNullable(dadConfigList).orElse(Collections.emptyList())
                .stream()
                .map(dadConfig -> {
                    ListItem item = new ListItem();
                    item.setCode(dadConfig.getCode());
                    item.setName(dadConfig.getName());
                    item.setImage(dadConfig.getImage());
                    item.setSort(dadConfig.getSort());
                    item.setIsEnabled(dadConfig.getIsEnabled());
                    item.setExtraAttribute(
                            Optional.ofNullable(extraAttributeMap.get(dadConfig.getId())).orElse(Collections.emptyList())
                                    .stream()
                                    .map(ConfigAttributeValueDo::new)
                                    .collect(Collectors.toMap(ConfigAttributeValueDo::getKey, ConfigAttributeValueDo::getDeserializeValue)));
                    return item;
                }).collect(Collectors.toList());
    }

    @Data
    public static class ListItem {

        @ApiModelProperty(value = "标识")
        private String code;

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "图片")
        private String image;

        @ApiModelProperty(value = "排序 越大优先级越高")
        private Integer sort;

        @ApiModelProperty(value = "状态")
        private Boolean isEnabled;

        @ApiModelProperty(value = "额外属性")
        private Map<String, Object> extraAttribute;

    }
}
