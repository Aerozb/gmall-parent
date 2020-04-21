package com.yhy.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.pms.entity.*;
import com.yhy.gmall.pms.mapper.*;
import com.yhy.gmall.pms.service.ProductLadderService;
import com.yhy.gmall.pms.service.ProductService;
import com.yhy.gmall.vo.PageInfoVo;
import com.yhy.gmall.vo.product.PmsProductParam;
import com.yhy.gmall.vo.product.PmsProductQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;


/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 * @since 2020-04-15
 */
@Service
@Component
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductLadderMapper productLadderMapper;

    @Autowired
    ProductFullReductionMapper productFullReductionMapper;

    @Autowired
    MemberPriceMapper memberPriceMapper;

    @Autowired
    SkuStockMapper skuStockMapper;

    @Autowired
    ProductAttributeValueMapper productAttributeValueMapper;

    //让每个线程都有自己的商品id
    ThreadLocal<Long> productId=new ThreadLocal<>();

    @Override
    public PageInfoVo productPageInfo(PmsProductQueryParam para) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(para.getPublishStatus() != null, "publish_status", para.getPublishStatus()).
                eq(para.getVerifyStatus() != null, "verify_status", para.getVerifyStatus()).
                like(StringUtils.isNotBlank(para.getKeyword()), "keywords", para.getKeyword()).
                eq(StringUtils.isNotBlank(para.getProductSn()), "product_sn", para.getProductSn()).
                eq(para.getProductCategoryId() != null, "product_category_id", para.getProductCategoryId()).
                eq(para.getBrandId() != null, "brand_id", para.getBrandId());
        Page<Product> productPage = productMapper.selectPage(new Page<Product>(para.getPageNum(), para.getPageSize()),
                queryWrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(productPage.getTotal(), productPage.getPages(), productPage.getSize(), productPage.getRecords(), productPage.getCurrent());
        return pageInfoVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveProduct(PmsProductParam productParam) {
        ProductServiceImpl proxy = (ProductServiceImpl) AopContext.currentProxy();
        //1.保存商品表的基本信息，返回商品id，供下面其他信息保存关联使用
        proxy.saveBaseInfo(productParam);
        //2.保存sku库存信息
        proxy.saveSkuStock(productParam);
        //3.保存满减价格
        proxy.saveFullReduction(productParam);
        //4.保存会员价格
        proxy.saveMemberPrice(productParam);
        //5.保存商品参数及自定义规格属性
        proxy.saveProductAttributeValue(productParam);
    }

    //声明事务传播性为REQUIRES_NEW，调用这个方法的下面其他语句，就算出错，此方法也不会回滚
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void saveBaseInfo(PmsProductParam productParam) {
        productMapper.insert(productParam);
        productId.set(productParam.getId());
        //2.保存促销信息
        List<ProductLadder> productLadderList = productParam.getProductLadderList();
        productLadderList.forEach(productLadder -> {
            productLadder.setProductId(productId.get());
            productLadderMapper.insert(productLadder);
        });
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void saveSkuStock(PmsProductParam productParam) {
        List<SkuStock> skuStockList = productParam.getSkuStockList();
        for (int i = 1; i <= skuStockList.size(); i++) {
            SkuStock skuStock = skuStockList.get(i-1);
            skuStock.setProductId(productId.get());
            if (skuStock.getSkuCode() == null) {
                    skuStock.setSkuCode(productId+"_"+i);
            }
            skuStockMapper.insert(skuStock);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveFullReduction(PmsProductParam productParam) {
        int i=5/0;
        List<ProductFullReduction> productFullReductionList = productParam.getProductFullReductionList();
        productFullReductionList.forEach(productFullReduction->{
            productFullReduction.setProductId(productId.get());
            productFullReductionMapper.insert(productFullReduction);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveMemberPrice(PmsProductParam productParam) {
        List<MemberPrice> memberPriceList = productParam.getMemberPriceList();
        memberPriceList.forEach(memberPrice->{
            memberPrice.setProductId(productId.get());
            memberPriceMapper.insert(memberPrice);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveProductAttributeValue(PmsProductParam productParam) {
        List<ProductAttributeValue> productAttributeValueList = productParam.getProductAttributeValueList();
        productAttributeValueList.forEach(productAttributeValue->{
            productAttributeValue.setProductId(productId.get());
            productAttributeValueMapper.insert(productAttributeValue);
        });
    }
}
