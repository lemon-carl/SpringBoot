package com.example.demo.repository;

import com.example.demo.model.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * 用户 Repository
 * @Author: Carl
 * @Date: 2018/9/17 15:37
 * @Version 1.0
 */
public interface AyUserRepository extends JpaRepository<AyUser,String>{


    /**
     * 通过名字相等查询，参数name
     * select u from ay_user u where u.name = ?1
     * @param name
     * @return
     */
       List<AyUser> findByName(String name);

    /**
     * 通过名字like查询，参数name
     * select u from ay_user u where u.name like ?1
     * @param name
     * @return
     */
       List<AyUser> findByNameLike(String name);

    /**
     * 通过主键id集合查询，参数id集合
     *  select u from ay_user u where id in (?,?,?)
     * @param ids
     * @return
     */
    List<AyUser> findByIdIn(Collection<String> ids);

}
