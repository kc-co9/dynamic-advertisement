package com.share.co.kcl.dad.repository.model.domain.builder;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.co.kcl.dad.repository.model.domain.ConfigAttributeValueDo;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeSelectParams;
import com.share.co.kcl.dad.repository.service.DadConfigAttributeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ConfigAttributeValueDoBuilder {

    private final DadConfigAttributeService dadConfigAttributeService;

    public List<ConfigAttributeValueDo> buildConfigAttributeValueDo(Map<Long, Object> attributeValueMap) {
        if (MapUtils.isEmpty(attributeValueMap)) {
            return Collections.emptyList();
        }
        ConfigAttributeSelectParams params = new ConfigAttributeSelectParams();
        params.setAttributeIdList(new ArrayList<>(attributeValueMap.keySet()));
        return dadConfigAttributeService.getConfigAttributeList(new Page<>(1, attributeValueMap.size()), params)
                .convert(item -> new ConfigAttributeValueDo(item, attributeValueMap.get(item.getId()))).getRecords();
    }
}
