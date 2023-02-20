package com.share.co.kcl.dad.repository.manager;

import com.share.co.kcl.dad.repository.model.po.entities.*;
import com.share.co.kcl.dad.repository.service.DadConfigService;
import com.share.co.kcl.dad.repository.service.DadModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DadModuleManager {

    private final DadConfigManager dadConfigManager;
    private final DadModuleService dadModuleService;
    private final DadConfigService dadConfigService;

    @Transactional(rollbackFor = Exception.class)
    public void removeModule(Long moduleId) {

        dadModuleService.removeById(moduleId);

        List<DadConfig> configsOnlyContainingId = dadConfigService.list(dadConfigService.getQueryWrapper()
                .select(DadConfig::getId)
                .eq(DadConfig::getModuleId, moduleId));
        List<Long> configIdList = Optional.ofNullable(configsOnlyContainingId).orElse(Collections.emptyList())
                .stream()
                .map(DadConfig::getId)
                .collect(Collectors.toList());
        dadConfigManager.removeConfigIncludingExtraAttribute(configIdList);
    }

}
