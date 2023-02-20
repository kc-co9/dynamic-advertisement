package com.share.co.kcl.dad.adminserver.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.co.kcl.dad.repository.manager.DadModuleManager;
import com.share.co.kcl.dad.adminserver.vo.module.*;
import com.share.co.kcl.dad.repository.model.po.entities.DadModule;
import com.share.co.kcl.dad.repository.service.DadModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Api(tags = "广告模块路由")
@RequiredArgsConstructor
@RequestMapping(value = "/module")
public class ModuleController {

    private final DadModuleManager dadModuleManager;
    private final DadModuleService dadModuleService;

    @ApiOperation(value = "获取模块列表")
    @GetMapping(value = "/v1/getModuleList")
    public ModuleListQueryResponse getModuleList(@ModelAttribute @Validated ModuleListQueryRequest request) {
        IPage<DadModule> result = dadModuleService.page(new Page<>(request.getPageNo(), request.getPageSize()), dadModuleService.getQueryWrapper()
                .eq(Objects.nonNull(request.getId()), DadModule::getId, request.getId())
                .eq(StringUtils.isNotBlank(request.getCode()), DadModule::getCode, request.getCode())
                .eq(StringUtils.isNotBlank(request.getName()), DadModule::getName, request.getName())
                .eq(Objects.nonNull(request.getIsEnabled()), DadModule::getIsEnabled, request.getIsEnabled())
                .orderByDesc(DadModule::getId));
        return new ModuleListQueryResponse(result);
    }

    @ApiOperation(value = "获取模块详情")
    @GetMapping(value = "/v1/getModuleDetail")
    public ModuleDetailGetResponse getModuleDetail(@RequestParam(name = "id") Long id) {
        DadModule module = dadModuleService.getById(id);
        ModuleDetailGetResponse response = new ModuleDetailGetResponse();
        BeanUtils.copyProperties(module, response);
        return response;
    }

    @ApiOperation(value = "保存/更新模块")
    @PostMapping(value = "/v1/saveOrUpdateModule")
    public void saveOrUpdateModule(@RequestBody @Validated ModuleSaveOrUpdateRequest request) {
        DadModule module = new DadModule();
        BeanUtils.copyProperties(request, module);
        dadModuleService.saveOrUpdate(module);
    }

    @ApiOperation(value = "移除模块")
    @PostMapping(value = "/v1/removeModule")
    public void removeModule(@RequestBody @Validated ModuleRemoveRequest request) {
        dadModuleManager.removeModule(request.getId());
    }
}
