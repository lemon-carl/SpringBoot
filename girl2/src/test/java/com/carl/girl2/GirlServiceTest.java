package com.carl.girl2;

import com.carl.girl2.entity.Girl;
import com.carl.girl2.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName : GirlServiceTest
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-09-03 22:16
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;

    @Test
    public void findOneTest(){
        Girl   girl = girlService.findOne(7);
        Assert.assertEquals("D",girl.getCupSize());
    }

}
