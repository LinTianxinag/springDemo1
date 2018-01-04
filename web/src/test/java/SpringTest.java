import com.MyDemo.bean.UserMG;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * desciper::介绍方法名
 * Created by LinTianxiang.
 * Date: 2018/1/3.
 * time:${time}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config.xml")
public class SpringTest {
    @Autowired
    MongoTemplate mongoTemplate;
//    @Before//这个地方是before
//    public static void setUpBeforeClass() throws Exception {
//        context = new ClassPathXmlApplicationContext("applicationContext.xml");
//    }
    /**
     * 看是否测试成功，因为这里需要一些配置上下文，如果可以说明测试通过
      */
    @Test
    public void testDemo1(){
        org.springframework.data.mongodb.core.query.Query query1=new org.springframework.data.mongodb.core.query.Query();
        query1.addCriteria(Criteria.where("sex").is(0));
//        query1.with(new Sort(new Sort.Order(Sort.Direction.DESC ,"regdate")));
        List<UserMG> list=mongoTemplate.find(query1.skip(1*2).limit(2),UserMG.class);
//        List<UserMG> list=mongoTemplate.find(new org.springframework.data.mongodb.core.query.Query(Criteria.where("id").is(394005)), UserMG.class);
        System.out.println("list123"+list.toString());
    }

}
