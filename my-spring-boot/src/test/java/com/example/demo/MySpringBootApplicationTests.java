package com.example.demo;

import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootApplicationTests {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
    private AyUserService ayUserService;

	/**
	 * 第二章、MySql 集成 Spring Boot 测试
	 */
	@Test
	public void mySqlTest(){
		String sql =  "select id,name,password from ay_user";
		List<AyUser> userList = jdbcTemplate.query(sql, new RowMapper<AyUser>() {
			@Override
			public AyUser mapRow(ResultSet rs, int i) throws SQLException {
				AyUser user = new AyUser();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
    System.out.println("-------");
		assertNotNull(userList);

		System.out.println("查询成功");
		for (AyUser user : userList) {
			System.out.println("[id]:" + user.getId() + ";[name]:" + user.getName());
      		System.out.println(user);
		}
	}

    /**
     * 第三章、集成测试，增删改查，分页，，，
     */
      @Test
      public void testRepository() {
        // 查询所有数据
        List<AyUser> userList = ayUserService.findAll();
        System.out.println("findAll() :" + userList.size());

        // 通过name查询数据
        List<AyUser> userList2 = ayUserService.findByName("Carl");
        System.out.println("findByName() :" + userList2.size());
        Assert.isTrue(userList2.get(0).getName().equals("Carl"), "data error!");

        // 通过name模糊查询
          List<AyUser> userList3 = ayUserService.findByNameLike("%o%");
        System.out.println("findByNameLike() : " + userList3.size());
        Assert.isTrue(userList3.get(0).getName().equals("Tom"),"data error!");

        // 通过id列表查询
          List<String> ids = Lists.newArrayList();
          ids.add("1");
          ids.add("2");
          ids.add("3");
          List<AyUser> userList4 = ayUserService.findByIdIn(ids);
        System.out.println("findByIdIn() : " + userList4.size());

          // 分页查询
          PageRequest pageRequest = new PageRequest(0,5);
          Page<AyUser> page = ayUserService.findAll(pageRequest);
        System.out.println("page findAll() : " + page.getTotalPages() +"/" + page.getSize());

        // 新增数据
          AyUser ayUser = new AyUser();
          ayUser.setId("9");
          ayUser.setName("liming");
          ayUser.setPassword("xiaoming");
          ayUserService.save(ayUser);

        // 删除数据
        ayUserService.delete("8");

      }

    /**
     * 五、springboot事务支持
     */
    @Test
    public void testTransaction(){
          AyUser ayUser = new AyUser();
          ayUser.setId("10");
          ayUser.setName("阿敏");
        ayUser.setPassword("admin");
        ayUserService.save(ayUser);
    }

}
