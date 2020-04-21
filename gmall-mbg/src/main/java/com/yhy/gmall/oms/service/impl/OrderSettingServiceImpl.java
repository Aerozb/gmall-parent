package com.yhy.gmall.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.oms.entity.OrderSetting;
import com.yhy.gmall.oms.mapper.OrderSettingMapper;
import com.yhy.gmall.oms.service.OrderSettingService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单设置表 服务实现类
 * </p>
 * @since 2020-04-15
 */
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements OrderSettingService {

}
