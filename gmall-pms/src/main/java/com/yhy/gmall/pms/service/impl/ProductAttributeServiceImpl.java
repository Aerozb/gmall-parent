package com.yhy.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.pms.entity.ProductAttribute;
import com.yhy.gmall.pms.entity.ProductAttributeCategory;
import com.yhy.gmall.pms.mapper.ProductAttributeMapper;
import com.yhy.gmall.pms.service.ProductAttributeService;
import com.yhy.gmall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 * @since 2020-04-15
 */
@Service
@Component
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    @Autowired
    ProductAttributeMapper productAttributeMapper;
    @Override
    public PageInfoVo getCategoryAttributes(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        QueryWrapper<ProductAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_attribute_category_id",cid).eq("type",type);
        Page<ProductAttribute> productAttributePage = productAttributeMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return PageInfoVo.getVo(productAttributePage);

    }
}
