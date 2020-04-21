package com.yhy.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.constant.SysCacheConstant;
import com.yhy.gmall.pms.entity.ProductCategory;
import com.yhy.gmall.pms.mapper.ProductCategoryMapper;
import com.yhy.gmall.pms.service.ProductCategoryService;
import com.yhy.gmall.vo.product.PmsProductCategoryWithChildrenItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 * @since 2020-04-15
 */
@Slf4j
@Service
@Component
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<PmsProductCategoryWithChildrenItem> listCatelogWithChilder(int i) {
        List<PmsProductCategoryWithChildrenItem> item;
        Object o = redisTemplate.opsForValue().get(SysCacheConstant.CATEGORY_MENU_CACHE_KEY);
        if (o != null) {
            log.info("商品分类数据命中缓存。。。。");
            item= (List<PmsProductCategoryWithChildrenItem>)o;
        }else {
            item  = productCategoryMapper.listCatelogWithChilder(i);
            redisTemplate.opsForValue().set(SysCacheConstant.CATEGORY_MENU_CACHE_KEY,item);
        }
        return item;
    }
}
