package com.share.co.kcl.dad.repository.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 动态广告-配置项(DadConfig)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-15 14:25:30
 */
@Mapper
public interface DadConfigDao extends BaseMapper<DadConfig> {
}

