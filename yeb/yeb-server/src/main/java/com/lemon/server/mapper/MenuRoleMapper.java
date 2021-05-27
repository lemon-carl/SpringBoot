package com.lemon.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.server.model.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * 角色菜单接口
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 更新角色菜单（批量或单个）
     *
     * @param rid
     * @param mIds
     * @return
     */
    int insertRecord(@Param("rid") Integer rid, @Param("mIds") Integer[] mIds);
}
