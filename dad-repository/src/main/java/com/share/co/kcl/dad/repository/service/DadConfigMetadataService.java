package com.share.co.kcl.dad.repository.service;

import com.share.co.kcl.dad.repository.dao.DadConfigMetadataDao;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigMetadata;
import org.springframework.stereotype.Service;

/**
 * 动态广告-配置项元数据(DadConfigMetadata)表服务接口
 *
 * @author makejava
 * @since 2023-02-15 14:25:34
 */
@Service
public class DadConfigMetadataService extends DadBaseService<DadConfigMetadataDao, DadConfigMetadata> {

    public DadConfigMetadata getByAttributeId(Long attributeId) {
        return this.getOne(this.getQueryWrapper().eq(DadConfigMetadata::getAttributeId, attributeId));
    }

}
