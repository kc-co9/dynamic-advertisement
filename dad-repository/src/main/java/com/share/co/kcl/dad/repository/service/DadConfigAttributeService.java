package com.share.co.kcl.dad.repository.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.share.co.kcl.dad.repository.dao.DadConfigAttributeDao;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeSelectParams;
import com.share.co.kcl.dad.repository.model.po.dos.ConfigAttributeSelectResult;
import com.share.co.kcl.dad.repository.model.po.entities.DadConfigAttribute;
import org.springframework.stereotype.Service;

/**
 * 动态广告-配置项属性(DadConfigAttribute)表服务接口
 *
 * @author makejava
 * @since 2023-02-15 14:25:34
 */
@Service
public class DadConfigAttributeService extends DadBaseService<DadConfigAttributeDao, DadConfigAttribute> {

    public IPage<ConfigAttributeSelectResult> getConfigAttributeList(
            IPage<ConfigAttributeSelectResult> page, ConfigAttributeSelectParams params) {
        return this.baseMapper.selectConfigAttribute(page, params);
    }

}
