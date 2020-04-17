package com.yhy.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.pms.entity.Brand;
import com.yhy.gmall.pms.mapper.BrandMapper;
import com.yhy.gmall.pms.service.BrandService;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-04-15
 */
@Service
@Component
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}
