package com.pt1002.modules.service;

import com.pt1002.modules.pojo.Persons;
import com.pt1002.modules.pojo.query.PersonsQuery;

public interface PersonsService {
    PersonsQuery findPage(Integer page, Integer rows);

    void personsDelete(String ids);

    int personsUpdate(Persons persons);
}
