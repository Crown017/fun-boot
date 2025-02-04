package com.fun.project.admin.system.service;

import com.fun.project.admin.system.entity.Config;

import java.util.List;

/**
 * 参数配置
 *
 * @author DJun
 * @date 2019/10/30
 */
public interface IConfigService {

    /**
     * 查询Config列表
     *
     * @param config 查询对象
     * @return 查询列表
     */
    List<Config> selectConfigList(Config config);

    /**
     * 通过Id查询 Config
     *
     * @param configId 查询Id
     * @return 查询对象
     */
    Config selectConfigById(long configId);

    /**
     * 新增Config
     *
     * @param config 新增对象
     * @return 插入行数
     */
    int insertConfig(Config config);

    /**
     * 修改Config信息
     *
     * @param config 用户对象
     * @return 更新行数
     */
    int updateConfig(Config config);

    /**
     * 通过id删除Config
     *
     * @param configId 删除id
     * @return 删除行数
     */
    int deleteConfigById(long configId);

    /**
     * 通过id批量删除Config
     *
     * @param configIds 删除ids
     * @return 删除行数
     */
    int deleteConfigByIds(String configIds);

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数信息
     * @return 结果
     */
    String checkConfigKeyUnique(Config config);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    String selectConfigByKey(String configKey);
}
