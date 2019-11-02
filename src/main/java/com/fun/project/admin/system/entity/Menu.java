package com.fun.project.admin.system.entity;

import com.fun.framework.web.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DJun
 * @date 2019/9/14 9:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Menu extends BaseEntity {
    /** 菜单ID */
    private Long menuId;

    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 30, message = "菜单名称长度不能超过15个字")
    private String menuName;

    /** 父菜单名称 */
    private String parentName;
    private Long parentId;

    private Integer orderNum;

    @Size( max = 200, message = "请求地址不能超过200个字符")
    private String url;

    /** 打开方式：menuItem页签 menuBlank新窗口 */
    private String target;

    /** 类型:0目录,1菜单,2按钮 */
    @NotBlank(message = "菜单类型不能为空")
    private String menuType;

    /** 菜单状态:0显示,1隐藏 */
    private String visible;

    /** 权限字符串 */
    @Size(max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;

    private String icon;

    /** 子菜单 */
    private List<Menu> children = new ArrayList<>();
}
