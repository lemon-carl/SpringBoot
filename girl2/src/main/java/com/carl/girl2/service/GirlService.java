package com.carl.girl2.service;

import com.carl.girl2.entity.Girl;
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
}
