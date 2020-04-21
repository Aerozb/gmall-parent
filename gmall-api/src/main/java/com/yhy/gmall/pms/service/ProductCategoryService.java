package com.yhy.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yhy.gmall.pms.entity.ProductCategory;
import com.yhy.gmall.vo.product.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 * @since 2020-04-15
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    public List<PmsProductCategoryWithChildrenItem> listCatelogWithChilder(int i);
}
