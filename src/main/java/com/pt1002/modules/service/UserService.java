package com.pt1002.modules.service;

import com.pt1002.modules.pojo.User;
import com.pt1002.modules.pojo.query.UserQuery;

public interface UserService {


    User login(User user);

    UserQuery findPage();

    void userUpdate(User user);

    void userDelete(String ids);

    void userAdd(User user);
}

