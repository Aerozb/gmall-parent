package com.yhy.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yhy.gmall.pms.entity.ProductAttributeCategory;
import com.yhy.gmall.vo.PageInfoVo;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 * @since 2020-04-15
 */
public interface ProductAttributeCategoryService extends IService<ProductAttributeCategory> {

    PageInfoVo productAttributeCategoryPageInfo(Integer pageNum, Integer pageSize);
}
