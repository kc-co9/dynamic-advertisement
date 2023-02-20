package com.share.co.kcl.dad.adminserver.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.co.kcl.dad.adminserver.vo.config.*;
import com.share.co.kcl.dad.repository.manager.DadConfigManager;
import com.share.co.kcl.dad.repository.model.domain.ConfigAttributeValueDo;
import com.share.co.kcl.dad.repository.model.domain.builder.ConfigAttributeValueDoBuilder;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigIncludingExtraAttributeResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfig;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigAttribute;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigMetadata;
import com.share.co.kcl.dad.repository.model.po.entities.DadModule;
import com.share.co.kcl.dad.repository.service.DadConfigAttributeService;
import com.share.co.kcl.dad.repository.service.DadConfigMetadataService;
import com.share.co.kcl.dad.repository.service.DadConfigService;
import com.share.co.kcl.dad.repository.service.DadModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Api(tags = "广告配置路由")
@RequiredArgsConstructor
@RequestMapping(value = "/config")
public class ConfigController {

    private final DadConfigService dadConfigService;
    private final DadModuleService dadModuleService;
    private final DadConfigMetadataService dadConfigMetadataService;
    private final DadConfigAttributeService dadConfigAttributeService;

    private final DadConfigManager dadConfigManager;
    private final ConfigAttributeValueDoBuilder configAttributeValueDoBuilder;

    @ApiOperation(value = "获取配置额外属性列表")
    @GetMapping(value = "/v1/getConfigExtraAttributeList")
    public ConfigAttributeListQueryResponse getConfigExtraAttributeList(@ModelAttribute @Validated ConfigAttributeListQueryRequest request) {
        ConfigAttributeSelectParams configAttributeSelectParams = new ConfigAttributeSelectParams();
        BeanUtils.copyProperties(request, configAttributeSelectParams);
        IPage<ConfigAttributeSelectResult> result = dadConfigAttributeService.getConfigAttributeList(new Page<>(request.getPageNo(), request.getPageSize()), configAttributeSelectParams);
        return new ConfigAttributeListQueryResponse(result);
    }

    @ApiOperation(value = "获取配置额外属性详情")
    @GetMapping(value = "/v1/getConfigExtraAttributeDetail")
    public ConfigAttributeDetailGetResponse getConfigExtraAttributeDetail(@RequestParam(name = "attributeId") Long attributeId) {
        DadConfigAttribute dadConfigAttribute = dadConfigAttributeService.getById(attributeId);
        DadConfigMetadata dadConfigMetadata = dadConfigMetadataService.getByAttributeId(attributeId);
        return new ConfigAttributeDetailGetResponse(dadConfigMetadata, dadConfigAttribute);
    }

    @ApiOperation(value = "保存/更新配置额外属性")
    @PostMapping(value = "/v1/saveOrUpdateConfigExtraAttribute")
    public void saveOrUpdateConfigExtraAttribute(@RequestBody @Validated ConfigAttributeSaveOrUpdateRequest request) {
        DadConfigAttribute dadConfigAttribute = request.extractConfigAttribute();
        DadConfigMetadata dadConfigMetadata = request.extractConfigMetadata();
        dadConfigManager.saveOrUpdateConfigExtraAttribute(dadConfigMetadata, dadConfigAttribute);
    }

    @ApiOperation(value = "移除配置额外属性")
    @PostMapping(value = "/v1/removeConfigExtraAttribute")
    public void removeConfigExtraAttribute(@RequestBody @Validated ConfigAttributeRemoveRequest request) {
        dadConfigManager.removeConfigExtraAttribute(Collections.singletonList(request.getAttributeId()));
    }

    @ApiOperation(value = "获取配置列表")
    @GetMapping(value = "/v1/getConfigList")
    public ConfigListQueryResponse getConfigList(@ModelAttribute @Validated ConfigListQueryRequest request) {
        IPage<DadConfig> result = dadConfigService.page(new Page<>(request.getPageNo(), request.getPageSize()), dadConfigService.getQueryWrapper()
                .eq(Objects.nonNull(request.getId()), DadConfig::getId, request.getId())
                .eq(Objects.nonNull(request.getModuleId()), DadConfig::getModuleId, request.getModuleId())
                .eq(StringUtils.isNotBlank(request.getCode()), DadConfig::getCode, request.getCode())
                .eq(StringUtils.isNotBlank(request.getName()), DadConfig::getName, request.getName())
                .eq(Objects.nonNull(request.getIsEnabled()), DadConfig::getIsEnabled, request.getIsEnabled())
                .orderByDesc(DadConfig::getId));
        List<Long> moduleIdList = Optional.ofNullable(result.getRecords()).orElse(Collections.emptyList())
                .stream()
                .map(DadConfig::getModuleId)
                .collect(Collectors.toList());
        Map<Long, DadModule> dadModuleMap = dadModuleService.getMapByIds(moduleIdList);
        return new ConfigListQueryResponse(result, dadModuleMap);
    }

    @ApiOperation(value = "获取配置详情")
    @GetMapping(value = "/v1/getConfigDetail")
    public ConfigDetailGetResponse getConfigDetail(@RequestParam(name = "configId") Long configId) {
        ConfigIncludingExtraAttributeResult configIncludingExtraAttribute = dadConfigManager.getConfigIncludingExtraAttribute(configId);
        DadModule dadModule = dadModuleService.getById(configIncludingExtraAttribute.getModuleId());
        return new ConfigDetailGetResponse(dadModule, configIncludingExtraAttribute);
    }

    @ApiOperation(value = "保存/更新配置")
    @PostMapping(value = "/v1/saveOrUpdateConfig")
    public void saveOrUpdateConfig(@RequestBody @Validated ConfigSaveOrUpdateRequest request) {
        DadConfig dadConfig = new DadConfig();
        BeanUtils.copyProperties(request, dadConfig);
        List<ConfigAttributeValueDo> configAttributeValueDoList =
                configAttributeValueDoBuilder.buildConfigAttributeValueDo(request.getExtraAttributeValueMap());
        dadConfigManager.saveOrUpdateConfigIncludingExtraAttribute(dadConfig, configAttributeValueDoList);
    }

    @ApiOperation(value = "移除配置")
    @PostMapping(value = "/v1/removeConfig")
    public void removeConfig(@RequestBody @Validated ConfigRemoveRequest request) {
        dadConfigManager.removeConfigIncludingExtraAttribute(Collections.singletonList(request.getConfigId()));
    }
}
