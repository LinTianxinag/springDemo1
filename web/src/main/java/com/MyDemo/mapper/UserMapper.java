package com.MyDemo.mapper;

import com.MyDemo.bean.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);
    List<HashMap>  selectByNameMobile(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}