package com.share.co.kcl.dad.appserver.controller;

import com.share.co.kcl.dad.appserver.vo.ConfigListQueryRequest;
import com.share.co.kcl.dad.appserver.vo.ConfigListQueryResponse;
import com.share.co.kcl.dad.common.exception.ToastException;
import com.share.co.kcl.dad.repository.manager.DadConfigManager;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfig;
import com.share.co.kcl.dad.repository.model.po.entities.DadModule;
import com.share.co.kcl.dad.repository.service.DadConfigService;
import com.share.co.kcl.dad.repository.service.DadModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Api(tags = "广告配置路由")
@RequiredArgsConstructor
@RequestMapping(value = "/config")
public class ConfigController {

    private final DadModuleService dadModuleService;
    private final DadConfigService dadConfigService;
    private final DadConfigManager dadConfigManager;

    @ApiOperation(value = "获取配置列表")
    @GetMapping(value = "/v1/getConfigList")
    public ConfigListQueryResponse getConfigList(@ModelAttribute @Validated ConfigListQueryRequest request) {
        DadModule dadModule = dadModuleService.getByCode(request.getModuleCode())
                .filter(module -> Boolean.TRUE.equals(module.getIsEnabled()))
                .orElseThrow(() -> new ToastException("模块CODE不存在"));
        List<DadConfig> dadConfigList = dadConfigService.list(dadConfigService.getQueryWrapper()
                .eq(DadConfig::getModuleId, dadModule.getId())
                .eq(DadConfig::getIsEnabled, true)
                .orderByAsc(DadConfig::getSort));
        List<Long> configIdList =
                Optional.ofNullable(dadConfigList).orElse(Collections.emptyList())
                        .stream()
                        .map(DadConfig::getId)
                        .collect(Collectors.toList());
        List<MultiConfigAttributeValueSelectResult> extraAttributeList = dadConfigManager.getConfigListIncludingExtraAttribute(configIdList);
        return new ConfigListQueryResponse(dadConfigList, extraAttributeList);
    }
}
