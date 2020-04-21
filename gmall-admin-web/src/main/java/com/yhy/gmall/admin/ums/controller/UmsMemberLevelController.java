package com.yhy.gmall.admin.ums.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yhy.gmall.to.CommonResult;
import com.yhy.gmall.ums.entity.MemberLevel;
import com.yhy.gmall.ums.service.MemberLevelService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "MemberLevelController", description = "后台会员等级列表")
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Reference
    private MemberLevelService memberLevelService;

    @GetMapping("/list")
    public CommonResult memberLevelList(){
        List<MemberLevel> list = memberLevelService.list();
        return new CommonResult().success(list);
    }
}
