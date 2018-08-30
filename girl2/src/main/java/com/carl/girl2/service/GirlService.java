package com.carl.girl2.service;

import com.carl.girl2.entity.Girl;
import com.carl.girl2.enums.ResultEnum;
import com.carl.girl2.exception.GirlException;
import com.carl.girl2.respository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName : GirlService Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2018-08-17 0:31 @Description :
 */
@Service
public class GirlService {

  @Autowired public GirlRepository girlRepository;

  @Transactional
  public void insertTwo() {
    Girl girlA = new Girl();
    girlA.setCupSize("A");
    girlA.setAge(18);
    girlRepository.save(girlA);

    Girl girlB = new Girl();
    girlB.setCupSize("Bbbbbb");
    girlB.setAge(19);
    girlRepository.save(girlB);
  }

  public void getAge(Integer id) throws Exception {
    Girl girl = girlRepository.findOne(id);
    Integer age = girl.getAge();
    if (age < 10) {
      // 返回你还在上小学 code =100
      throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
    } else if (age > 10 && age < 16) {
      // 返回可能在上初中 code= 101
      throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
    }

    // 如果大于16 ，加钱
  }
}
