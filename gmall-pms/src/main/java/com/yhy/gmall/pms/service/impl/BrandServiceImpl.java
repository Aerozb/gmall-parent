package com.yhy.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.pms.entity.Brand;
import com.yhy.gmall.pms.mapper.BrandMapper;
import com.yhy.gmall.pms.service.BrandService;
import com.yhy.gmall.vo.PageInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 * @since 2020-04-15
 */
@Service
@Component
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    @Override
    public PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        QueryWrapper<Brand> queryWrapper = new QueryWrapper<Brand>();
        queryWrapper.like(StringUtils.isNotBlank(keyword), "name", keyword);
        IPage<Brand> brandIPage = brandMapper.selectPage(new Page<Brand>(pageNum.longValue(), pageSize.longValue()), queryWrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(brandIPage.getTotal(),
                brandIPage.getPages(), pageSize.longValue(), brandIPage.getRecords(),
                brandIPage.getCurrent());
        return pageInfoVo;
    }
}
