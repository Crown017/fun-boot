package com.fun.project.admin.system.controller;

import com.fun.common.constant.Constants;
import com.fun.common.pagehelper.CommonPage;
import com.fun.common.result.CommonResult;

import com.fun.common.utils.StringUtils;
import com.fun.framework.annotation.Log;
import com.fun.framework.shiro.helper.ShiroUtils;
import com.fun.framework.web.controller.BaseController;
import com.fun.project.admin.system.entity.user.AdminUser;
import com.fun.project.admin.system.service.IAdminUserService;
import com.fun.project.admin.system.service.IPostService;
import com.fun.project.admin.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fun.common.result.CommonResult.*;

/**
 * @author DJun
 */
@Controller
@RequestMapping("/admin/system/user")
@Api(description = "管理员用户接口")
public class AdminUserController extends BaseController {
    private final String prefix = "system/user";

    @Autowired
    private IAdminUserService adminUserService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String userIndex() {
        return view("system/user/user");
    }

    @ApiOperation("获取用户列表")
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public CommonResult getAdminUserList(AdminUser adminUser) {
        startPage();
        List<AdminUser> list = adminUserService.selectAdminUserList(adminUser);
        return success(CommonPage.restPage(list));
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("roles", roleService.selectRoleAll());
        mmap.put("posts", postService.selectPostAll());
        return view(prefix + "/add");
    }

    @ApiOperation("新增用户")
    @RequiresPermissions("system:user:add")
    @Log("新增用户")
    @PostMapping("/add")
    @ResponseBody
    public CommonResult addUser(@Validated AdminUser user) {
        if (Constants.NOT_UNIQUE.equals(adminUserService.checkLoginNameUnique(user.getLoginName()))) {
            return warn("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        } else if (Constants.NOT_UNIQUE.equals(adminUserService.checkPhoneUnique(user))) {
            return warn("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (Constants.NOT_UNIQUE.equals(adminUserService.checkEmailUnique(user))) {
            return warn("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        return success(adminUserService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", adminUserService.selectUserById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return view(prefix + "/edit");
    }

    @ApiOperation("修改用户")
    @RequiresPermissions("system:user:edit")
    @Log("修改用户")
    @PostMapping("/edit")
    @ResponseBody
    public CommonResult editSave(@Validated AdminUser user) {
        if (StringUtils.isNotNull(user.getUserId()) && AdminUser.isAdmin(user.getUserId())) {
            return failed("不允许修改超级管理员用户");
        } else if (Constants.NOT_UNIQUE.equals(adminUserService.checkPhoneUnique(user))) {
            return warn("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (Constants.NOT_UNIQUE.equals(adminUserService.checkEmailUnique(user))) {
            return warn("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        return success(adminUserService.updateUser(user));
    }


    @RequiresPermissions("system:user:resetPwd")
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", adminUserService.selectUserById(userId));
        return view(prefix + "/resetPwd");
    }

    @ApiOperation("重置密码")
    @RequiresPermissions("system:user:resetPwd")
    @Log("重置密码")
    @PostMapping("/resetPwd")
    @ResponseBody
    public CommonResult resetPwdSave(AdminUser user) {
        if (adminUserService.resetUserPwd(user) > 0) {
            if (ShiroUtils.getUserId().equals(user.getUserId())) {
                setSysUser(adminUserService.selectUserById(user.getUserId()));
            }
            return success(Constants.SUCCESS);
        }
        return failed();
    }

    @RequiresPermissions("system:user:remove")
    @Log("删除用户")
    @PostMapping("/remove")
    @ResponseBody
    public CommonResult remove(String ids) {
        try {
            return success(adminUserService.deleteUserByIds(ids));
        } catch (Exception e) {
            return failed(e.getMessage());
        }
    }

    @ApiOperation("异步验证用户名是否唯一")
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(AdminUser user) {
        return adminUserService.checkLoginNameUnique(user.getLoginName());
    }

    @ApiOperation("异步验证手机号码是否唯一")
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(AdminUser user) {
        System.out.println(adminUserService.checkPhoneUnique(user));
        return adminUserService.checkPhoneUnique(user);
    }

    @ApiOperation("异步验证邮箱是否唯一")
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(AdminUser user) {
        System.out.println(adminUserService.checkEmailUnique(user));
        return adminUserService.checkEmailUnique(user);
    }

    @ApiOperation("修改用户状态")
    @Log("修改用户状态")
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public CommonResult changeStatus(AdminUser user) {
        return success(adminUserService.changeStatus(user));
    }

}
