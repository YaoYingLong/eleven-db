package com.eleven.icode.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleven.icode.shardingsphere.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select(value = "select u.user_id,u.username,d.uvalue ustatus " +
            "from user u left join t_dict d on u.ustatus = d.ustatus")
    List<User> queryUserStatus();
}
