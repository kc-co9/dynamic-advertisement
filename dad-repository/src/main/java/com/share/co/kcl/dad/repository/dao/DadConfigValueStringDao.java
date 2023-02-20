package com.share.co.kcl.dad.repository.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.MultiConfigAttributeValueSelectResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigValueString;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 动态广告-配置项VARCHAR值(DadConfigValueVarchar)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-15 14:25:34
 */
@Mapper
public interface DadConfigValueStringDao extends BaseMapper<DadConfigValueString> {

    List<ConfigAttributeValueSelectResult<String>> selectConfigAttributeValue(@Param("params") ConfigAttributeValueSelectParams params);

    List<MultiConfigAttributeValueSelectResult> selectMultiConfigAttributeValue(@Param("params") MultiConfigAttributeValueSelectParams params);

}

