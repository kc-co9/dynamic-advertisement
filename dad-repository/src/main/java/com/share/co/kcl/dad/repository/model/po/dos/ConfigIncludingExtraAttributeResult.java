package com.share.co.kcl.dad.repository.model.po.dos;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigIncludingExtraAttributeResult extends DadConfig {

    private transient List<ConfigAttributeValueSelectResult<Integer>> intAttributeResult;
    private transient List<ConfigAttributeValueSelectResult<Long>> longAttributeResult;
    private transient List<ConfigAttributeValueSelectResult<String>> stringAttributeResult;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    public List<ConfigAttributeValueSelectResult<?>> getExtraAttributeList() {
        List<ConfigAttributeValueSelectResult<?>> extraAttributeList = new ArrayList<>();
        extraAttributeList.addAll(this.getIntAttributeResult());
        extraAttributeList.addAll(this.getLongAttributeResult());
        extraAttributeList.addAll(this.getStringAttributeResult());
        return extraAttributeList;
    }
}
