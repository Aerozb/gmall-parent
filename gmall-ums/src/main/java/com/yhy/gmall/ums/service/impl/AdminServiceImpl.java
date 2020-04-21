package com.yhy.gmall.ums.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhy.gmall.ums.entity.Admin;
import com.yhy.gmall.ums.mapper.AdminMapper;
import com.yhy.gmall.ums.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 * @since 2020-04-15
 */
@Service
@Component
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) {
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).eq("password",s);
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    /**
     * 获取用户详情
     */
    @Override
    public Admin getUserInfo(String userName) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",userName));
    }
}
