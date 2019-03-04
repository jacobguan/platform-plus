/*
 * 项目名称:platform-plus
 * 类名称:SysConfigController.java
 * 包名称:com.platform.modules.sys.controller
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2018/11/21 16:04    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.modules.sys.controller;

import com.platform.common.annotation.SysLog;
import com.platform.common.utils.PageUtils;
import com.platform.common.utils.RestResponse;
import com.platform.common.validator.ValidatorUtils;
import com.platform.modules.sys.entity.SysConfigEntity;
import com.platform.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author 李鹏军
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有系统配置列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public RestResponse list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysConfigService.queryPage(params);

        return RestResponse.success().put("page", page);
    }


    /**
     * 系统配置信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public RestResponse info(@PathVariable("id") String id) {
        SysConfigEntity config = sysConfigService.selectById(id);

        return RestResponse.success().put("config", config);
    }

    /**
     * 保存系统配置
     */
    @SysLog("保存系统配置")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public RestResponse save(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);

        sysConfigService.save(config);

        return RestResponse.success();
    }

    /**
     * 修改系统配置
     */
    @SysLog("修改系统配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public RestResponse update(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);

        sysConfigService.update(config);

        return RestResponse.success();
    }

    /**
     * 删除系统配置
     */
    @SysLog("删除系统配置")
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public RestResponse delete(@RequestBody String[] ids) {
        sysConfigService.deleteBatch(ids);

        return RestResponse.success();
    }

}