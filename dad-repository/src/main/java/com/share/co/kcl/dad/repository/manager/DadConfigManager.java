package com.share.co.kcl.dad.repository.manager;

import com.share.co.kcl.dad.common.exception.BusinessException;
import com.share.co.kcl.dad.repository.model.domain.ConfigAttributeValueDo;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.dos.*;
import com.share.co.kcl.dad.repository.model.po.entities.*;
import com.share.co.kcl.dad.repository.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DadConfigManager {

    private final DadConfigService dadConfigService;
    private final DadConfigMetadataService dadConfigMetadataService;
    private final DadConfigAttributeService dadConfigAttributeService;

    private final DadConfigValueIntService dadConfigValueIntService;
    private final DadConfigValueLongService dadConfigValueLongService;
    private final DadConfigValueStringService dadConfigValueStringService;

    public ConfigIncludingExtraAttributeResult getConfigIncludingExtraAttribute(Long configId) {
        DadConfig dadConfig = dadConfigService.getById(configId);
        if (Objects.isNull(dadConfig)) {
            throw new BusinessException("config id is not exist");
        }

        List<ConfigAttributeValueSelectResult<Integer>> intAttributeResult = dadConfigValueIntService.getConfigAttributeValueList(new ConfigAttributeValueSelectParams(configId));
        List<ConfigAttributeValueSelectResult<Long>> longAttributeResult = dadConfigValueLongService.getConfigAttributeValueList(new ConfigAttributeValueSelectParams(configId));
        List<ConfigAttributeValueSelectResult<String>> stringAttributeResult = dadConfigValueStringService.getConfigAttributeValueList(new ConfigAttributeValueSelectParams(configId));

        ConfigIncludingExtraAttributeResult result = new ConfigIncludingExtraAttributeResult();
        BeanUtils.copyProperties(dadConfig, result);
        result.setIntAttributeResult(intAttributeResult);
        result.setLongAttributeResult(longAttributeResult);
        result.setStringAttributeResult(stringAttributeResult);
        return result;
    }

    public List<MultiConfigAttributeValueSelectResult> getConfigListIncludingExtraAttribute(List<Long> configIdList) {
        if (CollectionUtils.isEmpty(configIdList)) {
            return Collections.emptyList();
        }
        MultiConfigAttributeValueSelectParams multiConfigAttributeValueSelectParams = new MultiConfigAttributeValueSelectParams();
        multiConfigAttributeValueSelectParams.setConfigIdList(configIdList);
        List<MultiConfigAttributeValueSelectResult> intAttributeResult = dadConfigValueIntService.getMultiConfigAttributeValueList(multiConfigAttributeValueSelectParams);
        List<MultiConfigAttributeValueSelectResult> longAttributeResult = dadConfigValueLongService.getMultiConfigAttributeValueList(multiConfigAttributeValueSelectParams);
        List<MultiConfigAttributeValueSelectResult> stringAttributeResult = dadConfigValueStringService.getMultiConfigAttributeValueList(multiConfigAttributeValueSelectParams);
        List<MultiConfigAttributeValueSelectResult> attributeResult = new ArrayList<>();
        attributeResult.addAll(intAttributeResult);
        attributeResult.addAll(longAttributeResult);
        attributeResult.addAll(stringAttributeResult);
        return attributeResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateConfigIncludingExtraAttribute(DadConfig dadConfig, List<ConfigAttributeValueDo> extraAttributeList) {

        dadConfigService.saveOrUpdate(dadConfig);

        Map<Long, DadConfigValueInt> attributeValueIntMap = dadConfigValueIntService.getAttributeValueMapByConfigId(dadConfig.getId());
        Map<Long, DadConfigValueLong> attributeValueLongMap = dadConfigValueLongService.getAttributeValueMapByConfigId(dadConfig.getId());
        Map<Long, DadConfigValueString> attributeValueStringMap = dadConfigValueStringService.getAttributeValueMapByConfigId(dadConfig.getId());

        List<DadConfigValueInt> dadConfigValueIntSaveOrUpdateList = new ArrayList<>();
        List<DadConfigValueLong> dadConfigValueLongSaveOrUpdateList = new ArrayList<>();
        List<DadConfigValueString> dadConfigValueStringSaveOrUpdateList = new ArrayList<>();
        for (ConfigAttributeValueDo extraAttribute : extraAttributeList) {

            extraAttribute.validatedAttributeValue();

            if (MetadataValueType.DatabaseType.getIntTypeValue().contains(extraAttribute.getValueType())) {
                DadConfigValueInt dadConfigValueInt =
                        Optional.ofNullable(attributeValueIntMap.get(extraAttribute.getId()))
                                .map(attributeValueInt -> {
                                    DadConfigValueInt item = new DadConfigValueInt();
                                    item.setId(attributeValueInt.getId());
                                    item.setConfigId(attributeValueInt.getConfigId());
                                    item.setAttributeId(attributeValueInt.getAttributeId());
                                    item.setValue(extraAttribute.getSerializeValue().map(Integer.class::cast).orElse(0));
                                    item.setIsNull(!extraAttribute.getSerializeValue().isPresent());
                                    return item;
                                }).orElseGet(() -> {
                                    DadConfigValueInt item = new DadConfigValueInt();
                                    item.setConfigId(dadConfig.getId());
                                    item.setAttributeId(extraAttribute.getId());
                                    item.setValue(extraAttribute.getSerializeValue().map(Integer.class::cast).orElse(0));
                                    item.setIsNull(!extraAttribute.getSerializeValue().isPresent());
                                    return item;
                                });
                dadConfigValueIntSaveOrUpdateList.add(dadConfigValueInt);
            }

            if (MetadataValueType.DatabaseType.getLongTypeValue().contains(extraAttribute.getValueType())) {
                DadConfigValueLong dadConfigValueLong =
                        Optional.ofNullable(attributeValueLongMap.get(extraAttribute.getId()))
                                .map(attributeValueLong -> {
                                    DadConfigValueLong item = new DadConfigValueLong();
                                    item.setId(attributeValueLong.getId());
                                    item.setConfigId(attributeValueLong.getConfigId());
                                    item.setAttributeId(attributeValueLong.getAttributeId());
                                    item.setValue(extraAttribute.getSerializeValue().map(Long.class::cast).orElse(0L));
                                    item.setIsNull(!extraAttribute.getSerializeValue().isPresent());
                                    return item;
                                }).orElseGet(() -> {
                                    DadConfigValueLong item = new DadConfigValueLong();
                                    item.setConfigId(dadConfig.getId());
                                    item.setAttributeId(extraAttribute.getId());
                                    item.setValue(extraAttribute.getSerializeValue().map(Long.class::cast).orElse(0L));
                                    item.setIsNull(!extraAttribute.getSerializeValue().isPresent());
                                    return item;
                                });
                dadConfigValueLongSaveOrUpdateList.add(dadConfigValueLong);
            }

            if (MetadataValueType.DatabaseType.getStringTypeValue().contains(extraAttribute.getValueType())) {
                DadConfigValueString dadConfigValueString =
                        Optional.ofNullable(attributeValueStringMap.get(extraAttribute.getId()))
                                .map(attributeValueString -> {
                                    DadConfigValueString item = new DadConfigValueString();
                                    item.setId(attributeValueString.getId());
                                    item.setConfigId(attributeValueString.getConfigId());
                                    item.setAttributeId(attributeValueString.getAttributeId());
                                    item.setValue(extraAttribute.getSerializeValue().map(String.class::cast).orElse(""));
                                    item.setIsNull(!extraAttribute.getSerializeValue().isPresent());
                                    return item;
                                }).orElseGet(() -> {
                                    DadConfigValueString item = new DadConfigValueString();
                                    item.setConfigId(dadConfig.getId());
                                    item.setAttributeId(extraAttribute.getId());
                                    item.setValue(extraAttribute.getSerializeValue().map(String.class::cast).orElse(""));
                                    item.setIsNull(!extraAttribute.getSerializeValue().isPresent());
                                    return item;
                                });
                dadConfigValueStringSaveOrUpdateList.add(dadConfigValueString);
            }
        }
        dadConfigValueIntService.saveOrUpdateBatchIgnoreEmpty(dadConfigValueIntSaveOrUpdateList);
        dadConfigValueLongService.saveOrUpdateBatchIgnoreEmpty(dadConfigValueLongSaveOrUpdateList);
        dadConfigValueStringService.saveOrUpdateBatchIgnoreEmpty(dadConfigValueStringSaveOrUpdateList);

        Set<Long> existIntAttributeIdList = dadConfigValueIntSaveOrUpdateList
                .stream()
                .map(DadConfigValueInt::getAttributeId)
                .collect(Collectors.toSet());
        Set<Long> removeAttributeValueIntSet = attributeValueIntMap.values()
                .stream()
                .filter(o -> !existIntAttributeIdList.contains(o.getAttributeId()))
                .map(DadConfigValueInt::getId)
                .collect(Collectors.toSet());
        dadConfigValueIntService.removeByIds(removeAttributeValueIntSet);

        Set<Long> existLongAttributeIdList = dadConfigValueLongSaveOrUpdateList
                .stream()
                .map(DadConfigValueLong::getAttributeId)
                .collect(Collectors.toSet());
        Set<Long> removeAttributeValueLongSet = attributeValueLongMap.values()
                .stream()
                .filter(o -> !existLongAttributeIdList.contains(o.getAttributeId()))
                .map(DadConfigValueLong::getId)
                .collect(Collectors.toSet());
        dadConfigValueLongService.removeByIds(removeAttributeValueLongSet);

        Set<Long> existStringAttributeIdList = dadConfigValueStringSaveOrUpdateList
                .stream()
                .map(DadConfigValueString::getAttributeId)
                .collect(Collectors.toSet());
        Set<Long> removeAttributeValueStringSet = attributeValueStringMap.values()
                .stream()
                .filter(o -> !existStringAttributeIdList.contains(o.getAttributeId()))
                .map(DadConfigValueString::getId)
                .collect(Collectors.toSet());
        dadConfigValueStringService.removeByIds(removeAttributeValueStringSet);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeConfigIncludingExtraAttribute(List<Long> configIdList) {
        if (CollectionUtils.isEmpty(configIdList)) {
            return;
        }
        dadConfigService.remove(dadConfigService.getQueryWrapper()
                .in(DadConfig::getId, configIdList));
        dadConfigValueIntService.remove(dadConfigValueIntService.getQueryWrapper()
                .in(DadConfigValueInt::getConfigId, configIdList));
        dadConfigValueLongService.remove(dadConfigValueLongService.getQueryWrapper()
                .in(DadConfigValueLong::getConfigId, configIdList));
        dadConfigValueStringService.remove(dadConfigValueStringService.getQueryWrapper()
                .in(DadConfigValueString::getConfigId, configIdList));
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateConfigExtraAttribute(
            DadConfigMetadata dadConfigMetadata, DadConfigAttribute dadConfigAttribute) {
        if (Objects.isNull(dadConfigAttribute.getId())) {
            dadConfigAttributeService.save(dadConfigAttribute);
            dadConfigMetadata.setAttributeId(dadConfigAttribute.getId());
            dadConfigMetadataService.save(dadConfigMetadata);
        } else {
            dadConfigAttributeService.updateById(dadConfigAttribute);
            dadConfigMetadataService.update(dadConfigMetadata, dadConfigMetadataService.getUpdateWrapper()
                    .eq(DadConfigMetadata::getAttributeId, dadConfigMetadata.getAttributeId()));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeConfigExtraAttribute(List<Long> attributeIdList) {
        if (CollectionUtils.isEmpty(attributeIdList)) {
            return;
        }
        dadConfigAttributeService.remove(dadConfigAttributeService.getQueryWrapper()
                .in(DadConfigAttribute::getId, attributeIdList));
        dadConfigMetadataService.remove(dadConfigMetadataService.getQueryWrapper()
                .in(DadConfigMetadata::getAttributeId, attributeIdList));
        dadConfigValueIntService.remove(dadConfigValueIntService.getQueryWrapper()
                .in(DadConfigValueInt::getAttributeId, attributeIdList));
        dadConfigValueLongService.remove(dadConfigValueLongService.getQueryWrapper()
                .in(DadConfigValueLong::getAttributeId, attributeIdList));
        dadConfigValueStringService.remove(dadConfigValueStringService.getQueryWrapper()
                .in(DadConfigValueString::getAttributeId, attributeIdList));
    }
}
