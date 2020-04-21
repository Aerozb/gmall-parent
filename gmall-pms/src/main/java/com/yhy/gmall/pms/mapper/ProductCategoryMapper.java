package com.yhy.gmall.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhy.gmall.pms.entity.ProductCategory;
import com.yhy.gmall.vo.product.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 * @since 2020-04-15
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    List<PmsProductCategoryWithChildrenItem> listCatelogWithChilder(int i);
}
