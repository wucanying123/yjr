package com.pt1002.modules.service;

import com.pt1002.modules.pojo.TemplatesWithBLOBs;
import com.pt1002.modules.pojo.query.TemQuery;

public interface TemplatesService {
    TemQuery findPage(Integer page, Integer rows);

    void temDelete(String ids);

    void temUpdate(TemplatesWithBLOBs templates);

    TemQuery findAll(String id, Integer page, Integer rows);
}
