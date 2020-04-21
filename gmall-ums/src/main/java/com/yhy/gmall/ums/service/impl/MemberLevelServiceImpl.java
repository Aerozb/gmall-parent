package com.yhy.gmall.ums.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.ums.entity.MemberLevel;
import com.yhy.gmall.ums.mapper.MemberLevelMapper;
import com.yhy.gmall.ums.service.MemberLevelService;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 * @since 2020-04-15
 */
@Service
@Component
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel> implements MemberLevelService {

}
