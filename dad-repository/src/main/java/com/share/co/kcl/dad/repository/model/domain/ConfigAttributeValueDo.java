package com.share.co.kcl.dad.repository.model.domain;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.google.common.annotations.VisibleForTesting;
import com.share.co.kcl.dad.common.exception.ToastException;
import com.share.co.kcl.dad.common.utils.DateUtils;
import com.share.co.kcl.dad.repository.model.enums.MetadataFormatType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValidatedType;
import com.share.co.kcl.dad.repository.model.enums.MetadataValueType;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectResult;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import lombok.Data;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
public class ConfigAttributeValueDo {

    /**
     * 属性ID
     */
    private Long id;
    /**
     * 属性键
     */
    private String key;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 值类型
     */
    private MetadataValueType valueType;
    /**
     * 值限制
     */
    private Boolean valueLimit;
    /**
     * 属性值
     */
    private Object value;
    /**
     * 是否为空
     */
    private Boolean isNull;
    /**
     * 取值范围JSON
     *
     * @see MetadataValueRangeDo
     */
    private String valueRange;
    /**
     * 内容格式校验类型
     */
    private MetadataFormatType formatType;
    /**
     * 内容格式校验规则
     */
    private String formatRule;
    /**
     * 是否必须填写
     */
    private Boolean isRequired;
    /**
     * 业务校验类型
     */
    private MetadataValidatedType validatedType;
    /**
     * 业务校验规则
     */
    private String validatedRule;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public ConfigAttributeValueDo(ConfigAttributeValueSelectResult<?> configAttributeValueSelectResult) {
        this.setId(configAttributeValueSelectResult.getId());
        this.setKey(configAttributeValueSelectResult.getKey());
        this.setName(configAttributeValueSelectResult.getName());
        this.setDescription(configAttributeValueSelectResult.getDescription());
        this.setFormatType(configAttributeValueSelectResult.getFormatType());
        this.setFormatRule(configAttributeValueSelectResult.getFormatRule());
        this.setIsRequired(configAttributeValueSelectResult.getIsRequired());
        this.setValidatedType(configAttributeValueSelectResult.getValidatedType());
        this.setValidatedRule(configAttributeValueSelectResult.getValidatedRule());
        this.setValueType(configAttributeValueSelectResult.getValueType());
        this.setValue(configAttributeValueSelectResult.getValue());
        this.setIsNull(configAttributeValueSelectResult.getIsNull());
        this.setValueLimit(configAttributeValueSelectResult.getValueLimit());
        this.setValueRange(configAttributeValueSelectResult.getValueRange());
        this.setCreateTime(configAttributeValueSelectResult.getCreateTime());
    }

    public ConfigAttributeValueDo(MultiConfigAttributeValueSelectResult multiConfigAttributeValueSelectResult) {
        this.setId(multiConfigAttributeValueSelectResult.getId());
        this.setKey(multiConfigAttributeValueSelectResult.getKey());
        this.setName(multiConfigAttributeValueSelectResult.getName());
        this.setDescription(multiConfigAttributeValueSelectResult.getDescription());
        this.setFormatType(multiConfigAttributeValueSelectResult.getFormatType());
        this.setFormatRule(multiConfigAttributeValueSelectResult.getFormatRule());
        this.setIsRequired(multiConfigAttributeValueSelectResult.getIsRequired());
        this.setValidatedType(multiConfigAttributeValueSelectResult.getValidatedType());
        this.setValidatedRule(multiConfigAttributeValueSelectResult.getValidatedRule());
        this.setValueType(multiConfigAttributeValueSelectResult.getValueType());
        this.setValue(multiConfigAttributeValueSelectResult.getValue());
        this.setIsNull(multiConfigAttributeValueSelectResult.getIsNull());
        this.setValueLimit(multiConfigAttributeValueSelectResult.getValueLimit());
        this.setValueRange(multiConfigAttributeValueSelectResult.getValueRange());
        this.setCreateTime(multiConfigAttributeValueSelectResult.getCreateTime());
    }

    public ConfigAttributeValueDo(ConfigAttributeSelectResult configAttributeSelectResult, Object deserializeValue) {
        this.setId(configAttributeSelectResult.getId());
        this.setKey(configAttributeSelectResult.getKey());
        this.setName(configAttributeSelectResult.getName());
        this.setDescription(configAttributeSelectResult.getDescription());
        this.setFormatType(configAttributeSelectResult.getFormatType());
        this.setFormatRule(configAttributeSelectResult.getFormatRule());
        this.setIsRequired(configAttributeSelectResult.getIsRequired());
        this.setValidatedType(configAttributeSelectResult.getValidatedType());
        this.setValidatedRule(configAttributeSelectResult.getValidatedRule());
        this.setValueType(configAttributeSelectResult.getValueType());
        this.setValue(serialize(configAttributeSelectResult.getValueType(), deserializeValue));
        this.setIsNull(!getSerializeValue().isPresent());
        this.setValueLimit(configAttributeSelectResult.getValueLimit());
        this.setValueRange(configAttributeSelectResult.getValueRange());
        this.setCreateTime(configAttributeSelectResult.getCreateTime());
    }

