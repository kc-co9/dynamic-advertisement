package com.share.co.kcl.dad.repository.service;

import com.share.co.kcl.dad.repository.dao.DadConfigValueLongDao;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigValueLong;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 动态广告-配置项LONG值(DadConfigValueLong)表服务接口
 *
 * @author makejava
 * @since 2023-02-15 14:25:34
 */
@Service
public class DadConfigValueLongService extends DadBaseService<DadConfigValueLongDao, DadConfigValueLong> {

    public List<DadConfigValueLong> getListByConfigId(Long configId) {
        return this.list(this.getQueryWrapper().eq(DadConfigValueLong::getConfigId, configId));
    }

    public Map<Long, DadConfigValueLong> getAttributeValueMapByConfigId(Long configId) {
        List<DadConfigValueLong> dadConfigValueLongList = getListByConfigId(configId);
        if (CollectionUtils.isEmpty(dadConfigValueLongList)) {
            return Collections.emptyMap();
        }
        return dadConfigValueLongList.stream().collect(Collectors.toMap(DadConfigValueLong::getAttributeId, Function.identity(), (a1, a2) -> a1));
    }

    public List<ConfigAttributeValueSelectResult<Long>> getConfigAttributeValueList(ConfigAttributeValueSelectParams params) {
        return this.baseMapper.selectConfigAttributeValue(params);
    }

    public List<MultiConfigAttributeValueSelectResult> getMultiConfigAttributeValueList(MultiConfigAttributeValueSelectParams params) {
        return this.baseMapper.selectMultiConfigAttributeValue(params);
    }


}
