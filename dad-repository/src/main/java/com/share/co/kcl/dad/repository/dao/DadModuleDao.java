package com.share.co.kcl.dad.repository.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.co.kcl.dad.repository.model.po.entities.DadModule;
import org.apache.ibatis.annotations.Mapper;

/**
 * 动态广告-模块项(DadModule)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-15 14:25:34
 */
@Mapper
public interface DadModuleDao extends BaseMapper<DadModule> {

}

