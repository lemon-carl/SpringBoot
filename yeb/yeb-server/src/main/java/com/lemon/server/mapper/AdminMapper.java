package com.lemon.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.server.model.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作员 Mapper 接口
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 查询所有操作员
     *
     * @param id
     * @param keyWorks
     * @return
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keyWorks") String keyWorks);
}
