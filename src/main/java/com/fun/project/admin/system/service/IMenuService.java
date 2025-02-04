package com.fun.project.admin.system.service;

import com.fun.framework.web.entity.Ztree;
import com.fun.project.admin.system.entity.Menu;
import com.fun.project.admin.system.entity.role.Role;
import com.fun.project.admin.system.entity.user.AdminUser;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 菜单
 *
 * @author DJun
 */
public interface IMenuService {
    /**
     * 根据用户ID查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    List<Menu> selectMenusByUser(AdminUser user);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<Menu> selectMenuList(Menu menu);

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    List<Menu> selectMenuAll();

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    List<Ztree> roleMenuTreeData(Role role);

    /**
     * 查询所有菜单信息
     *
     * @return 菜单列表
     */
    List<Ztree> menuTreeData();

    /**
     * 查询系统所有权限
     *
     * @return 权限列表
     */
    Map<String, String> selectPermsAll();

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    Menu selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     *
     * @param parentId 菜单父ID
     * @return 结果
     */
    int selectCountMenuByParentId(Long parentId);

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(Menu menu);

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(Menu menu);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 存在则返回0，不存在则返回1
     */
    String checkMenuNameUnique(Menu menu);
}
