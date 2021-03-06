package com.eleven.icode.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eleven.icode.shardingsphere.entity.Course;
import com.eleven.icode.shardingsphere.entity.Dict;
import com.eleven.icode.shardingsphere.entity.User;
import com.eleven.icode.shardingsphere.mapper.CourseMapper;
import com.eleven.icode.shardingsphere.mapper.DictMapper;
import com.eleven.icode.shardingsphere.mapper.UserMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCTest {
    @Resource
    CourseMapper courseMapper;
    @Resource
    DictMapper dictMapper;
    @Resource
    UserMapper userMapper;

    @Test
    public void addCourse() {
        for (int i = 0; i < 10; i++) {
            Course c = new Course();
//            c.setCid(Long.valueOf(i));
            c.setCname("shardingsphere");
            c.setUserId(Long.valueOf("" + (1000 + i)));
            c.setCstatus("1");
            courseMapper.insert(c);
        }
    }

    @Test
    public void queryCourse() {
        //select * from course
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cid");
        wrapper.eq("cid", 675302121926561792L);
//        wrapper.in()
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(course -> System.out.println(course));
    }

    @Test
    public void queryOrderRange() {
        //select * from course
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.between("cid", 675302121926561792L, 675302123751084032L);
//        wrapper.in()
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(course -> System.out.println(course));
    }


    @Test
    public void queryOrderIn() {
        //select * from course
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.in("cid", 675302121926561792L, 675302123751084032L);
//        wrapper.in()
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(course -> System.out.println(course));
    }

    @Test
    public void queryCourseComplex() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.between("cid", 553684818806706177L, 553684819184193537L);
        wrapper.eq("user_id", 1009L);
//        wrapper.in()
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(course -> System.out.println(course));
    }

    @Test
    public void queryCourseByHint() {
        HintManager hintManager = HintManager.getInstance();
        hintManager.addTableShardingValue("course", 2);
        List<Course> courses = courseMapper.selectList(null);
        courses.forEach(course -> System.out.println(course));
        hintManager.close();
    }

    @Test
    public void addDict() {
//        Dict d1 = new Dict();
//        d1.setUstatus("1");
//        d1.setUvalue("??????");
//        dictMapper.insert(d1);
//
//        Dict d2 = new Dict();
//        d2.setUstatus("0");
//        d2.setUvalue("?????????");
//        dictMapper.insert(d2);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("user No " + i);
            user.setUstatus("" + (i % 2));
            user.setUage(i * 10);
            userMapper.insert(user);
        }
    }

    @Test
    public void queryUserStatus() {
        List<User> users = userMapper.queryUserStatus();
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void addDictByMS() {
        Dict d1 = new Dict();
        d1.setUstatus("1");
        d1.setUvalue("??????");
        dictMapper.insert(d1);

        Dict d2 = new Dict();
        d2.setUstatus("0");
        d2.setUvalue("?????????");
        dictMapper.insert(d2);
    }

    @Test
    public void queryDictByMS() {
        List<Dict> dicts = dictMapper.selectList(null);
        dicts.forEach(dict -> System.out.println(dict));
    }

}
