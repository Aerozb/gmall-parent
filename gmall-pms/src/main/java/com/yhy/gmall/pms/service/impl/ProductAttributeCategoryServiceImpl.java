package com.yhy.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.pms.entity.ProductAttributeCategory;
import com.yhy.gmall.pms.mapper.ProductAttributeCategoryMapper;
import com.yhy.gmall.pms.service.ProductAttributeCategoryService;
import com.yhy.gmall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 * @since 2020-04-15
 */
@Service
@Component
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {

    @Autowired
    ProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public PageInfoVo productAttributeCategoryPageInfo(Integer pageNum, Integer pageSize) {
        Page<ProductAttributeCategory> productAttributeCategoryPage = productAttributeCategoryMapper.selectPage(new Page<>(pageNum, pageSize), null);
        return PageInfoVo.getVo(productAttributeCategoryPage);
    }
}
