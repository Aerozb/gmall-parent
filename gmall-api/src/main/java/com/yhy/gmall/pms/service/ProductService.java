package com.yhy.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yhy.gmall.pms.entity.Product;
import com.yhy.gmall.vo.PageInfoVo;
import com.yhy.gmall.vo.product.PmsProductParam;
import com.yhy.gmall.vo.product.PmsProductQueryParam;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 * @since 2020-04-15
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据复杂查询条件返回分页数据
     */
    PageInfoVo productPageInfo(PmsProductQueryParam pmsProductQueryParam);

    /**
     * 保存商品，包含商品的促销，属性N多信息
     */
    void saveProduct(PmsProductParam productParam);
}
