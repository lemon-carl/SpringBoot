package com.hr.girl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName : GirlRepository
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-05-02 23:21
 * @Description :
 */
public interface GirlRepository extends JpaRepository<Girl,Integer> {

    //通过年龄来查询
    public List<Girl> findByAge(Integer age);
}
