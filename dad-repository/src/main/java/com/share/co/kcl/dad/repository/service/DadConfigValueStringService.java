package com.share.co.kcl.dad.repository.service;

import com.share.co.kcl.dad.repository.dao.DadConfigValueStringDao;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigValueString;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 动态广告-配置项VARCHAR值(DadConfigValueVarchar)表服务接口
 *
 * @author makejava
 * @since 2023-02-15 14:25:34
 */
@Service
public class DadConfigValueStringService extends DadBaseService<DadConfigValueStringDao, DadConfigValueString> {

    public List<DadConfigValueString> getListByConfigId(Long configId) {
        return this.list(this.getQueryWrapper().eq(DadConfigValueString::getConfigId, configId));
    }

    public Map<Long, DadConfigValueString> getAttributeValueMapByConfigId(Long configId) {
        List<DadConfigValueString> dadConfigValueStringList = getListByConfigId(configId);
        if (CollectionUtils.isEmpty(dadConfigValueStringList)) {
            return Collections.emptyMap();
        }
        return dadConfigValueStringList.stream().collect(Collectors.toMap(DadConfigValueString::getAttributeId, Function.identity(), (a1, a2) -> a1));
    }

    public List<ConfigAttributeValueSelectResult<String>> getConfigAttributeValueList(ConfigAttributeValueSelectParams params) {
        return this.baseMapper.selectConfigAttributeValue(params);
    }

    public List<MultiConfigAttributeValueSelectResult> getMultiConfigAttributeValueList(MultiConfigAttributeValueSelectParams params) {
        return this.baseMapper.selectMultiConfigAttributeValue(params);
    }


}
