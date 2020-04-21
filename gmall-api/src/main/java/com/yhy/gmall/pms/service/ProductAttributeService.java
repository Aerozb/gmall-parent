package com.yhy.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yhy.gmall.pms.entity.ProductAttribute;
import com.yhy.gmall.vo.PageInfoVo;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 * @since 2020-04-15
 */
public interface ProductAttributeService extends IService<ProductAttribute> {

    PageInfoVo getCategoryAttributes(Long cid, Integer type, Integer pageSize, Integer pageNum);
}
