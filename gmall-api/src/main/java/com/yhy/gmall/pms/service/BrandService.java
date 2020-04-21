package com.yhy.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yhy.gmall.pms.entity.Brand;
import com.yhy.gmall.vo.PageInfoVo;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 * @since 2020-04-15
 */
public interface BrandService extends IService<Brand> {
    PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize);
}
