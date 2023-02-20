package com.share.co.kcl.dad.repository.service;

import com.share.co.kcl.dad.repository.dao.DadConfigValueIntDao;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigValueInt;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 动态广告-配置项INT值(DadConfigValueInt)表服务接口
 *
 * @author makejava
 * @since 2023-02-15 14:25:34
 */
@Service
public class DadConfigValueIntService extends DadBaseService<DadConfigValueIntDao, DadConfigValueInt> {

    public List<DadConfigValueInt> getListByConfigId(Long configId) {
        return this.list(this.getQueryWrapper().eq(DadConfigValueInt::getConfigId, configId));
    }

    public Map<Long, DadConfigValueInt> getAttributeValueMapByConfigId(Long configId) {
        List<DadConfigValueInt> dadConfigValueIntList = getListByConfigId(configId);
        if (CollectionUtils.isEmpty(dadConfigValueIntList)) {
            return Collections.emptyMap();
        }
        return dadConfigValueIntList.stream().collect(Collectors.toMap(DadConfigValueInt::getAttributeId, Function.identity(), (a1, a2) -> a1));
    }

    public List<ConfigAttributeValueSelectResult<Integer>> getConfigAttributeValueList(ConfigAttributeValueSelectParams params) {
        return this.baseMapper.selectConfigAttributeValue(params);
    }

    public List<MultiConfigAttributeValueSelectResult> getMultiConfigAttributeValueList(MultiConfigAttributeValueSelectParams params) {
        return this.baseMapper.selectMultiConfigAttributeValue(params);
    }

}
