package com.fun.project.admin.monitor.log.controller;

import com.fun.common.pageHelper.CommonPage;
import com.fun.common.result.CommonResult;
import com.fun.framework.annotaion.Log;
import com.fun.framework.annotaion.NeedLoginToken;
import com.fun.project.admin.monitor.log.entity.OperLog;
import com.fun.project.admin.monitor.log.service.IOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by DJun on 2019/9/10 15:39
 * desc:
 */

@RestController
@NeedLoginToken
@RequestMapping("/admin/monitor/operLog")
public class OperlogController {

    @Autowired
    private IOperLogService operLogService;

    @Log("获取操作日志列表")
    @PostMapping("/list")
    public CommonResult<CommonPage<OperLog>> operList(OperLog operLog,
                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<OperLog> list = operLogService.selectOperLogList(operLog,pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @Log("获取单个操作日志")
    @GetMapping("/detail/{operId}")
    public CommonResult operDetail(@PathVariable("operId") Long operId) {
       return CommonResult.success(operLogService.selectOperLogById(operId));
    }

    @Log("批量删除操作日志")
    @PostMapping("/remove")
    public CommonResult remove(String ids){
        return CommonResult.success(operLogService.deleteOperLogByIds(ids));
    }

    @Log("清空操作日志")
    @PostMapping("/clean")
    public CommonResult clean() {
        operLogService.cleanOperLog();
        return CommonResult.success(null);
    }
}