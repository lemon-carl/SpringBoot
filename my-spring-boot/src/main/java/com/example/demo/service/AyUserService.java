package com.example.demo.service;

import com.example.demo.model.AyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 用户服务层接口
 * @Author: Carl
 * @Date: 2018/9/17 16:02
 * @Version 1.0
 *
 */
public interface AyUserService {

    Optional<AyUser> findById(String id);

    List<AyUser> findAll();

    AyUser save(AyUser ayUser);

    void delete(String id);

    //分页
    Page<AyUser> findAll(Pageable pageable);

    List<AyUser> findByName(String name);

    List<AyUser> findByNameLike(String name);

    List<AyUser> findByIdIn(Collection<String> ids);

}
