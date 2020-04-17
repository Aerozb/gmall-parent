package com.yhy.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.pms.entity.ProductAttributeCategory;
import com.yhy.gmall.pms.mapper.ProductAttributeCategoryMapper;
import com.yhy.gmall.pms.service.ProductAttributeCategoryService;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-04-15
 */
@Service
@Component
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {

}
