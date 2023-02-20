package com.share.co.kcl.dad.repository.service;

import com.share.co.kcl.dad.repository.dao.DadModuleDao;
import com.share.co.kcl.dad.repository.model.po.entities.DadModule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 动态广告-模块项(DadModule)表服务接口
 *
 * @author makejava
 * @since 2023-02-15 14:25:34
 */
@Service
public class DadModuleService extends DadBaseService<DadModuleDao, DadModule> {

    public Optional<DadModule> getByCode(String code) {
        if (StringUtils.isBlank(code)){
            return Optional.empty();
        }
        return this.getFirst(this.getQueryWrapper().eq(DadModule::getCode, code));
    }

    public Map<Long, DadModule> getMapByIds(List<Long> moduleIdList) {
        if (CollectionUtils.isEmpty(moduleIdList)) {
            return Collections.emptyMap();
        }
        List<DadModule> moduleList = this.list(this.getQueryWrapper().in(DadModule::getId, moduleIdList));
        return Optional.ofNullable(moduleList).orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toMap(DadModule::getId, Function.identity(), (a1, a2) -> a1));
    }

}
