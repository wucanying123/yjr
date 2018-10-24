package com.pt1002.modules.service.impl;



import com.pt1002.modules.mapper.UserMapper;
import com.pt1002.modules.pojo.User;
import com.pt1002.modules.pojo.UserExample;
import com.pt1002.modules.pojo.query.UserQuery;
import com.pt1002.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAccountEqualTo(user.getAccount());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if (users!=null && users.size()>0){
            return user;
        }
     return null;
    }

    @Override
    public UserQuery findPage() {
        int i = userMapper.countByExample(null);
        List<User> users = userMapper.selectByExample(null);
        UserQuery query = new UserQuery();
        query.setTotal(i);
        query.setRows(users);
        return query;
    }

    @Override
    public void userUpdate(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void userDelete(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Integer value = Integer.valueOf(s);
            userMapper.deleteByPrimaryKey(value);
        }
    }

    @Override
    public void userAdd(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(user.getAccount());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(example);
        if (users!=null && users.size() >0){
            return;
        }
        userMapper.insert(user);
    }
}
