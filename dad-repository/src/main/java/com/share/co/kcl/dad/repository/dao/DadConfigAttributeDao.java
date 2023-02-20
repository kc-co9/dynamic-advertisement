package com.share.co.kcl.dad.repository.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeSelectResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 动态广告-配置项属性(DadConfigAttribute)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-15 14:25:34
 */
@Mapper
public interface DadConfigAttributeDao extends BaseMapper<DadConfigAttribute> {

    IPage<ConfigAttributeSelectResult> selectConfigAttribute(
            IPage<ConfigAttributeSelectResult> page, @Param("params") ConfigAttributeSelectParams params);

}