    public Optional<Object> getSerializeValue() {
        if (Boolean.TRUE.equals(this.getIsNull())) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.getValue());
    }

    public Optional<Object> getDeserializeValue() {
        if (Boolean.TRUE.equals(this.getIsNull())) {
            return Optional.empty();
        }
        Object result = deserialize(this.getValueType(), this.getValue());
        return Optional.ofNullable(result);
    }

    public String getSerializeRangeValue() {
        return this.getValueRange();
    }

    public MetadataValueRangeDo<? extends MetadataValueRangeDo.IRange> getDeserializeRangeValue() {
        return deserializeValueRange(this.getValueType(), this.getValueRange());
    }

    public void validatedAttributeValue() {
        this.validatedAttributeRequired();
        this.validatedAttributeFormat();
        this.validatedAttributeRange();
        this.validatedAttributeBusiness();
    }

    @VisibleForTesting
    void validatedAttributeRequired() {
        if (Boolean.FALSE.equals(this.getIsRequired())) {
            return;
        }
        if (!this.getSerializeValue().isPresent()) {
            throw new ToastException(this.getName() + "不能为空");
        }
    }

    @VisibleForTesting
    void validatedAttributeFormat() {
        if (Objects.isNull(this.getFormatType()) || MetadataFormatType.NONE.equals(this.getFormatType())) {
            return;
        }
        if (StringUtils.isBlank(this.getFormatRule())) {
            return;
        }
        Optional<Object> deserializeValue = this.getDeserializeValue();
        if (!deserializeValue.isPresent()) {
            return;
        }
        MetadataFormatType metadataFormatType = Optional.ofNullable(this.getFormatType()).orElse(MetadataFormatType.NONE);
        switch (metadataFormatType) {
            case REGULAR_EXPRESSION:
                if (!Pattern.matches(this.getFormatRule(), deserializeValue.map(String::valueOf).orElse(""))) {
                    throw new ToastException(this.getName() + "数据格式不正确");
                }
                break;
            case NONE:
            default:
                break;
        }
    }

    @VisibleForTesting
    void validatedAttributeRange() {
        if (Boolean.FALSE.equals(this.getValueLimit())) {
            return;
        }
        Optional<Object> serializeValue = this.getSerializeValue();
        if (!serializeValue.isPresent()) {
            return;
        }
        switch (this.getValueType()) {
            case INT:
                MetadataValueRangeDo<? extends MetadataValueRangeDo.IRange> intMetadataValueRangeDo = this.getDeserializeRangeValue();
                for (MetadataValueRangeDo.IRange range : intMetadataValueRangeDo.getRangeList()) {
                    Optional<MetadataValueRangeDo.ValueRange> intValueRange = Optional.ofNullable(range).map(MetadataValueRangeDo.ValueRange.class::cast);
                    Integer intStart = intValueRange.map(MetadataValueRangeDo.ValueRange::getStart)
                            .map(start -> serialize(this.getValueType(), start))
                            .map(String::valueOf)
                            .map(Integer::parseInt)
                            .orElse(Integer.MIN_VALUE);
                    Integer intEnd = intValueRange.map(MetadataValueRangeDo.ValueRange::getEnd)
                            .map(end -> serialize(this.getValueType(), end))
                            .map(String::valueOf)
                            .map(Integer::parseInt)
                            .orElse(Integer.MAX_VALUE);
                    Integer intValue = serializeValue.map(String::valueOf).map(Integer::parseInt).orElse(0);
                    if ((intStart <= intValue) && (intEnd >= intValue)) {
                        return;
                    }
                }
                throw new ToastException(this.getName() + "数据选值超出可选范围");
            case LONG:
            case DATETIME:
            case DATE:
            case TIME:
                MetadataValueRangeDo<? extends MetadataValueRangeDo.IRange> longMetadataValueRangeDo = this.getDeserializeRangeValue();
                for (MetadataValueRangeDo.IRange range : longMetadataValueRangeDo.getRangeList()) {
                    Optional<MetadataValueRangeDo.ValueRange> longValueRange = Optional.ofNullable(range).map(MetadataValueRangeDo.ValueRange.class::cast);
                    Long longStart = longValueRange.map(MetadataValueRangeDo.ValueRange::getStart)
                            .map(start -> serialize(this.getValueType(), start))
                            .map(String::valueOf)
                            .map(Long::parseLong)
                            .orElse(Long.MIN_VALUE);
                    Long longEnd = longValueRange.map(MetadataValueRangeDo.ValueRange::getEnd)
                            .map(end -> serialize(this.getValueType(), end))
                            .map(String::valueOf)
                            .map(Long::parseLong)
                            .orElse(Long.MAX_VALUE);
                    Long longValue = serializeValue.map(String::valueOf).map(Long::parseLong).orElse(0L);
                    if ((longStart <= longValue) && (longEnd >= longValue)) {
                        return;
                    }
                }
                throw new ToastException(this.getName() + "数据选值超出可选范围");
            case ENUM:
                MetadataValueRangeDo<? extends MetadataValueRangeDo.IRange> enumMetadataValueRangeDo = this.getDeserializeRangeValue();
                Set<String> enumCodeList =
                        Optional.ofNullable(enumMetadataValueRangeDo.getRangeList()).orElse(Collections.emptyList())
                                .stream()
                                .map(MetadataValueRangeDo.OptionRange.class::cast)
                                .map(MetadataValueRangeDo.OptionRange::getCode)
                                .map(code -> serialize(this.getValueType(), code))
                                .map(String::valueOf)
                                .collect(Collectors.toSet());
                String enumCode = serializeValue.map(String::valueOf).orElse("");
                if (enumCodeList.contains(enumCode)) {
                    return;
                }
                throw new ToastException(this.getName() + "数据选值超出可选范围");
            case VARCHAR:
            case BOOLEAN:
                return;
        }
        throw new ToastException("数据范围异常");
    }

    @VisibleForTesting
    void validatedAttributeBusiness() {
        if (Objects.isNull(this.getValidatedType()) || MetadataValidatedType.NONE.equals(this.getValidatedType())) {
            return;
        }
        if (StringUtils.isBlank(this.getValidatedRule())) {
            return;
        }
        Optional<Object> deserializeValue = this.getDeserializeValue();
        if (!deserializeValue.isPresent()) {
            return;
        }
        switch (this.getValidatedType()) {
            case GROOVY:
                Binding binding = new Binding();
                binding.setProperty("valueType", Optional.ofNullable(this.getValueType()).map(String::valueOf).orElse(null));
                binding.setProperty("value", deserializeValue.orElse(null));
                GroovyShell groovyShell = new GroovyShell(binding);
                Object result = groovyShell.evaluate(this.getValidatedRule());
                if (!BooleanUtils.toBoolean(String.valueOf(result))) {
                    throw new ToastException(this.getName() + "数据业务校验失败");
                }
                break;
            case NONE:
            default:
                break;
        }
    }

    public static String serializeValueRange(
            MetadataValueType metadataValueType, MetadataValueRangeDo<? extends MetadataValueRangeDo.IRange> metadataValueRangeDo) {
        if (Objects.isNull(metadataValueType) || Objects.isNull(metadataValueRangeDo)) {
            return JSON.toJSONString(new MetadataValueRangeDo<>(Collections.emptyList()));
        }

        if (MetadataValueType.MetadataRangeType.getValueRangeType().contains(metadataValueType)) {
            List<MetadataValueRangeDo.ValueRange> valueRangeList =
                    Optional.ofNullable(metadataValueRangeDo.getRangeList()).orElse(Collections.emptyList())
                            .stream()
                            .map(MetadataValueRangeDo.ValueRange.class::cast)
                            .map(o -> new MetadataValueRangeDo.ValueRange(serialize(metadataValueType, o.getStart()), serialize(metadataValueType, o.getEnd())))
                            .collect(Collectors.toList());
            return JSON.toJSONString(new MetadataValueRangeDo<>(valueRangeList));
        }

        if (MetadataValueType.MetadataRangeType.getOptionRangeType().contains(metadataValueType)) {
            List<MetadataValueRangeDo.OptionRange> enumRangeList =
                    Optional.ofNullable(metadataValueRangeDo.getRangeList()).orElse(Collections.emptyList())
                            .stream()
                            .map(MetadataValueRangeDo.OptionRange.class::cast)
                            .map(o -> new MetadataValueRangeDo.OptionRange(serialize(metadataValueType, o.getCode()), o.getName()))
                            .collect(Collectors.toList());
            return JSON.toJSONString(new MetadataValueRangeDo<>(enumRangeList));
        }

        return JSON.toJSONString(new MetadataValueRangeDo<>(Collections.emptyList()));
    }

    public static MetadataValueRangeDo<? extends MetadataValueRangeDo.IRange> deserializeValueRange(MetadataValueType metadataValueType, String metadataValueRange) {
        if (Objects.isNull(metadataValueType) || StringUtils.isBlank(metadataValueRange)) {
            return new MetadataValueRangeDo<>(Collections.emptyList());
        }

        if (MetadataValueType.MetadataRangeType.getValueRangeType().contains(metadataValueType)) {
            MetadataValueRangeDo<MetadataValueRangeDo.ValueRange> metadataValueRangeDo =
                    JSON.parseObject(metadataValueRange, new TypeReference<MetadataValueRangeDo<MetadataValueRangeDo.ValueRange>>() {
                    });
            List<MetadataValueRangeDo.ValueRange> valueRangeList =
                    Optional.ofNullable(metadataValueRangeDo)
                            .map(MetadataValueRangeDo::getRangeList)
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(MetadataValueRangeDo.ValueRange.class::cast)
                            .map(o -> new MetadataValueRangeDo.ValueRange(deserialize(metadataValueType, o.getStart()), deserialize(metadataValueType, o.getEnd())))
                            .collect(Collectors.toList());
            return new MetadataValueRangeDo<>(valueRangeList);
        }

        if (MetadataValueType.MetadataRangeType.getOptionRangeType().contains(metadataValueType)) {
            MetadataValueRangeDo<MetadataValueRangeDo.OptionRange> enumMetadataValueRangeDo =
                    JSON.parseObject(metadataValueRange, new TypeReference<MetadataValueRangeDo<MetadataValueRangeDo.OptionRange>>() {
                    });
            List<MetadataValueRangeDo.OptionRange> enumRangeList =
                    Optional.ofNullable(enumMetadataValueRangeDo)
                            .map(MetadataValueRangeDo::getRangeList)
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(MetadataValueRangeDo.OptionRange.class::cast)
                            .map(o -> new MetadataValueRangeDo.OptionRange(deserialize(metadataValueType, o.getCode()), o.getName()))
                            .collect(Collectors.toList());
            return new MetadataValueRangeDo<>(enumRangeList);
        }

        return new MetadataValueRangeDo<>(Collections.emptyList());
    }

    public static Object serialize(MetadataValueType metadataValueType, Object metadataValue) {
        if (Objects.isNull(metadataValueType) || Objects.isNull(metadataValue)) {
            return null;
        }

        switch (metadataValueType) {
            case INT:
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .filter(StringUtils::isNotBlank)
                        .map(Integer::parseInt)
                        .orElse(null);
            case LONG:
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .filter(StringUtils::isNotBlank)
                        .map(Long::parseLong)
                        .orElse(null);
            case DATETIME:
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME);
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .filter(StringUtils::isNotBlank)
                        .map(datetime -> LocalDateTime.parse(datetime, dateTimeFormatter))
                        .map(DateUtils::valueOfSecond)
                        .orElse(null);
            case DATE:
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE);
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .filter(StringUtils::isNotBlank)
                        .map(date -> LocalDate.parse(date, dateFormatter))
                        .map(DateUtils::valueOfSecond)
                        .orElse(null);
            case TIME:
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME);
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .filter(StringUtils::isNotBlank)
                        .map(time -> LocalTime.parse(time, timeFormatter))
                        .map(DateUtils::valueOfSecond)
                        .orElse(null);
            case ENUM:
            case VARCHAR:
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .filter(StringUtils::isNotBlank)
                        .orElse(null);
            case BOOLEAN:
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .filter(StringUtils::isNotBlank)
                        .map(BooleanUtils::toBoolean)
                        .map(BooleanUtils::toInteger)
                        .orElse(null);
            default:
                return null;
        }
    }

    public static Object deserialize(MetadataValueType metadataValueType, Object metadataValue) {
        if (Objects.isNull(metadataValueType) || Objects.isNull(metadataValue)) {
            return null;
        }

        switch (metadataValueType) {
            case INT:
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .map(Integer::parseInt)
                        .orElse(null);
            case LONG:
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .map(Long::parseLong)
                        .orElse(null);
            case DATETIME:
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATETIME);
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .map(Long::parseLong)
                        .map(Instant::ofEpochSecond)
                        .map(second -> LocalDateTime.ofInstant(second, ZoneOffset.ofHours(8)))
                        .map(dateTimeFormatter::format)
                        .orElse(null);
            case DATE:
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_DATE);
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .map(Long::parseLong)
                        .map(Instant::ofEpochSecond)
                        .map(second -> LocalDateTime.ofInstant(second, ZoneOffset.ofHours(8)))
                        .map(dateFormatter::format)
                        .orElse(null);
            case TIME:
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DateUtils.FORMAT_COMMON_TIME);
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .map(Long::parseLong)
                        .map(LocalTime::ofSecondOfDay)
                        .map(timeFormatter::format)
                        .orElse(null);
            case ENUM:
            case VARCHAR:
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .orElse(null);
            case BOOLEAN:
                return Optional.of(metadataValue)
                        .map(String::valueOf)
                        .map(Integer::parseInt)
                        .map(BooleanUtils::toBoolean)
                        .orElse(null);
            default:
                return null;
        }
    }

}
